package org.transgalactica.maven.plugin.test.defaite;


import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;
import org.transgalactica.maven.plugin.defaite.DefaiteMojo;

public class DefaiteMojoTest {

	/**
	 * Test la configuration le chargement et l'execution du Mojo
	 * <code>DefaiteMojo</code>.
	 */
	@Test
	public void testMojoGoal() throws Exception {
		DefaiteMojo mojo = new DefaiteMojo();
		try {
			mojo.execute();
		} catch (MojoFailureException e) {
			// NOP
		}
	}
}