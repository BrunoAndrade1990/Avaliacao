package br.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dao.OrcamentoDAO;
import br.dao.PessoaDAO;
import br.model.Orcamento;
import br.model.Pessoa;
import br.model.Servico;

@ManagedBean
@ViewScoped
public class OrcamentoMB {
	private Orcamento orcamento;
	private List<Orcamento> orcamentoLista;
	private OrcamentoDAO orcamentoDAO;
	private List<Pessoa> listaPessoas;
	private List<Servico> listaServico;
	private Pessoa pessoa;
	private Servico servico;

	@PostConstruct
	public void inicial() {
		orcamentoDAO = new OrcamentoDAO();
		orcamento = new Orcamento();
		orcamentoLista = new ArrayList<Orcamento>();
		setPessoa(new Pessoa());
		orcamentoLista = orcamentoDAO.listar();

		listaPessoas = new ArrayList<Pessoa>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		listaPessoas = pessoaDAO.listar();
	}

	public String adicionaServico() {
		orcamento.getServLista().add(servico);
		listaServico.remove(servico);
		return "";
	}

	public String salvar() {
		orcamentoDAO.atualizar(orcamento);
		novoOrcamento();
		orcamentoLista = orcamentoDAO.listar();
		return "";
	}

	private void novoOrcamento() {
		orcamento = new Orcamento();
	}

	public String excluir() {
		orcamentoDAO.excluir(orcamento);
		novoOrcamento();
		orcamentoLista = orcamentoDAO.listar();
		return "";
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public List<Orcamento> getOrcamentoLista() {
		return orcamentoLista;
	}

	public void setOrcamentoLista(List<Orcamento> orcamentoLista) {
		this.orcamentoLista = orcamentoLista;
	}

	public List<Pessoa> getPessoaLista() {
		return listaPessoas;
	}

	public void setPessoaLista(List<Pessoa> pessoaLista) {
		this.listaPessoas = pessoaLista;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Servico> getListaServico() {
		return listaServico;
	}

	public void setListaServico(List<Servico> listaServico) {
		this.listaServico = listaServico;
	}

}
