package br.com.mfelix.mobile.petamigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Pet> pets = new ArrayList<>();
    public static ArrayList<Pet> pets_api = new ArrayList<>();
    private ListView lista;
    private PetAdapter adapter;
    private BancoPets db =new BancoPets(this);
    private   Integer s=0;
    public static  Integer tutor_id=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Ciclo de Vida","Ciclo de Vida - onCreate");

        setTitle("Pets");

        //colando um titulo no tipo da aplicacao




        //criando uma lista de tarefas
        Tarefa tarefa = new Tarefa ();
        tarefa.execute("https://raw.githubusercontent.com/matheusfelix34/api/main/pets.json");


        //garantindo que os dados da API, FORAM PEGOS
         if(pets_api.isEmpty()){
            Log.d("APITESTE","essa desgraça tá vazia man:");

           startActivity(getIntent());
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }

//preenchendo banco com dados do Json API
       if(db.check_empty()){


            for(int i=0; i < pets_api.size(); i++){
                //PRECISAMOS VERIFICAR, ANTES DE ADICIONAR, SE O PET JÁ NÃO ESTÁ NO BANCO

                Log.d("BANCO_API","ADICIONANDO"+pets_api.get(i).getNome());

                db.addPet(pets_api.get(i));
                Log.d("Teste_Banco","Entrando no metodo");

                if(i==0){

                    db.addUser(new User("Apolo","123456"));
                    Log.d("pondo user","id user");
//erro aqui



                }
            }
         //  tutor_id=db.selecionarUser(1).getId();






        }

       if(!pets_api.isEmpty()){
           tutor_id=db.selecionarUser(1).getId();
       }
            //  s=db.selecionarUser(1).getId();




        if(tutor_id!=0){
            Log.d("Teste_Banco_user","id user"+tutor_id);
        }

   /* if(s!=0){
        Log.d("Teste_Banco_user","id user"+s);
    }*/



        //TESTE CRUD
        //ler dados ok
//PREENCHENDO TELA VISIVEL

    if(pets.isEmpty()){


         long numero_rows=db.getProfilesCount();
          Log.d("TESTE_BANCO","Némero de rows:"+numero_rows);
        for(int i=0; i < numero_rows; i++){
            Log.d("TESTE_BANCO","Adicionando"+db.selecionarPet(i+1).getNome());
                    pets.add(db.selecionarPet(i+1));

        }

        }


      // Log.d("TESTE_BANCO_user","Vendo id usuario"+db.selecionarUser(1).getId());

  /*  if(id_tutor==0){
        id_tutor=  db.selecionarUser(1).getId();
        Log.d("TESTE_BANCO","Pondo id tutor"+id_tutor);
    }*/


   /*     id_tutor= db.selecionarUser(1).getId();
        Log.d("TESTE_BANCO","Pondo id tutor"+id_tutor);
        name_tutor=db.selecionarUser(1).getName();
        Log.d("TESTE_BANCO","Pondo Nome tutor"+name_tutor);
*/



        //      //chamando nossa lista do layout
        lista = findViewById(R.id.mains_list_pets);
        //metodo que ao clicarmos em um item da lista, fazermos algo
        //no caso estamos alterando
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Pet pet = (Pet)adapter.getItemAtPosition(posicao);
                //pegamos item selecionado com a sua posicao  usando getItemAtPosition(posicao);
                //da classe AdapterView
                Toast.makeText(MainActivity.this,
                        "Amigo: "+pet.getNome(),
                        Toast.LENGTH_SHORT).show();
                //Alterar , aqui estamos ativando a intent form, pois é por ele que alteramos
                //Alterar

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("PET", pet);

                //esse put extra só vai aceitar o segunto paramentro
                //se implementarmos Serializable na classe dele


                startActivity(intent);
            }
        });



    }

    public class  Tarefa extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno =Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {


            pets_api=(ArrayList)ConsumirJson.jsonDados(s);

            //FUNCIONANDO:OK
            /*for(int i=0; i < pets_api.size(); i++){
                Log.d("APITESTE","chegamo porra7"+pets_api.get(i).getNome()
                );

            }*/

        }

    }

    public void formulario(View view){

        Toast.makeText(this,"Vai para o form", Toast.LENGTH_SHORT).show();
        //INTENT EXPLICITA POSI SABEMOS ONDE ESTAMOS E PRA ONDE ESTAMOS INDO
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    public void adotados(View view){

        Toast.makeText(this,"Adotados", Toast.LENGTH_SHORT).show();
        //INTENT EXPLICITA, POIS SABEMOS ONDE ESTAMOS E PRA ONDE ESTAMOS INDO
        Intent intent = new Intent(this, Adotados_Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {




        super.onResume();
        Log.d("Ciclo de Vida","Ciclo de Vida - onResume");
        //usando as formas adpatadas que o android tem de apresentar listas

      //  Collections.sort(pets);
        //adapter = new PetAdapter(this, new ArrayList<>(pets));

        adapter = new PetAdapter(this, new ArrayList<>(pets));

        //pondo a adptaçaõ na nossa listvew do layout
        lista.setAdapter(adapter);
    }
}