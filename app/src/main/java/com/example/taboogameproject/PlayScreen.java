package com.example.taboogameproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
 *1 - Süre Göstergesi
 * 2 - Pause Düğmesi
 * 3 - Takım Adı
 * 4 - Skor Bilgisi
 * 5 - Kelime Kartı
 * 6 - Doğru Butonu
 * 7 - Tabu Butonu
 * 8 - Pas Butonu
 * 9 - Pas göstergesi
 */

public class PlayScreen extends AppCompatActivity
{

    // Görsel öğeler
    TextView textSure;
    TextView textTakim;
    ImageView imageDurakla;
    TextView textSkor;
    int skor = 0;
    TextView textAnaKelime;
    TextView textKelime1;
    TextView textKelime2;
    TextView textKelime3;
    TextView textKelime4;
    TextView textKelime5;
    ImageView imagePas;
    ImageView imageTabu;
    ImageView imageDogru;
    TextView textKalanPas;
    int kalanPas = 3;

    // Countdown timer için değişkenler
    CountDownTimer countDownTimer;
    public long timeLeftInMillis = 10000;

    List<wordsObject> wordsList = new ArrayList<>();
    SQLiteDatabase db;

    String takim = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        // Singleton Veri Alma
        if (VeriSingleton.getInstance().getTakim() != null)
        {
            takim = VeriSingleton.getInstance().getTakim();
        }
        else
        {
            takim = "1";
        }



     /*   wordsObject word = new wordsObject("Ana Kelime 11", "Tabu Kelime11");

        long insertedId = addWord(word);

        if (insertedId != -1)
        {
            // Ekleme başarılı
            Toast.makeText(getApplicationContext(), "Ekleme başarılı!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // Ekleme başarısız!
            Toast.makeText(getApplicationContext(), "Ekleme Başarısız!", Toast.LENGTH_SHORT).show();
        }*/

        // VERI ALMA
        String query = "SELECT * FROM wordsObject";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                // Verileri cursor'dan al
                String mainWord = cursor.getString(cursor.getColumnIndex("mainWord"));
                String tabooWord1 = cursor.getString(cursor.getColumnIndex("tabooWord1"));

                // Kaydedilenleri al
                wordsObject object = new wordsObject(mainWord, tabooWord1);
                wordsList.add(object);

            }while (cursor.moveToNext());
        }

        System.out.println("Liste doldu!");


        // Ekrandaki alanların activity'ye bağlanma işlemi
        textSure = findViewById(R.id.textSure);
        textTakim = findViewById(R.id.textTakim);
        imageDurakla = findViewById(R.id.imageDurakla);
        textSkor = findViewById(R.id.textSkor);
        textAnaKelime = findViewById(R.id.textAnaKelime);
        textKelime1 = findViewById(R.id.textKelime1);
        textKelime2 = findViewById(R.id.textKelime2);
        textKelime3 = findViewById(R.id.textKelime3);
        textKelime4 = findViewById(R.id.textKelime4);
        textKelime5 = findViewById(R.id.textKelime5);
        imagePas = findViewById(R.id.imagePas);
        imageTabu = findViewById(R.id.imageTabu);
        imageDogru = findViewById(R.id.imageDogru);
        textKalanPas = findViewById(R.id.textKalanPas);

        textSkor.setText(String.valueOf(skor));
        textKalanPas.setText("Kalan Pas Hakkı: " + String.valueOf(kalanPas));

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                int seconds = (int) (millisUntilFinished / 1000) % 60;

                textSure.setText(String.valueOf(seconds));
            }
            @Override
            public void onFinish()
            {
                // Singleton'a veri atama
                if (takim.equals("1"))
                {
                    VeriSingleton.getInstance().setTakim("2");
                    int skor1Before = VeriSingleton.getInstance().getSkor1();
                    VeriSingleton.getInstance().setSkor1(skor + skor1Before);
                }
                else
                {
                    VeriSingleton.getInstance().setTakim("1");
                    int skor2Before = VeriSingleton.getInstance().getSkor2();
                    VeriSingleton.getInstance().setSkor2(skor2Before + skor);
                }

                // onBackPressed(); // Bir önceki ekrana geri dönülmesini sağlar.
                Intent intent = new Intent(PlayScreen.this, ChoosingScreen.class);
                startActivity(intent);
            }
        }.start();




        imageDogru.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                skor = skor + 1;
                textSkor.setText(String.valueOf(skor));

                // Ana Kelime ve tabu kelimelerini değiştir.
                textAnaKelime.setText(wordsList.get(0).getMainWord());
                textKelime1.setText(wordsList.get(0).getTabooWord1());
                textKelime2.setText("kale");
                textKelime3.setText("oyuncu");
                textKelime4.setText("kaleci");
                textKelime5.setText("saha");
            }
        });

        imageTabu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                skor = skor - 1;
                textSkor.setText(String.valueOf(skor));

                // Ana Kelime ve tabu kelimelerini değiştir.
                textAnaKelime.setText(wordsList.get(1).getMainWord());
                textKelime1.setText(wordsList.get(1).getTabooWord1());
                textKelime2.setText("sabah");
                textKelime3.setText("sürülebilen");
                textKelime4.setText("şeker");
                textKelime5.setText("kavanoz");
            }
        });

        imagePas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (kalanPas == 0)
                {
                    Toast.makeText(getApplicationContext(), "Pas hakkınız bitti!", Toast.LENGTH_LONG).show();
                }
                else if (kalanPas > 0)
                {
                    kalanPas = kalanPas - 1;
                    textKalanPas.setText("Kalan Pas Hakkı: " + String.valueOf(kalanPas));

                    // Ana Kelime ve tabu kelimelerini değiştir.
                    textAnaKelime.setText(wordsList.get(2).getMainWord());
                    textKelime1.setText(wordsList.get(2).getTabooWord1());
                    textKelime2.setText("mouse");
                    textKelime3.setText("şarj aleti");
                    textKelime4.setText("tablet");
                    textKelime5.setText("windows");
                }
            }
        });

        imageDurakla.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                countDownTimer.cancel();
                countDownTimer.start();
            }
        });

    }

    // VERİ KAYDETME
    public long addWord(wordsObject word)
    {
        ContentValues values = new ContentValues();
        values.put("mainWord", word.getMainWord());
        values.put("tabooWord1", word.getTabooWord1());

        long id = db.insert("wordsObject", null, values);

        return id;
    }

}

