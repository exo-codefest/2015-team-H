package org.exoplatform.codefestH.webui.core;

import org.exoplatform.codefestH.webui.portlet.MeetingPortlet;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.UIPopupComponent;
import org.exoplatform.webui.core.UIPopupWindow;
import org.exoplatform.webui.core.lifecycle.UIContainerLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.organization.account.UIUserSelector;

@ComponentConfig(
        lifecycle = UIContainerLifecycle.class,
        events = {@EventConfig(listeners = UIUserContainer.AddActionListener.class)}
)

public class UIUserContainer extends UIContainer implements UIPopupComponent  {

  public UIUserContainer() throws Exception {
    UIUserSelector uiUserSelector = getChild(UIUserSelector.class);
    if (uiUserSelector == null) {
      uiUserSelector = addChild(UIUserSelector.class, null, null);
    }
    uiUserSelector.setMulti(true);
    uiUserSelector.setShowSearchGroup(false);
    uiUserSelector.setShowSearchUser(true);
    uiUserSelector.setShowSearch(true);
  }
  public void activate() {

  }

  public void deActivate() {

  }

  static public class AddActionListener extends EventListener<UIUserContainer> {
    public void execute(Event<UIUserContainer> event) throws Exception {
      UIUserContainer uiUserContainer = event.getSource();
      UIUserSelector uiUserSelector = uiUserContainer.getChild(UIUserSelector.class);
      MeetingPortlet meetingPortlet = event.getSource().getAncestorOfType(MeetingPortlet.class);
      meetingPortlet.getChild(CreateMeetingForm.class).getUIStringInput(CreateMeetingForm
              .FIELD_PARTICIPANTS_TEXT_BOX).setValue(uiUserSelector.getSelectedUsers());
    }
  }
}
