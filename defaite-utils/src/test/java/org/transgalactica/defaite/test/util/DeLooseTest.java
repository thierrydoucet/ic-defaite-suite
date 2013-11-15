package org.transgalactica.defaite.test.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.transgalactica.defaite.util.DeLoose;

/**
 * Tests unitaires de la classe <code>DeLoose</code>
 */
public class DeLooseTest {

	/**
	 * Test verifiant si 50 lancement du dé de la loose, font apparaitre toutes
	 * les faces.
	 */
	@Test
	public void lancerLeDe_face() {
		boolean looseReloadTrouve = false;
		boolean noLooseTrouve = false;
		boolean lowLooseTrouve = false;
		boolean midLooseTrouve = false;
		boolean megaLooseTrouve = false;
		boolean utlimateLooseTrouve = false;

		for (int i = 0; i < 50; i++) {
			DeLoose face = DeLoose.lancerLeDe();
			looseReloadTrouve = looseReloadTrouve
					|| face == DeLoose.LOOSE_RELOAD;
			noLooseTrouve = noLooseTrouve || face == DeLoose.NO_LOOSE;
			lowLooseTrouve = lowLooseTrouve || face == DeLoose.LOW_LOOSE;
			midLooseTrouve = midLooseTrouve || face == DeLoose.MID_LOOSE;
			megaLooseTrouve = megaLooseTrouve || face == DeLoose.MEGA_LOOSE;
			utlimateLooseTrouve = utlimateLooseTrouve
					|| face == DeLoose.ULTIMATE_LOOSE;
		}

		assertTrue(looseReloadTrouve);
		assertTrue(noLooseTrouve);
		assertTrue(lowLooseTrouve);
		assertTrue(midLooseTrouve);
		assertTrue(megaLooseTrouve);
		assertTrue(utlimateLooseTrouve);
	}

	/**
	 * Test vérifiant l'égalités de probabilité d'apparition des différentes
	 * faces du dé de la loose. Test effectué sur 600000 lancé. Probabilité
	 * controlée a +- 1%.
	 */
	@Test
	public void lancerLeDe_probabilite() {
		int nbJeux = 600000;

		int looseReloadTrouve = 0;
		int noLooseTrouve = 0;
		int lowLooseTrouve = 0;
		int midLooseTrouve = 0;
		int megaLooseTrouve = 0;
		int utlimateLooseTrouve = 0;

		for (int i = 0; i < nbJeux; i++) {
			DeLoose face = DeLoose.lancerLeDe();
			if (face == DeLoose.LOOSE_RELOAD) {
				looseReloadTrouve++;
			}
			if (face == DeLoose.NO_LOOSE) {
				noLooseTrouve++;
			}
			if (face == DeLoose.LOW_LOOSE) {
				lowLooseTrouve++;
			}
			if (face == DeLoose.MID_LOOSE) {
				midLooseTrouve++;
			}
			if (face == DeLoose.MEGA_LOOSE) {
				megaLooseTrouve++;
			}
			if (face == DeLoose.ULTIMATE_LOOSE) {
				utlimateLooseTrouve++;
			}
		}
		System.out.println(looseReloadTrouve + "/" + noLooseTrouve + "/"
				+ lowLooseTrouve + "/" + midLooseTrouve + "/" + megaLooseTrouve
				+ "/" + utlimateLooseTrouve);

		int jeuxParFace = nbJeux / 6;
		double toleranceMin = 0.99;
		double toleranceMax = 1.01;
		assertTrue(looseReloadTrouve < jeuxParFace * toleranceMax);
		assertTrue(looseReloadTrouve > jeuxParFace * toleranceMin);
		assertTrue(noLooseTrouve < jeuxParFace * toleranceMax);
		assertTrue(noLooseTrouve > jeuxParFace * toleranceMin);
		assertTrue(lowLooseTrouve < jeuxParFace * toleranceMax);
		assertTrue(lowLooseTrouve > jeuxParFace * toleranceMin);
		assertTrue(midLooseTrouve < jeuxParFace * toleranceMax);
		assertTrue(midLooseTrouve > jeuxParFace * toleranceMin);
		assertTrue(megaLooseTrouve < jeuxParFace * toleranceMax);
		assertTrue(megaLooseTrouve > jeuxParFace * toleranceMin);
		assertTrue(utlimateLooseTrouve < jeuxParFace * toleranceMax);
		assertTrue(utlimateLooseTrouve > jeuxParFace * toleranceMin);
	}
}
