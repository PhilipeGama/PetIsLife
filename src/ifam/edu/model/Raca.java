package ifam.edu.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "raca")
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    private Especie especie;

    @OneToMany(mappedBy = "raca")
    private List<Pet> pets;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void addPets(Pet pet) {
        if(!this.pets.contains(pet)){
            this.pets.add(pet);
            pet.setRaca(this);
        }
    }
}
