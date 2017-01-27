package co.tecno.sersoluciones.listapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import co.tecno.sersoluciones.listapplication.models.Ciudad;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<Ciudad> ciudades;
    private ArrayList<String> nombresCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ciudades = new ArrayList<>();
        nombresCiudades = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ciudades.add(new Ciudad("Pereira", "Risaralda", 550000, 1463));
                updateList();
            }
        });
        ciudades.add(new Ciudad("Bogota", "Bogota", 110111, 2600));
        ciudades.add(new Ciudad("Medellin", "Antioquia", 240000, 1500));
        ciudades.add(new Ciudad("Cali", "Valle del Cauca", 330000, 800));
        for (Ciudad ciudad: ciudades) {
            nombresCiudades.add(ciudad.getNombre());
        }
        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, nombresCiudades);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ciudades.remove(position);
                updateList();
                return false;
            }
        });
    }

    private void updateList() {
        nombresCiudades.clear();
        for (Ciudad ciudad: ciudades) {
            nombresCiudades.add(ciudad.getNombre());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
