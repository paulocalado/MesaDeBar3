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
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paulo.mesadebar3.AdapterListView;
import com.example.paulo.mesadebar3.Entitys.Pessoa;
import com.example.paulo.mesadebar3.Data.PessoaDAO;
import com.example.paulo.mesadebar3.R;

import java.util.ArrayList;
import java.util.List;

public class MainMesa extends AppCompatActivity {

    // Definindo variaveis
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<Pessoa> pessoas;
    PessoaDAO db = new PessoaDAO(this);
    Button btAdicionarPessoa;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mesa);

        final PessoaDAO db = new PessoaDAO(this);

        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(android.R.id.empty));
       // btAdicionarPessoa = (Button)findViewById(R.id.btAdicionarPessoa);
        //Define o Listener quando alguem clicar no item.

        createListView();

        //listView.setSelected(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {

                //listView.getChildAt(position).setBackgroundColor(Color.BLACK);

                return true;
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
                String codigo = ""+adapterListView.getItem(position).getId();
                Intent intent = new Intent(MainMesa.this, ContaPessoaActivity.class);
                intent.putExtra("codigo",codigo);
                startActivity(intent);

                // se tu pressionar ou clicar ele ta mostrando o toaster.
                //Toast.makeText(getApplicationContext(), codigo, Toast.LENGTH_SHORT).show();
            }
        });

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {


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
                switch  (item.getItemId()) {
                    case R.id.selectAll:
                        //
                        final int checkedCount  = pessoas.size();
                        // If item  is already selected or checked then remove or
                        // unchecked  and again select all
                        adapterListView.removeSelection();
                        for (int i = 0; i <  checkedCount; i++) {
                            listView.setItemChecked(i,   true);
                            //  listviewadapter.toggleSelection(i);
                        }// Set the  CAB title according to total checked items

                        // Calls  toggleSelection method from ListViewAdapter Class

                        // Count no.  of selected item and print it
                        mode.setTitle(checkedCount + "  Selecionadas");
                        return true;
                    case R.id.delete:
                        // Add  dialog for confirmation to delete selected item
                        // record.
                        AlertDialog.Builder  builder = new AlertDialog.Builder(MainMesa.this);
                        builder.setMessage("Você deseja deletar as pessoas selecionadas?");

                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

                            @Override
                            public void  onClick(DialogInterface dialog, int which) {
                                // TODO  Auto-generated method stub

                            }
                        });
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void  onClick(DialogInterface dialog, int which) {
                                // TODO  Auto-generated method stub
                                SparseBooleanArray selected = adapterListView.getSelectedIds();

                                for (int i =  (selected.size() - 1); i >= 0; i--) {
                                    if  (selected.valueAt(i)) {
                                        String  selecteditem = adapterListView.getItem(selected.keyAt(i)).toString();
                                        Pessoa ds = adapterListView.getItem(selected.keyAt(i));
                                        Toast.makeText(getApplicationContext(),ds.getNome()+" foi deletado" , Toast.LENGTH_SHORT).show();
                                        Pessoa x = adapterListView.getItem(selected.keyAt(i));
                                        // Remove  selected items following the ids
                                        adapterListView.remove(selecteditem);
                                        db.deletaPessoa(x);

                                    }
                                }

                                // Close CAB
                                mode.finish();
                                selected.clear();

                                createListView();

                            }
                        });
                        AlertDialog alert =  builder.create();
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
                // TODO  Auto-generated method stub
                final int checkedCount  = listView.getCheckedItemCount();
                SparseBooleanArray selected = adapterListView.getSelectedIds();


                // Set the  CAB title according to total checked items
                mode.setTitle(checkedCount + "  Selecionados");
                // Calls  toggleSelection method from ListViewAdapter Class
                adapterListView.toggleSelection(position);


                /*
                for(int i = 0; selected.size() > 0 ; i ++){
                    if(selected.valueAt(i)){
                        listView.getChildAt(position).setBackgroundColor(Color.BLACK);
                    }else{
                        listView.getChildAt(position).setBackgroundColor(Color.BLACK);

                    }
                }*/

                //listView.getChildAt(position).setBackgroundColor(Color.BLACK);

                //Toast.makeText(getApplicationContext(), adapterListView.getItem(position).getNome(), Toast.LENGTH_SHORT).show();

            }
        });


        /*btAdicionarPessoa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTrigger();

            }
        });*/


        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        /*db.inserirPessoa(new Pessoa("Ravi", 0,0));
        db.inserirPessoa(new Pessoa("Srinivas",0,0));
        db.inserirPessoa(new Pessoa("Tommy",0,0));
        db.inserirPessoa(new Pessoa("Karthik",0,0));*/

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Pessoa> pessoas = db.getTodasPessoas();

        for (Pessoa p : pessoas) {
            String log = "Id: "+p.getId()+" ,Nome: " + p.getNome() + " ,Parcial: " + p.getParcial() +" ,Total: " + p.getTotal();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogTrigger();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_mesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
         //   return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    private void createListView()
    {
        //Criamos nossa lista que preenchera o ListView
        //db = new DataBaseHandler(this);


        //db.inserirPessoa(new Pessoa("Guilherme Biff"));
        //db.inserirPessoa(new Pessoa("Lucas Volgarini"));
        //db.inserirPessoa(new Pessoa("Eduardo Ricoldi 6", 1.5,1.8));
        //db.inserirPessoa(new Pessoa("Felipe Panngo 5", 1.5,1.8));
        //db.inserirPessoa(new Pessoa("Felipe Panngo 4",1.5,1.8 ));

        pessoas = db.getTodasPessoas();

        //Cria o adapter
        adapterListView = new AdapterListView(this, pessoas);

        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void dialogTrigger() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//seta o builder do dialog
        final EditText input = new EditText(this);
        final PessoaDAO db = new PessoaDAO(this);
        builder.setTitle("Entre com o nome da Pessoa");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Adicionar Pessoa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String nomeString = input.getText().toString();
                db.inserirPessoa(new Pessoa(nomeString,3.2,3.5));
                createListView();
            }
        });
        builder.show();

    }

    public void setSelectedView(View selectedView, int position) {
        if (selectedView != null) {
            selectedView.setBackgroundColor(Color.WHITE);
        }else{

            selectedView.setBackgroundColor(Color.argb(200, 135, 206, 255));

        }
    }

    public void setClickView(View selectedView, int position) {
        selectedView.setBackgroundColor(Color.WHITE);

    }

}
