package com.example.pc_gustavo.firstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editTextPeso;
    private EditText editTextAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);

    }

    public void calcularBMI(View view){
        String peso = editTextPeso.getText().toString();
        String altura = editTextAltura.getText().toString();
        if (peso.isEmpty() || altura.isEmpty()){
            Log.e(TAG, "los campos de peso y altura estan vacios");
            return;
        }
        //int height = Integer.parseInt(altura);
        String expRegex = "\\d{1,3}";
        if (peso.matches(expRegex) && altura.matches(expRegex)){
            Log.d(TAG, "valores numericos");
        }
    }
}
