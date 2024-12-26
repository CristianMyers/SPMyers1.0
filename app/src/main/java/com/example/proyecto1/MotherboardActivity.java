package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;

public class MotherboardActivity extends AppCompatActivity {

    private ListView listViewMotherboard;
    private SearchView searchViewMotherboard;
    private MotherboardAdapter adapter;
    private ArrayList<Motherboard> motherboardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motherboard); // Layout XML para MotherboardActivity

        listViewMotherboard = findViewById(R.id.listViewMotherboard);
        searchViewMotherboard = findViewById(R.id.searchViewMotherboard);

        // Crear lista de placas madre
        motherboardList = new ArrayList<>();
        motherboardList.add(new Motherboard("ASUS ROG Strix B550-F", "$227900.00", R.drawable.asus_rog_strix_b550f));
        motherboardList.add(new Motherboard("MSI MPG X570 Gaming Plus", "$199990.00", R.drawable.msi_mpg_x570_gaming_plus));
        motherboardList.add(new Motherboard("Gigabyte Z590 AORUS Elite", "$259990.00", R.drawable.gigabyte_z590_aorus_elite));
        motherboardList.add(new Motherboard("ASRock B450M PRO4", "$77900.00", R.drawable.asrock_b450m_pro4));
        motherboardList.add(new Motherboard("ASUS TUF Gaming B450M-PLUS2", "$94900.00", R.drawable.asus_tuf_gaming_b450m_pro));
        motherboardList.add(new Motherboard("MSI Z490-A Pro", "$275434.00", R.drawable.msi_z490_a_pro));
        motherboardList.add(new Motherboard("Gigabyte B450 AORUS Elite", "$144990.00", R.drawable.gigabyte_b450_aorus_elite));
        motherboardList.add(new Motherboard("ASRock Z590 Phantom Gaming", "$200000.00", R.drawable.asrock_z590_phantom_gaming));
        motherboardList.add(new Motherboard("ASUS PRIME B560-PLUS", "$145990.00", R.drawable.asus_prime_b560_plus));
        motherboardList.add(new Motherboard("MSI MAG B550 TOMAHAWK", "$189696.00", R.drawable.msi_mag_b550_tomahawk));

        // Configurar el adaptador y asignarlo al ListView
        adapter = new MotherboardAdapter(motherboardList);
        listViewMotherboard.setAdapter(adapter);

        // Configurar el SearchView para filtrar resultados
        searchViewMotherboard.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    // Clase Motherboard para almacenar la información de cada placa madre
    private class Motherboard {
        String name;
        String price;
        int imageResId;

        Motherboard(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado para mostrar cada placa madre en el ListView
    private class MotherboardAdapter extends ArrayAdapter<Motherboard> implements Filterable {
        ArrayList<Motherboard> originalList;
        ArrayList<Motherboard> filteredList;

        MotherboardAdapter(ArrayList<Motherboard> motherboards) {
            super(MotherboardActivity.this, R.layout.motherboard_item, motherboards);
            this.originalList = new ArrayList<>(motherboards);
            this.filteredList = motherboards;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.motherboard_item, parent, false);
            }

            Motherboard currentMotherboard = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewMotherboard);
            TextView nameTextView = convertView.findViewById(R.id.textViewMotherboardName);
            TextView priceTextView = convertView.findViewById(R.id.textViewMotherboardPrice);

            imageView.setImageResource(currentMotherboard.imageResId);
            nameTextView.setText(currentMotherboard.name);
            priceTextView.setText(currentMotherboard.price);

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public Motherboard getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String filterString = constraint.toString().toLowerCase();
                    FilterResults results = new FilterResults();
                    ArrayList<Motherboard> filteredResults = new ArrayList<>();

                    for (Motherboard board : originalList) {
                        if (board.name.toLowerCase().contains(filterString)) {
                            filteredResults.add(board);
                        }
                    }

                    results.values = filteredResults;
                    results.count = filteredResults.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList = (ArrayList<Motherboard>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
