package br.validador;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * Classe utilitária para validar CPF e CNPJ a anotação @FacesValidator torna
 * essa classe um validator, podendo assim ser utilizada através do xhtml
 * 
 */
@FacesValidator(value = "validadorCpfCnpj")
public class ValidadorCpfCnpj implements Validator {

	/**
	 * Recebe do contexto da tela um objeto com a String CPF ou CNPJ e realiza a
	 * validação
	 * 
	 * @param arg2
	 *            objeto contendo o CPF ou CNPJ
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String campoCpfCnpj = (String) arg2;
		String msg = "";
		
		if (campoCpfCnpj != null  && !campoCpfCnpj.trim().equals("")){
			if(campoCpfCnpj.length() < 11){
				msg += "CPF/CNPJ Inválido" ;
			} else {
				
				campoCpfCnpj = campoCpfCnpj.replace(".", "");
				campoCpfCnpj = campoCpfCnpj.replace("-", "");
				campoCpfCnpj = campoCpfCnpj.replace("/", "");
				
				boolean cpfOK = Validacao.validaCPF(campoCpfCnpj);
				boolean cnpjOk = Validacao.validaCNPJ(campoCpfCnpj) && Validacao.isNumeroRepetido(campoCpfCnpj);
				
				if(!cpfOK && !cnpjOk){
					msg += "CPF/CNPJ Inválido" ;
				}
			}

			if (msg.length() > 0) {
				FacesMessage message = new FacesMessage();
				message.setSummary(msg);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}

	@SuppressWarnings("all")
	private abstract static class Validacao {

		private static String calcDigVerif(String num) {
			Integer primDig, segDig;
			int soma = 0, peso = 10;
			for (int i = 0; i < num.length(); i++) {
				soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
			}
			if (soma % 11 == 0 | soma % 11 == 1) {
				primDig = new Integer(0);
			} else {
				primDig = new Integer(11 - (soma % 11));
			}
			soma = 0;
			peso = 11;
			for (int i = 0; i < num.length(); i++) {
				soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
			}
			soma += primDig.intValue() * 2;
			if (soma % 11 == 0 | soma % 11 == 1) {
				segDig = new Integer(0);
			} else {
				segDig = new Integer(11 - (soma % 11));
			}
			return primDig.toString() + segDig.toString();
		}

		private int calcSegDig(String cpf, int primDig) {
			int soma = 0, peso = 11;
			for (int i = 0; i < cpf.length(); i++) {
				soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
			}
			soma += primDig * 2;
			if (soma % 11 == 0 | soma % 11 == 1) {
				return 0;
			}
			return 11 - (soma % 11);
		}

		public static boolean validaCPF(String cpf) {
			if (cpf.length() != 11) {
				return false;
			}
			String numDig = cpf.substring(0, 9);
			return calcDigVerif(numDig).equals(cpf.substring(9, 11));
		}

		public static boolean validaCNPJ(String str_cnpj) {
			int soma = 0, aux, dig;
			
			if(str_cnpj.length() < 12){
				return false;
			}
			
			String cnpj_calc = str_cnpj.substring(0, 12);

			if (str_cnpj.length() != 14) {
				return false;
			}
			char[] chr_cnpj = str_cnpj.toCharArray();

			/* Primeira parte */
			for (int i = 0; i < 4; i++) {
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
				}
			}
			dig = 11 - (soma % 11);

			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

			/* Segunda parte */
			soma = 0;
			for (int i = 0; i < 5; i++) {
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
				}
			}
			for (int i = 0; i < 8; i++) {
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
				}
			}
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

			return str_cnpj.equals(cnpj_calc);
		}

		public static boolean isNumeroRepetido(String numero) {
			int repeticoes = 1;
			char primeiroNumero = numero.charAt(0);
			for (int i = 0; i < numero.length(); i++) {
				if (i == 0)
					continue;
				if (primeiroNumero == numero.charAt(i)) {
					repeticoes++;
				}
			}
			
			if (numero.length() == repeticoes){
				return false;
			}
			
			return true;
		}
	}
}
