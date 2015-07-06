package org.exoplatform.codefestH.webui.portlet;

import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/createmeeting/webui/component/CreateMeetingPortlet.gtmpl")
public class CreateMeetingPortlet extends UIPortletApplication {
  public CreateMeetingPortlet() throws Exception {

  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }
}