package game.gallows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedImageDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnGame, btnCampaign, btnShop;
    private ImageButton btnProfile;
    private MediaPlayer sheet1;
    private TextView balance;
    private boolean soundMode = true;
    private ImageView fallenSnow;
    private AnimatedImageDrawable animatedGifFallenSnow;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fallenSnow = findViewById(R.id.fallenSnow);
        animatedGifFallenSnow = (AnimatedImageDrawable) fallenSnow.getDrawable();

        sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        int money = sharedPreferences.getInt("userMoney", 0);
        balance = findViewById(R.id.userBalanceMain);
        balance.setText(String.valueOf(money));

        int currentLevel = sharedPreferences.getInt("userLevel", 1);
        btnCampaign = findViewById(R.id.btnCampaign);
        btnCampaign.setText("Уровень " + String.valueOf(currentLevel));

        btnGame = findViewById(R.id.btnGame);
        btnProfile = findViewById(R.id.btnProfile);
        btnShop = findViewById(R.id.btnShop);

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
                    intent.putExtra("sourceActivity", "MainActivity");
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


                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("array", "");
                    intent.putExtra("category", "Уровень " + String.valueOf(currentLevel));
                    startActivity(intent);
                }
            });
        }

//        if (btnNews != null) {
//            btnNews.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (sheet1.isPlaying()) {
//                        sheet1.pause();
//                        sheet1.seekTo(0);
//                    }
//                    if (soundMode) {
//                        sheet1.start();
//                    }
//
//                    Intent intent = new Intent(MainActivity.this,  NewsActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//        }

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
    protected void onRestart() {
        super.onRestart();
        int money = sharedPreferences.getInt("userMoney", 0);
        int currentLevel = sharedPreferences.getInt("userLevel", 1);
        balance = findViewById(R.id.userBalanceMain);
        btnCampaign = findViewById(R.id.btnCampaign);
        balance.setText(String.valueOf(money));
        btnCampaign.setText("Уровень " + String.valueOf(currentLevel));
    }

    @Override
    protected void onResume() {
        super.onResume();
        animatedGifFallenSnow.start();
        int money = sharedPreferences.getInt("userMoney", 0);
        int currentLevel = sharedPreferences.getInt("userLevel", 1);
        balance = findViewById(R.id.userBalanceMain);
        btnCampaign = findViewById(R.id.btnCampaign);
        balance.setText(String.valueOf(money));
        btnCampaign.setText("Уровень " + String.valueOf(currentLevel));
    }

    @Override
    protected void onPause() {
        super.onPause();
        animatedGifFallenSnow.stop();
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