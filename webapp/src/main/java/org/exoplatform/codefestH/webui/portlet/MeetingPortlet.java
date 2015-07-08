package org.exoplatform.codefestH.webui.portlet;

import org.exoplatform.codefestH.webui.core.CreateMeetingForm;
import org.exoplatform.codefestH.webui.core.UIMeetingList;
import org.exoplatform.codefestH.webui.core.UIViewMeeting;
import org.exoplatform.webui.application.WebuiApplication;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class)
public class MeetingPortlet extends UIPortletApplication {
  public MeetingPortlet() throws Exception {
    addChild(CreateMeetingForm.class, null, null).setRendered(false);
    addChild(UIPopupContainer.class, null, null);
    addChild(UIMeetingList.class, null, null);
    addChild(UIViewMeeting.class, null, null).setRendered(false);
  }

  public UIPopupContainer getPopupContainer() {
    return this.findFirstComponentOfType(UIPopupContainer.class);
  }

  public void processRender(WebuiApplication app, WebuiRequestContext context) throws Exception {
    super.processRender(app, context);
  }
}
