package game.gallows;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    ImageButton btnBack;
    MediaPlayer sheet1;

    boolean soundMode = true;
    SharedPreferences sharedPreferences;

    Button btnReset;
    TextView totalAttemptsTextView, correctAttemptsTextView, perfectAttemptsTextView;

    EditText etUsername;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBackProfile);
        btnReset = findViewById(R.id.btnReset);
        totalAttemptsTextView = findViewById(R.id.totalAttemptsTextView);
        correctAttemptsTextView = findViewById(R.id.correctAttemptsTextView);
        perfectAttemptsTextView = findViewById(R.id.perfectAttemptsTextView);
        etUsername = findViewById(R.id.etUsername);
        btnSave = findViewById(R.id.btnSave);

        sheet1 = MediaPlayer.create(this, R.raw.sheet1);

        sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        // Получение данных статистики из SharedPreferences
        int totalAttempts = sharedPreferences.getInt("totalAttempts", 0);
        int correctAttempts = sharedPreferences.getInt("correctAttempts", 0);
        int perfectAttempts = sharedPreferences.getInt("perfectAttempts", 0);

        // Настройка TextView элементов статистики
        totalAttemptsTextView.setText("Всего раз сыграно: " + totalAttempts);
        correctAttemptsTextView.setText("Всего слов отгадано: " + correctAttempts);
        perfectAttemptsTextView.setText("Всего идеальных отгадываний: " + perfectAttempts);

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

        if (btnReset != null) {
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("totalAttempts", 0);
                    editor.putInt("correctAttempts", 0);
                    editor.putInt("perfectAttempts", 0);
                    editor.apply();

                    totalAttemptsTextView.setText("Всего раз сыграно: 0");
                    correctAttemptsTextView.setText("Всего слов отгадано: 0");
                    perfectAttemptsTextView.setText("Всего идеальных отгадываний: 0");

                    Toast.makeText(getApplicationContext(), "Статистика была сброшена!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadUserData();
    }

    private void saveData() {
        String username = etUsername.getText().toString().trim();

        // Проверяем, что поле имени не пустое
        if (username.isEmpty()) {
            Toast.makeText(ProfileActivity.this, "Имя не может отсутствовать!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();

        Toast.makeText(ProfileActivity.this, "Данные сохранены!", Toast.LENGTH_SHORT).show();
    }

    private void loadUserData() {
        String username = sharedPreferences.getString("username", "Default Android User");
        etUsername.setText(username);
    }

}
