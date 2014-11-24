package br.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orcamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Date dataOrcamento;
	private Date validadeOrcamento;
	private int pagamento;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@OneToMany
	private List<Servico> servLista;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataOrcamento() {
		return dataOrcamento;
	}

	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}

	public Date getValidadeOrcamento() {
		return validadeOrcamento;
	}

	public void setValidadeOrcamento(Date validadeOrcamento) {
		this.validadeOrcamento = validadeOrcamento;
	}

	public int getPagamento() {
		return pagamento;
	}

	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Servico> getServLista() {
		return servLista;
	}

	public void setServLista(List<Servico> servLista) {
		this.servLista = servLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataOrcamento == null) ? 0 : dataOrcamento.hashCode());
		result = prime * result + id;
		result = prime * result + pagamento;
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result
				+ ((servLista == null) ? 0 : servLista.hashCode());
		result = prime
				* result
				+ ((validadeOrcamento == null) ? 0 : validadeOrcamento
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		if (dataOrcamento == null) {
			if (other.dataOrcamento != null)
				return false;
		} else if (!dataOrcamento.equals(other.dataOrcamento))
			return false;
		if (id != other.id)
			return false;
		if (pagamento != other.pagamento)
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (servLista == null) {
			if (other.servLista != null)
				return false;
		} else if (!servLista.equals(other.servLista))
			return false;
		if (validadeOrcamento == null) {
			if (other.validadeOrcamento != null)
				return false;
		} else if (!validadeOrcamento.equals(other.validadeOrcamento))
			return false;
		return true;
	}

}
