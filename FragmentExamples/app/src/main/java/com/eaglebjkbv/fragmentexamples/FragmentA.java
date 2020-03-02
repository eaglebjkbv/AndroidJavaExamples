package com.eaglebjkbv.fragmentexamples;

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

public class FragmentA extends Fragment {
Button buttonMesajAktar;
TextView textViewMesaj;
EditText editTextMEsaj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_a_layout,container,false);
       buttonMesajAktar=view.findViewById(R.id.buttonAMessageAktar);
       textViewMesaj=view.findViewById(R.id.textviewAMessage);
       editTextMEsaj=view.findViewById(R.id.edittextFragA);
       buttonMesajAktar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               textViewMesaj.append(editTextMEsaj.getText().toString().trim());
           }
       });
       return view;
    }
}
