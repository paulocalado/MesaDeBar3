package com.example.paulo.mesadebar3.Entitys;

/**
 * Created by Paulo on 13/01/2016.
 */
public class Pessoa {
    String nome;
    double parcial,total ;
    int id;

    public Pessoa(){

    }

    public Pessoa(int id,String nome) {
        this.id = id;
        this.nome = nome;

    }

   public Pessoa(String nome,double parcial, double total) {
        this.nome = nome;
        this.parcial = parcial;
        this.total = total;

    }



    public Pessoa(int id,String nome,double parcial,double total) {
        this.id = id;
        this.nome = nome;
        this.parcial = parcial;
        this.total = total;
    }

    public Pessoa(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getParcial() {
        return parcial;
    }

    public void setParcial(double parcial) {
        this.parcial = parcial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
