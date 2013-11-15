package org.transgalactica.maven.plugin.defaite;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.transgalactica.defaite.util.DeLoose;
import org.transgalactica.defaite.util.HelicoLoose;

/**
 * Mojo qui va evaluer la defaite probable du projet à l'aide du résultat du
 * lancement du dé de la loose. <br />
 * Si defaite, lancement d'une MojoFailureException.
 */
@Mojo(name = "verifierDefaite", defaultPhase = LifecyclePhase.VALIDATE, requiresProject = false)
public class DefaiteMojo extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		DeLoose niveauLoose = lancerLeDe();
		verifierDefaiteProbable(niveauLoose);
	}

	protected DeLoose lancerLeDe() {
		DeLoose niveauLoose = null;
		do {
			getLog().info("Casting the Loose dice...");
			niveauLoose = DeLoose.lancerLeDe();
			getLog().info("... got: " + niveauLoose);
			if (niveauLoose == DeLoose.LOOSE_RELOAD) {
				getLog().info("Loose level is 'reload', casting again");
			}
		} while (niveauLoose == DeLoose.LOOSE_RELOAD);
		return niveauLoose;
	}

	protected void verifierDefaiteProbable(DeLoose niveauLoose)
			throws MojoFailureException {
		int niveauDefaite = (int) (Math.random() * 100) + 1;
		getLog().info(
				"Loose defaite for level '" + niveauLoose + "' is: "
						+ niveauLoose.getPourcentageDefaiteProbable() + "%");
		getLog().info("Build random defaite cast is: " + niveauDefaite + "%");
		if (niveauDefaite <= niveauLoose.getPourcentageDefaiteProbable()) {
			getLog().info("\n" + HelicoLoose.getAsText());
			throw new MojoFailureException("Damned! " + niveauLoose
					+ " make your build do defaite!");
		}
		getLog().info("Ouf! No defaite was done...");
	}
}
