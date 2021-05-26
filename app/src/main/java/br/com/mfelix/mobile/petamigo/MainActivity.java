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
    private ListView lista;
    private PetAdapter adapter;
    private BancoPets db =new BancoPets(this);


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
        for(int i=0; i < pets.size(); i++){
            Log.d("APITESTE","chegamo porri:"+pets.get(i).getNome());

        }
        if(pets.isEmpty()){
            Log.d("APITESTE","essa desgraça tá vazia man:");

            //startActivity(getIntent());
          /*  finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);*/
        }



        //temos que criar aqui uma verficação pra não preecher a lista de novo saca

        //TESTE CRUD
        //insert ok
        //abaixo estamos verifificando se a o banco  esta vazio antes de preencher

     /*   if(db.check_empty()){
            db.addPet(new Pet("Ruy","12","foda","71 8933-3408","cao"));
            db.addPet(new Pet("Rex","12","maquina de matar","71 8933-3407","cao"));
            db.addPet(new Pet("Garfilde","13","maquina de comer","71 8794-6591","gato"));
            db.addPet(new Pet("Loro José","15","maquina de amor","71 8854-6963","passaro"));
        }

        //TESTE CRUD
        //Alterar dados
        Pet pet=new Pet();
        pet.setId(2);
        pet.setNome("INVENCIVEL"); */



      //  db.atualizarPet(pet);

        //TESTE CRUD
        //delete ok
    /*  Pet pet=new Pet();
        pet.setId(4);
        db.apagarPet(pet);*/


        //TESTE CRUD
        //ler dados ok

      /*  if(pets.isEmpty()){
            Pet pet1= db.selecionarPet(1);
            Pet pet2= db.selecionarPet(2);
            Pet pet3= db.selecionarPet(3);
           // Pet pet4= db.selecionarPet(4);
            pets.addAll(
                    Arrays.asList(

                            pet1,pet2,pet3,pet4

                            // new Dog("LER UM LIVRO", "DESCRICAO !", 1)
                    )
            );
        }
        */





        /*if(pets.isEmpty()){

            pets.addAll(
                    Arrays.asList(

                            new Pet(1,"Rex","12","fodase","71 8933-3407","cao"),
                            new Pet(1,"Garfilde","13","fodase","71 8794-6591","gato"),
                            new Pet(1,"Loro José","15","fodase","71 8854-6963","passaro")

                           // new Dog("LER UM LIVRO", "DESCRICAO !", 1)
                    )
            );
        }*/
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
 /*for(int i=0; i < lista.size(); i++){
            Log.d("APITESTE","chegamo porra8"+lista.get(i));
            //  System.out.println( petList.get(i) );
        }*/
            pets=(ArrayList)ConsumirJson.jsonDados(s);
            setTitle(pets.get(0).getNome());
            Log.d("APITESTE","mas que porra é essa?:");
            db.teste();
           /* for(int i=0; i < pets.size(); i++){
                Log.d("APITESTE","chegamo porri:"+pets.get(i).getNome());

            }*/
//diagnostico, não está saindo desse metodo a lista
        }

    }

    public void formulario(View view){

        Toast.makeText(this,"Vai para o form", Toast.LENGTH_SHORT).show();
        //INTENT EXPLICITA POSI SABEMOS ONDE ESTAMOS E PRA ONDE ESTAMOS INDO
        Intent intent = new Intent(this, FormActivity.class);
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