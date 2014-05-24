package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private Sessao sessao;

	private BigDecimal calculaCinemaShow(Integer quantidade) {
		BigDecimal preco;
		if (estaAcabandoIngressos (0.05)) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private BigDecimal calculaTeatro(Integer quantidade) {
		BigDecimal preco;
		// nao aplica aumento para teatro (quem vai é pobretão)
		preco = sessao.getPreco();
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private BigDecimal calculaBallet(Integer quantidade) {
		BigDecimal preco;
		if (estaAcabandoIngressos (0.50)) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}

		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(0.10)));
		}
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private BigDecimal calculaOrquestra(Integer quantidade) {
		BigDecimal preco;
		if (estaAcabandoIngressos (0.50)) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}

		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(0.10)));
		}
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}
	
	private boolean estaAcabandoIngressos(double limite){
		boolean estaAcabando = (sessao.getTotalIngressos() - sessao.getIngressosReservados()) 
		/ sessao.getTotalIngressos().doubleValue() <= limite;
		return estaAcabando;
	}

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		
		BigDecimal preco;
		CalculadoraDePrecos calculadora = new CalculadoraDePrecos();
		calculadora.sessao = sessao;
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();

		if (tipo.equals(TipoDeEspetaculo.CINEMA)
				|| tipo.equals(TipoDeEspetaculo.SHOW)) {
			// quando estiver acabando os ingressos...
			preco = calculadora.calculaCinemaShow(quantidade);

		} else if (tipo	.equals(TipoDeEspetaculo.BALLET)) {
			preco = calculadora.calculaBallet(quantidade);
		} else if (tipo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calculadora.calculaOrquestra(quantidade);
		} else {
			preco = calculadora.calculaTeatro(quantidade);
		}

		return preco;
	}

}