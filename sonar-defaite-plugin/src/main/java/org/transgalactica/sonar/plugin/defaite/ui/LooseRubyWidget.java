package org.transgalactica.sonar.plugin.defaite.ui;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

@UserRole(UserRole.USER)
@Description("widget.loose-widget.description")
@WidgetCategory("Defaite")
@WidgetProperties({ @WidgetProperty(
		key = "sonar.defaite.loose_status_prefix",
		type = WidgetPropertyType.STRING,
		defaultValue = "/") })
public class LooseRubyWidget extends AbstractRubyTemplate implements RubyRailsWidget {

	public String getId() {
		return "loose-widget";
	}

	public String getTitle() {
		return "widget.loose-widget.name";
	}

	@Override
	protected String getTemplatePath() {
		return "/org/transgalactica/sonar/plugin/defaite/ui/loose_widget.html.erb";
	}
}
