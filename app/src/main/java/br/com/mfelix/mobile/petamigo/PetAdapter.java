package br.com.mfelix.mobile.petamigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PetAdapter extends BaseAdapter {

    private List<Pet> lista;
    private Context contexto;

    public PetAdapter(Context contexto,List<Pet> pets) {
        this.contexto = contexto;
        this.lista = pets;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Pet getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
       //metodo para retornar views
        // FAZEMOS O view abaixo, recebendo o layout da lista tarefa_list_item_2
        //o ViewGroup é o parent, ou seja onde vai ficar , que no caso sera no nosso listview

        //de acordo com o professor as primeiras views podem estar nulas, então é bom testar
        //a parte do false é pra deixar claro que ele não é o proncipal, a do parente é a posicao, ou seja
        // ele vai ficar dentro do listview que é seu superior .
        if(view == null ) {
            view = LayoutInflater.from(contexto).inflate(R.layout.pet_list_item, parent, false);
        }
        //definindo onde vai ficar cada texto na layout
        TextView nome = view.findViewById(R.id.list_pet_nome);
        TextView descricao = view.findViewById(R.id.list_pet_descricao);
        TextView idade = view.findViewById(R.id.list_pet_idade);
        TextView contato = view.findViewById(R.id.list_pet_contato);

        //PEGAMOS A NOSSA TAREFA BASEADO NA POSICAO INFORMADA COM O GETITEM
        Pet pet =getItem(position);

        //DEPOIS CETAMOS OS ITENS DA TAREFA QUE PEGAMOS NA LISTA nos locais que acabamos defeinir que ficariam
        nome.setText(pet.getNome());
        descricao.setText(pet.getDescricao());
        idade.setText(pet.getIdade()+"");
        contato.setText(pet.getContato());



        return view;
    }
}
