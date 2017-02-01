package co.tecno.sersoluciones.intentapplication;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Contacto> {

    private ArrayList<Contacto> dataSet;
    private Context context;
    public CustomArrayAdapter(Context context, ArrayList<Contacto> objects) {
        super(context, 0, objects);
        dataSet = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater =(LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_item_2, parent, false);
        }
        ( (TextView)view.findViewById(R.id.text1))
                .setText(dataSet.get(position).getNombre());
        ((TextView)view.findViewById(R.id.text2))
                .setText(dataSet.get(position).getWeb());
        return view;
    }
}
