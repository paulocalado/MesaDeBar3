package com.example.paulo.mesadebar3.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 13/01/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 14;
    private static final String DATABASE_NAME = "MesaDeBar";

    // Pessoa
    public static final String TABLE_PESSOA = "pessoa";
    public static final String KEY_ID = "id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_TOTAL = "total";
    public static final String KEY_PARCIAL = "parcial";
    // Item
    public static final String TABLE_ITEM = "item";
    public static final String KEY_NOME_ITEM = "nome";
    public static final String KEY_PRECO = "preco";
    public static final String KEY_QUANTIDADE = "quantidade";

    //Pessa e Item
    public static final String TABLE_PESSOA_ITEM = "pessoa_item";
    public static final String KEY_ID_ITEM = "id_item";
    public static final String KEY_ID_PESSOA = "id_pessoa";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_PESSOA_TABLE = "CREATE TABLE " + TABLE_PESSOA + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME + " TEXT,"
            + KEY_PARCIAL + " DOUBLE," + KEY_TOTAL + " DOUBLE" + ")";

    private static final String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME_ITEM + " TEXT,"
            + KEY_PRECO + " DOUBLE," + KEY_QUANTIDADE + " INTEGER" + ")";

    private static final String CREATE_TABLE_PESSOA_ITEM = "CREATE TABLE "
            + TABLE_PESSOA_ITEM + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ID_ITEM + " INTEGER," + KEY_ID_PESSOA + " INTEGER" + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PESSOA_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_TABLE_PESSOA_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESSOA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESSOA_ITEM);
        // Create tables again
        onCreate(db);
    }

  /*  public void inserirPessoa(Pessoa pessoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, pessoa.getNome());// Nome da Pessoa
        values.put(KEY_PARCIAL,pessoa.getParcial());
        values.put(KEY_TOTAL,pessoa.getTotal());
        // Insere a linha
        db.insert(TABLE_PESSOA, null, values);
        db.close(); // Closing database connection
    }

    public Pessoa recuperarPessoa(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PESSOA, new String[]{KEY_ID,
                        KEY_NOME,KEY_PARCIAL, KEY_TOTAL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3));

        return pessoa;
    }

    public int atualizarPessoa(Pessoa pessoa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, pessoa.getNome());
        values.put(KEY_PARCIAL,pessoa.getParcial());
        values.put(KEY_TOTAL, pessoa.getTotal());

        return db.update(TABLE_PESSOA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pessoa.getId()) });
    }


    // Getting All Contacts
    public ArrayList<Pessoa> getTodasPessoas() {
        ArrayList<Pessoa> pessoaList = new ArrayList<Pessoa>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PESSOA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(Integer.parseInt(cursor.getString(0)));
                pessoa.setNome(cursor.getString(1));
                pessoa.setParcial(cursor.getDouble(2));
                pessoa.setTotal(cursor.getDouble(3));

                // Adding contact to list
                pessoaList.add(pessoa);
            } while (cursor.moveToNext());
        }

        // return contact list
        return pessoaList;
    }

    public int getNumeroPessoas() {
        String countQuery = "SELECT  * FROM " + TABLE_PESSOA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Deleting single contact
    public void deletaPessoa(Pessoa pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PESSOA, KEY_ID + " = ?",
                new String[]{String.valueOf(pessoa.getId()) });
        db.close();
    }
*/
}
