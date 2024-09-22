package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class SamsungOdysseyG6Activity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private SamsungOdysseyG6ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.samsung_odyssey_g6);  // Asegúrate de que el nombre del layout coincide con el XML

        viewPager2 = findViewById(R.id.viewPager);

        // Crear una lista de imágenes
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.samsung_odyssey_image1); // Asegúrate de que estos recursos existan
        images.add(R.drawable.samsung_odyssey_image2);
        images.add(R.drawable.samsung_odyssey_image3);
        images.add(R.drawable.samsung_odyssey_image4);

        // Configurar el adaptador con la lista de imágenes
        adapter = new SamsungOdysseyG6ImageAdapter(images);
        viewPager2.setAdapter(adapter);
    }
}

