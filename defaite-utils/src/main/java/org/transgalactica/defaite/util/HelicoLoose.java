package org.transgalactica.defaite.util;

/**
 * Encore une création du plus grand intégrateur: l'HelicoLoose, inspiré par
 * l'hélicobide.
 */
public abstract class HelicoLoose {

	private static final String HELICOLOOSE_ASCII = "................................\n"
			+ "....< LOOSE LOOSE LOOSE LOOSE >.\n"
			+ "................||..............\n"
			+ ".............__\\||/____.........\n"
			+ ".\\\\.........|'-|--| \\  \\........\n"
			+ "..\\\\_.......|--|--|  \\  \\.......\n"
			+ "../L \\____,/-------\\_\\   \\......\n"
			+ ".|OOS|-------------O-----,|.....\n"
			+ "..\\E/______,------------,/......\n"
			+ "..//.......\\_________ ,/........\n"
			+ ".//.......____//___ __\\\\__/.....\n"
			+ "................................";

	private HelicoLoose() {
		super();
	}

	public static String getAsText() {
		return HELICOLOOSE_ASCII;
	}
}
