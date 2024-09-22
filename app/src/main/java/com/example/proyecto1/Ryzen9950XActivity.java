package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class Ryzen9950XActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Ryzen9950XImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ryzen9950x);  // Asegúrate de que el nombre del layout coincida con el XML correcto

        // Referencias a las vistas del XML
        viewPager2 = findViewById(R.id.viewPager);

        // Crear una lista de imágenes (estas son las que has añadido a /res/drawable)
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.ryzen1);
        images.add(R.drawable.ryzen2);
        images.add(R.drawable.ryzen3);
        images.add(R.drawable.ryzen4);
        // Añade más imágenes según sea necesario

        // Configurar el adaptador con la lista de imágenes
        adapter = new Ryzen9950XImageAdapter(images);
        viewPager2.setAdapter(adapter);
    }
}



