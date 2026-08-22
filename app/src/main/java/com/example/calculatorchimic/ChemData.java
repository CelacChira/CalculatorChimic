package com.example.calculatorchimic;

import android.content.Context;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Incarca datele din assets/Date.json (mase atomice, tip element, stare de agregare)
 * si le pune la dispozitie sub forma de Map-uri, la fel ca in scriptul Python.
 */
public class ChemData {

    public Map<String, Double> maseAtomice = new HashMap<>();
    public Map<String, String> tipElement = new HashMap<>();
    public Map<String, String> stareAgregare = new HashMap<>();

    public ChemData(Context context) {
        try {
            InputStream is = context.getAssets().open("Date.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject root = new JSONObject(json);

            JSONObject mase = root.getJSONObject("mase_atomice");
            Iterator<String> keysMase = mase.keys();
            while (keysMase.hasNext()) {
                String k = keysMase.next();
                maseAtomice.put(k, mase.getDouble(k));
            }

            JSONObject tip = root.getJSONObject("tip_element");
            Iterator<String> keysTip = tip.keys();
            while (keysTip.hasNext()) {
                String k = keysTip.next();
                tipElement.put(k, tip.getString(k));
            }

            JSONObject stare = root.getJSONObject("stare_agregare");
            Iterator<String> keysStare = stare.keys();
            while (keysStare.hasNext()) {
                String k = keysStare.next();
                stareAgregare.put(k, stare.getString(k));
            }

        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }
    }
}
