package org.transgalactica.maven.plugin.test.defaite;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;


import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.transgalactica.maven.plugin.defaite.DeLooseMojo;

public class DeLooseMojoTest {

	/**
	 * Test la configuration le chargement et l'execution du Mojo
	 * <code>DeLooseMojo</code>.
	 */
	@Test
	public void testMojoGoal() throws Exception {
		DeLooseMojo mojo = new DeLooseMojo();
		File resultFile = File.createTempFile("DeLooseMojoTest#testMojoGoal_",
				null);
		resultFile.deleteOnExit();
		mojo.setReportFile(resultFile);
		try {
			mojo.execute();
		} catch (MojoFailureException e) {
			// NOP
		}
		String result = FileUtils.fileRead(resultFile, "UTF-8");
		assertThat(result, CoreMatchers.anyOf(is("NO_LOOSE"), is("LOW_LOOSE"),
				is("MID_LOOSE"), is("MEGA_LOOSE"), is("ULTIMATE_LOOSE")));
	}
}