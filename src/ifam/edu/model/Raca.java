package ifam.edu.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Raca {

    @Id
    @GeneratedValue
    private Integer id;

    private String nome;

    private Especie especie;
}
