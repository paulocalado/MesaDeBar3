package com.example.paulo.mesadebar3.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.mesadebar3.Entitys.Pessoa;
import com.example.paulo.mesadebar3.Data.PessoaDAO;
import com.example.paulo.mesadebar3.R;


public class ExibirItem extends AppCompatActivity {
    String codigo;
    PessoaDAO db = new PessoaDAO(this);
    TextView txtNomePessoa;
    TextView txtIdPessoa;
    TextView txtParcialPessoa;
    TextView txtTotalPessoa;
    Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_inf);
        //p = new Pessoa("",0,0);

        codigo = this.getIntent().getStringExtra("codigo");
        //db.recuperarPessoa(Integer.parseInt(codigo)).getNome();

        Toast.makeText(getApplicationContext(), codigo, Toast.LENGTH_SHORT).show();

       txtNomePessoa = (TextView)findViewById(R.id.txtViewMostrarNome);
       txtIdPessoa = (TextView)findViewById(R.id.txtViewMostrarId);
       txtParcialPessoa = (TextView)findViewById(R.id.txtViewMostrarParcial);
       txtTotalPessoa = (TextView) findViewById(R.id.txtViewMostrarTotal);

        txtNomePessoa.setText(db.recuperarPessoa(Integer.parseInt(codigo)).getNome());
        txtIdPessoa.setText(""+db.recuperarPessoa(Integer.parseInt(codigo)).getId());
        txtParcialPessoa.setText(""+db.recuperarPessoa(Integer.parseInt(codigo)).getParcial());
        txtTotalPessoa.setText(""+db.recuperarPessoa(Integer.parseInt(codigo)).getTotal());

    }

    /*public void onBackPressed() { --esse método é usado para o botão de voltar do próprio android, ele dispara automaticamente
        Intent intent = new Intent(ActivityContaPessoa.this, MainMesa.class);
        startActivity(intent);
    }*/
}












