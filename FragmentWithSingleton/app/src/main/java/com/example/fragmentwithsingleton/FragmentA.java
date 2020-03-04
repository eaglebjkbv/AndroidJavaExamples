package com.example.fragmentwithsingleton;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
    DataSingleton dataSingleton;
    EditText editTextIsim;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_a_layout,container,false);
        dataSingleton=DataSingleton.getDataSingletonInstance();
        String  id= String.valueOf(dataSingleton.getId());
        TextView textViewid=view.findViewById(R.id.textviewId);

        textViewid.setText(id);
        editTextIsim=view.findViewById(R.id.edittextIsim);
        editTextIsim.setText(dataSingleton.getIsim());
        Log.d("Mesaj","Fragment A onCreateView");
        Button buttonTikla=view.findViewById(R.id.buttonTikla);
        buttonTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSingleton.setIsim(editTextIsim.getText().toString());
                MyListener myListener= (MyListener) getActivity();
                myListener.myListener();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //editTextIsim.setText(dataSingleton.getIsim());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("Mesaj","Fragmanet A Attached");
    }
}
