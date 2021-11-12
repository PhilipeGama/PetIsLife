package ifam.edu.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Enumerated(EnumType.ORDINAL)
	private SexoEnum sexo;

	@Column(unique = true, nullable = false)
	private String documento;


	private String telefone;

	private String email;

	private LocalDateTime dataNascimento;

	private String endereco;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Cidade cidade;

	@Enumerated(EnumType.STRING)
	private PessoaEnum tipo;

	public Pessoa() {
	}

	public Pessoa(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}



	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		cidade.addPessoa(this);
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public PessoaEnum getTipo() {
		return tipo;
	}

	public void setTipo(PessoaEnum tipo) {
		this.tipo = tipo;
	}

	//	public List<Interesse> getInteresses() {
//		return interesses;
//	}
//
//	public void addInteresse(Interesse interesse) {
//		if(!this.interesses.contains(interesse)){
//			this.interesses.add(interesse);
//		}
//
//	}


	@Override
	public String toString() {
		return "Pessoa{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", sexo=" + sexo +
				", documento='" + documento + '\'' +
				", telefone='" + telefone + '\'' +
				", email='" + email + '\'' +
				", dataNascimento=" + dataNascimento +
				", endereco='" + endereco + '\'' +
				", cidade=" + cidade +
				", tipo=" + tipo +
				'}';
	}
}
