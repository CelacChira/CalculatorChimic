package com.example.calculatorchimic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConcentratieFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_concentratie, container, false);

        EditText entryMasa = view.findViewById(R.id.entry_masa);
        EditText entryVolum = view.findViewById(R.id.entry_volum);
        EditText entryMasaMolara = view.findViewById(R.id.entry_masa_molara);
        Button btnCalculeaza = view.findViewById(R.id.btn_calculeaza_concentratie);
        TextView rezultatLabel = view.findViewById(R.id.rezultat_concentratie);

        btnCalculeaza.setOnClickListener(v -> {
            try {
                double masa = Double.parseDouble(entryMasa.getText().toString());
                double volum = Double.parseDouble(entryVolum.getText().toString());
                double masaMolara = Double.parseDouble(entryMasaMolara.getText().toString());

                if (volum == 0 || masaMolara == 0) {
                    rezultatLabel.setText("Volumul și masa molară nu pot fi 0");
                    return;
                }

                double concentratie = masa / (volum * masaMolara);
                rezultatLabel.setText(String.format("Concentrația molară: %.3f mol/L", concentratie));

            } catch (NumberFormatException e) {
                rezultatLabel.setText("Completați toate cele 3 câmpuri cu numere valide");
            }
        });

        return view;
    }
}
