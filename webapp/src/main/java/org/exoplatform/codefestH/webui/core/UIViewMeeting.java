package org.exoplatform.codefestH.webui.core;

import org.exoplatform.codefestH.webui.portlet.MeetingPortlet;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponentDecorator;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.Event.Phase;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/UIViewMeeting.gtmpl",
        events = {
                @EventConfig(listeners = UIViewMeeting.SaveActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = UIViewMeeting.CancelActionListener.class)
        })
public class UIViewMeeting extends UIForm {

  static public class SaveActionListener extends EventListener<UIViewMeeting> {
    public void execute(Event<UIViewMeeting> event) throws Exception {
    }
  }

  static public class CancelActionListener extends EventListener<UIViewMeeting> {
    public void execute(Event<UIViewMeeting> event) throws Exception {
      UIViewMeeting uiViewMeeting = event.getSource();
      uiViewMeeting.setRendered(false);
      uiViewMeeting.getAncestorOfType(MeetingPortlet.class).getChild(UIMeetingList.class).setRendered(true);
      event.getRequestContext().addUIComponentToUpdateByAjax(uiViewMeeting.getParent());
    }
  }
}
