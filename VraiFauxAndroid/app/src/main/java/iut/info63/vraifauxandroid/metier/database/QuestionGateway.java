package iut.info63.vraifauxandroid.metier.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import iut.info63.vraifauxandroid.metier.Question;

/**
 * Created by Nawhal on 07/03/2016.
 */
public class QuestionGateway {

    private DataBaseHelper dbh;

    public QuestionGateway(DataBaseHelper dbh)
    {
        this.dbh = dbh;
    }

    public Question getQuestionByIndex(int index)
    {
        dbh.openReadable();

        String[] projection = {
                FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_NAME,
                FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME,
                FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME
        };

        Cursor c = dbh.getDatabase().query(
                FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_NAME + "=" + index,
                null,
                null,
                null,
                null                                 // The sort order
        );

        c.moveToFirst();
        return new Question(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME)),
                Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME))));
    }

    public void putQuestion(int id, String question, boolean reponse)
    {
        dbh.openWritable();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_NAME, id);
        values.put(FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME, question);
        values.put(FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME, reponse);

        dbh.getDatabase().insert(
                FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,
                null,
                values);
    }

    public void testDeleteAll()
    {
        dbh.openWritable();

        dbh.getWritableDatabase().delete(FeedReaderContract.QuestionsFeedEntry.TABLE_NAME, null, null);
    }
}
