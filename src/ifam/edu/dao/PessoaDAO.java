package ifam.edu.dao;

import ifam.edu.model.Pessoa;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PessoaDAO {

    private EntityManager entityManager;
    private DAOGenerico<Pessoa> dao;

    public PessoaDAO(EntityManager entityManager){
        this.entityManager = entityManager;
        dao = new DAOGenerico<>(entityManager,Pessoa.class);
    }

    private void salvar(Pessoa pessoa){
        dao.salvar(pessoa);
    }

    private void consultar(Integer id){
        dao.consultar(id);
    }

    public void remover(Integer id){
        dao.remover(id);
    }

    public List<Pessoa> lista(){
        return dao.listar();
    }

    public List<Pessoa> listarPorNome(String nome){
        Query query = entityManager.createQuery("select p from pessoa p where p.nome=:parNome");

        query.setParameter("parNome",nome);

        List<Pessoa> objetos = query.getResultList();
        return objetos;
    }

    public List<Pessoa> listarPorNomeParcial(String nome){
        Query query = entityManager.createQuery("select p from pessoa p where p.nome like :parNome");

        query.setParameter("parNome","%"+nome+"%");

        List<Pessoa> objetos = query.getResultList();
        return objetos;
    }


}
