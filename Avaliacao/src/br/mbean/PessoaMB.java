package br.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dao.PessoaDAO;
import br.model.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaMB {
	private Pessoa pessoa;
	private List<Pessoa> pessoaLista;
	private PessoaDAO pessoaDAO;

	@PostConstruct
	public void inicial() {
		pessoaDAO = new PessoaDAO();
		pessoa = new Pessoa();
		pessoaLista = new ArrayList<Pessoa>();
		//pessoaLista = pessoaDAO.listar();

	}

	public String salvar() {
		pessoaDAO.atualizar(pessoa);
		novaPessoa();
		pessoaLista = pessoaDAO.listar();
		return "";
	}

	private void novaPessoa() {
		pessoa = new Pessoa();
	}

	public String excluir() {
		pessoaDAO.excluir(pessoa);
		novaPessoa();
		pessoaLista = pessoaDAO.listar();
		return "";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoaLista() {
		return pessoaLista;
	}

	public void setPessoaLista(List<Pessoa> pessoaLista) {
		this.pessoaLista = pessoaLista;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

}
