package com.example.taboogameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class StartScreen extends AppCompatActivity
{
    Button buttonStartToPlay;
    List<wordsObject> wordsList = new ArrayList<>();
    List<String> mainWordsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Uygulamaya ilk kez girilmesinin kontrolü
        boolean firstTime = sharedPreferences.getBoolean("firstTime", true);

        if (firstTime)
        {
            editor.putBoolean("firstTime", false);
            editor.apply();
        }


        buttonStartToPlay = findViewById(R.id.buttonStartToPlay);

         buttonStartToPlay.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
             {
                 Intent intentGoChoosing = new Intent(StartScreen.this, ChoosingScreen.class);
                 startActivity(intentGoChoosing);
             }
         });


         // Obje Örneği
     /*    wordsObject object1 = new wordsObject("FUTBOL", "ofsayt");
         wordsObject object2 = new wordsObject("DOKTOR", "reçete");
         wordsObject object3 = new wordsObject("BILGISAYAR", "klavye");

         wordsList.add(object1);
         wordsList.add(object2);
         wordsList.add(object3);

         for (int i = 0; i < wordsList.size(); i++)
         {
             mainWordsList.add(wordsList.get(i).getMainWord());
         }

        System.out.println(mainWordsList.get(1));*/

    }
}