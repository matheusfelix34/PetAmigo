package br.com.mfelix.mobile.petamigo;

import java.util.Objects;
public class User {

    private Integer id;
    private String name;
    private String senha;

    public User(Integer id, String name, String senha) {
        this.id = id;
        this.name = name;
        this.senha = senha;
    }
    public User( String name, String senha) {
        this.name = name;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(senha, user.senha);
    }
}
