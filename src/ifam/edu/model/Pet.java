package ifam.edu.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pet {
    private String nome;
    private SexoPetENUM sexo;
    private LocalDateTime nascimento;
    private List<Pessoa> proprietarios;
}
