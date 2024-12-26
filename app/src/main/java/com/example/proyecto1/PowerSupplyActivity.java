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

public class PowerSupplyActivity extends AppCompatActivity {

    private ListView listViewPowerSupply;
    private SearchView searchViewPowerSupply;
    private PowerSupplyAdapter adapter;
    private ArrayList<PowerSupply> powerSupplyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_supply);

        listViewPowerSupply = findViewById(R.id.listViewPowerSupply);
        searchViewPowerSupply = findViewById(R.id.searchViewPowerSupply);

        // Crear lista de fuentes de poder
        powerSupplyList = new ArrayList<>();
        powerSupplyList.add(new PowerSupply("Corsair RM850x", "$149900.00", R.drawable.corsair_rm850x));
        powerSupplyList.add(new PowerSupply("EVGA SuperNOVA 750 G5", "$189990.00", R.drawable.evga_supernova_750));
        powerSupplyList.add(new PowerSupply("Seasonic PRIME TX-1000", "$429990.00", R.drawable.seasonic_prime_tx1000));
        powerSupplyList.add(new PowerSupply("Cooler Master V750", "$183008.00", R.drawable.cooler_master_v750));
        powerSupplyList.add(new PowerSupply("Thermaltake Toughpower GF1 750W", "$119990.00", R.drawable.thermaltake_toughpower_gf1_750));
        powerSupplyList.add(new PowerSupply("ASUS ROG Strix 850W", "$209000.00", R.drawable.asus_rog_strix_850));
        powerSupplyList.add(new PowerSupply("Gigabyte AORUS P850W", "$174670.00", R.drawable.gigabyte_aorus_p850));
        powerSupplyList.add(new PowerSupply("FSP Hydro G Pro 850W", "$139990.00", R.drawable.fsp_hydro_g_pro_850));
        powerSupplyList.add(new PowerSupply("Be Quiet! Dark Power Pro 12 1200W", "$259990.00", R.drawable.be_quiet_dark_power_pro_12));
        powerSupplyList.add(new PowerSupply("XPG Core Reactor 850W", "$124990.00", R.drawable.xpg_core_reactor_850));

        // Configurar el adaptador y asignarlo al ListView
        adapter = new PowerSupplyAdapter(powerSupplyList);
        listViewPowerSupply.setAdapter(adapter);

        // Configurar el SearchView para filtrar resultados
        searchViewPowerSupply.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    // Clase PowerSupply para almacenar la información de cada fuente de poder
    private class PowerSupply {
        String name;
        String price;
        int imageResId;

        PowerSupply(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado para mostrar cada fuente de poder en el ListView
    private class PowerSupplyAdapter extends ArrayAdapter<PowerSupply> implements Filterable {
        ArrayList<PowerSupply> originalList;
        ArrayList<PowerSupply> filteredList;

        PowerSupplyAdapter(ArrayList<PowerSupply> powerSupplies) {
            super(PowerSupplyActivity.this, R.layout.power_supply_item, powerSupplies);
            this.originalList = new ArrayList<>(powerSupplies);
            this.filteredList = powerSupplies;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.power_supply_item, parent, false);
            }

            PowerSupply currentPowerSupply = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewPowerSupply);
            TextView nameTextView = convertView.findViewById(R.id.textViewPowerSupplyName);
            TextView priceTextView = convertView.findViewById(R.id.textViewPowerSupplyPrice);

            imageView.setImageResource(currentPowerSupply.imageResId);
            nameTextView.setText(currentPowerSupply.name);
            priceTextView.setText(currentPowerSupply.price);

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public PowerSupply getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String filterString = constraint.toString().toLowerCase();
                    FilterResults results = new FilterResults();
                    ArrayList<PowerSupply> filteredResults = new ArrayList<>();

                    // Filtrar la lista de fuentes de poder
                    for (PowerSupply supply : originalList) {
                        if (supply.name.toLowerCase().contains(filterString)) {
                            filteredResults.add(supply);
                        }
                    }

                    results.values = filteredResults;
                    results.count = filteredResults.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList = (ArrayList<PowerSupply>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}




