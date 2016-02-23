package iut.info63.vraifauxandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    }
}
