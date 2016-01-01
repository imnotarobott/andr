package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Random;



public class GameActivity extends Activity implements View.OnClickListener {

    int Eredmeny;
    int Ossz_max;
    int Szor_max;
    String Pelda;													//maga a pelda
    int Pelda_szama = 0;
    TextView TV5, TV4, TV2;
    boolean rep_ = false;											//repeat - �j j�t�k
    boolean allj_ = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
    }

    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void Pelda_generalas()
    {
        if (Pelda_szama < 20)
        {
            int p = randInt(1,4);
            if (p == 1)
            {
                Eredmeny = FOsszeadas(Ossz_max);
            }
            else if (p == 2)
            {
                Eredmeny = FKivonas(Ossz_max);
            }
            else if (p == 3)
            {
                Eredmeny = FSzorzas(Szor_max);
            }
            else
            {
                Eredmeny = FOsztas(Szor_max);
            }
            TV5.setText(Pelda);
            Pelda_szama++;
            TV4.setText(Integer.toString(Pelda_szama) + " / 20");
        }
        else
        {
            TV5.setText("Vége a játéknak");
            Button b10 = (Button) findViewById(R.id.Button10);
            b10.setText("Új jéták");
            b10.setTextSize(20);
            rep_ = true;
            allj_ = true;
        }
    }

    public int FOsszeadas(int Max)
    {
        int a = randInt(0, Max);
        int b = randInt(0, Max);
        while (a + b > 999)
            if (a > b)
                a = randInt(0, Max);
            else
                b = randInt(0, Max);
        Pelda = Integer.toString(a) + " + " + Integer.toString(b) + " = ";
        return a + b;
    }
    public int FKivonas(int Max)
    {
        int a = randInt(0, Max);
        int b = randInt(0, Max);
        if (a >= b)
        {
            Pelda = Integer.toString(a) + " - " + Integer.toString(b) + " = ";
            return a - b;
        }
        else
        {
            Pelda = Integer.toString(b) + " - " + Integer.toString(a) + " = ";
            return b - a;
        }

    }
    public int FSzorzas(int Max)
    {
        int a = randInt(0, Max);
        int b = randInt(0, Max);
        Pelda = Integer.toString(a) + " x " + Integer.toString(b) + " = ";
        return a * b;
    }
    public int FOsztas(int Max)
    {
        int a = randInt(1, Max);
        int b = randInt(1, Max);
        int c;
        c = a * b;
        Pelda = Integer.toString(c) + " / " + Integer.toString(a) + " = ";
        return c / a;
    }
    protected void alertbox(String s)
    {
        new AlertDialog.Builder(this)
                .setMessage(s)
                .setTitle(s)
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){}
                        })
                .show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button11:
                this.finish();
                break;
            case R.id.button1:
                alertbox("1");
                break;
            case R.id.Button02:
                alertbox("2");
                break;
            case R.id.Button03:
                alertbox("3");
                break;
            case R.id.Button01:
                alertbox("4");
                break;
            case R.id.Button04:
                alertbox("5");
                break;
            case R.id.Button05:
                alertbox("6");
                break;
            case R.id.Button06:
                alertbox("7");
                break;
            case R.id.Button07:
                alertbox("8");
                break;
            case R.id.Button08:
                alertbox("9");
                break;
            case R.id.Button09:
                alertbox("0");
                break;
            case R.id.Button10:
                if (!rep_)
                    alertbox("11");
                else
                {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
