package iut.info63.vraifauxandroid.metier.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import iut.info63.vraifauxandroid.metier.Question;

/**
 * Created by Nawhal on 14/03/2016.
 */
public class DatabaseQuestionAccessor implements IQuestionGetter {

    private DataBaseHelper dataBaseHelper;

    public DatabaseQuestionAccessor(DataBaseHelper dataBaseHelper)
    {
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public Question getByIndex(int index) {
        dataBaseHelper.openReadable();

        String[] projection = {
                FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_NAME,
                FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME,
                FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME
        };

        Cursor c = dataBaseHelper.getDatabase().query(
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

    @Override
    public List<Question> getAll() {
        dataBaseHelper.openReadable();

        String[] projection = {
                FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME,
                FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME
        };

        Cursor c = dataBaseHelper.getDatabase().query(
                FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,
                null,
                null,
                null,
                null                                 // The sort order
        );

        c.moveToFirst();

        List<Question> questionList = new ArrayList<>();

        while (!c.isAfterLast())
        {
            questionList.add(new Question(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME)),
                    Boolean.parseBoolean(c.getString(c.getColumnIndexOrThrow(FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME)))));
            c.moveToNext();
        }

        return questionList;
    }

    @Override
    public void put(Question question) {
        dataBaseHelper.openWritable();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME, question.getQuestion());
        values.put(FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME, question.getAnswer());

        dataBaseHelper.getDatabase().insert(
                FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,
                null,
                values);
    }

    @Override
    public void putAll(List<Question> questionList) {
        dataBaseHelper.openWritable();
        ContentValues values;

        for (Question question : questionList)
        {
            values = new ContentValues();
            values.put(FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME, question.getQuestion());
            values.put(FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME, question.getAnswer());

            dataBaseHelper.getDatabase().insert(
                    FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,
                    null,
                    values);
        }
    }

    public void testDeleteAll()
    {
        dataBaseHelper.openWritable();
        dataBaseHelper.getWritableDatabase().delete(FeedReaderContract.QuestionsFeedEntry.TABLE_NAME, null, null);
    }

    public int count() {
        dataBaseHelper.openReadable();

        String[] projection = { "COUNT(*)" };

        Cursor c = dataBaseHelper.getDatabase().query(
                FeedReaderContract.QuestionsFeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,
                null,
                null,
                null,
                null                                 // The sort order
        );

        c.moveToFirst();
        return c.getInt(0);
    }
}
