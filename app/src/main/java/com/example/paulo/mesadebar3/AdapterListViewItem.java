package com.example.paulo.mesadebar3;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.paulo.mesadebar3.Entitys.Item;
import com.example.paulo.mesadebar3.Entitys.Pessoa;

import java.util.ArrayList;

/**
 * Created by Paulo on 21/01/2016.
 */
public class AdapterListViewItem extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Item> itens;
    private SparseBooleanArray mSelectedItemsIds;
    private Context context;

    public AdapterListViewItem(Context context, ArrayList<Item> itens)
    {
        //Itens que preencheram o listview
        mSelectedItemsIds = new  SparseBooleanArray();
        this.context = context;
        this.itens = itens;
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
        return itens.size();
    }

    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public Item getItem(int position)
    {
        return itens.get(position);
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
        Item item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.todos_itens, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.

        ((TextView) view.findViewById(R.id.txtIdItem)).setText(""+item.getIdItem());
        ((TextView) view.findViewById(R.id.txtNomeItem)).setText(item.getNomeItem());
        ((TextView) view.findViewById(R.id.txtPrecoItem)).setText(""+item.getPreco());
        ((TextView) view.findViewById(R.id.txtQuantidadeItem)).setText(""+item.getQuantidade());



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
        itens.remove(object);
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
