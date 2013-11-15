package org.transgalactica.defaite.util;

/**
 * <p>
 * Le dé de la Loose représente un dé à jouer à six faces. <br />
 * Cinq des six des faces indiquent un status de loose. <br />
 * La dernière face est un joker permettant de relancer le dé.
 * </p>
 */
public enum DeLoose {

	LOOSE_RELOAD(0), NO_LOOSE(0), LOW_LOOSE(25), MID_LOOSE(50), MEGA_LOOSE(75), ULTIMATE_LOOSE(
			100);

	private final int pourcentageDefaiteProbable;

	private DeLoose(int pourcentageDefaiteProbable) {
		this.pourcentageDefaiteProbable = pourcentageDefaiteProbable;
	}

	/**
	 * @return une face du d�, tir�e au hasard.
	 */
	public static DeLoose lancerLeDe() {
		int level = (int) (Math.random() * 6) + 1;
		switch (level) {
		case 2:
			return DeLoose.NO_LOOSE;
		case 3:
			return DeLoose.LOW_LOOSE;
		case 4:
			return DeLoose.MID_LOOSE;
		case 5:
			return DeLoose.MEGA_LOOSE;
		case 6:
			return DeLoose.ULTIMATE_LOOSE;
		default:
			return DeLoose.LOOSE_RELOAD;
		}
	}

	public int getPourcentageDefaiteProbable() {
		return pourcentageDefaiteProbable;
	}
}
