package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Menü létrehozva");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(getApplicationContext(),GameActivity.class);
                intent.putExtra("xz", 1);
                Log.d(TAG, "Nehézségi szint: 1, játék indítása");
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(getApplicationContext(),GameActivity.class);
                intent.putExtra("xz", 2);
                Log.d(TAG, "Nehézségi szint: 2, játék indítása");
                startActivity(intent);;
                break;
            case R.id.button3:
                intent = new Intent(getApplicationContext(),GameActivity.class);
                intent.putExtra("xz", 3);
                Log.d(TAG, "Nehézségi szint: 3, játék indítása");
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(getApplicationContext(),AboutActivity.class);
                Log.d(TAG, "Súgó indítása");
                startActivity(intent);
                break;
            case R.id.button5:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}