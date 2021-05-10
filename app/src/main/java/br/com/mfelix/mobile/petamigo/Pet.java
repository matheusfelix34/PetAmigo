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
    private Integer idade;
    private String descricao;
    private String contato;
    private String especie;

    public Pet(Integer id, String nome, Integer idade, String descricao, String contato, String especie) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.contato = contato;
        this.especie = especie;
    }

    public Pet() {
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
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
