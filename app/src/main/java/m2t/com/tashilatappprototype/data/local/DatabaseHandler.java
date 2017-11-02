package m2t.com.tashilatappprototype.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import m2t.com.tashilatappprototype.common.pojo.Operator;

/**
 * Created by laanaya on 10/30/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHandler.class.getSimpleName();
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TasshilatManager";

    // Oper table name
    private static final String TABLE_OPERATORS = "operators";

    // Oper Table Oper names
    private static final String KEY_ID_TAB = "id";
    private static final String KEY_ID = "id_oper";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_ISFAVORI = "is_favorite";
    private static final String KEY_CATEGORIE_ID = "categorie_id";
    private static final String KEY_CATEGORIE_NAME = "categorie_name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_OPERATORS + "("
                + KEY_ID_TAB + " INTEGER PRIMARY KEY,"
                + KEY_ID + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_ISFAVORI + " INTEGER,"
                + KEY_CATEGORIE_ID + " TEXT,"
                + KEY_CATEGORIE_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATORS);

        // Create tables again
        onCreate(db);
    }
    /* All CRUD(Create, Read, Update, Delete) Operators
     */

    // Adding new operator
    public void addOperator(Operator operator) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, operator.getID_OPER());
        values.put(KEY_NAME, operator.getName());
        values.put(KEY_DESC, operator.getDescription());
        values.put(KEY_ISFAVORI, operator.getFavorite());
        values.put(KEY_CATEGORIE_ID, operator.getCategorie_id());
        values.put(KEY_CATEGORIE_NAME, operator.getCategorie_name());

        // Inserting Row
        long id = db.insert(TABLE_OPERATORS, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New operator inserted into sqlite: " + id);
    }

    // Getting single contact
    Operator getOperator(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_OPERATORS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESC, KEY_ISFAVORI, KEY_CATEGORIE_ID, KEY_CATEGORIE_NAME }, KEY_ID + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Operator operator = new Operator(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5),
                cursor.getString(6)
        );
        // return contact
        Log.d(TAG, "Fetching operator from Sqlite: " + operator.toString());
        return operator;
    }

    // Getting All Contacts
    public List<Operator> getAllOperators() {
        List<Operator> operatorsList = new ArrayList<Operator>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OPERATORS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Operator operator = new Operator();
                operator.setID_OPER(cursor.getString(1));
                operator.setName(cursor.getString(2));
                operator.setDescription(cursor.getString(3));
                operator.setFavorite(cursor.getInt(4));
                operator.setCategorie_id(cursor.getString((5)));
                operator.setCategorie_name(cursor.getString(6));
                // Adding contact to list
                operatorsList.add(operator);
            } while (cursor.moveToNext());
        }
        Log.d(TAG, "Fetching operatorsList from Sqlite: " + operatorsList.toString());
        // return contact list
        return operatorsList;
    }

    // Deleting single contact
    public void deleteOperators(Operator operator) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_OPERATORS, KEY_ID + " = ?",
                new String[] {operator.getID_OPER()});
        db.close();
        Log.d(TAG, "Delete operator from Sqlite: " + id);
    }

    public void deleteAllOperators() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OPERATORS, null, null);
        db.close();
        Log.d(TAG, "Deleted all operators info from sqlite");
    }

    // Getting contacts Count
    public int getOperatorsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_OPERATORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        Log.e(TAG, "Operators count from Sqlite: " + cursor.getCount());
        // return count
        return cursor.getCount();
    }
}
