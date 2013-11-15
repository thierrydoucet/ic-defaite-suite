package org.transgalactica.sonar.plugin.defaite.batch;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.Measure;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.SonarException;
import org.transgalactica.defaite.util.DeLoose;
import org.transgalactica.sonar.plugin.defaite.LooseMetrics;
import org.transgalactica.sonar.plugin.defaite.LoosePlugin;


public class LooseSensor implements Sensor {

	private static final Logger LOG = LoggerFactory.getLogger(LooseSensor.class);

	private Settings settings;

	public LooseSensor(Settings settings) {
		this.settings = settings;
	}

	public boolean shouldExecuteOnProject(Project project) {
		return true;
	}

	public void analyse(Project project, SensorContext sensorContext) {
		// retrieve from build
		final File buildOutputDir = project.getFileSystem().getBuildOutputDir();
		if (!buildOutputDir.exists()) {
			LOG.info("Can't find build output directory: {}. Skipping Loose analysis.", buildOutputDir);
			return;
		}

		DeLoose looseStatus = readLoose(project);
		saveLoose(looseStatus, sensorContext);
	}

	/**
	 * Read Loose Satus
	 */
	private DeLoose readLoose(Project project) {
		File reportFile = project.getFileSystem().resolvePath(
				settings.getString(LoosePlugin.LOOSE_REPORT_FILE_PROPERTY));
		LOG.info("Analysing Loose status from file: {}", reportFile);
		String value;
		try {
			value = FileUtils.readFileToString(reportFile);
		}
		catch (IOException e) {
			throw new SonarException(e);
		}
		return DeLoose.valueOf(value);
	}

	/**
	 * Save metrics
	 */
	private void saveLoose(DeLoose looseStatus, SensorContext sensorContext) {
		Measure measure = new Measure(LooseMetrics.LOOSE_STATUS, new Double(looseStatus.ordinal()));
		sensorContext.saveMeasure(measure);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
