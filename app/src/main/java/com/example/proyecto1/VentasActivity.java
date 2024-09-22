package com.example.proyecto1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VentasActivity extends AppCompatActivity {

    private EditText nameField, lastNameField, emailField, phoneField, addressField, productField, rutField, saleNumberField;
    private Button registerSaleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        // Asignar las vistas
        nameField = findViewById(R.id.nameField);
        lastNameField = findViewById(R.id.lastNameField);
        emailField = findViewById(R.id.emailField);
        phoneField = findViewById(R.id.phoneField);
        addressField = findViewById(R.id.addressField);
        productField = findViewById(R.id.productField);
        rutField = findViewById(R.id.rutField);
        saleNumberField = findViewById(R.id.saleNumberField);
        registerSaleButton = findViewById(R.id.registerSaleButton);

        // Acción del botón de registrar venta
        registerSaleButton.setOnClickListener(view -> {
            String name = nameField.getText().toString();
            String lastName = lastNameField.getText().toString();
            String email = emailField.getText().toString();
            String phone = phoneField.getText().toString();
            String address = addressField.getText().toString();
            String product = productField.getText().toString();
            String rut = rutField.getText().toString();
            String saleNumber = saleNumberField.getText().toString();

            if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || product.isEmpty() || rut.isEmpty() || saleNumber.isEmpty()) {
                Toast.makeText(VentasActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí podrías agregar la lógica para guardar la venta en una base de datos o realizar alguna acción
                Toast.makeText(VentasActivity.this, "Venta registrada exitosamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
