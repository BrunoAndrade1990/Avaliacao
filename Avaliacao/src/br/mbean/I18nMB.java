package br.mbean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class I18nMB {

	private String linguagem;
	private String pais;
	
	public String mudarIdioma(){
		if (!"".equals(pais)){
			mudarLocalidade(new Locale(linguagem, pais));
		}else {
			mudarLocalidade(new Locale(linguagem));
		}
		
		return null;
	}
	
	private void mudarLocalidade(Locale locale){
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(locale);
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}

