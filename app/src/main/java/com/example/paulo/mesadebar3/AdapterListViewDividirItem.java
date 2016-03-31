package com.example.paulo.mesadebar3;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.paulo.mesadebar3.Entitys.Item;
import com.example.paulo.mesadebar3.Entitys.Pessoa;

import java.util.ArrayList;

/**
 * Created by Paulo on 30/03/2016.
 */
public class AdapterListViewDividirItem extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Pessoa> pessoas;
    private SparseBooleanArray mSelectedItemsIds;
    private Context context;

    public AdapterListViewDividirItem(Context context, ArrayList<Pessoa> pessoas)
    {
        //Itens que preencherão o listview
        mSelectedItemsIds = new  SparseBooleanArray();
        this.context = context;
        this.pessoas = pessoas;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount()
    {
        return pessoas.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Pessoa getItem(int position)
    {
        return pessoas.get(position);
    }

    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        Pessoa pessoa = pessoas.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.fragment_dividir_item, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.


        //((TextView) view.findViewById(R.id.txtNomePessoaDividir)).setText(pessoa.getNome());
         CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkDividirItem);
        checkBox.setText(pessoa.getNome()+"                                                                                 0");


        return view;
    }

    // Remove selection after unchecked
    public void  removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public  SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }


    public void remove(String  object) {
        pessoas.remove(object);
        notifyDataSetChanged();
    }

    // Item checked on selection
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position,  value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public void  toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }
}
