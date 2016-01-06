package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class AboutActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Log.d(TAG, "Súgó létrehozva");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.About_back:
                Log.d(TAG, "Visszatérés a menübe");
                this.finish();
                break;
            default:
                break;
        }
    }
}
