package org.transgalactica.sonar.plugin.defaite;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;
import org.transgalactica.defaite.util.DeLoose;

public final class LooseMetrics implements Metrics {

	public static final Metric LOOSE_STATUS = new Metric.Builder("loose_status", "metric.loose_status.name",
			Metric.ValueType.INT).setDescription("metric.loose_status.description") //
			.setBestValue(new Double(DeLoose.NO_LOOSE.ordinal())) //
			.setWorstValue(new Double(DeLoose.ULTIMATE_LOOSE.ordinal())) //
			.setDirection(Metric.DIRECTION_WORST) //
			.setQualitative(true) //
			.setDomain(CoreMetrics.DOMAIN_GENERAL).create();

	// getMetrics() method is defined in the Metrics interface and is used by
	// Sonar to retrieve the list of new metrics
	public List<Metric> getMetrics() {
		return Arrays.asList(LOOSE_STATUS);
	}
}
