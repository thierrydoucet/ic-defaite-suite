package org.transgalactica.ant.defaite.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.transgalactica.ant.defaite.DefaiteTaskUtils;

public class DefaiteTaskUtilsTest {

	@Test
	public void shouldGetVersion() {
		String version = DefaiteTaskUtils.getTaskVersion();
		assertThat(version, containsString("."));
		assertThat(version, not(containsString("$")));
	}
}