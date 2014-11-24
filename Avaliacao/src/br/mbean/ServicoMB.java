package br.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dao.ServicoDAO;
import br.model.Servico;

@ManagedBean
@ViewScoped
public class ServicoMB {
	private Servico servico;
	private List<Servico> servicoLista;
	private ServicoDAO servicoDAO;

	@PostConstruct
	public void inicial() {
		servicoDAO = new ServicoDAO();
		servico = new Servico();
		servicoLista = new ArrayList<Servico>();
		//pessoaLista = pessoaDAO.listar();

	}

	public String salvar() {
		servicoDAO.atualizar(servico);
		novaServico();
		servicoLista = servicoDAO.listar();
		return "";
	}

	private void novaServico() {
		servico = new Servico();
	}

	public String excluir() {
		servicoDAO.excluir(servico);
		novaServico();
		servicoLista = servicoDAO.listar();
		return "";
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicoLista() {
		return servicoLista;
	}

	public void setServicoLista(List<Servico> servicoLista) {
		this.servicoLista = servicoLista;
	}

	public ServicoDAO getServicoDAO() {
		return servicoDAO;
	}

	public void setServicoDAO(ServicoDAO servicoDAO) {
		this.servicoDAO = servicoDAO;
	}

}
