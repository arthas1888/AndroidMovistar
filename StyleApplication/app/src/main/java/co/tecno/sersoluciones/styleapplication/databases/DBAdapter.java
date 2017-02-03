package co.tecno.sersoluciones.styleapplication.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import co.tecno.sersoluciones.styleapplication.models.Point;
import co.tecno.sersoluciones.styleapplication.utilities.Constantes;

import static co.tecno.sersoluciones.styleapplication.utilities.Constantes.DB_NAME;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes.DB_VERSION;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes.TABLE_NAME;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes._ADDRESS;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes._ID;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes._NAME;
import static co.tecno.sersoluciones.styleapplication.utilities.Constantes._PK;

public class DBAdapter extends SQLiteOpenHelper{

    private SQLiteDatabase _db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        _db = this.getWritableDatabase();
    }

    public void insert(ArrayList<Point> pointArrayList){
        for (Point p : pointArrayList){
            ContentValues cv = new ContentValues();
            cv.put(_PK, p.getId());
            cv.put(_NAME, p.getNombre());
            cv.put(_ADDRESS, p.getDireccion());
            _db.insert(TABLE_NAME, null, cv);
        }
    }

    public void update(Point p, int id){
        ContentValues cv = new ContentValues();
        cv.put(_PK, p.getId());
        cv.put(_NAME, p.getNombre());
        cv.put(_ADDRESS, p.getDireccion());
        _db.update(TABLE_NAME, cv, _ID + " = " + id, null);
    }

    public void delete(int id){
        _db.delete(TABLE_NAME, _ID + " = " + id, null);
    }

    public Cursor getAll(){
        return _db.query(TABLE_NAME, Constantes.cols, null, null, null, null, null);
    }

    public Cursor get(int id){
        return _db.query(TABLE_NAME, Constantes.cols, _ID + " = " + id, null, null, null, null);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID  + " integer primary key autoincrement, " +
                _PK  + " integer, " +
                _NAME + " text, " +
                _ADDRESS + " text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
