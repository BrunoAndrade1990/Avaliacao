package br.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.dao.PessoaDAO;
import br.model.Pessoa;

@FacesConverter(value = "conversorDePessoas")
public class ConversorPessoas implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null && !valor.equals("")){
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa disciplina = pessoaDAO.buscarPorNome(valor);
			return disciplina;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null){
			Pessoa disciplina = (Pessoa) valor;
			return disciplina.getNome();
		}
		return null;
	}

}
