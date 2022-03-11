package com.example.interviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class CountryDetailActivity extends AppCompatActivity {
    // Variables
    TextView txtCountryName, txtCapital, txtPopulation, txtArea;
    TextView txtRegion, txtSubRegion;
    String countryName, capital, population, area, region, subRegion;
    JSONArray capitalArray;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        // Instantiate
        txtCountryName = findViewById(R.id.txtCountryName);
        txtCapital =  findViewById(R.id.txtCapital);
        txtPopulation =  findViewById(R.id.txtPopulation);
        txtArea =  findViewById(R.id.txtArea);
        txtRegion =  findViewById(R.id.txtRegion);
        txtSubRegion =  findViewById(R.id.txtSubRegion);
        Intent intent = getIntent();


        //Get country name from Intent
        countryName = intent.getStringExtra("name");
        txtCountryName.setText(countryName);

        // Make API call
        url = getResources().getString(R.string.countries_uri) + countryName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject country = new JSONObject();
                    country = response.getJSONObject(0);
                    capitalArray = country.getJSONArray("capital");
                    capital = capitalArray.getString(0);
                    population = country.getString("population");
                    area = country.getString("area");
                    region = country.getString("region");
                    subRegion = country.getString("subregion");

                    displayInformation();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(CountryDetailActivity.this, "Failed to retrieve data!", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountryDetailActivity.this, "Failed to retrieve data!", Toast.LENGTH_SHORT).show();
            }
        });

        // Send request
        RequestSingleton.getInstance(CountryDetailActivity.this).addToRequestQueue(request);

    }

    private void displayInformation() {
        // Set text view to information
        txtCapital.setText(capital);
        txtPopulation.setText(population);
        txtArea.setText(area);
        txtRegion.setText(region);
        txtSubRegion.setText(subRegion);
    }
}