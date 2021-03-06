package br.ufsc.ine.leb.projetos.estoria.testes;

import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFalha;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFinalizado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteIgnorado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteIniciado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTestesFinalizados;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTestesIniciados;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.JUnit4;

import br.ufsc.ine.leb.projetos.estoria.Notificacao;
import br.ufsc.ine.leb.projetos.estoria.TipoDeNotificacao;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes.ClasseDeTeste101;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes.ClasseDeTeste104;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes.SuiteDeTeste13;

@RunWith(JUnit4.class)
public final class TesteCombinadorDeNotificacao {

	private Notificacao testesIniciados;
	private Notificacao testeIniciado;
	private Notificacao testeFinalizado;
	private Notificacao testeIgnorado;
	private Notificacao testeFalha;
	private Notificacao testesFinalizados;

	@Rule public ExpectedException excecao = ExpectedException.none();

	@Before
	public void prepararCenario() {
		Description descricaoDoTeste = Description.createTestDescription(ClasseDeTeste101.class, "testar");
		Description descricaoDaSuite = Description.createSuiteDescription(SuiteDeTeste13.class);
		descricaoDaSuite.addChild(descricaoDoTeste);
		Failure falha = new Failure(descricaoDoTeste, new AssertionError("mensagem da exceção"));
		Result resultado = new Result();
		testesIniciados = new Notificacao(TipoDeNotificacao.TESTES_INICIADOS, descricaoDaSuite);
		testeIniciado = new Notificacao(TipoDeNotificacao.TESTE_INICIADO, descricaoDoTeste);
		testeFinalizado = new Notificacao(TipoDeNotificacao.TESTE_FINALIZADO, descricaoDoTeste);
		testeIgnorado = new Notificacao(TipoDeNotificacao.TESTE_IGNORADO, descricaoDoTeste);
		testeFalha = new Notificacao(TipoDeNotificacao.TESTE_FALHA, falha);
		testesFinalizados = new Notificacao(TipoDeNotificacao.TESTES_FINALIZADOS, resultado);
	}

	@Test
	public void combinaIniciados() throws Exception {
		assertThat(testesIniciados, combinaComTestesIniciados(SuiteDeTeste13.class));
	}

	@Test
	public void combinaIniciado() throws Exception {
		assertThat(testeIniciado, combinaComTesteIniciado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void combinaFinalizado() throws Exception {
		assertThat(testeFinalizado, combinaComTesteFinalizado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void combinaIgnorado() throws Exception {
		assertThat(testeIgnorado, combinaComTesteIgnorado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void combinaFalha() throws Exception {
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste101.class, "testar", AssertionError.class));
	}

	@Test
	public void combinaFalhaComMensagem() throws Exception {
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste101.class, "testar", AssertionError.class, "mensagem da exceção"));
	}

	@Test
	public void combinaFinalizados() throws Exception {
		assertThat(testesFinalizados, combinaComTestesFinalizados(0, 0, 0));
	}

	@Test
	public void naoCombinaIniciadoComIniciadoDeOutraClasse() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_INICIADO> ClasseDeTeste104.testar");
		excecao.expectMessage("but: <TESTE_INICIADO> ClasseDeTeste101.testar");
		assertThat(testeIniciado, combinaComTesteIniciado(ClasseDeTeste104.class, "testar"));
	}

	@Test
	public void naoCombinaIniciadoComIniciadoDeOutroMetodo() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_INICIADO> ClasseDeTeste101.testarFalhando");
		excecao.expectMessage("but: <TESTE_INICIADO> ClasseDeTeste101.testar");
		assertThat(testeIniciado, combinaComTesteIniciado(ClasseDeTeste101.class, "testarFalhando"));
	}

	@Test
	public void naoCombinaIniciadoComIniciados() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_INICIADO> ClasseDeTeste101.testar");
		excecao.expectMessage("but: <TESTES_INICIADOS> SuiteDeTeste13.class");
		assertThat(testesIniciados, combinaComTesteIniciado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void naoCombinaIniciadoComFinalizado() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_INICIADO> ClasseDeTeste101.testar");
		excecao.expectMessage("but: <TESTE_FINALIZADO> ClasseDeTeste101.testar");
		assertThat(testeFinalizado, combinaComTesteIniciado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void naoCombinaIniciadoComIgnorado() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_INICIADO> ClasseDeTeste101.testar");
		excecao.expectMessage("but: <TESTE_IGNORADO> ClasseDeTeste101.testar");
		assertThat(testeIgnorado, combinaComTesteIniciado(ClasseDeTeste101.class, "testar"));
	}

	@Test
	public void naoCombinaFalhaComFalhaDeOutraClasse() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_FALHA> ClasseDeTeste104.testar lança AssertionError.class [*]");
		excecao.expectMessage("but: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [mensagem da exceção]");
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste104.class, "testar", AssertionError.class));
	}

	@Test
	public void naoCombinaFalhaComFalhaDeOutroMetodo() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_FALHA> ClasseDeTeste101.testarFalhando lança AssertionError.class [*]");
		excecao.expectMessage("but: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [mensagem da exceção]");
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste101.class, "testarFalhando", AssertionError.class));
	}

	@Test
	public void naoCombinaFalhaComFalhaDeOutraExcecao() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_FALHA> ClasseDeTeste101.testar lança RuntimeException.class [*]");
		excecao.expectMessage("but: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [mensagem da exceção]");
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste101.class, "testar", RuntimeException.class));
	}

	@Test
	public void naoCombinaFalhaComFalhaDeOutraExcecaoMensagemErrada() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [mensagem]");
		excecao.expectMessage("but: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [mensagem da exceção]");
		assertThat(testeFalha, combinaComTesteFalha(ClasseDeTeste101.class, "testar", AssertionError.class, "mensagem"));
	}

	@Test
	public void naoCombinaFalhaComIniciado() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTE_FALHA> ClasseDeTeste101.testar lança AssertionError.class [*]");
		excecao.expectMessage("but: <TESTE_INICIADO> ClasseDeTeste101.testar");
		assertThat(testeIniciado, combinaComTesteFalha(ClasseDeTeste101.class, "testar", AssertionError.class));
	}

	@Test
	public void naoCombinaFinalizadosComTestesDiferentes() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTES_FINALIZADOS> 1 executados, 0 falhas, 0 ignorados");
		excecao.expectMessage("but: <TESTES_FINALIZADOS> 0 executados, 0 falhas, 0 ignorados");
		assertThat(testesFinalizados, combinaComTestesFinalizados(1, 0, 0));
	}

	@Test
	public void naoCombinaFinalizadosComFalhasDiferentes() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTES_FINALIZADOS> 0 executados, 1 falhas, 0 ignorados");
		excecao.expectMessage("but: <TESTES_FINALIZADOS> 0 executados, 0 falhas, 0 ignorados");
		assertThat(testesFinalizados, combinaComTestesFinalizados(0, 1, 0));
	}

	@Test
	public void naoCombinaFinalizadosComIgnoradosDiferentes() throws Exception {
		excecao.expect(AssertionError.class);
		excecao.expectMessage("Expected: <TESTES_FINALIZADOS> 0 executados, 0 falhas, 1 ignorados");
		excecao.expectMessage("but: <TESTES_FINALIZADOS> 0 executados, 0 falhas, 0 ignorados");
		assertThat(testesFinalizados, combinaComTestesFinalizados(0, 0, 1));
	}

}
