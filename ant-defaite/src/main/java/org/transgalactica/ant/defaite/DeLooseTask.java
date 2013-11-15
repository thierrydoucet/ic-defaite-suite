package org.transgalactica.ant.defaite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Main;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import org.transgalactica.defaite.util.DeLoose;
import org.transgalactica.defaite.util.HelicoLoose;

public class DeLooseTask extends Task {

	private static final String DEFAULT_REPORT_FILE = "loose.txt";

	/**
	 * File where Loose reports are written to.<br/>
	 * Not required, default is <code>${basedir}/loose.txt</code>.
	 */
	private File reportFile;

	/**
	 * Set this to "true" to fail during Loosing. <br/>
	 * Not required, default is <code>true</code>.
	 */
	private boolean failOnError = true;

	@Override
	public void execute() throws BuildException {
		log("Ant version: " + Main.getAntVersion());
		log("Defaite Ant Task version: " + DefaiteTaskUtils.getTaskVersion());

		validate();
		DeLoose result = launchDice();
		write(result);
		checkResult(result);
	}

	protected void validate() {
		if (reportFile == null) {
			reportFile = getProject().resolveFile(DEFAULT_REPORT_FILE);
		}
		if (!reportFile.getParentFile().exists()) {
			reportFile.getParentFile().mkdirs();
		}
	}

	protected DeLoose launchDice() {
		DeLoose niveauLoose = null;
		do {
			log("Casting the Loose dice...");
			niveauLoose = DeLoose.lancerLeDe();
			log("... got: " + niveauLoose);
			if (niveauLoose == DeLoose.LOOSE_RELOAD) {
				log("Loose level is 'reload', casting again");
			}
		} while (niveauLoose == DeLoose.LOOSE_RELOAD);
		return niveauLoose;
	}

	protected void write(DeLoose niveauLoose) {
		OutputStream out = null;
		try {
			out = new FileOutputStream(reportFile);
			out.write(niveauLoose.name().getBytes("UTF-8"));
		}
		catch (IOException e) {
			throw new BuildException("Can't file loose dice results", e);
		}
		finally {
			FileUtils.close(out);
		}
	}

	protected void checkResult(DeLoose niveauLoose) {
		if (niveauLoose == DeLoose.NO_LOOSE) {
			log("Ouf! No loose break the build.");
		}
		else {
			log("\n" + HelicoLoose.getAsText());
			if (failOnError) {
				throw new BuildException("Damned! " + niveauLoose + " break the build!");
			}
			else {
				log("Damned! " + niveauLoose + " break the build!");
			}
		}
	}

	public void setDestfile(File destfile) {
		this.reportFile = destfile;
	}

	public void setFailonerror(boolean failonerror) {
		this.failOnError = failonerror;
	}
}
