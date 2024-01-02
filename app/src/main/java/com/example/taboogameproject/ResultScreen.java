package com.example.taboogameproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity
{
    TextView textTeamName;
    Button buttonPlayAgain;

    int skor1;
    int skor2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        textTeamName = findViewById(R.id.textTeamName);
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);

        skor1 = VeriSingleton.getInstance().getSkor1();
        skor2 = VeriSingleton.getInstance().getSkor2();

        if (skor1 >= 10)
        {
            textTeamName.setText(getResources().getString(R.string.team1IsWinner));
        }
        else if (skor2 >= 10)
        {
            textTeamName.setText(getResources().getString(R.string.team2IsWinner));
        }

        // Oyun bitti ve veriler sıfırlanarak diğer activity'ye intent ile gidildi.
        buttonPlayAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                VeriSingleton.getInstance().setSkor1(0);
                VeriSingleton.getInstance().setSkor2(0);
                VeriSingleton.getInstance().setTakim("1");

                Intent intent = new Intent(ResultScreen.this, ChoosingScreen.class);
                startActivity(intent);
            }
        });
    }
}