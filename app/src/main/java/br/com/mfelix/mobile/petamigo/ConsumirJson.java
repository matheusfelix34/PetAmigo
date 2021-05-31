package br.com.mfelix.mobile.petamigo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumirJson {


    public  static List<Pet>  jsonDados(String conteudo){
        try {
            List<Pet> petList = new ArrayList<>();

            JSONArray jSONArray =null;
            JSONObject jSONObject= null;

            jSONArray = new  JSONArray(conteudo);

            for (int i= 0; i<jSONArray.length();i++){
                jSONObject = jSONArray.getJSONObject(i);

                Pet pet = new Pet();
                pet.setId(i+1);
                String text=jSONObject.getString("nome");
                pet.setNome(text);
                text=jSONObject.getString("idade");
                pet.setIdade(text);
                text=jSONObject.getString("descricao");
                pet.setDescricao(text);
                text=jSONObject.getString("contato");
                pet.setContato(text);
                text=jSONObject.getString("especie");
                pet.setEspecie(text);
                text=jSONObject.getString("situacao");
                pet.setSituacao(text);
                pet.setId_tutor(0);
                Log.d("APITESTE","chegamo porra7");
            petList.add(pet);
            }

          /*  for(int i=0; i < petList.size(); i++){
                Log.d("APITESTE","chegamo porra7"+petList.get(i));
              //  System.out.println( petList.get(i) );
            }*/
            //retorna lista de pets do Json
            return petList;


        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }


}
