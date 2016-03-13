package iut.info63.vraifauxandroid.metier.database;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Nawhal on 29/02/2016.*
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    private static String DB_NAME = "VraiFauxDatabase.db";//the extension may be .sqlite or .db
    private SQLiteDatabase db;
        public SQLiteDatabase getDatabase() { return db; }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.QuestionsFeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_NAME + FeedReaderContract.QuestionsFeedEntry.FIRST_COLUMN_TYPE + " CONSTRAINT pk_index PRIMARY KEY AUTOINCREMENT, " +
                    FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_NAME + FeedReaderContract.QuestionsFeedEntry.SECOND_COLUMN_TYPE + " CONSTRAINT nn_question NOT NULL, " +
                    FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_NAME + FeedReaderContract.QuestionsFeedEntry.THIRD_COLUMN_TYPE + " CONSTRAINT nn_reponse NOT NULL" +
                    ");" +
                    "CREATE TABLE " + FeedReaderContract.QuestionsFeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.HighScoresFeedEntry.FIRST_COLUMN_NAME + FeedReaderContract.HighScoresFeedEntry.FIRST_COLUMN_TYPE + " CONSTRAINT pk_index PRIMARY KEY AUTOINCREMENT," +
                    FeedReaderContract.HighScoresFeedEntry.SECOND_COLUMN_NAME + FeedReaderContract.HighScoresFeedEntry.SECOND_COLUMN_TYPE + " CONSTRAINT nn_name NOT NULL," +
                    FeedReaderContract.HighScoresFeedEntry.THIRD_COLUMN_NAME + FeedReaderContract.HighScoresFeedEntry.THIRD_COLUMN_TYPE + " CONSTRAINT nn_score NOT NULL" +
                    ")" ;

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.QuestionsFeedEntry.TABLE_NAME +
                    "DROP TABLE " + FeedReaderContract.HighScoresFeedEntry.TABLE_NAME;

    public DataBaseHelper(Context context) throws IOException
    {
        super(context, DB_NAME, null, 1);
    }

    public void openWritable() throws SQLiteException
    {
        db = getWritableDatabase();
    }

    public void openReadable() throws SQLiteException
    {
        db = getReadableDatabase();
    }

    @Override
    public synchronized void close(){ // Pour que la BDD ne se ferme qu'une fois, on utilise synchronized
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // Mettre les id des ressources des questions au lieu des strings directement.
        db.execSQL(SQL_CREATE_ENTRIES);

        //createEntries(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void putDefaultValues()
    {

    }
}