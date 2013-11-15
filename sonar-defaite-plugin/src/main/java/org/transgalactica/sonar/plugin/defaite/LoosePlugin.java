package org.transgalactica.sonar.plugin.defaite;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;
import org.transgalactica.sonar.plugin.defaite.batch.LooseSensor;
import org.transgalactica.sonar.plugin.defaite.ui.LooseRubyWidget;

@Properties({ @Property(
		key = LoosePlugin.LOOSE_REPORT_FILE_PROPERTY,
		name = "property.sonar.loose.reportFile.name",
		description = "property.sonar.loose.reportFile.description",
		defaultValue = "target/defaite-reports/loose.txt") })
public final class LoosePlugin extends SonarPlugin {

	public static final String LOOSE_REPORT_FILE_PROPERTY = "sonar.loose.reportFile";

	public List getExtensions() {
		return Arrays.asList(
		// Definitions
				LooseMetrics.class,
				// Batch
				LooseSensor.class,
				// UI
				LooseRubyWidget.class);
	}
}
