package br.ufsc.ine.leb.projetos.estoria.testes.figuracao.classes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClasseComUmMetodoDeTesteFalhandoExcecaoEsperada {

	@Test(expected = UnsupportedOperationException.class)
	public void testar() {
		assertTrue(true);
	}

}
