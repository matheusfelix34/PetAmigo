package br.com.mfelix.mobile.petamigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BancoPets extends SQLiteOpenHelper {
    private  static final int VERSAO_BANCO=1;
    private static final String BANCO_PETS="bd_pets";

    //entre aspas estão os nomes que as colunas vao ter no banco
    //nome da tabela e depois das colunas
    private static final String TABELA_PET ="tb_pets";
    private static final String COLUNA_ID="id";
    private static final String COLUNA_NOME="nome";
    private static final String COLUNA_IDADE="idade";
    private static final String COLUNA_DESC="descricao";
    private static final String COLUNA_CONTATO="contato";
    private static final String COLUNA_ESPECIE="especie";
    private static Pet pet;
    private static SQLiteDatabase db;
    private static ContentValues values;



   /* public BancoPets(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BANCO_PETS, factory, VERSAO_BANCO);
        //super(context, name, factory, version);
    }*/
   public BancoPets(Context context) {
       super(context, BANCO_PETS, null, VERSAO_BANCO);
       //super(context, name, factory, version);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
//metodo que cria o banco, é só uma vez,pois ele vê antes
//de gerar se já não tem um
        // SQLite, ao pormos a primeira solua com INTEGER PRIMARY KEY, ele já usa auto incremente
        String  QUERY_COLUNA =" CREATE TABLE "+ TABELA_PET + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, "
                + COLUNA_IDADE + " TEXT, " + COLUNA_DESC + " TEXT, "
                +COLUNA_CONTATO+ " TEXT, " + COLUNA_ESPECIE + " TEXT)";
    //aqui já criamos o nosso banco com essa tabela
        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //pra quando vc já tem um banco e quer criar
    //um novo campo no banco sem perder os dados
    }

/* CRUD ABAIXO*/

    void addPet(Pet pet){
        //como já estamos numa classe SQL já temos o banco e o contexto
        //aqui iniciamos a escrita no banco
         db=this.getWritableDatabase();
        //
         values = new ContentValues();
        //vamos agora passar os parametros para o banco
        values.put(COLUNA_NOME , pet.getNome());
        values.put(COLUNA_IDADE , pet.getIdade());
        values.put(COLUNA_DESC , pet.getDescricao());
        values.put(COLUNA_CONTATO , pet.getContato());
        values.put(COLUNA_ESPECIE , pet.getEspecie());

        db.insert(TABELA_PET, null,values);
        Log.d("Banco","ADICIONADO COM SUCESSO");
        db.close();
    }

    void apagarPet(Pet pet){
        SQLiteDatabase db=this.getWritableDatabase();
        //new String [] { String.valueOf(pet.getId())}, isso aqui é porque ele aceita string, mas como o id tá interger
        //é necessario fazer esse cast
        db.delete(TABELA_PET, COLUNA_ID + " = ?", new String [] { String.valueOf(pet.getId())});
        Log.d("Banco","Apagado com sucesso");
        db.close();
    }

    Pet selecionarPet(int id){
        //agora queremos ler o data base em vez de escrever
         db=this.getReadableDatabase();

        Cursor cursor=db.query(TABELA_PET, new String[] {COLUNA_ID,
                COLUNA_NOME, COLUNA_IDADE, COLUNA_DESC, COLUNA_CONTATO, COLUNA_ESPECIE }, COLUNA_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            //mover para a primeira posição
            cursor.moveToFirst();

        }
        pet= new Pet(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));


        Log.d("Banco","Lido com sucesso");
        return pet;

    }

    void atualizarPet( Pet pet){

        db = this.getWritableDatabase();
        values = new ContentValues();

        values.put(COLUNA_NOME , pet.getNome());
        values.put(COLUNA_IDADE , pet.getIdade());
        values.put(COLUNA_DESC , pet.getDescricao());
        values.put(COLUNA_CONTATO , pet.getContato());
        values.put(COLUNA_ESPECIE , pet.getEspecie());

        db.update(TABELA_PET, values, COLUNA_ID + " = ?",
                new String[] { String.valueOf(pet.getId())});


    }



  boolean check_empty(){
         db = this.getWritableDatabase();
       String count = "SELECT count(*) FROM "+TABELA_PET;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){
            //leave
            return false;
        }else{
            return true;

        }

    }

    void teste(){
        Log.d("APITESTE","safada");
    }




}
