package com.dsiedlarz.zlokalizujmiasto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dsiedlarz.zlokalizujmiasto.GeocodeResponse.*;

import com.dsiedlarz.zlokalizujmiasto.GeocodeResponse.GeocodeResponse;

public class Main2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Powrót");
            getSupportActionBar().setIcon(R.drawable.walking);

        }

        TextView miejscowość=(TextView)findViewById(R.id.miejscowosc);
        TextView gmina = (TextView)findViewById(R.id.gmina);
        TextView powiat =(TextView)findViewById(R.id.powiat);
        TextView wojewodztwo = (TextView) findViewById(R.id.wojewodztwo);
        TextView kraj= (TextView) findViewById(R.id.Kraj);

        miejscowość.append(i.getStringExtra("miejscowosc"));
        gmina.append(i.getStringExtra("gmina"));
        powiat.append(i.getStringExtra("powiat"));
        wojewodztwo.append(i.getStringExtra("wojewodztwo"));
        kraj.append(i.getStringExtra("kraj"));
      //  miejscowość.append(MainActivity.geocodeResponse.getResults().get(0).getAddress_components().get(0).getLong_name());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getActionBar().setDisplayHomeAsUpEnabled(true);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case android.R.id.home:
                finish();
                break;
            case android.R.id.title:
                finish();
            default:
                break;
        }

        return true;
    }

}
