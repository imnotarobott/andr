package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Random;
import android.os.CountDownTimer;
import android.os.SystemClock;

public class GameActivity extends Activity implements View.OnClickListener {

    int Ossz_max;
    int Szor_max;
    int Eredmeny;													//példa eredménye int-ben
    int Tipp;														//felhasználó által megadott tipp
    int Nehezseg;
    boolean TippSeged_ = false;										//A tipp valtozasat nezi
    String Pelda;													//maga a pelda
    int Pelda_szama = 0;											//példa száma
    TextView TV5, TV4, TV2;
    boolean rep_ = false;											//repeat - új játék
    boolean allj_ = false;

    long mStartTime = 0L;
    String Ido;
    Handler mHandler = new Handler();
    ///
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Nehezseg = intent.getIntExtra("xz", 0);
        TV5 = (TextView)findViewById(R.id.textView5);
        TV4 = (TextView)findViewById(R.id.textView4);
        TV2 = (TextView)findViewById(R.id.textView2);
        BDisable(false);
        final CounterClass timer = new CounterClass(5000, 1000, 0);
        timer.start();
        TV2.setText("0:000");
        Foprogram();
    }

    public class CounterClass extends CountDownTimer
    {
        public CounterClass(long millisInFuture, long countDownInterval, int tmp)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            TV5.setText("               " + Long.toString((millisUntilFinished / 1000) - 1));
        }

        @Override
        public void onFinish() {
            BEnable();
            TV5.setText(Pelda);
            mStartTime = SystemClock.uptimeMillis();
            mHandler.removeCallbacks(mUpdateTimeTask);
            mHandler.postDelayed(mUpdateTimeTask, 100);
        }
    }
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run()
        {
            if (!allj_){
                final long start = mStartTime;
                long millis = SystemClock.uptimeMillis()- start;
                int seconds = (int) (millis / 1000);
                int MilliSec = (int) (millis % 1000);
                TV2.setText("" + seconds + ":" + String.format("%02d", MilliSec));
                Ido = seconds + ":" + String.format("%02d", MilliSec);
                mHandler.postDelayed(this, 200);
            }else
            {
                mHandler.removeCallbacks(mUpdateTimeTask);
                TV2.setText(Ido);
            }
        }
    };
    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void BEnable ()
    {
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setEnabled(true);
        Button b01 = (Button) findViewById(R.id.Button01);
        b01.setEnabled(true);
        Button b02 = (Button) findViewById(R.id.Button02);
        b02.setEnabled(true);
        Button b03 = (Button) findViewById(R.id.Button03);
        b03.setEnabled(true);
        Button b04 = (Button) findViewById(R.id.Button04);
        b04.setEnabled(true);
        Button b05 = (Button) findViewById(R.id.Button05);
        b05.setEnabled(true);
        Button b06 = (Button) findViewById(R.id.Button06);
        b06.setEnabled(true);
        Button b07 = (Button) findViewById(R.id.Button07);
        b07.setEnabled(true);
        Button b08 = (Button) findViewById(R.id.Button08);
        b08.setEnabled(true);
        Button b09 = (Button) findViewById(R.id.Button09);
        b09.setEnabled(true);
        Button b10 = (Button) findViewById(R.id.Button10);
        b10.setEnabled(true);
    }
    public void BDisable(boolean b)
    {
        if (b = false)
        {
            Button b1 = (Button) findViewById(R.id.button1);
            b1.setEnabled(false);
            Button b01 = (Button) findViewById(R.id.Button01);
            b01.setEnabled(false);
            Button b02 = (Button) findViewById(R.id.Button02);
            b02.setEnabled(false);
            Button b03 = (Button) findViewById(R.id.Button03);
            b03.setEnabled(false);
            Button b04 = (Button) findViewById(R.id.Button04);
            b04.setEnabled(false);
            Button b05 = (Button) findViewById(R.id.Button05);
            b05.setEnabled(false);
            Button b06 = (Button) findViewById(R.id.Button06);
            b06.setEnabled(false);
            Button b07 = (Button) findViewById(R.id.Button07);
            b07.setEnabled(false);
            Button b08 = (Button) findViewById(R.id.Button08);
            b08.setEnabled(false);
            Button b09 = (Button) findViewById(R.id.Button09);
            b09.setEnabled(false);
            Button b10 = (Button) findViewById(R.id.Button10);
            b10.setEnabled(false);
        }
        else
        {
            Button b1 = (Button) findViewById(R.id.button1);
            b1.setEnabled(false);
            Button b01 = (Button) findViewById(R.id.Button01);
            b01.setEnabled(false);
            Button b02 = (Button) findViewById(R.id.Button02);
            b02.setEnabled(false);
            Button b03 = (Button) findViewById(R.id.Button03);
            b03.setEnabled(false);
            Button b04 = (Button) findViewById(R.id.Button04);
            b04.setEnabled(false);
            Button b05 = (Button) findViewById(R.id.Button05);
            b05.setEnabled(false);
            Button b06 = (Button) findViewById(R.id.Button06);
            b06.setEnabled(false);
            Button b07 = (Button) findViewById(R.id.Button07);
            b07.setEnabled(false);
            Button b08 = (Button) findViewById(R.id.Button08);
            b08.setEnabled(false);
            Button b09 = (Button) findViewById(R.id.Button09);
            b09.setEnabled(false);
        }
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
            BDisable(true);
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