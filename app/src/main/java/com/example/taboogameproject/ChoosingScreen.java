package com.example.taboogameproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChoosingScreen extends AppCompatActivity
{
    Button buttonStart;

    String takim = "1";
    TextView textTakim1Skor;
    TextView textTakim2Skor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_screen);

        textTakim1Skor = findViewById(R.id.textTakim1Skor);
        textTakim2Skor =findViewById(R.id.textTakim2Skor);


        // Singleton Değer Alma

        if (VeriSingleton.getInstance().getTakim() == null)
        {
            takim = "1";
        }
        else
        {
            takim = VeriSingleton.getInstance().getTakim();
        }

        textTakim1Skor.setText(String.valueOf(VeriSingleton.getInstance().getSkor1()));
        textTakim2Skor.setText(String.valueOf(VeriSingleton.getInstance().getSkor2()));


        // Takımlardan skoru 10'a ulaşan kazanır ve alert dialog açılır.
        if ((VeriSingleton.getInstance().getSkor1() >= 10) || (VeriSingleton.getInstance().getSkor2() >= 10))
        {
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
            Button dialogButton = dialogView.findViewById(R.id.buttonOk);
            dialogButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(ChoosingScreen.this, ResultScreen.class);
                    startActivity(intent);
                }
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }



        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ChoosingScreen.this, PlayScreen.class);
                startActivity(intent);
            }
        });
    }
}