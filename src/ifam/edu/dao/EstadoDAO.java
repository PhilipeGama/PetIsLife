package ifam.edu.dao;

import ifam.edu.model.Estado;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EstadoDAO {

    private EntityManager entityManager;

    public EstadoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(Estado estado){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(estado);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    public Estado consultar(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Estado estado = entityManager.find(Estado.class, id);
        entityManager.close();
        return estado;
    }

    public void remover(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();
        Estado estado = entityManager.find(Estado.class, id);
        entityManager.remove(estado);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Estado> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery("select e from estado e");

        List<Estado> estados = query.getResultList();

        return estados;
    }
}
