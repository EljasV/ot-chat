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
	public void edullinenLounasostoKateisellaToimiiRiittavallaRahalla() {
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
	public void edullinenLounasostoKateisellaToimiiTasarahalla() {
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
	public void edullinenLounasostoKateisellaToimiiLiianVahallaRahalla() {
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
	public void maukasLounasostoKateisellaToimiiRiittavallaRahalla() {
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
	public void maukasLounasostoKateisellaToimiiTasarahalla() {
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
	public void maukasLounasostoKateisellaToimiiLiianVahallaRahalla() {
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
	@Test
	public void edullinenLounasostoKortillaToimiiRiittavallaRahalla() {
		Maksukortti kortti = new Maksukortti(1000);

		int rahaaKortillaEnnen = kortti.saldo();
		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		boolean palautus = kassapaate.syoEdullisesti(kortti);

		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertTrue(palautus);
		assertEquals(240, rahaaKortillaEnnen - rahaaKortillaJalkeen);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);


	}

	@Test
	public void edullinenLounasostoKortillaToimiiTasarahalla() {
		Maksukortti kortti = new Maksukortti(240);

		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		boolean palautus = kassapaate.syoEdullisesti(kortti);

		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertTrue(palautus);
		assertEquals(0, rahaaKortillaJalkeen);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}


	@Test
	public void edullinenLounasostoKortillaToimiiLiianVahallaRahalla() {
		Maksukortti kortti = new Maksukortti(200);

		int rahaaKortillaEnnen = kortti.saldo();
		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.edullisiaLounaitaMyyty();

		boolean palautus = kassapaate.syoEdullisesti(kortti);


		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.edullisiaLounaitaMyyty();

		assertFalse(palautus);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(myytyjaJalkeen, myytyjaEnnen);
		assertEquals(rahaaKortillaEnnen, rahaaKortillaJalkeen);
	}


	//Maukkaat lounaat
	@Test
	public void maukasLounasostoKortillaToimiiRiittavallaRahalla() {
		Maksukortti kortti = new Maksukortti(1000);

		int rahaaKortillaEnnen = kortti.saldo();
		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		boolean palautus = kassapaate.syoMaukkaasti(kortti);

		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertTrue(palautus);
		assertEquals(400, rahaaKortillaEnnen - rahaaKortillaJalkeen);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void maukasLounasostoKortillaToimiiTasarahalla() {
		Maksukortti kortti = new Maksukortti(400);

		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		boolean palautus = kassapaate.syoMaukkaasti(kortti);

		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertTrue(palautus);
		assertEquals(0, rahaaKortillaJalkeen);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(1, myytyjaJalkeen - myytyjaEnnen);
	}

	@Test
	public void maukasLounasostoKortillaToimiiLiianVahallaRahalla() {

		Maksukortti kortti = new Maksukortti(200);

		int rahaaKortillaEnnen = kortti.saldo();
		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();
		int myytyjaEnnen = kassapaate.maukkaitaLounaitaMyyty();

		boolean palautus = kassapaate.syoMaukkaasti(kortti);


		int rahaaKortillaJalkeen = kortti.saldo();
		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();
		int myytyjaJalkeen = kassapaate.maukkaitaLounaitaMyyty();

		assertFalse(palautus);
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
		assertEquals(myytyjaJalkeen, myytyjaEnnen);
		assertEquals(rahaaKortillaEnnen, rahaaKortillaJalkeen);
	}

	@Test
	public void lataaRahaaKortille() {
		Maksukortti kortti = new Maksukortti(0);

		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();

		kassapaate.lataaRahaaKortille(kortti, 5000);

		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();

		assertEquals(5000, kortti.saldo());
		assertEquals(5000, rahaaKassassaJalkeen - rahaaKassassaEnnen);
	}

	@Test
	public void lataaRahaaKortilleNegatiivinen() {
		Maksukortti kortti = new Maksukortti(0);

		int rahaaKassassaEnnen = kassapaate.kassassaRahaa();

		kassapaate.lataaRahaaKortille(kortti, -1);

		int rahaaKassassaJalkeen = kassapaate.kassassaRahaa();

		assertEquals(0, kortti.saldo());
		assertEquals(rahaaKassassaJalkeen, rahaaKassassaEnnen);
	}
}
