package co.tecno.sersoluciones.listapplication;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListaActivity extends ListActivity {

    String[] ciudades = {"Bogota", "Medellin", "Cali", "Pereira", "Bucaramanga",
    "Cartagena", "Barranqulla", "Pasto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ciudades);
        setListAdapter(arrayAdapter);

    }
}
