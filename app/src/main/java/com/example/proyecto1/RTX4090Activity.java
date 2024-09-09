package com.example.proyecto1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RTX4090Activity extends AppCompatActivity {

    private ImageView rtxImage;
    private TextView rtxName;
    private TextView rtxDescription;
    private TextView rtxSpecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtx4090);  // Asegúrate de que el nombre del layout coincida con el XML

        // Referencias a las vistas del XML
        rtxImage = findViewById(R.id.rtxImage);
        rtxName = findViewById(R.id.rtxName);
        rtxDescription = findViewById(R.id.rtxDescription);
        rtxSpecs = findViewById(R.id.rtxSpecs);

        // Establecer los valores para las vistas
        rtxName.setText("NVIDIA GeForce RTX 4090");

        // Establecer texto programáticamente (puedes también hacerlo en el XML)
        rtxDescription.setText(getString(R.string.rtx_description));
        rtxSpecs.setText(getString(R.string.rtx_specs));
    }
}

