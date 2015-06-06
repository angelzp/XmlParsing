package com.example.angel.xmlparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class vista_individual extends Activity {

    // XML node keys
    static final String KEY_N_SERIE = "n_serie";
    static final String KEY_MARCA = "marca";
    static final String KEY_MODELO = "modelo";
    static final String KEY_COLOR = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_individual);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous intent
        String n_serie = in.getStringExtra(KEY_N_SERIE);
        String marca = in.getStringExtra(KEY_MARCA);
        String modelo = in.getStringExtra(KEY_MODELO);
        String color = in.getStringExtra(KEY_COLOR);

        // Displaying all values on the screen
        TextView lblNserie = (TextView) findViewById(R.id.a_n_serie);
        TextView lblMarca = (TextView) findViewById(R.id.txt_a_marca);
        TextView lblModelo = (TextView) findViewById(R.id.txt_a_modelo);
        TextView lblColor = (TextView) findViewById(R.id.txt_a_color);

        lblNserie.setText(n_serie);
        lblMarca.setText(marca);
        lblModelo.setText(modelo);
        lblColor.setText(color);
    }
}
