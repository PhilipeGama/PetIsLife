package ifam.edu;

import ifam.edu.dao.PessoaDAO;
import ifam.edu.dao.PetDAO;
import ifam.edu.model.*;
import ifam.edu.util.JPAUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void inserirPet(){
        EntityManager em = JPAUtil.getEntityManager();

        Especie especie1 = new Especie();
        especie1.setNome("Cachorro");

        Raca raca1 = new Raca();
        raca1.setEspecie(especie1);
        raca1.setNome("Chiyaya");

        Pet pet1 = new Pet();
        pet1.setNome("Toto");
        pet1.setSexo(SexoPetENUM.MACHO);
        pet1.setNascimento(LocalDateTime.of(1990,01,1,10,10,10));


        Pet pet2 = new Pet();
        pet2.setNome("Au Au");
        pet2.setSexo(SexoPetENUM.MACHO);
        pet2.setNascimento(LocalDateTime.of(1990,01,1,10,10,10));


        pet1.setRaca(raca1);
        pet2.setRaca(raca1);

        em.getTransaction().begin();

        em.persist(especie1);

        em.persist(raca1);

        em.persist(pet1);
        em.persist(pet2);
        em.getTransaction().commit();

        em.close();
    }

    public static void inserirPessoaComCidadeNoBD() {
        EntityManager em = JPAUtil.getEntityManager();

        Pais pais1 = new Pais();
        pais1.setCodigoISO("BR1234");
        pais1.setNome("Brasil");



        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setCodigoIBGE("AM11111");
        estado.setPais(pais1);

        Cidade cidade = new Cidade();
        cidade.setCodigoIBGE("MA1111");
        cidade.setEstado(estado);
        cidade.setNome("Manaus");

        //Transient
        Pessoa p1 = new Pessoa();
        p1.setNome("Paula");
        p1.setDocumento("2212112");
        p1.setTelefone("3113-5678");
        p1.setSexo(SexoEnum.FEMININO);

        Pessoa p2 = new Pessoa();
        p2.setNome("Joao");
        p2.setDocumento("221s22");
        p2.setTelefone("3113-5678");
        p2.setSexo(SexoEnum.MASCULINO);





        p1.setCidade(cidade);
        p2.setCidade(cidade);


        //Persintindo no BD
        em.getTransaction().begin();


        //Persistindo Pais (Managed)
        em.persist(pais1);


        //Persistindo Estado (Managed)
        em.persist(estado);

        //Persistindo Cidade (Managed)
        em.persist(cidade);

        //cidade.setNome("Belem Atualizado");

        //Persistindo Pessoa (Managed)
       em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

        em.close();
    }


    public  static void addPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Peter");
        pessoa.setDocumento("PETER123");

        PessoaDAO dao = new PessoaDAO();
        dao.salvar(pessoa);
    }
    public static void listPessoa(){
        PessoaDAO dao = new PessoaDAO();
        List<Pessoa> pessoas = dao.listar();
        for(Pessoa p: pessoas){
            System.out.println(p.toString());
        }
    }

    public  static void consultarPessoa(){
        PessoaDAO dao = new PessoaDAO();
        Pessoa p = dao.consultar(1);
        System.out.println(p);
    }

    public  static void deletePessoa(){
        PessoaDAO dao = new PessoaDAO();
        dao.remover(1);
    }

    public  static void addPet(){
        Pet pet = new Pet();
        pet.setNome("TotoMaluco");

        PetDAO dao = new PetDAO();
        dao.salvar(pet);
    }
    public static void listPet(){
        PetDAO dao = new PetDAO();
        List<Pet> pets = dao.listar();
        for(Pet p: pets){
            System.out.println(p.toString());
        }
    }

    public  static void consultarPet(){
        PetDAO dao = new PetDAO();
        Pet p = dao.consultar(1);
        System.out.println(p);
    }

    public  static void deletePet(){
        PetDAO dao = new PetDAO();
        dao.remover(1);
    }


    public static void main(String[] args) {
//        inserirPessoaComCidadeNoBD();
        //        inserirPet();
        //Pessoa
//        addPessoa();

//        listPessoa();
//        consultarPessoa();
//        deletePessoa();


        //Pet

//        addPet();

//        listPet();
        consultarPet();
//        deletePet();
    }


}

