package ifam.edu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "interesse")
public class Interesse {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String descriçao;

    @ManyToMany(mappedBy = "interesses")
    private List<Pessoa> pessoas = new ArrayList<>();

    public Interesse() {
    }

    public Interesse(String descriçao) {
        this.descriçao = descriçao;
    }

    public String getDescriçao() {
        return descriçao;
    }

    public void setDescriçao(String descriçao) {
        this.descriçao = descriçao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoas(Pessoa pessoa) {
        if(!this.pessoas.contains(pessoa)) {
            this.pessoas.add(pessoa);
            pessoa.addInteresse(this);
        }
    }

}
