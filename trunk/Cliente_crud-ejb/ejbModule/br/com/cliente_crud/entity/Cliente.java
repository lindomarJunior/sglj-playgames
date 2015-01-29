package br.com.cliente_crud.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CLIENTE")
	private Integer id;
	
	@Column(name="NOME_CLIENTE")
	private String nome;
	
	@Column(name="SEXO_CLIENTE")
	private String sexo;
	
	@Column(name="LOGIN_CLIENTE")
	private String login;
	
	@Column(name="CPF_CLIENTE")
	private Long cpf;
	
	@Column(name="TELEFONE_CLIENTE")
	private String telefone;
	
	@Column(name="DATA_NASC_CLIENTE")
	private Calendar dataNasc;
	
	@Column(name="ENDERECO_CLIENTE")
	private String endereco;
	
	@Column(name="CIDADE_CLIENTE")
	private String cidade;
	
	@Column(name="ESTADO_CLIENTE")
	private String estado;
	
	@Column(name="OBSERVACAO")
	private String observacao;
	
	//gets and sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
