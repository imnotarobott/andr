package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Random;
import android.os.CountDownTimer;
import android.os.SystemClock;

public class GameActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "GameActivity";
    /**
     * Összeadáshoz és kivonáshoz használt maximális véletlenszerű szám
    */
    int Ossz_max;
    /**
     * szorzáshoz és osztáshoz használt maximális véletlenszerű szám
    */
    int Szor_max;
    /**
     * aktuális példa eredménye
    */
    int Eredmeny;
    /**
     * felhasználó által megadott tipp
    */
    int Tipp;
    /**
     * játék nehézségi szintje
    */
    int Nehezseg;
    /**
     * megadott tipp változásához szükséges változó (többjegyű szám esetében fontos)
    */
    boolean TippSeged_ = false;
    /**
     * generált példa
    */
    String Pelda;
    /**
     * aktuális példa száma
    */
    int Pelda_szama = 0;
    /**
     * TV2 - időzítő TextView-ja
     * TV4 - aktuális példa számának TextView-ja
     * TV5 - aktuális példa TextView-ja
    */
    TextView TV5, TV4, TV2;
    /**
     * repeat - új játék azonos nehézségi szinten
    */
    boolean rep_ = false;
    /**
     * időszámlálóhoz használt logikai változó, mely leállítja az időszámlálót, ha a játéknak
     * vége
    */
    boolean allj_ = false;
    /**
     * időszámláló indulási ideje
    */
    long mStartTime = 0L;
    /**
     * idő karakterláncban
    */
    String Ido;
    /**
     * handler
    */
    Handler mHandler = new Handler();
    /**
     * osztály inicializálása
     * <p>
     *     TextView-ok inicializállása, nehézségi szint beállítása a kapott adatok alapján,
     *     gombok letiltása, timer TextView-jának nullázása, főprogram indítása
     * </p>
    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Log.d(TAG, "Játék létrehozva");
        Nehezseg = intent.getIntExtra("xz", 0);
        Log.d(TAG, "Nehézségi szint beállítva");
        TV5 = (TextView)findViewById(R.id.textView5);
        TV4 = (TextView)findViewById(R.id.textView4);
        TV2 = (TextView)findViewById(R.id.textView2);
        Log.d(TAG, "TextView-ok inicializálva");
        BEnable(false);
        Log.d(TAG, "Gombok letiltva");
        final CounterClass timer = new CounterClass(5000, 1000, 0);
        Log.d(TAG, "Időzítő létrehozva");
        timer.start();
        Log.d(TAG, "Időzítő elindítva");
        TV2.setText("0:000");
        Log.d(TAG, "Főprogram indítása");
        Foprogram();
    }
    /**
    * számláló osztály létrehozása a számlálóhoz
     * <p>
     *     Az osztályban meghatározzuk a Timer-t és annak intervallumát, ehhez inicializáljuk a
     *     neki szánt TextView-ot (TextView5 közepére számlállunk majd vissza 3-tól a kezdésnél),
     *     a végén pedig engedélyezésre kerül a gombok használata és indul az időzítő ezredmásodperc
     *     pontossággal
     * </p>
    */
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
            BEnable(true);
            TV5.setText(Pelda);
            mStartTime = SystemClock.uptimeMillis();
            mHandler.removeCallbacks(mUpdateTimeTask);
            mHandler.postDelayed(mUpdateTimeTask, 100);
        }
    }
    /**
     * Az időítő futtatása
    */
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
    /*
    * Véletlenszerű egész szám generálása
    */
    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        Log.d(TAG, "randInt: véletlenszerű szám létrehozva");
        return randomNum;
    }
    /*
    * Gombok letiltása és engedélyezése
    */
    public void BEnable (boolean b)
    {
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setEnabled(b);
        Button b01 = (Button) findViewById(R.id.Button01);
        b01.setEnabled(b);
        Button b02 = (Button) findViewById(R.id.Button02);
        b02.setEnabled(b);
        Button b03 = (Button) findViewById(R.id.Button03);
        b03.setEnabled(b);
        Button b04 = (Button) findViewById(R.id.Button04);
        b04.setEnabled(b);
        Button b05 = (Button) findViewById(R.id.Button05);
        b05.setEnabled(b);
        Button b06 = (Button) findViewById(R.id.Button06);
        b06.setEnabled(b);
        Button b07 = (Button) findViewById(R.id.Button07);
        b07.setEnabled(b);
        Button b08 = (Button) findViewById(R.id.Button08);
        b08.setEnabled(b);
        Button b09 = (Button) findViewById(R.id.Button09);
        b09.setEnabled(b);
        Button b10 = (Button) findViewById(R.id.Button10);
        b10.setEnabled(b);
        Log.d(TAG, "BEnable: gombok engedélyezése áttállíva" + b + "-ra");
    }
    /**
     * nehézségi szint beállítása
     * <p>
     *     beállításra kerül nehézség alapján, hogy milyen nagyságrendű számok szerepelhessenek a
     *     példákban, példagenerálás
     * </p>
    */
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
        Log.d(TAG, "Foprogram: nehézségi szint: " + Nehezseg + ", összeadás/kivonáshoz generált " +
                "szám maximális értéke: " + Ossz_max + ", szorzáshoz/osztáshoz generált szám " +
                "maximális értéke: " + Szor_max);
        Log.d(TAG, "Foprogram: példa generálásának indítása");
        Pelda_generalas();
    }
    /**
     * Felhasználó tippjeinek ellenőrzése
     * <p>
     *     Előszötis ellenőrzésre kerül, hogy a beérkező szám kisebb-e, mint 10 a hibák elkerülése
     *     érdekében, ha igen, akkor megnézzük, hogy a tipp és az aktuális példa eredménye kisebb
     *     vagy egyenlő-e. Ha igen, akkor folytatjuk a beírt szám ellenőrzéség. A következő lépésben
     *     megvizsgáljuk, hogy a tipp nagysága egyenlő-e 1-el és használtunk-e segédet. Amennyiben
     *     1 és nem használtunk még segédet, akkor a Tipp egyenlő lesz az általunk lenyomott gomb
     *     értékével és a segédet igazra állítjuk. Amennyiben a feltétel nem teljesül, tovább
     *     ellenőrzünk (nagyobb számok esetén hasznos). Ha a tipp nem egynelő 0-val, akkor
     *     beszorozzuk 10-el, valamint hozzáadjuk az utoljára megadott számjegyet (pl. így tudunk
     *     többjegyű számot ellenőrizni a későbbiekben). Amennyiben nem teljesül a feltétel, akkor
     *     a Tipp-et kinullázzuk és a TextView kizárólag az aktuális példát fogja mutatni.
     *
     *     Ha a Tippünk és az eredményünk azonos hosszúságú, akkor a feladat komplimálás következik
     *     a Fkomplimator metódus segítségével. Ha helyes az eredmény, generálunk egy új példát.
     *
     *     Ha eleve az általunk megadott számjegy (x) nem kisebb, mint 10, akkor a Tippet
     *     lenullázzuk, a segédet hamisra állítjuk és kiíratjuk az aktuális példát.
     * </p>
     * @param x - az általunk megadott számjegy
    */
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
                    Log.d(TAG, "Feldolgoz: Példa generálásának indítása");
                    Pelda_generalas();
                }
            }
        }
        else
        {
            Tipp = 0;
            TippSeged_ = false;
            Log.d(TAG, "Feldolgoz: beírt tipp törölve");
            TV5.setText(Pelda);
        }
    }
    /**
     * Ellenőrzi, hogy helyes megoldást adott-e a felhasználó
     * @return a beírt tipp megfelel az eredmények?
    */
    public boolean FKompilator()
    {
        Log.d(TAG, "FKompilator: megadott eredmény helyességének ellenőrzése");
        if (Eredmeny == Tipp)
        {
            Tipp = 0;
            TippSeged_ = false;
            Log.d(TAG, "FKompilator: helyes tipp");
            return true;
        }
        else
        {
            Tipp = 0;
            TippSeged_ = false;
            TV5.setText(Pelda);
            Log.d(TAG, "FKompilator: helytelen tipp");
            return false;
        }
    }
    /**
     * példák generálása
     * <p>
     *     Ha még a példák száma nem érte el a huszat, akkor véletlenszerűen egy összeadás,
     *     kivonás, szorzás vagy osztás generálódik a játék nehézségének függvényében, majd a példa
     *     kiírásra kerül. Ezt követően a példák száma növekszik 1-el és kiíratásra kerül az
     *     aktuális példának száma. Amennyiben a példák száma elérte már a maximumot, a játék
     *     végetér.
     * </p>
    */
    public void Pelda_generalas()
    {
        if (Pelda_szama < 20)
        {
            int p = randInt(1,4);
            if (p == 1)
            {
                Log.d(TAG, "Pelda_generalas: összeadási példa generálása");
                Eredmeny = FOsszeadas(Ossz_max);
            }
            else if (p == 2)
            {
                Log.d(TAG, "Pelda_generalas: kivonási példa generálása");
                Eredmeny = FKivonas(Ossz_max);
            }
            else if (p == 3)
            {
                Log.d(TAG, "Pelda_generalas: szorzási példa generálása");
                Eredmeny = FSzorzas(Szor_max);
            }
            else
            {
                Log.d(TAG, "Pelda_generalas: osztási példa generálása");
                Eredmeny = FOsztas(Szor_max);
            }
            TV5.setText(Pelda);
            Pelda_szama++;
            Log.d(TAG, "Pelda_generalas: példa száma = " + Pelda_szama);
            TV4.setText(Integer.toString(Pelda_szama) + " / 20");
        }
        else
        {
            Log.d(TAG, "Pelda_generalas: Játék vége");
            TV5.setText("Vége a játéknak");
            Button b10 = (Button) findViewById(R.id.Button10);
            b10.setText("Új jéták");
            b10.setTextSize(20);
            rep_ = true;
            allj_ = true;
            BEnable(false);
            b10.setEnabled(true);
            alertbox("Vége a játéknak!", "Játékidő: " + Ido);
        }
    }
    /**
     * Összeadási példa generálása
     * @param Max - maximális véletlenszerű szám
     * @return eredmény
    */
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
        Log.d(TAG, "FOsszeadas: összeadási példa generálva (" + Pelda + ")");
        return a + b;
    }
    /**
     * Kivonási példa generálása
     * @param Max - maximális véletlenszerű szám
     * @return eredmény
    */
    public int FKivonas(int Max)
    {
        int a = randInt(0, Max);
        int b = randInt(0, Max);
        if (a >= b)
        {
            Pelda = Integer.toString(a) + " - " + Integer.toString(b) + " = ";
            Log.d(TAG, "FKivonas: kivonási példa generálva (" + Pelda + ")");
            return a - b;
        }
        else
        {
            Pelda = Integer.toString(b) + " - " + Integer.toString(a) + " = ";
            Log.d(TAG, "FKivonas: kivonási példa generálva (" + Pelda + ")");
            return b - a;
        }
    }
    /**
     * Szorzási példa generálása
     * @param Max - maximális véletlenszerű szám
     * @return eredmény
    */
    public int FSzorzas(int Max)
    {
        int a = randInt(0, Max);
        int b = randInt(0, Max);
        Pelda = Integer.toString(a) + " x " + Integer.toString(b) + " = ";
        Log.d(TAG, "FSzorzas: szorzási példa generálva (" + Pelda + ")");
        return a * b;
    }
    /**
     * Osztási példa generálása
     * @param Max - maximális véletlenszerű szám
     * @return eredmény
    */
    public int FOsztas(int Max)
    {
        int a = randInt(1, Max);
        int b = randInt(1, Max);
        int c;
        c = a * b;
        Pelda = Integer.toString(c) + " / " + Integer.toString(a) + " = ";
        Log.d(TAG, "FOsztas: osztási példa generálva (" + Pelda + ")");
        return c / a;
    }
    /**
     * Felugró ablak inicializálása
     * @param s - kiíratandó üzenet
    */
    protected void alertbox(String s, String jatekido)
    {
        Log.d(TAG, "alertbox: felugró ablak létrehozása");
        new AlertDialog.Builder(this)
                .setMessage(jatekido)
                .setTitle(s)
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){}
                        })
                .show();
    }
    /**
     * OnClick események
     * @param v - View
    */
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
                    Log.d(TAG, "onClick: új játék");
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}