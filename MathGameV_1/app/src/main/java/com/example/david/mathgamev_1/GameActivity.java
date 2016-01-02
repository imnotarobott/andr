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
    int Nehezseg;
    int Tipp;
    boolean TippSeged_ = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Nehezseg = intent.getIntExtra("xz", 0);
        TV5 = (TextView)findViewById(R.id.textView5);
        TV4 = (TextView)findViewById(R.id.textView4);
        TV2 = (TextView)findViewById(R.id.textView2);
        Foprogram();
    }

    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public void Foprogram()
    {
        if (Nehezseg == 1)
        {
            Ossz_max = 19;
            Szor_max = 5;
        }
        else if (Nehezseg == 2)
        {
            Ossz_max = 98;
            Szor_max = 9;
        }
        else
        {
            Ossz_max = 998;
            Szor_max = 31;
        }
        Pelda_generalas();
    }
    public void Feldolgoz(int x)
    {
        if (x < 10)
        {
            if ((Integer.toString(Tipp)).length() <= (Integer.toString(Eredmeny)).length())
            {
                if ((Integer.toString(Tipp)).length() == 1 && !TippSeged_)
                {
                    Tipp = x;
                    TippSeged_ = true;
                }
                else
                {
                    if (Tipp != 0)
                    {
                        Tipp *= 10;
                        Tipp += x;
                    }
                    else
                    {
                        Tipp = 0;
                        TV5.setText(Pelda);
                    }
                }
            }
            TV5.setText(Pelda + Integer.toString(Tipp));
            if ((Integer.toString(Tipp)).length() == (Integer.toString(Eredmeny)).length())
            {
                if (FKompilator())
                {
                    Pelda_generalas();
                }
            }
        }
        else
        {
            Tipp = 0;
            TippSeged_ = false;
            TV5.setText(Pelda);
        }
    }
    public boolean FKompilator()
    {
        if (Eredmeny == Tipp)
        {
            Tipp = 0;
            TippSeged_ = false;
            return true;
        }
        else
        {
            Tipp = 0;
            TippSeged_ = false;
            TV5.setText(Pelda);
            return false;
        }
    }
    public void Pelda_generalas()
    {
        if (Pelda_szama < 3)
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
                Feldolgoz(1);
                break;
            case R.id.Button02:
                Feldolgoz(2);
                break;
            case R.id.Button03:
                Feldolgoz(3);
                break;
            case R.id.Button01:
                Feldolgoz(4);
                break;
            case R.id.Button04:
                Feldolgoz(5);
                break;
            case R.id.Button05:
                Feldolgoz(6);
                break;
            case R.id.Button06:
                Feldolgoz(7);
                break;
            case R.id.Button07:
                Feldolgoz(8);
                break;
            case R.id.Button08:
                Feldolgoz(9);
                break;
            case R.id.Button09:
                Feldolgoz(0);
                break;
            case R.id.Button10:
                if (!rep_)
                    Feldolgoz(11);
                else
                {
                    Intent intent = getIntent();
                    finish();
                    intent.putExtra("xz", Nehezseg);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
