package ifam.edu.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private SexoPetENUM sexo;
    private LocalDateTime nascimento;

    @ManyToOne
    private Raca raca;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pessoa> proprietarios = new ArrayList<>();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoPetENUM getSexo() {
        return sexo;
    }

    public void setSexo(SexoPetENUM sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDateTime nascimento) {
        this.nascimento = nascimento;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public List<Pessoa> getProprietarios() {
        return proprietarios;
    }

    public void addProprietarios(Pessoa proprietario) {
        this.proprietarios.add(proprietario);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", nascimento=" + nascimento +
                ", raca=" + raca +
                ", proprietarios=" + proprietarios +
                '}';
    }
}
