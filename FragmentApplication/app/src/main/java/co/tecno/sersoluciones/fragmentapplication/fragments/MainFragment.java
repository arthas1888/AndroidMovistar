package co.tecno.sersoluciones.fragmentapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.tecno.sersoluciones.fragmentapplication.MainActivity;
import co.tecno.sersoluciones.fragmentapplication.R;

public class MainFragment extends Fragment{

    private EditText editTextNum1, editTextNum2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        editTextNum1 = (EditText) view.findViewById(R.id.editTextNum1);
        editTextNum2 = (EditText) view.findViewById(R.id.editTextNum2);
        Button button = (Button) view.findViewById(R.id.buttonSum);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = editTextNum1.getText().toString();
                String num2 = editTextNum2.getText().toString();
                sumar(num1, num2);
            }
        });

        return view;
    }

    private void sumar(String num1, String num2) {
        if (validateNum(num1, num2)) {
            ((MainActivity) getActivity())
                    .replaceFragment(MainActivity
                            .newInstance(Integer.valueOf(num1), Integer.valueOf(num2)));
        }else{
            editTextNum2.requestFocus();
            editTextNum2.setError("Revice por favor el contenido de los EditText para seguir");
        }
    }

    private boolean validateNum(String a, String b){
        String expRegex = "\\d+";
        return (!a.isEmpty() && !b.isEmpty()
                && ( a.matches(expRegex) && b.matches(expRegex)) );
    }
}
