package org.transgalactica.ant.defaite.test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.transgalactica.ant.defaite.DeLooseTask;

public class DeLooseTaskTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	public File basedir;

	private DeLooseTask task;

	private Project project;

	@Before
	public void mockProject() throws IOException {
		project = mock(Project.class);
		basedir = folder.newFolder("baseDir");
		when(project.getBaseDir()).thenReturn(basedir);
		when(project.resolveFile("loose.txt")).thenReturn(new File(basedir, "loose.txt"));
		task = new DeLooseTask();
		task.setProject(project);
	}

	@Test
	public void testLaunchDice() throws IOException {
		try {
			task.execute();
		}
		catch (BuildException e) {
			// nop
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(basedir, "loose.txt")), "UTF-8"));
		try {
			String result = in.readLine();
			assertThat(result,
					anyOf(is("NO_LOOSE"), is("LOW_LOOSE"), is("MID_LOOSE"), is("MEGA_LOOSE"), is("ULTIMATE_LOOSE")));
		}
		finally {
			in.close();
		}
	}
}