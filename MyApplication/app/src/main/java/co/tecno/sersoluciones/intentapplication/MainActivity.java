package co.tecno.sersoluciones.intentapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacto> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayList.add(new Contacto("Google", 1111, "http://www.google.com/"));
        arrayList.add(new Contacto("Twitter", 2222, "http://www.twitter.com/"));
        arrayList.add(new Contacto("Facebook", 3333, "http://www.facebook.com/"));
        arrayList.add(new Contacto("Yahoo", 4444, "http://www.yahoo.com/"));
        CustomArrayAdapter arrayAdapter = new CustomArrayAdapter(this,
                arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Contacto contacto = arrayList.get(position);
                intent.putExtra("obj", contacto);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        switch (resultCode) {
            case DetailsActivity.TELEFONO:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + extras.getInt("value"))));
                break;
            case DetailsActivity.WEB:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(extras.getString("value"))));
                break;
        }

    }

    public void launchNewApp(View view) {

        Intent intent = new Intent();
        intent.setAction("android.intent.test.LEARN");
        intent.addCategory("android.intent.category.DEFAULT");

        startActivity(intent);
    }
}
