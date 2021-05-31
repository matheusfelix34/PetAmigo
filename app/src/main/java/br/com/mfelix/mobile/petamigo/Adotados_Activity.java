package br.com.mfelix.mobile.petamigo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Adotados_Activity extends AppCompatActivity {
    public static ArrayList<Pet> pets_adotados = new ArrayList<>();
    private ListView lista_adotados;
    private PetAdapter adapter;
    private BancoPets db =new BancoPets(this);
   // private int id_tutor_adotado=1;
     private int id_tutor_adotado=MainActivity.tutor_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adotados);
        setTitle("Adotados");
        lista_adotados = findViewById(R.id.mains_list_pets_adotados);

        if (pets_adotados.isEmpty()) {


            long numero_rows = db.getProfilesCount();

            Log.d("TESTE_BANCO", "Némero de rows:" + numero_rows);
            for (int i = 0; i < numero_rows; i++) {
                Log.d("TESTE_BANCO", "id do user"+id_tutor_adotado);
                if (db.selecionarPet(i + 1).getId_tutor().equals(id_tutor_adotado)) {

                    Log.d("TESTE_BANCO", "Adotado do tutor varaivel " +id_tutor_adotado);
                    Log.d("TESTE_BANCO", "Id do adotado" + db.selecionarPet(i + 1).getId_tutor());
                    pets_adotados.add(db.selecionarPet(i + 1));

                }


            }



        }else{
            pets_adotados.clear();
            long numero_rows = db.getProfilesCount();

            Log.d("TESTE_BANCO", "Atualizando adotados");
            for (int i = 0; i < numero_rows; i++) {
                Log.d("TESTE_BANCO", "id do user"+id_tutor_adotado);
                if (db.selecionarPet(i + 1).getId_tutor().equals(id_tutor_adotado)) {

                    Log.d("TESTE_BANCO", "Adotado" + db.selecionarPet(i + 1).getNome());
                    Log.d("TESTE_BANCO", "Id do adotado" + db.selecionarPet(i + 1).getId_tutor());
                    pets_adotados.add(db.selecionarPet(i + 1));

                }


            }

        }



    }

    @Override
    protected void onResume() {




        super.onResume();
        Log.d("Ciclo de Vida","Ciclo de Vida - onResume");
        //usando as formas adpatadas que o android tem de apresentar listas

        //  Collections.sort(pets);
        //adapter = new PetAdapter(this, new ArrayList<>(pets));

        adapter = new PetAdapter(this, new ArrayList<>(pets_adotados));

        //pondo a adptaçaõ na nossa listvew do layout
        lista_adotados.setAdapter(adapter);
    }
}
