package com.example.paulo.mesadebar3.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.paulo.mesadebar3.Entitys.Item;
import com.example.paulo.mesadebar3.Entitys.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 21/01/2016.
 */
public class ItemDAO {
    private DataBaseHandler helper;

    public ItemDAO(Context ctx){
        helper = new DataBaseHandler(ctx);
    }

    public void inserirItem(Item item){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_NOME, item.getNomeItem());// Nome da Pessoa
        values.put(DataBaseHandler.KEY_PRECO,item.getPreco());
        values.put(DataBaseHandler.KEY_QUANTIDADE,item.getQuantidade());
        // Insere a linha
        db.insert(DataBaseHandler.TABLE_ITEM, null, values);
        db.close(); // Closing database connection
    }

    public void inserirItemPessoa(Item item,Pessoa pessoa){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_ID_ITEM, item.getIdItem());
        values.put(DataBaseHandler.KEY_ID_PESSOA, pessoa.getId());

        db.insert(DataBaseHandler.TABLE_PESSOA_ITEM,null,values);
        db.close();
    }


    public Item recuperarItem(int id){

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(DataBaseHandler.TABLE_ITEM, new String[]{DataBaseHandler.KEY_ID,
                        DataBaseHandler.KEY_NOME, DataBaseHandler.KEY_PRECO,DataBaseHandler.KEY_QUANTIDADE}, DataBaseHandler.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getDouble(2),Integer.parseInt(cursor.getString(3)));

        return item;
    }

    public int atualizarItem(Item item){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_NOME, item.getNomeItem());
        values.put(DataBaseHandler.KEY_PRECO,item.getPreco());
        values.put(DataBaseHandler.KEY_QUANTIDADE,item.getQuantidade());

        return db.update(DataBaseHandler.TABLE_ITEM, values, DataBaseHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(item.getIdItem()) });
    }

    public ArrayList<Item> getTodosItens() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_ITEM;

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setIdItem(Integer.parseInt(cursor.getString(0)));
                item.setNomeItem(cursor.getString(1));
                item.setPreco(cursor.getDouble(2));
                item.setQuantidade(Integer.parseInt(cursor.getString(3)));

                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
    }



    public ArrayList<Item> getTodosItensNovo() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_PESSOA_ITEM;

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setIdItem(Integer.parseInt(cursor.getString(0)));
                item.setNomeItem(cursor.getString(1));
                item.setPreco(cursor.getDouble(2));
                item.setQuantidade(Integer.parseInt(cursor.getString(3)));

                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;
    }

    public int getNumeroItem() {
        String countQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_PESSOA_ITEM;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public void deletaItem(Item item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(DataBaseHandler.TABLE_ITEM, DataBaseHandler.KEY_ID + " = ?",
                new String[]{String.valueOf(item.getIdItem())});
        db.close();
    }

    public ArrayList<Item> getTodosItensPorNome(String tag_name) {
        ArrayList<Item> itemList = new ArrayList<Item>();

        String selectQuery = "SELECT  * FROM " + DataBaseHandler.TABLE_ITEM + " td, "
                + DataBaseHandler.TABLE_PESSOA + " tg, " + DataBaseHandler.TABLE_PESSOA_ITEM + " tt WHERE tg."
                + DataBaseHandler.KEY_NOME + " = '" + tag_name + "'" + " AND tg." + DataBaseHandler.KEY_ID
                + " = " + "tt." + DataBaseHandler.KEY_ID_PESSOA + " AND td." + DataBaseHandler.KEY_ID + " = "
                + "tt." + DataBaseHandler.KEY_ID_ITEM;



        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item td = new Item();
                td.setIdItem(Integer.parseInt(cursor.getString(0)));
                td.setNomeItem(cursor.getString(1));
                td.setPreco(cursor.getDouble(2));
                td.setQuantidade(Integer.parseInt(cursor.getString(3)));

                // adding to todo list
                itemList.add(td);
            } while (cursor.moveToNext());
        }

        return itemList;
    }
}
