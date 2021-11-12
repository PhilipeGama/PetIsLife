package ifam.edu.dao;

import ifam.edu.model.Pessoa;
import ifam.edu.model.Pet;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PetDAO {
    public void salvar(Pet pet){
        EntityManager entityManager = JPAUtil.getEntityManager();


        entityManager.getTransaction().begin();

        entityManager.persist(pet);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Pet consultar(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pet pet = entityManager.find(Pet.class, id);
        entityManager.close();
        return pet;
    }

    public void remover(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Pet pet = entityManager.find(Pet.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(pet);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Pet> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery("select e from pet e");

        List<Pet> pets = query.getResultList();

        return pets;
    }

    public List<Pet> listarPorPetNomeParcial(String nome){
        EntityManager entityManager = JPAUtil.getEntityManager();
        Query query = entityManager.createQuery("select p from pet p where p.nome like :parNome");

        query.setParameter("parNome","%"+nome+"%");

        List<Pet> objetos = query.getResultList();
        return objetos;
    }

}
