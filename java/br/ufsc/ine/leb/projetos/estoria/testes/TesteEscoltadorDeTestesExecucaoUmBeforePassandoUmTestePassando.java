package br.ufsc.ine.leb.projetos.estoria.testes;

import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

import br.ufsc.ine.leb.projetos.estoria.EscoltadorDeTestes;
import br.ufsc.ine.leb.projetos.estoria.EspiaoDeEscolta;
import br.ufsc.ine.leb.projetos.estoria.Notificacao;
import br.ufsc.ine.leb.projetos.estoria.SeletorDeTestes;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.classesDeTesteComBefore.UmBeforePassandoUmTestePassando;

public final class TesteEscoltadorDeTestesExecucaoUmBeforePassandoUmTestePassando {

	private List<Notificacao> notificacoes;

	@Before
	public void prepararCenario() {
		SeletorDeTestes seletor = new SeletorDeTestes();
		seletor.adicionarClasse(UmBeforePassandoUmTestePassando.class);
		EscoltadorDeTestes escoltador = new EscoltadorDeTestes(seletor);
		RunNotifier mensageiroDeEscolta = new RunNotifier();
		EspiaoDeEscolta espiaoDeEscolta = new EspiaoDeEscolta();
		mensageiroDeEscolta.addFirstListener(espiaoDeEscolta);
		escoltador.run(mensageiroDeEscolta);
		notificacoes = espiaoDeEscolta.obterNotificacoes();
	}

	@Test
	public void testar() throws Exception {
		assertThat(notificacoes.get(0), combinaComTestesIniciados(SeletorDeTestes.class));
		assertThat(notificacoes.get(1), combinaComTesteIniciado(UmBeforePassandoUmTestePassando.class, "testar"));
		assertThat(notificacoes.get(2), combinaComTesteFinalizado(UmBeforePassandoUmTestePassando.class, "testar"));
		assertThat(notificacoes.get(3), combinaComTestesFinalizados(1, 0, 0));
		assertEquals(4, notificacoes.size());
	}

}