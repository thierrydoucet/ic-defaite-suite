package org.transgalactica.maven.plugin.test.hello;

import org.junit.Test;
import org.transgalactica.maven.plugin.hello.HelloMojo;

public class HelloMojoTest {

	@Test
	public void execute() throws Exception {
		HelloMojo mojo = new HelloMojo();
		mojo.setMessagePattern("Hello {0}!");
		mojo.setName("Test");
		mojo.execute();
	}
}