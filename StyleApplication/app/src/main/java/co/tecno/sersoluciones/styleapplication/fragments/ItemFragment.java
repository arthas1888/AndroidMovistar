package co.tecno.sersoluciones.styleapplication.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import co.tecno.sersoluciones.styleapplication.R;
import co.tecno.sersoluciones.styleapplication.databases.DBAdapter;
import co.tecno.sersoluciones.styleapplication.models.Point;
import co.tecno.sersoluciones.styleapplication.utilities.ConectionDetector;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ArrayList<Point> dataSet;
    private RecyclerView recyclerView;
    private DBAdapter dbAdapter;

    private MyItemRecyclerViewAdapter mAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSet = new ArrayList<>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        setHasOptionsMenu(true);
        dbAdapter = new DBAdapter(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        dbAdapter.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            Cursor cursor = dbAdapter.getAll();
            if (new ConectionDetector(getActivity()).isConnectingToInternet()){
                Toast.makeText(getActivity(), "Hay Internet", Toast.LENGTH_LONG).show();
                if (cursor.getCount() > 0){
                    updateMyList(cursor);
                }else getData();
            }else{
                if (cursor.getCount() > 0)
                    updateMyList(cursor);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateMyList(Cursor cursor) {
        while(cursor.moveToNext()){
            dataSet.add(new Point(cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3)
                    ));
        }

        mAdapter = new MyItemRecyclerViewAdapter(dataSet, mListener);
        recyclerView.setAdapter(mAdapter);
        recyclerView.invalidate();
    }

    public void getData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET,
                        "https://vesta.sersoluciones.com:9050/points/get_distance?lat=9999&lon=9999",
                        null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("MyDATA", response.toString());
                        Gson gson = new Gson();


                        dataSet = gson.fromJson(response.toString(),
                                new TypeToken<ArrayList<Point>>() {}.getType());
                        dbAdapter.insert(dataSet);
                        mAdapter = new MyItemRecyclerViewAdapter(dataSet, mListener);
                        recyclerView.setAdapter(mAdapter);
                        recyclerView.invalidate();
                        //mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        queue.add(jsObjRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            mAdapter = new MyItemRecyclerViewAdapter(dataSet, mListener);
            recyclerView.setAdapter(mAdapter);
            //registerForContextMenu(recyclerView);
            //mAdapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Point item);
    }
}
