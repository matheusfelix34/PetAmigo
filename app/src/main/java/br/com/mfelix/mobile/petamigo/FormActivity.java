package br.com.mfelix.mobile.petamigo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class FormActivity extends AppCompatActivity {
    private TextInputEditText nome;

    private TextInputEditText desc;

    private TextInputEditText numero;

    private TextInputEditText idade;

    private Pet pet;

    private  Spinner especie;

    private View b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);


        // prenchendo dados do select do form com as especies de animais aceitas
        String[] items = new String[]{"CÃO", "GATO", "AVE"};

        //CRIANDO O ADAPTER QUE SERA USADO PARA PASSAR O SELECT Spinner

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //IDENTIFICANDO NO LAYOUT O SELECT Spinner
        especie = (Spinner) findViewById(R.id.spinner1);
        nome = findViewById(R.id.form_text_nome);
        desc = findViewById(R.id.form_text_desc);
        numero= findViewById(R.id.form_text_numero);
        idade= findViewById(R.id.form_text_idade);

        numero.addTextChangedListener(Mask.insert("(##)####-####", numero));
        //d_do_campo.addTextChangedListener(Mask.insert("(##)####-####", id_do_campo));
        Intent intent = getIntent();


       if( intent.hasExtra("PET")){

        setTitle("Editar Pet");

        //COMO VAMOS ESTAR EDITANDO, É SUAVE DEIXAR O BUTTOM DE EXCLUIR APARECER AGORA
        b = findViewById(R.id.form_button_excluir);
        b.setVisibility(View.VISIBLE);


        pet = (Pet) intent.getSerializableExtra("PET");

        //abaixo estamos vendo qual é a posicao no select da especie já selecionada daquele pet e pondo ela
        String especie_selecionada = pet.getEspecie();
        int spinnerPosition = adapter.getPosition(especie_selecionada);

        especie.setAdapter(adapter);
        especie.setSelection(spinnerPosition);

        nome.setText(pet.getNome());
        desc.setText(pet.getDescricao());
        numero.setText(pet.getContato());
        idade.setText(pet.getIdade()+"");

        }else {
            setTitle("Novo Pet");
           especie.setAdapter(adapter);
            pet = new Pet();
        }

    }


    public void salvar(View view){
        //se a main  tiver o  pet ela altera

       if(!MainActivity.pets.contains(pet)) {
            Log.d("Metodo","Alterando7");
            MainActivity.pets.add(pet);
        }else {
            int index = MainActivity.pets.indexOf(pet);
            pet = MainActivity.pets.get(index);
        }
        pet.setNome(nome.getText().toString());
        pet.setContato(numero.getText().toString());
        pet.setDescricao(desc.getText().toString());
        pet.setEspecie(especie.getSelectedItem().toString());
        pet.setIdade((idade.getText().toString()));
        finish();


    }


    public void excluir(View view){
        if(MainActivity.pets.contains(pet)) {
            MainActivity.pets.remove(pet);
        }

        Log.d("Metodo","Excluindo");
        finish();
    }

}
