package br.com.caelum.agiletickets.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SessaoTest {

	Sessao sessao;
	
	@Before
	public void init(){
		sessao = new Sessao();
	}
	
	//@After
	// Roda após os testes
	
	//@BeforeClass
	// Roda antes de classe
	
	//@AfterClass
	// Roda após a classe
	
	@Test
	public void deveVenderQuantidadeIngressosMenoresQueQuantidadeVagas() throws Exception {
		
		sessao.setTotalIngressos(10);
		
		Assert.assertTrue(sessao.podeReservar(5));
	}

	@Test
	public void naoDeveVenderIngressosAMaisQueNumeroVagas() throws Exception {
		
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	@Test
	public void reservaTotalQuantidadeIngresso(){
		
		sessao.setTotalIngressos(3);
		
		Assert.assertTrue(sessao.podeReservar(3));
		
	}
	
	
}
