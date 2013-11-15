package org.transgalactica.maven.plugin.hello;

import java.text.MessageFormat;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {

	@Parameter(defaultValue = "Hello {0}!")
	private String messagePattern;

	@Parameter(defaultValue = "World")
	private String name;

	public void execute() throws MojoExecutionException, MojoFailureException {
		String phrase = MessageFormat.format(getMessagePattern(),
				new Object[] { getName() });
		getLog().info(phrase);
	}

	/*
	 * Accesseurs
	 */

	public String getMessagePattern() {
		return messagePattern;
	}

	public void setMessagePattern(String messagePattern) {
		this.messagePattern = messagePattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
