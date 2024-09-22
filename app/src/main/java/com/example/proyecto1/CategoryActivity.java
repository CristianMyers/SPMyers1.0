package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        TextView categoryTextView = findViewById(R.id.categoryTextView);

        // Obtiene el nombre de la categoría desde el Intent
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATEGORY_NAME");
        categoryTextView.setText(categoryName);
    }
}
