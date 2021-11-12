package ifam.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
//Generics
public class DAOGenerico<Tipo> {

    private EntityManager entityManager;
    private Class<Tipo> classe;


    public DAOGenerico(EntityManager entityManager, Class<Tipo> classe) {
        this.entityManager = entityManager;
        this.classe=classe;
    }

    public void salvar(Tipo object){

        entityManager.persist(object);

    }

    public Tipo consultar(Integer id){

        Tipo object=entityManager.find(classe,id);

        return object;
    }

    public void remover(Integer id){

        Tipo object = entityManager.find(classe,id);

        entityManager.remove(object);

    }

    public List<Tipo> listar(){

        Query query = entityManager.createQuery("select o from "+classe.getName()+" o");

        List<Tipo> objetos=query.getResultList();

        return objetos;

    }

}
