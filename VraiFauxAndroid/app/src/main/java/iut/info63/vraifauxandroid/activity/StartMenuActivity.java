package iut.info63.vraifauxandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iut.info63.vraifauxandroid.R;

public class StartMenuActivity extends AppCompatActivity {

    Button mButtonStart, mButtonMultiplayer, mButtonCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenu);

        mButtonStart = (Button)findViewById(R.id.b_play);
        mButtonMultiplayer = (Button)findViewById(R.id.b_multiplayer);
        mButtonCredits = (Button)findViewById(R.id.b_credits);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        mButtonMultiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(intent);
            }
        });

        mButtonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreditsActivity.class);
                startActivity(intent);
            }
        });
    }
}
