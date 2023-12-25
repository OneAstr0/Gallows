package game.gallows;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

public class ChooseActivity extends AppCompatActivity {
    ImageButton btnRandom, btnBack, btnAnimals, btnFlora, btnCountry, btnFood, btnMush, btnCurrency, btnCar, btnRiver, btnCity, btnChemistry, btnProf, btnSport, btnFlowers;
    MediaPlayer sheet1;
    String[] animalsArray, floraArray, countryArray, foodArray, mushArray, currencyArray, carArray, riverArray, cityArray, chemArray, profArray, sportArray, flowersArray;
    String category;
    ImageView newProfImage, newSportImage, newFlowersImage;

    boolean soundMode = true;

    private boolean newProf, newSport, newFlowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        SharedPreferences sharedPreferences = getSharedPreferences(SettingsActivity.SHARED_PREFS, MODE_PRIVATE);
        soundMode = sharedPreferences.getBoolean(SettingsActivity.SOUND_MODE, true);

        newProf = sharedPreferences.getBoolean("newProf", true);
        newSport = sharedPreferences.getBoolean("newSport", true);
        newFlowers = sharedPreferences.getBoolean("newFlowers", true);

        newProfImage = findViewById(R.id.newProf);
        newSportImage = findViewById(R.id.newSport);
        newFlowersImage = findViewById(R.id.newFlowers);


        btnBack = findViewById(R.id.btnBackChoose);

        sheet1 = MediaPlayer.create(this, R.raw.sheet1);

        btnRandom = findViewById(R.id.btnRandom);
        btnAnimals = findViewById(R.id.btnAnimals);
        btnFlora = findViewById(R.id.btnFlora);
        btnCountry = findViewById(R.id.btnCountry);
        btnFood = findViewById(R.id.btnFood);
        btnMush = findViewById(R.id.btnMush);
        btnCurrency = findViewById(R.id.btnCurrency);
        btnCar = findViewById(R.id.btnCar);
        btnRiver = findViewById(R.id.btnRiver);
        btnCity = findViewById(R.id.btnCity);
        btnChemistry = findViewById(R.id.btnChemistry);
        btnProf = findViewById(R.id.btnProf);
        btnSport = findViewById(R.id.btnSport);
        btnFlowers = findViewById(R.id.btnFlowers);


        Resources res = getResources();
        animalsArray = res.getStringArray(R.array.animals25);
        floraArray = res.getStringArray(R.array.flora);
        countryArray = res.getStringArray(R.array.countryArray);
        foodArray = res.getStringArray(R.array.foodArray);
        mushArray = res.getStringArray(R.array.mushArray);
        currencyArray = res.getStringArray(R.array.currencyArray);
        carArray = res.getStringArray(R.array.carArray);
        riverArray = res.getStringArray(R.array.riverArray);
        cityArray = res.getStringArray(R.array.cityArray);
        chemArray = res.getStringArray(R.array.chemArray);
        profArray = res.getStringArray(R.array.profArray);
        sportArray = res.getStringArray(R.array.sportArray);
        flowersArray = res.getStringArray(R.array.flowersArray);



        // Проверка для отображения звёздочек New для новых категорий
        // МЕНЯТЬ И ОБНОВЛЯТЬ ПРИ КАЖДОЙ НОВОЙ ВЕРСИИ!!!!!!!!!!!!!!!!
        if (newProf == false) {
            newProfImage.setVisibility(GONE);
        }
        if (newSport == false) {
            newSportImage.setVisibility(GONE);
        }
        if (newFlowers == false) {
            newFlowersImage.setVisibility(GONE);
        }



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

        Intent intent = new Intent(ChooseActivity.this, GameActivity.class);


        if (btnRandom != null) {
            btnRandom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    Random random = new Random();
                    int randomIndex = random.nextInt(13);

                    switch (randomIndex) {
                        case 0:
                            intent.putExtra("array", animalsArray);
                            break;
                        case 1:
                            intent.putExtra("array", floraArray);
                            break;
                        case 2:
                            intent.putExtra("array", countryArray);
                            break;
                        case 3:
                            intent.putExtra("array", foodArray);
                            break;
                        case 4:
                            intent.putExtra("array", mushArray);
                            break;
                        case 5:
                            intent.putExtra("array", currencyArray);
                            break;
                        case 6:
                            intent.putExtra("array", carArray);
                            break;
                        case 7:
                            intent.putExtra("array", riverArray);
                            break;
                        case 8:
                            intent.putExtra("array", cityArray);
                            break;
                        case 9:
                            intent.putExtra("array", chemArray);
                            break;
                        case 10:
                            intent.putExtra("array", profArray);
                            break;
                        case 11:
                            intent.putExtra("array", sportArray);
                            break;
                        case 12:
                            intent.putExtra("array", flowersArray);
                            break;
                        default:
                            intent.putExtra("array", animalsArray);
                            break;
                    }

                    intent.putExtra("category", "Случайная кат.");
                    startActivity(intent);
                    finish();
                }
            });
        }


        if (btnAnimals != null) {
            btnAnimals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", animalsArray);
                    intent.putExtra("category", "Животные");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnFlora != null) {
            btnFlora.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", floraArray);
                    intent.putExtra("category", "Природа");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnCountry != null) {
            btnCountry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", countryArray);
                    intent.putExtra("category", "Страны");

                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnFood != null) {
            btnFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", foodArray);
                    intent.putExtra("category", "Еда");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnMush != null) {
            btnMush.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", mushArray);
                    intent.putExtra("category", "Грибы");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnFlowers != null) {
            btnFlowers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (newFlowers) {
                        editor.putBoolean("newFlowers", false);
                        editor.apply();
                    }

                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", flowersArray);
                    intent.putExtra("category", "Цветы");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnCurrency != null) {
            btnCurrency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", currencyArray);
                    intent.putExtra("category", "Валюта");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnCar != null) {
            btnCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", carArray);
                    intent.putExtra("category", "Марки машин");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnRiver != null) {
            btnRiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", riverArray);
                    intent.putExtra("category", "Реки");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnCity != null) {
            btnCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", cityArray);
                    intent.putExtra("category", "Города");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnChemistry != null) {
            btnChemistry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", chemArray);
                    intent.putExtra("category", "Хим. элементы");
                    startActivity(intent);
                    finish();
                }
            });
        }

        if (btnProf != null) {
            btnProf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (newProf) {
                        editor.putBoolean("newProf", false);
                        editor.apply();
                    }

                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", profArray);
                    intent.putExtra("category", "Профессии");
                    startActivity(intent);
                    finish();
                }
            });
        }


        if (btnSport != null) {
            btnSport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (newSport) {
                        editor.putBoolean("newSport", false);
                        editor.apply();
                    }

                    if (sheet1.isPlaying()) {
                        sheet1.pause();
                        sheet1.seekTo(0);
                    }
                    if (soundMode) {
                        sheet1.start();
                    }

                    intent.putExtra("array", sportArray);
                    intent.putExtra("category", "Виды спорта");
                    startActivity(intent);
                    finish();
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

}