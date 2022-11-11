
package com.mycompany.unicafe;

public class Kassapaate {

	public static final int EDULLISEN_HINTA = 240;
	public static final int MAUKKAAN_HINTA = 400;
	private int kassassaRahaa;
	private int edulliset;
	private int maukkaat;

	public Kassapaate() {
		this.kassassaRahaa = 100000;
	}

	public int syoEdullisesti(int maksu) {
		if (maksu >= EDULLISEN_HINTA) {
			this.kassassaRahaa = kassassaRahaa + EDULLISEN_HINTA;
			++this.edulliset;
			return maksu - EDULLISEN_HINTA;
		} else {
			return maksu;
		}
	}

	public int syoMaukkaasti(int maksu) {
		if (maksu >= MAUKKAAN_HINTA) {
			this.kassassaRahaa = kassassaRahaa + MAUKKAAN_HINTA;
			this.maukkaat++;
			return maksu - MAUKKAAN_HINTA;
		} else {
			return maksu;
		}
	}

	public boolean syoEdullisesti(Maksukortti kortti) {
		if (kortti.saldo() >= EDULLISEN_HINTA) {
			kortti.otaRahaa(EDULLISEN_HINTA);
			this.edulliset++;
			return true;
		} else {
			return false;
		}
	}

	public boolean syoMaukkaasti(Maksukortti kortti) {
		if (kortti.saldo() >= MAUKKAAN_HINTA) {
			kortti.otaRahaa(MAUKKAAN_HINTA);
			this.maukkaat++;
			return true;
		} else {
			return false;
		}
	}

	public void lataaRahaaKortille(Maksukortti kortti, int summa) {
		if (summa >= 0) {
			kortti.lataaRahaa(summa);
			this.kassassaRahaa += summa;
		} else {
			return;
		}
	}

	public int kassassaRahaa() {
		return kassassaRahaa;
	}

	public int maukkaitaLounaitaMyyty() {
		return maukkaat;
	}

	public int edullisiaLounaitaMyyty() {
		return edulliset;
	}
}