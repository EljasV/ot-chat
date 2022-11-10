package com.mycompany.unicafe;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
	Kassapaate kassapaate;

	@Before
	public void setUp() {
		kassapaate = new Kassapaate();
	}

	@Test
	public void luotuKassapaateOikeanlainen() {
		assertEquals("Juuri luodulla kassapäätteellä on 1000e rahaa", 100000, kassapaate.kassassaRahaa());
		assertEquals("Juuriluodulla kassapäätteellä ei ole ollenkaan edullista lounasta myytynä", 0, kassapaate.edullisiaLounaitaMyyty());
		assertEquals("Juuriluodulla kassapäätteellä ei ole ollenkaan maukasta lounasta myytynä", 0, kassapaate.maukkaitaLounaitaMyyty());
	}


	//////
	//	Maksut käteisellä
	//////

	// edulliset lounaat
	@Test
	public void edullinenLounasostoToimiiRiittavallaRahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoEdullisesti(300);

		assertEquals(60, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertEquals(240, rahaaJalkeen - rahaaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void edullinenLounasostoToimiiTasarahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoEdullisesti(240);

		assertEquals(0, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertEquals(240, rahaaJalkeen - rahaaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void edullinenLounasostoToimiiLiianVahallaRahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoEdullisesti(200);

		assertEquals(200, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertEquals(0, rahaaJalkeen - rahaaEnnen);
		assertEquals(0, myytyjaJalkeen - myytyjaEnnen);
	}


	//Maukkaat lounaat
	@Test
	public void maukasLounasostoToimiiRiittavallaRahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoMaukkaasti(420);

		assertEquals(20, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertEquals(400, rahaaJalkeen - rahaaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void maukasLounasostoToimiiTasarahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoMaukkaasti(400);

		assertEquals(0, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertEquals(400, rahaaJalkeen - rahaaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void maukasLounasostoToimiiLiianVahallaRahalla() {
		int rahaaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		int vaihtoraha = kassapaate.syoMaukkaasti(200);

		assertEquals(200, vaihtoraha);

		int rahaaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertEquals(0, rahaaJalkeen - rahaaEnnen);
		assertEquals(0, myytyjaJalkeen - myytyjaEnnen);
	}

	//////
	// Maksut kortilla
	//////

	//Edulliset lounaat

}
