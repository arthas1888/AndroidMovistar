package co.tecno.sersoluciones.intentapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int TELEFONO = 0;
    public static final int WEB = 1;
    private TextView telefonoTextView;
    private TextView webTextView;
    public static final String KEY_OBJ = "obj";
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            contacto = (Contacto)extras.getSerializable(KEY_OBJ);

            TextView nombreTextView = (TextView) findViewById(R.id.textViewNombre);
            telefonoTextView = (TextView) findViewById(R.id.textViewTelefono);
            webTextView = (TextView) findViewById(R.id.textViewWeb);

            assert contacto != null;
            nombreTextView.setText(contacto.getNombre());
            telefonoTextView.setText(String.valueOf(contacto.getTelefono()));
            webTextView.setText(contacto.getWeb());

            telefonoTextView.setOnClickListener(this);
            webTextView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.textViewTelefono:
                intent.putExtra("value", contacto.getTelefono());
                setResult(TELEFONO, intent);
                break;
            case R.id.textViewWeb:
                intent.putExtra("value", contacto.getWeb());
                setResult(WEB, intent);
                break;
        }
        finish();
    }
}
