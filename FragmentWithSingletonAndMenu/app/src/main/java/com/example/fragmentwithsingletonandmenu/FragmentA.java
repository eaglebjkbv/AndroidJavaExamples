package com.example.fragmentwithsingletonandmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment implements View.OnFocusChangeListener {

    Button buttonHesapla;
    EditText edittextAKenari,edittextBKenari;
    TextView textViewAlanSonuc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_a_layout,container,false);
        final ModelSingleton modelSingleton=ModelSingleton.getInstance();
        buttonHesapla=v.findViewById(R.id.buttonHesapla);
        edittextAKenari=v.findViewById(R.id.edittextAKenari);
        edittextBKenari=v.findViewById(R.id.edittextBKenari);
        textViewAlanSonuc=v.findViewById(R.id.textviewAlanSonuc);
        edittextAKenari.setText(String.valueOf(modelSingleton.aKenari));
        edittextBKenari.setText(String.valueOf(modelSingleton.bKenari));
        textViewAlanSonuc.setText("Sonuc="+modelSingleton.alanSonuc);
        buttonHesapla.setEnabled(false);

        edittextBKenari.setOnFocusChangeListener(this);
        edittextAKenari.setOnFocusChangeListener(this);

        buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelSingleton.aKenari=Integer.parseInt(edittextAKenari.getText().toString());
                modelSingleton.bKenari=Integer.parseInt(edittextBKenari.getText().toString());
                modelSingleton.alanSonuc=modelSingleton.aKenari*modelSingleton.bKenari;
                textViewAlanSonuc.setText("Sonuç= "+modelSingleton.alanSonuc);

            }
        });


        return v;
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        buttonHesapla.setEnabled(true);
    }
}
