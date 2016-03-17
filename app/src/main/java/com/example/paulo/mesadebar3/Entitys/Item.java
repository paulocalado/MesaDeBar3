package com.example.paulo.mesadebar3.Entitys;

/**
 * Created by Paulo on 21/01/2016.
 */
public class Item {

    int idItem,quantidade;
    double preco;
    String nomeItem;

    public Item(){

    }

    public Item(String nomeItem,double preco,int quantidade) {
        this.preco = preco;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
    }

    public Item(int id,String nomeItem,double preco,int quantidade) {
        this.idItem = id;
        this.preco = preco;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }
}
