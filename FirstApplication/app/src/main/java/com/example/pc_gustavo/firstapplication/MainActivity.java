package com.example.pc_gustavo.firstapplication;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    @SuppressLint("SetTextI18n")
    public void calcularBMI(View view){
        String peso = editTextPeso.getText().toString();
        String altura = editTextAltura.getText().toString();
        String msg = getResources().getString(R.string.warning);
        if (!(peso.isEmpty() && altura.isEmpty())){
            String expRegex = "\\d{1,3}";
            msg = "Valores no numericos";
            if (peso.matches(expRegex) && altura.matches(expRegex)){
                Log.d(TAG, "valores numericos");
                int height = Integer.parseInt(altura);
                int weight = Integer.parseInt(peso);
                msg = "El resultado es: " + (height*weight);
            }
            ((TextView)findViewById(R.id.textResult)).setText(msg);
        }else {
            Log.e(TAG, msg);
            ((TextView)findViewById(R.id.textResult)).setText(msg);
        }
    }
}
