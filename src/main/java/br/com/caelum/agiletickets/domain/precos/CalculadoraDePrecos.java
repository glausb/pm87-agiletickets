package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {


	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {

		BigDecimal preco;
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();

		if (TipoDeEspetaculo.CINEMA.equals(tipo)){
			preco = TipoDeEspetaculo.CINEMA.calcula(sessao);
		} else if (TipoDeEspetaculo.SHOW.equals(tipo)){
			preco = TipoDeEspetaculo.SHOW.calcula(sessao);
		}else if (TipoDeEspetaculo.BALLET.equals(tipo)){
			preco = TipoDeEspetaculo.BALLET.calcula(sessao);
		}else if(TipoDeEspetaculo.ORQUESTRA.equals(tipo)){
			preco = TipoDeEspetaculo.ORQUESTRA.calcula(sessao);
		}else{
			preco = TipoDeEspetaculo.TEATRO.calcula(sessao);
		}
		
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
		
	}

}