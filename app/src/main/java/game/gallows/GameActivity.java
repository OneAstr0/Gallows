package game.gallows;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnBack;
    ImageView gallows;
    LinearLayout layout;
    TextView[] slots;
    Button[] buttons;
    String randomWord, category;
    int fallsCount = 0, totalAttempts, correctAttempts, perfectAttempts;
    TextView catText;
    String[] array, animalsArray, floraArray, countryArray, foodArray, mushArray, currencyArray, carArray, riverArray, cityArray, chemArray, profArray, sportArray, flowersArray;
    MediaPlayer sheet1, seehuman, win, lose;
    boolean soundMode = true;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        // Получение статистики из SharedPreferences
        totalAttempts = sharedPreferences.getInt("totalAttempts", 0);
        correctAttempts = sharedPreferences.getInt("correctAttempts", 0);
        perfectAttempts = sharedPreferences.getInt("perfectAttempts", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        totalAttempts = totalAttempts + 1;
        editor.putInt("totalAttempts", totalAttempts);
        editor.apply();

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/await.ttf");
        gallows = findViewById(R.id.gallows);
        gallows.setImageResource(R.drawable.f0);

        // звуки
        sheet1 = MediaPlayer.create(this, R.raw.sheet1);
        seehuman = MediaPlayer.create(this, R.raw.see_human);
        win = MediaPlayer.create(this, R.raw.win);
        lose = MediaPlayer.create(this, R.raw.lose);

        Intent intent = getIntent();
        // Получаем массив из намерения
        array = intent.getStringArrayExtra("array");
        category = String.valueOf(intent.getStringExtra("category"));

        if (category.startsWith("Уровень")) {
            Resources res = getResources();

            // массив массивов по категориям
            String[][] arrays = {
                    res.getStringArray(R.array.animals25),
                    res.getStringArray(R.array.flora),
                    res.getStringArray(R.array.countryArray),
                    res.getStringArray(R.array.foodArray),
                    res.getStringArray(R.array.mushArray),
                    res.getStringArray(R.array.currencyArray),
                    res.getStringArray(R.array.carArray),
                    res.getStringArray(R.array.riverArray),
                    res.getStringArray(R.array.cityArray),
                    res.getStringArray(R.array.chemArray),
                    res.getStringArray(R.array.profArray),
                    res.getStringArray(R.array.sportArray),
                    res.getStringArray(R.array.flowersArray)
            };

            Random random = new Random();
            int randomIndex = random.nextInt(arrays.length);

            array = arrays[randomIndex];
        }


        catText = findViewById(R.id.catText);
        catText.setText(category);

        // Беру случайное слово
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        randomWord = array[randomIndex];
        randomWord = randomWord.toUpperCase();

        btnBack = findViewById(R.id.btnBackGame);

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

                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        // работа с выводом и обработкой выпавшего слова
        layout = findViewById(R.id.layoutContainer);
        slots = new TextView[randomWord.length()];
        buttons = new Button[34];

        for (int i = 0; i < randomWord.length(); i++) {
            TextView textView = new TextView(this);
            textView.setText("_");
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(typeface);
            textView.setPadding(12, 0, 12, 0);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textView.setLayoutParams(layoutParams);

            layout.addView(textView);
            slots[i] = textView;
        }

        for (int i = 0; i < 34; i++) {
            int resID = getResources().getIdentifier("button_" + (i + 1), "id", getPackageName());
            Button button = findViewById(resID);
            button.setOnClickListener(this);
            buttons[i] = button;
        }

        if (!category.startsWith("Уровень")) {
            openFirstAndLastLetter(); // Открываем первую и последнюю букву
        }
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        clickedButton.setEnabled(false); // Отключаем кнопку

        char clickedLetter = clickedButton.getText().charAt(0);

        boolean letterFound = false;
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == clickedLetter) {
                letterFound = true;
                slots[i].setText(String.valueOf(clickedLetter)); // Открываем соответствующий слот буквы
            }
        }

        if (letterFound) {
            clickedButton.setTextColor(Color.GREEN); // Меняем цвет текста на зеленый
        } else {
            fallsCount++; // Увеличиваем счетчик осечек
            clickedButton.setTextColor(Color.RED); // Меняем цвет текста на красный
        }

        // обновление картинки
        switch (fallsCount) {
            case 1:
                gallows.setImageResource(R.drawable.f1);
                break;
            case 2:
                gallows.setImageResource(R.drawable.f2);
                break;
            case 3:
                gallows.setImageResource(R.drawable.f3);
                break;
            case 4:
                gallows.setImageResource(R.drawable.f4);
                break;
            case 5:
                gallows.setImageResource(R.drawable.f5);
                break;
            case 6:
                gallows.setImageResource(R.drawable.f6);
                break;
            case 7:
                gallows.setImageResource(R.drawable.f7);
                break;
            case 8:
                gallows.setImageResource(R.drawable.f8);

                if (seehuman.isPlaying()) {
                    seehuman.pause();
                    seehuman.seekTo(0);
                }
                if (soundMode) {
                    seehuman.start();
                }
                break;
            case 9:
                gallows.setImageResource(R.drawable.f9);

                if (lose.isPlaying()) {
                    lose.pause();
                    lose.seekTo(0);
                }
                if (soundMode) {
                    lose.start();
                }

                // Создание оверлея
                showEndGameDialog(false); // Вызов окна при поражении
                break;
        }
        checkWinCondition();
    }

    private void checkWinCondition() {
        for (TextView slot : slots) {
            if (slot.getText().toString().equals("_")) {
                return;
            }
        }

        if (win.isPlaying()) {
            win.pause();
            win.seekTo(0);
        }
        if (soundMode) {
            win.start();
        }
        updateStatistics(true);
        showEndGameDialog(true); // Вызов окна при победе
    }

    private void updateStatistics(boolean isWin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isWin) {
            correctAttempts++;
            if (fallsCount == 0) {
                perfectAttempts++;
            }
        }
        editor.putInt("correctAttempts", correctAttempts);
        editor.putInt("perfectAttempts", perfectAttempts);
        editor.apply();
    }

    private void openFirstAndLastLetter() {
        char firstLetter = randomWord.charAt(0);
        char lastLetter = randomWord.charAt(randomWord.length() - 1);
        Button firstButton = null;
        Button lastButton = null;

        for (Button button : buttons) {
            if (button != null) {
                char letter = button.getText().charAt(0);
                if (letter == firstLetter) {
                    firstButton = button;
                }
                if (letter == lastLetter) {
                    lastButton = button;
                }
            }
        }

        if (firstButton != null) {
            firstButton.setEnabled(false);
            firstButton.setTextColor(Color.GRAY);
        }

        if (lastButton != null) {
            lastButton.setEnabled(false);
            lastButton.setTextColor(Color.GRAY);
        }

        for (int i = 0; i < randomWord.length(); i++) {
            char letter = randomWord.charAt(i);
            if (letter == firstLetter) {
                slots[i].setText(String.valueOf(letter));
            }
            if (letter == lastLetter) {
                slots[i].setText(String.valueOf(letter));
            }
        }
    }

    private void showEndGameDialog(boolean isWin) {
        // Создание диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.win_screen, null);
        builder.setView(dialogView);

        // Настройка фонового рисунка окна (можно настроить по своему усмотрению)
        AlertDialog endGameDialog = builder.create();
        endGameDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        endGameDialog.getWindow().setWindowAnimations(R.style.DialogAnimation);

        // Настройка кнопок и текстового поля
        Button btnRestart = dialogView.findViewById(R.id.btnRestart);
        Button btnMenu = dialogView.findViewById(R.id.btnMenu);
        Button btnCategory = dialogView.findViewById(R.id.btnCategory);
        TextView result = dialogView.findViewById(R.id.result);
        TextView word = dialogView.findViewById(R.id.word);
        TextView falls = dialogView.findViewById(R.id.falls);

        // Установка текста и обработчиков кликов кнопок
        if (isWin) {
            result.setText("Вы угадали слово!");
        } else {
            result.setText("Вы не угадали слово!");
        }
        word.setText("Загаданное слово: " + randomWord);
        falls.setText("Количество ошибок: " + fallsCount + " из 9");

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создание нового интента для перезапуска активности
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                intent.putExtra("array", array);
                intent.putExtra("category", category);
                startActivity(intent);
                finish();
            }
        });

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Отображение диалогового окна
        endGameDialog.show();

        endGameDialog.setCancelable(false);
        endGameDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sheet1 != null) {
            sheet1.release();
            sheet1 = null;
        }

        if (seehuman != null) {
            seehuman.release();
            seehuman = null;
        }

        if (win != null) {
            win.release();
            win = null;
        }

        if (lose != null) {
            lose.release();
            lose = null;
        }
    }
}
