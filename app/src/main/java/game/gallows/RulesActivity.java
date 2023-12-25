package game.gallows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RulesActivity extends AppCompatActivity {
    ImageButton btnBack;
    MediaPlayer sheet1;

    boolean soundMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        btnBack = findViewById(R.id.btnBackRules);
        sheet1 = MediaPlayer.create(this, R.raw.sheet1);

        SharedPreferences sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        if (btnBack != null) {
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    finish();
                }
            });
        }

    }
}