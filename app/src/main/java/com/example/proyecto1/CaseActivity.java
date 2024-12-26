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

public class CaseActivity extends AppCompatActivity {

    private ListView listViewCases;
    private SearchView searchViewCases;
    private CaseAdapter adapter;
    private ArrayList<Case> caseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

        listViewCases = findViewById(R.id.listViewCases);
        searchViewCases = findViewById(R.id.searchViewCases);

        // Crear lista de gabinetes
        caseList = new ArrayList<>();
        caseList.add(new Case("NZXT H9 Flow White", "$249990.00", R.drawable.nzxt_h9_flow_white));
        caseList.add(new Case("Cooler Master Cosmos C700P Black Edition", "$395875.00", R.drawable.cooler_master_cosmos_c700p_black));
        caseList.add(new Case("Cougar DarkBlader X7 Midnight Green", "$59990.00", R.drawable.cougar_darkblader_x7_midnight_green));
        caseList.add(new Case("DeepCool CC560 V2", "$54990.00", R.drawable.deepcool_cc560_v2));
        caseList.add(new Case("WJ Coolman ORIGIN II Black", "$84990.00", R.drawable.wj_coolman_origin_ii_black));
        caseList.add(new Case("Airflow Hurricane - Black", "$109000.00", R.drawable.airflow_hurricane_black));
        caseList.add(new Case("Thermaltake Core P3 TG Pro", "$139997.00", R.drawable.thermaltake_core_p3_tg_pro));
        caseList.add(new Case("Lian Li PC-O11 Dynamic XL ROG Certified - White", "$299990.00", R.drawable.lian_li_pc_o11_dynamic_xl_rog_white));
        caseList.add(new Case("MSI MPG Gungnir 111R", "$119900.00", R.drawable.msi_mpg_gungnir_111r));
        caseList.add(new Case("Fractal Design Torrent - White", "$369990.00", R.drawable.fractal_design_torrent_white));

        // Configurar el adaptador y asignarlo al ListView
        adapter = new CaseAdapter(caseList);
        listViewCases.setAdapter(adapter);

        // Configurar el SearchView para filtrar resultados
        searchViewCases.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    // Clase Case para almacenar la información de cada gabinete
    private class Case {
        String name;
        String price;
        int imageResId;

        Case(String name, String price, int imageResId) {
            this.name = name;
            this.price = price;
            this.imageResId = imageResId;
        }
    }

    // Adaptador personalizado para mostrar cada gabinete en el ListView
    private class CaseAdapter extends ArrayAdapter<Case> implements Filterable {
        ArrayList<Case> originalList;
        ArrayList<Case> filteredList;

        CaseAdapter(ArrayList<Case> cases) {
            super(CaseActivity.this, R.layout.case_item, cases);
            this.originalList = new ArrayList<>(cases);
            this.filteredList = cases;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.case_item, parent, false);
            }

            Case currentCase = getItem(position);

            ImageView imageView = convertView.findViewById(R.id.imageViewCase);
            TextView nameTextView = convertView.findViewById(R.id.textViewCaseName);
            TextView priceTextView = convertView.findViewById(R.id.textViewCasePrice);

            imageView.setImageResource(currentCase.imageResId);
            nameTextView.setText(currentCase.name);
            priceTextView.setText(currentCase.price);

            return convertView;
        }

        @Override
        public int getCount() {
            return filteredList.size();
        }

        @Override
        public Case getItem(int position) {
            return filteredList.get(position);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String filterString = constraint.toString().toLowerCase();
                    FilterResults results = new FilterResults();
                    ArrayList<Case> filteredResults = new ArrayList<>();

                    // Filtrar la lista de gabinetes
                    for (Case caseItem : originalList) {
                        if (caseItem.name.toLowerCase().contains(filterString)) {
                            filteredResults.add(caseItem);
                        }
                    }

                    results.values = filteredResults;
                    results.count = filteredResults.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList = (ArrayList<Case>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
