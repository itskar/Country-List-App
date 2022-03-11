package com.example.interviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class CountryActivity extends AppCompatActivity {
    //Variables
    ListView lstCountries;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        // Fill ListView with countries
        lstCountries = findViewById(R.id.lstCountries);
        arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.countries_array) );
        lstCountries.setAdapter(arrayAdapter);

        // Launch detail activity on clicking the list item
        lstCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CountryDetailActivity.class );
                String countryName = (String) lstCountries.getItemAtPosition(i);
                intent.putExtra("name", countryName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate menu
        getMenuInflater().inflate(R.menu.nav_menu, menu);

        // Add the searchbar
        MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type a country");

        // Change text event listeners
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}