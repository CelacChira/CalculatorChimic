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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MasaMolaraFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_masa_molara, container, false);

        EditText entryFormula = view.findViewById(R.id.entry_formula);
        Button btnCalculeaza = view.findViewById(R.id.btn_calculeaza_masa);
        TextView rezultatLabel = view.findViewById(R.id.rezultat_masa);

        ChemData chemData = ((MainActivity) requireActivity()).chemData;

        btnCalculeaza.setOnClickListener(v -> {
            String formula = entryFormula.getText().toString().trim();

            // Acelasi regex ca in Python: ([A-Z][a-z]*)(\d*)
            Pattern pattern = Pattern.compile("([A-Z][a-z]*)(\\d*)");
            Matcher matcher = pattern.matcher(formula);

            double masaTotala = 0;
            Map<String, Double> masePartiale = new LinkedHashMap<>();

            while (matcher.find()) {
                String element = matcher.group(1);
                String cantitateStr = matcher.group(2);
                if (element.isEmpty()) continue;

                if (!chemData.maseAtomice.containsKey(element)) {
                    rezultatLabel.setText("Element necunoscut: " + element);
                    return;
                }

                int cantitate = cantitateStr.isEmpty() ? 1 : Integer.parseInt(cantitateStr);
                double masaElement = chemData.maseAtomice.get(element) * cantitate;
                masaTotala += masaElement;
                masePartiale.put(element, masaElement);
            }

            if (masaTotala == 0) {
                rezultatLabel.setText("Introduceți o formulă validă (ex: H2O)");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Masa molară: %.2f g/mol\n", masaTotala));
            for (Map.Entry<String, Double> entry : masePartiale.entrySet()) {
                String el = entry.getKey();
                double procent = (entry.getValue() / masaTotala) * 100;
                String tip = chemData.tipElement.getOrDefault(el, "Necunoscut");
                String stare = chemData.stareAgregare.getOrDefault(el, "Necunoscut");
                sb.append(String.format("%s: %.2f%%, %s (%s)\n", el, procent, tip, stare));
            }
            rezultatLabel.setText(sb.toString());
        });

        return view;
    }
}
