package br.com.mfelix.mobile.petamigo;
import java.io.Serializable;
import java.util.Objects;
public class Pet implements Serializable{
/**id
 *nome
 *idade
 *foto
 *descriçao
 *contato_para_adocao*/

    private Integer id;
    private String nome;
    private String idade;
    private String descricao;
    private String contato;
    private String especie;
    private String situacao;
    private Integer id_tutor;



    public Pet(Integer id, String nome, String idade, String descricao, String contato, String especie, String situacao,Integer id_tutor) {
        //com id pra update
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.contato = contato;
        this.especie = especie;
        this.situacao = situacao;
        this.id_tutor = id_tutor;
    }

    public Pet() {
        //usado para instancias
    }



    public Pet(String nome, String idade, String descricao, String contato, String especie, String situacao,Integer id_tutor) {
        //sem id, apenas para o insert, pois o id vai ser auto incremente
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.contato = contato;
        this.especie = especie;
        this.situacao = situacao;
        this.id_tutor = id_tutor;

    }



    public Integer getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(Integer id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(nome, pet.nome) &&
                Objects.equals(descricao, pet.descricao) &&
                Objects.equals(contato, pet.contato) &&
                Objects.equals(idade, pet.idade);
    }


}
