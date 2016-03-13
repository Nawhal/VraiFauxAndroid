package iut.info63.vraifauxandroid.metier.database;

import android.provider.BaseColumns;

/**
 * Created by Nawhal on 04/03/2016.
 */
public final class FeedReaderContract {
    public FeedReaderContract() {}

    public static abstract class QuestionsFeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Questions";
        public static final String FIRST_COLUMN_NAME = "id";
        public static final String FIRST_COLUMN_TYPE = " INTEGER";
        public static final String SECOND_COLUMN_NAME = "Question";
        public static final String SECOND_COLUMN_TYPE = " VARCHAR2(300)";
        public static final String THIRD_COLUMN_NAME = "Answer";
        public static final String THIRD_COLUMN_TYPE = " BOOLEAN";
    }

    public static abstract class HighScoresFeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "HighScores";
        public static final String FIRST_COLUMN_NAME = "id";
        public static final String FIRST_COLUMN_TYPE = " NUMBER(4)";
        public static final String SECOND_COLUMN_NAME = "Name";
        public static final String SECOND_COLUMN_TYPE = " VARCHAR2(100)";
        public static final String THIRD_COLUMN_NAME = "Score";
        public static final String THIRD_COLUMN_TYPE = " NUMBER(4,2)";
    }
}
