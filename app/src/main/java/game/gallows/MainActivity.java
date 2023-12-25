package game.gallows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton btnGame, btnRules, btnSettings, btnCampaign, btnNews, btnProfile;
    MediaPlayer sheet1;

    boolean soundMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        btnGame = findViewById(R.id.btnGame);
        btnRules = findViewById(R.id.btnRules);
        btnCampaign = findViewById(R.id.btnCampaign);
        btnNews = findViewById(R.id.btnNews);
        btnSettings = findViewById(R.id.btnSettings);
        btnProfile = findViewById(R.id.btnProfile);

        sheet1 = MediaPlayer.create(this, R.raw.sheet1);

        if (btnGame != null) {
            btnGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                    startActivity(intent);

                }
            });
        }

        if (btnRules != null) {
            btnRules.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                    startActivity(intent);

                }
            });
        }

        if (btnSettings != null) {
            btnSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);

                }
            });
        }

        if (btnCampaign != null) {
            btnCampaign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this, CampaignActivity.class);
                    startActivity(intent);
                }
            });
        }

        if (btnNews != null) {
            btnNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this,  NewsActivity.class);
                    startActivity(intent);

                }
            });
        }

        if (btnProfile != null) {
            btnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sheet1 != null) {
            sheet1.release();
            sheet1 = null;
        }
    }

    public void telegramOnClick(View view) {
        // Открываем ссылку на ваш телеграм канал
        String telegramChannelUrl = "https://t.me/fed_apps";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(telegramChannelUrl));
        startActivity(intent);
    }

}