package com.mycompany.unicafe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

	Maksukortti kortti;

	@Before
	public void setUp() {
		kortti = new Maksukortti(10);
	}

	@Test
	public void luotuKorttiOlemassa() {
		assertTrue(kortti != null);
	}

	@Test
	public void saldoOikein() {
		assertEquals(10, kortti.saldo());
	}

	@Test
	public void lataaRahaaOikeaMaara() {
		int alussa = kortti.saldo();
		kortti.lataaRahaa(5);
		int lopussa = kortti.saldo();
		assertTrue(lopussa - alussa == 5);
	}

	@Test
	public void otaRahaaOikeaMaara() {
		int alussa = kortti.saldo();
		kortti.otaRahaa(5);
		int lopussa = kortti.saldo();
		assertTrue(alussa - lopussa == 5);
	}

	@Test
	public void otaRahaaLiikaa() {
		int alussa = kortti.saldo();
		kortti.otaRahaa(11);
		int lopussa = kortti.saldo();
		assertEquals(lopussa, alussa);
	}

	@Test
	public void otaRahaaTarpeeksiTotuuspalautus() {
		assertTrue("Täytyy palauttaa true, kun kortilta rahan ottaminen onnistui", kortti.otaRahaa(1));
	}

	@Test
	public void otaRahaaLiikaaTotuuspalautus() {
		assertFalse("Täytyy palauttaa false, kun kortilta rahan ottaminen ei onnistunut", kortti.otaRahaa(11));
	}

}
