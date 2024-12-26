package com.example.proyecto1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView; // Importa SearchView
import android.view.View; // Para la clase View
import android.view.ViewGroup; // Para el ViewGroup
import android.widget.ArrayAdapter; // Para el ArrayAdapter
import android.widget.ImageView; // Para el ImageView
import android.widget.TextView; // Para el TextView
import android.widget.Filterable; // Para implementar Filterable
import android.widget.Filter; // Para el filtro
import java.util.ArrayList;

public class RamActivity extends AppCompatActivity {

    private ListView listViewRam;
    private SearchView searchViewRam; // Declara el SearchView
    private RamAdapter adapter;
    private ArrayList<RamModule> ramModules; // Almacena la lista original de RAM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram);

        listViewRam = findViewById(R.id.listViewRam);
        searchViewRam = findViewById(R.id.searchViewRam); // Encuentra el SearchView

        // Crear lista de RAM (ejemplo)
        ramModules = new ArrayList<>();
        ramModules.add(new RamModule("Corsair Vengeance LPX 16GB", "$42900.00", R.drawable.corsair_vengeance_lpx_16gb));
        ramModules.add(new RamModule("G.Skill Ripjaws V 16GB", "$89990.00", R.drawable.gskill_ripjaws));
        ramModules.add(new RamModule("Kingston HyperX Fury 16GB", "$113661.00", R.drawable.kingston_hyperx));
        ramModules.add(new RamModule("Crucial Ballistix 16GB", "$69990.00", R.drawable.crucial_ballistix));
        ramModules.add(new RamModule("Patriot Viper Steel 16GB", "$69900.00", R.drawable.patriot_viper));
        ramModules.add(new RamModule("Corsair Dominator Platinum 16GB", "$109990.00", R.drawable.corsair_dominator));
        ramModules.add(new RamModule("G.Skill Trident Z 16GB", "$89990.00", R.drawable.gskill_trident));
        ramModules.add(new RamModule("Team T-Force Vulcan 16GB", "$55216.00", R.drawable.team_t_force));
        ramModules.add(new RamModule("HyperX Fury Beast RGB 32GB", "$69890.00", R.drawable.hyperx_fury));
        ramModules.add(new RamModule("Crucial Ballistix Max 32GB", "$110990.00", R.drawable.crucial_ballistix_max));

        // Configurar adaptador y asignarlo al ListView
        adapter = new RamAdapter(ramModules);
        listViewRam.setAdapter(adapter);

        // Configurar el SearchView para filtrar resultados
        searchViewRam.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    // Clase RamModule para almacenar la información de cada módulo de RAM
    private class RamModule {
        String name;
        String price;
        int imageResId;

        RamModule(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado para mostrar cada módulo de RAM en el ListView
    private class RamAdapter extends ArrayAdapter<RamModule> implements Filterable {
        ArrayList<RamModule> originalList; // Lista original de RAM
        ArrayList<RamModule> filteredList; // Lista filtrada

        RamAdapter(ArrayList<RamModule> ramModules) {
            super(RamActivity.this, R.layout.ram_item, ramModules);
            this.originalList = new ArrayList<>(ramModules);
            this.filteredList = ramModules; // Inicializa la lista filtrada
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.ram_item, parent, false);
            }

            RamModule currentRamModule = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewRam);
            TextView nameTextView = convertView.findViewById(R.id.textViewRamName);
            TextView priceTextView = convertView.findViewById(R.id.textViewRamPrice);

            imageView.setImageResource(currentRamModule.imageResId);
            nameTextView.setText(currentRamModule.name);
            priceTextView.setText(currentRamModule.price);

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredList.size(); // Retorna el tamaño de la lista filtrada
        }

        @Override
        public RamModule getItem(int position) {
            return filteredList.get(position); // Retorna el elemento de la lista filtrada
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String filterString = constraint.toString().toLowerCase();
                    FilterResults results = new FilterResults();
                    ArrayList<RamModule> filteredResults = new ArrayList<>();

                    // Filtrar la lista
                    for (RamModule module : originalList) {
                        if (module.name.toLowerCase().contains(filterString)) {
                            filteredResults.add(module);
                        }
                    }

                    results.values = filteredResults;
                    results.count = filteredResults.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList = (ArrayList<RamModule>) results.values;
                    notifyDataSetChanged(); // Notificar cambios al adaptador
                }
            };
        }
    }
}

