package com.dsiedlarz.zlokalizujmiasto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dsiedlarz.zlokalizujmiasto.GeocodeResponse.GeocodeResponse;
import com.dsiedlarz.zlokalizujmiasto.ListFragment2.City.CityContent;
import com.dsiedlarz.zlokalizujmiasto.ListFragment2.ItemFragment;
import com.dsiedlarz.zlokalizujmiasto.ListFragment2.MyItemRecyclerViewAdapter;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {


    static public MyItemRecyclerViewAdapter adapter;
    private EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar()!=null)
        getSupportActionBar().setIcon(R.drawable.walking);


        cityName = (EditText) findViewById(R.id.cityName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                return true;

            default:
                break;
        }

        return true;
    }


    @Override
    public void onListFragmentInteraction(CityContent.DummyItem item) {
        Downloader downloader = new Downloader();


        if (isOnline()) {
            try {
                GeocodeResponse geocodeResponse = downloader.execute(item.content).get();
                Intent intent = new Intent(this, Main2Activity.class);


                if (geocodeResponse.getStatus().toString().compareTo("OK") != 0) {
                    Toast.makeText(this, geocodeResponse.getStatus(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, geocodeResponse.getResults().get(0).getFormatted_address(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("miejscowosc", geocodeResponse.getResults().get(0).getAddress_components().size() > 0 ?
                            geocodeResponse.getResults().get(0).getAddress_components().get(0).getLong_name().toString() : "-");
                    intent.putExtra("gmina", geocodeResponse.getResults().get(0).getAddress_components().size() - 4 > 0 ?
                            geocodeResponse.getResults().get(0).getAddress_components().get(geocodeResponse.getResults().get(0).getAddress_components().size() - 4).getLong_name().toString() : "-");
                    intent.putExtra("powiat", geocodeResponse.getResults().get(0).getAddress_components().size() - 3 > 0 ?
                            geocodeResponse.getResults().get(0).getAddress_components().get(geocodeResponse.getResults().get(0).getAddress_components().size() - 3).getLong_name().toString() : "-");
                    intent.putExtra("wojewodztwo", geocodeResponse.getResults().get(0).getAddress_components().size() - 2 > 0 ?
                            geocodeResponse.getResults().get(0).getAddress_components().get(geocodeResponse.getResults().get(0).getAddress_components().size() - 2).getLong_name().toString() : "-");
                    intent.putExtra("kraj", geocodeResponse.getResults().get(0).getAddress_components().get(geocodeResponse.getResults().get(0).getAddress_components().size() - 1).getLong_name().toString());


                }
                startActivity(intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Połącz sie najpierw z internetem", Toast.LENGTH_SHORT).show();

        }
    }

    public void addCity(View view) {
        if (cityName.getText().length() != 0) {
            boolean ok = true;

            for (CityContent.DummyItem d : CityContent.ITEMS) {
                if (d.content.compareToIgnoreCase(cityName.getText().toString()) == 0) ok = false;
            }
            if (!ok) {
                Toast.makeText(this, "Wymyśl coś nowego ", Toast.LENGTH_SHORT).show();
            } else {

                CityContent.addItem(CityContent.createDummyItem(cityName.getText().toString()));
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Dodano pomyślnie ", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "Wprowadz nazwę!", Toast.LENGTH_SHORT).show();
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
