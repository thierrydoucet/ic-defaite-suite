package org.transgalactica.ant.defaite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

public class DefaiteTaskUtils {

	private DefaiteTaskUtils() {
	}

	/**
	 * Returns the version of the Defaite Tasks
	 * 
	 * @return the version
	 */
	public static String getTaskVersion() {
		InputStream in = null;
		try {
			in = DefaiteTaskUtils.class.getResourceAsStream("/org/transgalactica/ant/defaite/version.txt");
			Properties props = new Properties();
			props.load(in);
			return props.getProperty("version");
		}
		catch (IOException e) {
			throw new BuildException("Could not load the version information for Sonar Ant Task", e);
		}
		finally {
			FileUtils.close(in);
		}
	}
}
