package com.example.paulo.mesadebar3;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.paulo.mesadebar3.Entitys.Pessoa;

import java.util.ArrayList;

/**
 * Created by Acer on 14/01/2016.
 */
public class AdapterListView extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ArrayList<Pessoa> itens;
    private  SparseBooleanArray mSelectedItemsIds;
    private Context context;
    public AdapterListView(Context context, ArrayList<Pessoa> itens)
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
    public Pessoa getItem(int position)
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
        Pessoa item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_listview, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.

        ((TextView) view.findViewById(R.id.txtIdPessoa)).setText(""+item.getId());
        ((TextView) view.findViewById(R.id.txtNomePessoa)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.txtParcialPessoa)).setText(""+item.getParcial());
        ((TextView) view.findViewById(R.id.txtTotalPessoa)).setText(""+item.getTotal());
        //((ImageView) view.findViewById(R.id.imagemview)).setImageResource(item.getId());

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
