package ifam.edu;

import ifam.edu.dao.CidadeDAO;
import ifam.edu.dao.DAOGenerico;
import ifam.edu.dao.EstadoDAO;
import ifam.edu.dao.PessoaDAO;
import ifam.edu.model.*;
import ifam.edu.util.JPAUtil;
import org.hibernate.type.LocalDateType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void inserirPessoaComCidadeNoBD() {
        EntityManager em = JPAUtil.getEntityManager();

        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setSigla("AM");

        //Transient
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Paula");
        pessoa.setDocumento("2212112");
        pessoa.setTelefone("3113-5678");
        pessoa.setSexo(SexoEnum.FEMININO);

        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        cidade.setNome("Manaus");



        pessoa.setCidade(cidade);


        //Persintindo no BD
        em.getTransaction().begin();

        //Persistindo Cidade (Managed)
        em.persist(estado);

        //Persistindo Cidade (Managed)
        em.persist(cidade);

        //cidade.setNome("Belem Atualizado");

        //Persistindo Pessoa (Managed)
       em.persist(pessoa);
        em.getTransaction().commit();

        em.close();
    }

    public static void consultar() {
        EntityManager em = JPAUtil.getEntityManager();
        Cidade cidade = em.find(Cidade.class, 1);

        System.out.println("Cidade - Id" + cidade.getId());
        System.out.println("Cidade - Nome" + cidade.getNome());

        Pessoa pessoa = em.find(Pessoa.class, 1);

        System.out.println("Pessoa - Id" + cidade.getId());
        System.out.println("Pessoa - Nome" + pessoa.getNome());
        System.out.println("Pessoa - Cidade" + pessoa.getCidade().getNome());
        System.out.println("Pessoa - Estado" + pessoa.getCidade().getEstado().getNome());
    }

    private static void remover() {

        //Removendo do BD

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa = em.find(Pessoa.class, 1);
        em.remove(pessoa);
        em.getTransaction().commit();
    }

    public static void inserirEstadoAtravesDoDao() {

        Estado estado = new Estado();
        estado.setSigla("SC");
        estado.setNome("Espírito Santos");

        EntityManager entityManager = JPAUtil.getEntityManager();

        //Injeção de Dependência (Dependece Injection - DI)
        EstadoDAO dao = new EstadoDAO(entityManager);

        entityManager.getTransaction().begin();
        dao.salvar(estado);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static void consultarEstadoAtravesDoDao() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        entityManager.getTransaction().begin();

        Estado estado = dao.consultar(22);
        System.out.println(estado.toString());

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    public static void removerEstadoAtravesDoDao() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        entityManager.getTransaction().begin();

        dao.remover(20);

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    public static void listarEstadoAtravesDoDao() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        EstadoDAO dao = new EstadoDAO(entityManager);

        List<Estado> estados = dao.listar();

        for (Estado estado : estados) {
            System.out.println(estado.toString());
        }
        entityManager.close();

    }

    public static void inserirAtravesDAOGenerico() {

        Estado estado = new Estado();
        estado.setSigla("RJ");
        estado.setNome("Rio de Janeiro");

        EntityManager entityManager = JPAUtil.getEntityManager();

        //Injeção de Dependência (Dependece Injection - DI)
        DAOGenerico<Estado> estadoDAO = new DAOGenerico<>(entityManager, Estado.class);

        entityManager.getTransaction().begin();
        estadoDAO.salvar(estado);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static void listaPorNomeAtravesPessoaDAO(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        PessoaDAO pessoaDAO = new PessoaDAO(entityManager);
        List<Pessoa> pessoas = pessoaDAO.listarPorNomeParcial("P");

        for(Pessoa p:pessoas){
            System.out.println(p);
        }
        entityManager.close();;
    }


    public static void testandoEnumeration(){
        Pessoa pessoa1 = new Pessoa();

        pessoa1.setNome("Jose");
        pessoa1.setTelefone("3222-1156");
        pessoa1.setSexo(SexoEnum.MASCULINO);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Julianna");
        pessoa2.setSexo(SexoEnum.FEMININO);

        System.out.println("Pessoa: "+pessoa1.getNome()+", "+pessoa1.getSexo());
        System.out.println("Pessoa: "+pessoa2.getNome()+", "+pessoa2.getSexo());
    }

    public static  void testandoFindGetReference(){
        EntityManager entityManager = JPAUtil.getEntityManager();

         //GetReference - Lazy

//        Pessoa pessoaGetReference = entityManager.getReference(Pessoa.class, 3);
//
//        System.out.println("Executei o GetReference.");
//        System.out.println(pessoaGetReference);
//
//        System.out.println("Mostrei a pessoa depois do GetReference");

        //Find
        Pessoa pessoaFind = entityManager.find(Pessoa.class,3);
        System.out.println("Executei o Find");

        System.out.println(pessoaFind);
        System.out.println("Mostrei a pessoa depois do Find");

    }

    public static  void testandoMergeCase1(){
        EntityManager entityManager = JPAUtil.getEntityManager();
        Pessoa pessoa = entityManager.find(Pessoa.class, 3);

        System.out.println("Nome: "+pessoa.getNome());
        entityManager.getTransaction().begin();

        entityManager.detach(pessoa);

        pessoa.setNome("Paula alterada");

        //Managed
        Pessoa pessoaMerge = entityManager.merge(pessoa);
        pessoaMerge.setNome("Juliana - Merge");
        pessoa.setNome("Juliana - Detach");


        System.out.println("Pessoa Original: "+pessoa);
        System.out.println("Pessoa Original: "+pessoaMerge);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static  void testandoMergeCase2(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Pessoa");
        pessoa.setDocumento("12345");



        //Managed
        Pessoa pessoaMerge = entityManager.merge(pessoa);
        pessoaMerge.setNome("João Paula - Merge");
        pessoa.setNome("Joao Paulo - Original");


        System.out.println("Pessoa Original: "+pessoa);
        System.out.println("Pessoa Original: "+pessoaMerge);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static void  testaPessoaCidadeAssociacao(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Cidade c1 = new Cidade();

        c1.setNome("Floripa");

        Pessoa p1 = new Pessoa();
        p1.setNome("Marianna de Floripa");
        p1.setDocumento("A12345");
        p1.setCidade(c1);

        Pessoa p2 = new Pessoa();
        p2.setNome("João de Floripa");
        p2.setDocumento("B12345");
        p2.setCidade(c1);

        entityManager.persist(p1);
        entityManager.persist(p2);


        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public  static void testaCidadePessoaListOneToMany(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Cidade cidade = entityManager.find(Cidade.class,2);

        System.out.println("Cidade: "+cidade);
        for (Pessoa p: cidade.getPessoas()){
            System.out.println("Pessoa: "+p.getNome());
        }
    }

    public static void testarPessoaComNascimento(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        Cidade cidade = entityManager.find(Cidade.class, 2);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alan");
        pessoa.setCidade(cidade);
        pessoa.setDocumento("FF123456");
        pessoa.setSexo(SexoEnum.MASCULINO);
        pessoa.setTelefone("999999-99991");

        pessoa.setDataNascimento(LocalDateTime.of(1990,01,1,10,10,10));
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();
        entityManager.close();


    }


    public static  void testaPessoaComInteresse(){

        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();
        Interesse i1 = new Interesse("Musica");
        Interesse i2 = new Interesse("Game");
        Interesse i3 = new Interesse("Esporte");

        Cidade c1 = entityManager.find(Cidade.class,2);

        Pessoa p1 = new Pessoa();
        p1.setNome("Zuleide");
        p1.setDocumento("ZU00001");
        p1.setCidade(c1);
        p1.addInteresse(i1);
        p1.addInteresse(i2);
        p1.addInteresse(i3);

        entityManager.persist(p1);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public static void addPessoaComInteresse(){
        EntityManager entityManager = JPAUtil.getEntityManager();


        Pessoa p1 = new Pessoa();
        p1.setDocumento("1");
        p1.setNome("João Paulo");
        Interesse i1 = new Interesse("Games");

        p1.addInteresse(i1);

        entityManager.getTransaction().begin();

        entityManager.persist(p1);
        entityManager.persist(i1);

        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = query.getResultList();
        for (Interesse i: interesses){
            System.out.println(i.getDescriçao());
        }

        entityManager.close();

    }

    public static void addInteresseComPessoa(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Interesse i1 = new Interesse("Futebol");
        Pessoa p1 = new Pessoa();
        p1.setDocumento("2");
        p1.setNome("Maria Gadooo");

        i1.addPessoas(p1);
        entityManager.getTransaction().begin();
        entityManager.persist(p1);
        entityManager.persist(i1);
        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = query.getResultList();
        for (Interesse i: interesses){
            System.out.println(i.getDescriçao());
        }

        entityManager.close();

    }

    public static void addPessoaComInteresses(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Interesse i1 = new Interesse("Naruto");
        Interesse i2 = new Interesse("Goku");
        Interesse i3 = new Interesse("Vegeta");

        Pessoa p1 = new Pessoa();
        p1.setDocumento("3");
        p1.setNome("João Kleber");

        i1.addPessoas(p1);
        i2.addPessoas(p1);
        i3.addPessoas(p1);

        entityManager.getTransaction().begin();
        entityManager.persist(p1);
        entityManager.persist(i1);

        entityManager.persist(i2);
        entityManager.persist(i3);

        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = query.getResultList();
        for (Interesse i: interesses){
            System.out.println(i.getDescriçao());
        }

        entityManager.close();

    }

    public static void addInteresseComPessoas(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Interesse i1 = new Interesse("Futebol");
        Pessoa p1 = new Pessoa();
        p1.setDocumento("2");
        p1.setNome("Maria Gadooo");

        i1.addPessoas(p1);
        entityManager.getTransaction().begin();
        entityManager.persist(p1);
        entityManager.persist(i1);
        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = query.getResultList();
        for (Interesse i: interesses){
            System.out.println(i.getDescriçao());
        }

        entityManager.close();

    }

    public static void listPessoaComInteresse(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = query.getResultList();
        System.out.println(interesses);

        for (Interesse i: interesses){
            System.out.println("--------------------------------");
            System.out.println("Interesse:"+i.getDescriçao());
            List<Pessoa> pessoas = i.getPessoas();
            for (Pessoa p: pessoas){
                System.out.println("Pessoa: "+p.getNome());
            }
        }

        entityManager.close();

    }

    public static void testlistLazy(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query queryInteresse = entityManager.createQuery("select i from interesse i");

        List<Interesse> interesses = queryInteresse.getResultList();

        System.out.println("##########\nInteresse Pessoa\n###########");
        for (Interesse i: interesses){
            System.out.println("--------------------------------");
            System.out.println("Interesse:"+i.getDescriçao());
            List<Pessoa> pessoas = i.getPessoas();
            for (Pessoa p: pessoas){
                System.out.println("Pessoa: "+p.getNome());
            }
        }

        entityManager.close();

    }

    public static void testlistEagly(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query queryPessoa = entityManager.createQuery("select p from pessoa p");

        List<Pessoa> pessoa = queryPessoa.getResultList();
        System.out.println("##########\nPessoa Interesse\n###########");

        System.out.println("Interesse Pessoa");
        for (Pessoa p: pessoa){
            System.out.println("--------------------------------");
            System.out.println("Interesse:"+p.getNome());
            List<Interesse> interesse = p.getInteresses();
            for (Interesse i: interesse){
                System.out.println("Interesse: "+i.getDescriçao());
            }
        }

        entityManager.close();

    }


    public static void main(String[] args) {
// Pessoa DAO
      //  inserirPessoaComCidadeNoBD();

//      Estado DAO
//        inserirEstadoAtravesDoDao();
//        consultarEstadoAtravesDoDao();
//        removerEstadoAtravesDoDao();
//        listarEstadoAtravesDoDao();

        //Generico DAO


//      DAO Generic
//        inserirAtravesDAOGenerico();
//        listaPorNomeAtravesPessoaDAO();


// Outros testes
//        testandoEnumeration();
//        testandoFindGetReference();
//        testandoMergeCase1();
//        testandoMergeCase2();
//        testaPessoaCidadeAssociacao();
//        testarPessoaComNascimento();


//testaPessoaComInteresse();
//addInteresseComPessoas();
//addPessoaComInteresse();
//addPessoaComInteresses();
//testlistEaglyLazy();
testlistEagly();
    }

}

