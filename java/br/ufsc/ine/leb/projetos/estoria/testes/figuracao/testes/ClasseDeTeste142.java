package br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;

@FixtureSetup(ClasseDeTeste138.class)
public class ClasseDeTeste142 {

	private int classe143AcessorioBefore1Test1;
	private int classe143AcessorioBefore2Test3;
	private int classe143AcessorioBefore0Test4;

	@Fixture private int classe138AcessorioBefore1Test1;
	@Fixture private int classe138AcessorioBefore2Test3;
	@Fixture private int classe138AcessorioBefore0Test4;

	@Fixture private int classe137AcessorioBefore1Test1;
	@Fixture private int classe137AcessorioBefore2Test3;
	@Fixture private int classe137AcessorioBefore0Test4;

	@Before
	public void configurar() {
		classe143AcessorioBefore1Test1 = 1;
		classe143AcessorioBefore2Test3 = 2;
		assertEquals(1, classe143AcessorioBefore1Test1);
		assertEquals(2, classe143AcessorioBefore2Test3);
		assertEquals(0, classe143AcessorioBefore0Test4);
		assertEquals(1, classe138AcessorioBefore1Test1);
		assertEquals(2, classe138AcessorioBefore2Test3);
		assertEquals(0, classe138AcessorioBefore0Test4);
		assertEquals(1, classe137AcessorioBefore1Test1);
		assertEquals(2, classe137AcessorioBefore2Test3);
		assertEquals(0, classe137AcessorioBefore0Test4);
	}

	@Test
	public void testar() {
		classe143AcessorioBefore2Test3 = 3;
		classe143AcessorioBefore0Test4 = 4;
		assertEquals(1, classe143AcessorioBefore1Test1);
		assertEquals(3, classe143AcessorioBefore2Test3);
		assertEquals(4, classe143AcessorioBefore0Test4);
		assertEquals(1, classe138AcessorioBefore1Test1);
		assertEquals(2, classe138AcessorioBefore2Test3);
		assertEquals(0, classe138AcessorioBefore0Test4);
		assertEquals(1, classe137AcessorioBefore1Test1);
		assertEquals(2, classe137AcessorioBefore2Test3);
		assertEquals(0, classe137AcessorioBefore0Test4);
	}

}
