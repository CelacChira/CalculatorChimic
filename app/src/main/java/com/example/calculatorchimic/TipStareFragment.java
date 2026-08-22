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

public class TipStareFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_stare, container, false);

        EditText entryElement = view.findViewById(R.id.entry_element);
        Button btnVerifica = view.findViewById(R.id.btn_verifica);
        TextView rezultatLabel = view.findViewById(R.id.rezultat_tip_stare);

        ChemData chemData = ((MainActivity) requireActivity()).chemData;

        btnVerifica.setOnClickListener(v -> {
            String element = entryElement.getText().toString().trim();
            String tip = chemData.tipElement.getOrDefault(element, "Necunoscut");
            String stare = chemData.stareAgregare.getOrDefault(element, "Necunoscut");
            rezultatLabel.setText(element + " este: " + tip + "\nStare: " + stare);
        });

        return view;
    }
}
