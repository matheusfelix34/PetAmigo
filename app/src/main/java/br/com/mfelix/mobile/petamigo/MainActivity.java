package br.com.mfelix.mobile.petamigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Ciclo de Vida","Ciclo de Vida - onCreate");
        setTitle("Cães");
        //colando um titulo no tipo da aplicacao


        //criando uma lista de tarefas
        //temos que criar aqui uma verficação pra não preecher a lista de novo saca
        if(pets.isEmpty()){

            pets.addAll(
                    Arrays.asList(

                            new Pet(1,"Rex",12,"fodase","71 8933-3407","cao"),
                            new Pet(1,"Garfild",13,"fodase","71 8794-6591","gato"),
                            new Pet(1,"Loro José",15,"fodase","71 8854-6963","passaro")

                           // new Dog("LER UM LIVRO", "DESCRICAO !", 1)
                    )
            );
        }
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