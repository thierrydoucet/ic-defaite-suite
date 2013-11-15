package org.transgalactica.maven.plugin.defaite;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;
import org.transgalactica.defaite.util.DeLoose;
import org.transgalactica.defaite.util.HelicoLoose;

/**
 * Mojo qui va lancer le dé de la loose. <br />
 * Si la face obtenue est différente de <code>NO_LOOSE</code>, lancement d'une
 * MojoFailureException.
 */
@Mojo(name = "lancerLeDe", defaultPhase = LifecyclePhase.TEST, requiresProject = false)
public class DeLooseMojo extends AbstractMojo {

	/**
	 * File where Loose reports are written to.
	 */
	@Parameter(defaultValue = "${project.build.directory}/defaite-reports/loose.txt")
	private File reportFile;

	/**
	 * Set this to "true" to ignore a failure during Loosing.
	 */
	@Parameter(property = "loose.failure.ignore", defaultValue = "false")
	private boolean looseFailureIgnore;

	public void execute() throws MojoExecutionException, MojoFailureException {
		DeLoose niveauLoose = lancerLeDe();
		ecrireResultat(niveauLoose);
		verifierResultat(niveauLoose);
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

	protected void ecrireResultat(DeLoose niveauLoose) throws MojoExecutionException {
		try {
			FileUtils.forceMkdir(reportFile.getParentFile());
			FileUtils.fileWrite(reportFile, "UTF-8", niveauLoose.name());
		}
		catch (IOException e) {
			throw new MojoExecutionException("Can't file loose dice results", e);
		}
	}

	protected void verifierResultat(DeLoose niveauLoose) throws MojoFailureException {
		if (niveauLoose == DeLoose.NO_LOOSE) {
			getLog().info("Ouf! No loose break the build.");
		}
		else {
			getLog().info("\n" + HelicoLoose.getAsText());
			if (looseFailureIgnore) {
				getLog().info("Damned! " + niveauLoose + " break the build!");
			}
			else {
				throw new MojoFailureException("Damned! " + niveauLoose + " break the build!");
			}
		}
	}

	public File getReportFile() {
		return reportFile;
	}

	public void setReportFile(File reportFile) {
		this.reportFile = reportFile;
	}
}
