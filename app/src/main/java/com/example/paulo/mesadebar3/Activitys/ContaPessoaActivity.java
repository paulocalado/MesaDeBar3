package com.example.paulo.mesadebar3.Activitys;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulo.mesadebar3.AdapterListView;
import com.example.paulo.mesadebar3.AdapterListViewDividirItem;
import com.example.paulo.mesadebar3.AdapterListViewItem;
import com.example.paulo.mesadebar3.Data.ItemDAO;
import com.example.paulo.mesadebar3.Data.PessoaDAO;
import com.example.paulo.mesadebar3.Entitys.Item;
import com.example.paulo.mesadebar3.Entitys.Pessoa;
import com.example.paulo.mesadebar3.R;

import java.util.ArrayList;
import java.util.List;

public class ContaPessoaActivity extends AppCompatActivity {

    String codigo;
    PessoaDAO db = new PessoaDAO(this);
    TextView txtNomeContaPessoa, testeTotal,txtParcialPessoa;
    ListView listViewConta,listViewItens;
    Button btAdicionarItem;
    private ArrayList<Item> itens,itens2;
    private ArrayList<Pessoa> pessoas,pessoas2;
    private AdapterListView adapterListViewP;
    private AdapterListViewItem adapterListView,adapterListView2;
    private AdapterListViewDividirItem adapterListViewDividirItem;
    AlertDialog alerta;
    Item objetoItem = new Item();
    ItemDAO dbItem = new ItemDAO(this);


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_pessoa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogTrigger2();
            }
        });

        listViewItens = (ListView)findViewById(R.id.listViewItens);

        listViewItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                dialogTriggerDividirItens();
               // int codigo = adapterListView2.getItem(position).getIdItem();


                // se tu pressionar ou clicar ele ta mostrando o toaster.
               // Toast.makeText(getApplicationContext(), ""+codigo, Toast.LENGTH_SHORT).show();
            }
        });


        listViewConta = (ListView)findViewById(R.id.listViewContaPessoa);
        listViewConta.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listViewConta.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // TODO  Auto-generated method stub
                mode.setTitle("Selecionar Itens");
                mode.getMenuInflater().inflate(R.menu.menu_main_mesa, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.selectAll:
                        //
                        final int checkedCount = itens.size();
                        // If item  is already selected or checked then remove or
                        // unchecked  and again select all
                        adapterListView.removeSelection();
                        for (int i = 0; i < checkedCount; i++) {
                            listViewConta.setItemChecked(i, true);
                            //  listviewadapter.toggleSelection(i);
                        }// Set the  CAB title according to total checked items

                        // Calls  toggleSelection method from ListViewAdapter Class

                        // Count no.  of selected item and print it
                        mode.setTitle(checkedCount + "  Selecionadas");
                        return true;
                    case R.id.delete:
                        // Add  dialog for confirmation to delete selected item
                        // record.
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContaPessoaActivity.this);
                        builder.setMessage("Você deseja deletar as pessoas selecionadas?");

                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO  Auto-generated method stub

                            }
                        });
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO  Auto-generated method stub
                                SparseBooleanArray selected = adapterListView.getSelectedIds();

                                for (int i = (selected.size() - 1); i >= 0; i--) {
                                    if (selected.valueAt(i)) {
                                        String selecteditem = adapterListView.getItem(selected.keyAt(i)).toString();
                                        Item item = adapterListView.getItem(selected.keyAt(i));
                                        Toast.makeText(getApplicationContext(), item.getNomeItem() + " foi deletado", Toast.LENGTH_SHORT).show();
                                        Item item2 = adapterListView.getItem(selected.keyAt(i));
                                        // Remove  selected items following the ids
                                        adapterListView.remove(selecteditem);
                                        dbItem.deletaItem(item2);

                                    }
                                }

                                // Close CAB
                                mode.finish();
                                selected.clear();

                                createListView();

                            }
                        });
                        AlertDialog alert = builder.create();
                        //alert.setIcon(R.drawable.questionicon);// dialog  Icon
                        alert.setTitle("Confirmação"); // dialog  Title
                        alert.show();
                        return true;
                    default:
                        return false;
                }
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount = listViewConta.getCheckedItemCount();
                SparseBooleanArray selected = adapterListView.getSelectedIds();


                // Set the  CAB title according to total checked items
                mode.setTitle(checkedCount + "  Selecionados");
                // Calls  toggleSelection method from ListViewAdapter Class
                adapterListView.toggleSelection(position);
            }
        });
















        listViewConta.setEmptyView(findViewById(android.R.id.empty));
        codigo = this.getIntent().getStringExtra("codigo");
        txtNomeContaPessoa = (TextView)findViewById(R.id.txtNomeContaPessoa);
        txtParcialPessoa = (TextView)findViewById(R.id.txtParcialPessoa);
        //testeTotal = (TextView)findViewById(R.id.txtTesteTotal);
        //btAdicionarItem = (Button)findViewById(R.id.btAdicionarItem);

        txtNomeContaPessoa.setText("Conta de: "+db.recuperarPessoa(Integer.parseInt(codigo)).getNome());
       // testeTotal.setText("" + db.recuperarPessoa(Integer.parseInt(codigo)).getTotal());
        //txtParcialPessoa.setText(""+dbItem.recuperarItem(Integer.parseInt(codigo)).getNomeItem());

        //txtNomeContaPessoa.setText(dbItem.recuperarItem(Integer.parseInt(codigo)).getNomeItem());
        //testeTotal.setText(""+dbItem.recuperarItem(Integer.parseInt(codigo)).getIdItem());
        //txtParcialPessoa.setText(""+dbItem.recuperarItem(Integer.parseInt(codigo)).getPreco());
        createListView();


        /*btAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dbItem.inserirItem(new Item(9.9, "Uva"));
                dialogTrigger2();
                //dbItem.inserirItem_Pessoa(dbItem.recuperarItem(Integer.parseInt(codigo)), db.recuperarPessoa(Integer.parseInt(codigo)));
                dbItem.inserirItem_Pessoa(dbItem.recuperarItem(Integer.parseInt("1")), db.recuperarPessoa(Integer.parseInt("2")));
                //Toast.makeText(getApplicationContext(), ""+codigo , Toast.LENGTH_SHORT).show();

                //createListView();
            }
        });*/

    }
    private void createListView()
    {
        // Seta o adapter só de Itens!
        itens2 = dbItem.getTodosItens();
        adapterListView2 = new AdapterListViewItem(this,itens2);
        listViewItens.setAdapter(adapterListView2);
        listViewItens.setCacheColorHint(Color.TRANSPARENT);

        // Seta o adapter de itens associados a uma pessoa



        itens = dbItem.getTodosItensPorNome("Joao");
        adapterListView = new AdapterListViewItem(this, itens);
        listViewConta.setAdapter(adapterListView);
        listViewConta.setCacheColorHint(Color.TRANSPARENT);







        //Toast.makeText(getApplicationContext(), ""+itens2.get(1).getIdItem() , Toast.LENGTH_SHORT).show();


    }


    private void dialogTrigger2(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//seta o builder do dialog


        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.fragment_dialog_itens, null);//Objeto que vai setar a view do Lyout customizado do Dialoh

        final EditText editTextNomeItemDialog = (EditText)view.findViewById(R.id.editTextNomeItemDialog);
        final EditText editTextValorItemDialog = (EditText)view.findViewById(R.id.editTextValorItemDialog);
        final EditText editTextQuantidadeItemDialog = (EditText)view.findViewById(R.id.editTextQuantidadeItemDialog);


        builder.setView(view);
        builder.setTitle("Adicione um novo pedido na sua mesa");

        builder.setPositiveButton("Adicionar Pedido", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //itemTeste.setNomeItem(editTextNomeItemDialog.getText().toString());

                dbItem.inserirItem(new Item(editTextNomeItemDialog.getText().toString(),Double.parseDouble(editTextValorItemDialog.getText().toString()),
                        Integer.parseInt(editTextQuantidadeItemDialog.getText().toString())));
                Toast.makeText(ContaPessoaActivity.this, "" + Double.parseDouble(editTextValorItemDialog.getText().toString()), Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
                // quando clicar no Ok, ele apenas fecha o dialog
                createListView();
            }
        });

        alerta = builder.create();

        alerta.show();
    }

    private void dialogTriggerDividirItens(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.fragment_listview_dividir_itens, null);

        final ListView listViewDividirItem = (ListView)view.findViewById(R.id.listViewDividirItens);
        pessoas = db.getTodasPessoas();
        adapterListViewDividirItem = new AdapterListViewDividirItem(this, pessoas);
        listViewDividirItem.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewDividirItem.setAdapter(adapterListViewDividirItem);

        builder.setView(view);

        builder.setPositiveButton("Dividir com essas pessoas", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


            }
        });

        alerta = builder.create();

        alerta.show();
    }
}
