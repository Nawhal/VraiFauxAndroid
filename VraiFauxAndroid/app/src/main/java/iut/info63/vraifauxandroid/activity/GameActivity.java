package iut.info63.vraifauxandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import iut.info63.vraifauxandroid.R;
import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;
import iut.info63.vraifauxandroid.metier.database.QuestionGateway;

public class GameActivity extends AppCompatActivity {

    Button mButtonTrue, mButtonFalse, mButtonNextQuestion, mButtonScore;
    TextView mTvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int test = 0;

        mButtonTrue = (Button)findViewById(R.id.b_true);
        mButtonFalse = (Button)findViewById(R.id.b_false);
        mButtonNextQuestion = (Button)findViewById(R.id.b_next);
        mButtonScore = (Button)findViewById(R.id.b_score);

        mTvQuestion = (TextView)findViewById(R.id.tv_question);

        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonTrue.setVisibility(View.INVISIBLE);
                mButtonFalse.setVisibility(View.INVISIBLE);
                mButtonNextQuestion.setVisibility(View.VISIBLE);
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonTrue.setVisibility(View.INVISIBLE);
                mButtonFalse.setVisibility(View.INVISIBLE);
                mButtonNextQuestion.setVisibility(View.VISIBLE);
            }
        });

        mButtonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonNextQuestion.setVisibility(View.INVISIBLE);
                mButtonTrue.setVisibility(View.VISIBLE);
                mButtonFalse.setVisibility(View.VISIBLE);
            }
        });

        try {
            test();
        }
        catch (Exception e)
        {
            Log.i("COUCOU", "Exception");
            e.printStackTrace();
        }
    }

    private void test() throws IOException
    {
        DataBaseHelper dbh = new DataBaseHelper(this);

        QuestionGateway qg = new QuestionGateway(dbh);
        qg.testDeleteAll();
        qg.putQuestion(0, "Oui.", true);
        qg.putQuestion(1, "Non.", false);
        qg.putQuestion(2, "Non2.", false);
        qg.putQuestion(3, "Oui2.", true);
        qg.putQuestion(4, "Oui3.", true);
        qg.putQuestion(5, "Oui4.", true);
        qg.putQuestion(6, "Non3.", false);
        qg.putQuestion(7, "Non4.", false);
        qg.putQuestion(8, "Non5.", false);
        qg.putQuestion(9, "Oui5.", true);

        Log.i("COUCOU", "Question n°1 " + qg.getQuestionByIndex(0));
        Log.i("COUCOU", "Question n°2 " + qg.getQuestionByIndex(1));
        Log.i("COUCOU", "Question n°3 " + qg.getQuestionByIndex(2));
        Log.i("COUCOU", "Question n°4 " + qg.getQuestionByIndex(3));
        Log.i("COUCOU", "Question n°5 " + qg.getQuestionByIndex(4));
        Log.i("COUCOU", "Question n°6 " + qg.getQuestionByIndex(5));
        Log.i("COUCOU", "Question n°7 " + qg.getQuestionByIndex(6));
        Log.i("COUCOU", "Question n°8 " + qg.getQuestionByIndex(7));
        Log.i("COUCOU", "Question n°9 " + qg.getQuestionByIndex(8));
        Log.i("COUCOU", "Question n°10 " + qg.getQuestionByIndex(9));

        dbh.close();
    }
}
