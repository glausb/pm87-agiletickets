package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.domain.precos.CalculadoraDePrecos;

public enum TipoDeEspetaculo {

	CINEMA {

		@Override
		public BigDecimal calcula(Sessao sessao) {
			return calculaCinemaShow(sessao);

		}

	},

	SHOW {

		@Override
		public BigDecimal calcula(Sessao sessao) {
			return calculaCinemaShow(sessao);
		}

	},

	TEATRO {

		@Override
		public BigDecimal calcula(Sessao sessao) {
			return calculaTeatro(sessao);
		}

	},
	BALLET {

		@Override
		public BigDecimal calcula(Sessao sessao) {
			return calculaOrquestraBallet(sessao);
		}

	},
	ORQUESTRA {

		@Override
		public BigDecimal calcula(Sessao sessao) {
			return calculaOrquestraBallet(sessao);
		}

	},
	;

	public abstract BigDecimal calcula(Sessao sessao);

	private static BigDecimal calculaCinemaShow(Sessao sessao) {
		BigDecimal preco;
		if (TipoDeEspetaculo.estaAcabandoIngressos(sessao, 0.05)) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	private static BigDecimal calculaTeatro(Sessao sessao) {
		BigDecimal preco;
		// nao aplica aumento para teatro (quem vai é pobretão)
		preco = sessao.getPreco();
		return preco;
	}

	private static BigDecimal calculaOrquestraBallet(Sessao sessao) {
		BigDecimal preco;
		if (estaAcabandoIngressos(sessao, 0.50)) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}

		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static boolean estaAcabandoIngressos(Sessao sessao, double limite) {
		boolean estaAcabando = (sessao.getTotalIngressos() - sessao
				.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue() <= limite;
		return estaAcabando;
	}

}
