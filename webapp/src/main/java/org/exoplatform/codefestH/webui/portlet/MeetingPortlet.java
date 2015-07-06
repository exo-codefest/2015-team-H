package org.exoplatform.codefestH.webui.portlet;

import org.exoplatform.codefestH.webui.core.CreateMeetingForm;
import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class)
public class MeetingPortlet extends UIPortletApplication {
  public MeetingPortlet() throws Exception {
    addChild(CreateMeetingForm.class, null, "CreateMeetingForm");
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }
}
