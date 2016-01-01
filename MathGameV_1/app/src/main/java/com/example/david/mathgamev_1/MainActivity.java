package com.example.david.mathgamev_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                ;
                break;
            case R.id.button3:
                intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(getApplicationContext(), GameActivity.class);
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