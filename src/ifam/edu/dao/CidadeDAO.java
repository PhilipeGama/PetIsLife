package ifam.edu.dao;

import ifam.edu.model.Cidade;
import ifam.edu.model.Estado;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CidadeDAO {
    public void salvar(Cidade cidade){
        EntityManager entityManager = JPAUtil.getEntityManager();


        entityManager.getTransaction().begin();

        entityManager.persist(cidade);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Cidade consultar(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade cidade = entityManager.find(Cidade.class, id);
        entityManager.close();
        return cidade;
    }

    public void remover(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade cidade = entityManager.find(Cidade.class, id);

        entityManager.getTransaction().begin();
        entityManager.remove(cidade);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Cidade> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery("select e from estado e");

        List<Cidade> cidades = query.getResultList();

        return cidades;
    }


}
