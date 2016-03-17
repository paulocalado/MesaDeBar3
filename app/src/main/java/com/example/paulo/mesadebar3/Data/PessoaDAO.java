package com.example.paulo.mesadebar3.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.paulo.mesadebar3.Entitys.Pessoa;

import java.util.ArrayList;

/**
 * Created by Paulo on 19/01/2016.
 */
public class PessoaDAO {
    private DataBaseHandler helper;

    public PessoaDAO(Context ctx){
        helper = new DataBaseHandler(ctx);
    }

    public void inserirPessoa(Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_NOME, pessoa.getNome());// Nome da Pessoa
        values.put(DataBaseHandler.KEY_PARCIAL,pessoa.getParcial());
        values.put(DataBaseHandler.KEY_TOTAL,pessoa.getTotal());
        // Insere a linha
        db.insert(DataBaseHandler.TABLE_PESSOA, null, values);
        db.close(); // Closing database connection
    }

    public Pessoa recuperarPessoa(int id){

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(DataBaseHandler.TABLE_PESSOA, new String[]{DataBaseHandler.KEY_ID,
                        DataBaseHandler.KEY_NOME,DataBaseHandler.KEY_PARCIAL, DataBaseHandler.KEY_TOTAL}, DataBaseHandler.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pessoa pessoa = new Pessoa(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3));

        return pessoa;
    }

    public int atualizarPessoa(Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_NOME, pessoa.getNome());
        values.put(DataBaseHandler.KEY_PARCIAL,pessoa.getParcial());
        values.put(DataBaseHandler.KEY_TOTAL, pessoa.getTotal());

        return db.update(DataBaseHandler.TABLE_PESSOA, values, DataBaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(pessoa.getId()) });
    }

    public ArrayList<Pessoa> getTodasPessoas() {
        ArrayList<Pessoa> pessoaList = new ArrayList<Pessoa>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_PESSOA;

        SQLiteDatabase db = helper.getWritableDatabase();
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



    public ArrayList<Pessoa> getTodasPessoasNovo() {
        ArrayList<Pessoa> pessoaList = new ArrayList<Pessoa>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_PESSOA_ITEM;

        SQLiteDatabase db = helper.getWritableDatabase();
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
        String countQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_PESSOA;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deletaPessoa(Pessoa pessoa) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_PESSOA, DataBaseHandler.KEY_ID + " = ?",
                new String[]{String.valueOf(pessoa.getId())});
        db.close();
    }
}
