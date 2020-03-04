package com.example.fragmentwithsingletonandmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment implements View.OnFocusChangeListener {

    Button buttonHesapla;
    EditText edittextAKenari,edittextBKenari,edittextCKenari;
    TextView textViewHacimSonuc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_b_layout,container,false);
        final ModelSingleton modelSingleton=ModelSingleton.getInstance();
        buttonHesapla=v.findViewById(R.id.buttonHesapla);
        edittextAKenari=v.findViewById(R.id.edittextAKenari);
        edittextBKenari=v.findViewById(R.id.edittextBKenari);
        edittextCKenari=v.findViewById(R.id.edittextCKenari);
        textViewHacimSonuc=v.findViewById(R.id.textviewHacimSonuc);
        edittextAKenari.setText(String.valueOf(modelSingleton.aKenari));
        edittextBKenari.setText(String.valueOf(modelSingleton.bKenari));
        edittextCKenari.setText(String.valueOf(modelSingleton.cKenari));
        edittextAKenari.setOnFocusChangeListener(this);
        edittextBKenari.setOnFocusChangeListener(this);
        edittextCKenari.setOnFocusChangeListener(this);
        textViewHacimSonuc.setText("Sonuc= "+modelSingleton.hacimSonuc);
        buttonHesapla.setEnabled(false);

        buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelSingleton.aKenari=Integer.parseInt(edittextAKenari.getText().toString());
                modelSingleton.bKenari=Integer.parseInt(edittextBKenari.getText().toString());
                modelSingleton.cKenari=Integer.parseInt(edittextCKenari.getText().toString());
                modelSingleton.hacimSonuc=modelSingleton.aKenari*modelSingleton.bKenari*modelSingleton.cKenari;
                textViewHacimSonuc.setText("Sonuc= "+modelSingleton.hacimSonuc);




            }
        });


        return v;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        buttonHesapla.setEnabled(true);
    }
}
