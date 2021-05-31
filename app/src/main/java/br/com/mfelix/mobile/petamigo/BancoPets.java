package br.com.mfelix.mobile.petamigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class BancoPets extends SQLiteOpenHelper {
    private  static final int VERSAO_BANCO=1;
    private static final String BANCO_PETS="bd_pets";

    //entre aspas estão os nomes que as colunas vao ter no banco
    //nome da tabela e depois das colunas
    //tabela pet
    private static final String TABELA_PET ="tb_pets";
    private static final String COLUNA_ID="id";
    private static final String COLUNA_NOME="nome";
    private static final String COLUNA_IDADE="idade";
    private static final String COLUNA_DESC="descricao";
    private static final String COLUNA_CONTATO="contato";
    private static final String COLUNA_ESPECIE="especie";
    private static final String COLUNA_SITUACAO="situacao";
    private static final String COLUNA_ID_TUTOR="id_tutor";


    //tabela user agora:
    private static final String TABELA_USER ="tb_user";
    private static final String COLUNA_ID_USER="id";
    private static final String COLUNA_NAME="name";
    private static final String COLUNA_SENHA="senha";

    private static Pet pet;

    private static SQLiteDatabase db;

    private static User user;
    private static SQLiteDatabase db_user;
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
                +COLUNA_CONTATO+ " TEXT, " + COLUNA_ESPECIE + " TEXT, " + COLUNA_SITUACAO + " TEXT, "
                + COLUNA_ID_TUTOR + " TEXT)";
    //aqui já criamos o nosso banco com essa tabela
        db.execSQL(QUERY_COLUNA);

        // SQLite, ao pormos a primeira solua com INTEGER PRIMARY KEY, ele já usa auto incremente
        String  QUERY_COLUNA_USER =" CREATE TABLE "+ TABELA_USER + "("
                + COLUNA_ID_USER + " INTEGER PRIMARY KEY, " + COLUNA_NAME + " TEXT, "
                + COLUNA_SENHA + " TEXT)";
        //aqui já criamos o nosso banco com essa tabela
        db.execSQL(QUERY_COLUNA_USER);

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
        values.put(COLUNA_SITUACAO , pet.getSituacao());
        values.put(COLUNA_ID_TUTOR , pet.getId_tutor());

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
                COLUNA_NOME, COLUNA_IDADE, COLUNA_DESC, COLUNA_CONTATO, COLUNA_ESPECIE, COLUNA_SITUACAO,COLUNA_ID_TUTOR}, COLUNA_ID + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            //mover para a primeira posição
            cursor.moveToFirst();

        }
        pet= new Pet(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)) );


        Log.d("Banco","Lido com sucesso");
        return pet;

    }


    /*
    * private static final String TABELA_USER ="tb_user";
    private static final String COLUNA_ID_USER="id";
    private static final String COLUNA_NAME="name";
    private static final String COLUNA_SENHA="senha";

    * */
    User selecionarUser(int id){
        //agora queremos ler o data base em vez de escrever
        Log.d("Banco","Selecionando user");
        db=this.getReadableDatabase();


        Cursor cursor=db.query(TABELA_USER, new String[] {COLUNA_ID_USER,
                        COLUNA_NAME, COLUNA_SENHA}, COLUNA_ID_USER + " = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            //mover para a primeira posição
            cursor.moveToFirst();

        }

        user= new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2) );


        Log.d("Banco","User Lido com sucesso");
        return user;

    }



    void atualizarPet( Pet pet){

        db = this.getWritableDatabase();
        values = new ContentValues();

        values.put(COLUNA_NOME , pet.getNome());
        values.put(COLUNA_IDADE , pet.getIdade());
        values.put(COLUNA_DESC , pet.getDescricao());
        values.put(COLUNA_CONTATO , pet.getContato());
        values.put(COLUNA_ESPECIE , pet.getEspecie());
        values.put(COLUNA_SITUACAO , pet.getSituacao());
        values.put(COLUNA_ID_TUTOR , pet.getId_tutor());

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


    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABELA_PET);
        db.close();
        return count;
    }

    void teste(){
        Log.d("APITESTE","safada");
    }


//CRUD USER
    void addUser(User user){

        //como já estamos numa classe SQL já temos o banco e o contexto
        //aqui iniciamos a escrita no banco
        db=this.getWritableDatabase();
        //
        values = new ContentValues();
        //vamos agora passar os parametros para o banco
        values.put(COLUNA_NAME , user.getName());
        values.put(COLUNA_SENHA , user.getSenha());


        db.insert(TABELA_USER, null,values);
        Log.d("Banco","ADICIONADO USER COM SUCESSO");
        db.close();

    }

}
