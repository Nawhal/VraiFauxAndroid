package iut.info63.vraifauxandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import iut.info63.vraifauxandroid.R;
import iut.info63.vraifauxandroid.metier.GameManager;
import iut.info63.vraifauxandroid.metier.Question;
import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;
import iut.info63.vraifauxandroid.metier.database.DatabaseQuestionAccessor;
import iut.info63.vraifauxandroid.metier.database.FakeQuestionAccessor;

public class GameActivity extends AppCompatActivity {

    private static final String TAG = GameActivity.class.getSimpleName();

    ImageView mIvHeart1, mIvHeart2, mIvHeart3;
    Button mButtonTrue, mButtonFalse, mButtonNextQuestion, mButtonScore;
    private TextView mTvQuestion, mTvResult;

    GameManager mGameManager = new GameManager();
    Question question;
    DataBaseHelper dbh;
    int compteurBadAnswer = 0, compteurGoodAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mButtonTrue = (Button)findViewById(R.id.b_true);
        mButtonFalse = (Button)findViewById(R.id.b_false);
        mButtonNextQuestion = (Button)findViewById(R.id.b_next);
        mButtonScore = (Button)findViewById(R.id.b_score);
        mTvResult = (TextView)findViewById(R.id.tv_result);
        mIvHeart1 = (ImageView)findViewById(R.id.iv_heart1);
        mIvHeart2 = (ImageView)findViewById(R.id.iv_heart2);
        mIvHeart3 = (ImageView)findViewById(R.id.iv_heart3);

        mTvQuestion = (TextView)findViewById(R.id.tv_question);

        try {
            dbh = new DataBaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayQuestion();

        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonTrue.setVisibility(View.INVISIBLE);
                mButtonFalse.setVisibility(View.INVISIBLE);

                verifyAnswer(true);

                if(compteurBadAnswer < 3) {
                    mButtonNextQuestion.setVisibility(View.VISIBLE);
                }
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonTrue.setVisibility(View.INVISIBLE);
                mButtonFalse.setVisibility(View.INVISIBLE);

                verifyAnswer(false);

                if(compteurBadAnswer < 3) {
                    mButtonNextQuestion.setVisibility(View.VISIBLE);
                }
            }
        });

        mButtonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonNextQuestion.setVisibility(View.INVISIBLE);
                mButtonTrue.setVisibility(View.VISIBLE);
                mButtonFalse.setVisibility(View.VISIBLE);

                mTvResult.setText("");

                displayQuestion();
            }
        });

        mButtonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultatActivity.class);
                intent.putExtra("goodanswer", compteurGoodAnswer);
                startActivity(intent);
                finish();
            }
        });
    }

    private void verifyAnswer(boolean answer) {
        if(answer == question.getAnswer()) {
            mTvResult.setText("Bonne réponse");
            compteurGoodAnswer++;
        } else {
            mTvResult.setText("Mauvaise réponse");
            compteurBadAnswer++;

            switch (compteurBadAnswer) {
                case 1:
                    mIvHeart3.setImageResource(R.drawable.emptyheart);
                    break;
                case 2:
                    mIvHeart2.setImageResource(R.drawable.emptyheart);
                    break;
                case 3:
                    mIvHeart1.setImageResource(R.drawable.emptyheart);
                    mButtonScore.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void displayQuestion() {
        if(compteurBadAnswer < 3) {
            question = mGameManager.randomQuestion(dbh);
            Log.d(TAG, question.getQuestion());
            Log.d(TAG, String.valueOf(question.getAnswer()));
            mTvQuestion.setText(question.getQuestion());
            compteurGoodAnswer++;
        }
    }

    /*private void test() throws IOException
    {
        DataBaseHelper dbh = new DataBaseHelper(this);

        DatabaseQuestionAccessor dqg = new DatabaseQuestionAccessor(dbh);

        dqg.put();

        dbh.close();
    }*/
}
