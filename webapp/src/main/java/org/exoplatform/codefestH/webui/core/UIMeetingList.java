package org.exoplatform.codefestH.webui.core;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.webui.portlet.MeetingPortlet;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponentDecorator;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ComponentConfig(
        template = "app:/groovy/meeting/webui/component/UIMeetingList.gtmpl",
        events = {
                @EventConfig(listeners = UIMeetingList.ViewActionListener.class),
                @EventConfig(listeners = UIMeetingList.AddNewActionListener.class)
        }
)
public class UIMeetingList extends UIComponentDecorator {

  public List<Meeting> getUpCommingMeetings() {
    return CommonsUtils.getService(MeetingService.class).getAllMeeting();
  }

  public List<Meeting> getMeetingsByOwnerOrParticipant() {
    List<Meeting> meetings = new ArrayList<Meeting>();
    String currentLoginUser = ConversationState.getCurrent().getIdentity().getUserId();
    MeetingService meetingService = CommonsUtils.getService(MeetingService.class);
    meetings.addAll(meetingService.getMeetingByOwner(currentLoginUser));
    meetings.addAll(meetingService.getMeetingByParticipant(currentLoginUser));
    return meetings;
  }

  public String getDateString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String format = formatter.format(date);
    return format;
  }

  static public class ViewActionListener extends EventListener<UIMeetingList> {
    public void execute(Event<UIMeetingList> event) throws Exception {
      UIMeetingList uiMeetingList = event.getSource();
      uiMeetingList.setRendered(false);
      uiMeetingList.getAncestorOfType(MeetingPortlet.class).getChild(UIViewMeeting.class).setRendered(true);
      event.getRequestContext().addUIComponentToUpdateByAjax(uiMeetingList.getParent());
    }
  }

  static public class AddNewActionListener extends EventListener<UIMeetingList> {
    public void execute(Event<UIMeetingList> event) throws Exception {
      UIMeetingList uiMeetingList = event.getSource();
      uiMeetingList.setRendered(false);
      ((MeetingPortlet)uiMeetingList.getParent()).getChild(CreateMeetingForm.class).setRendered(true);
      event.getRequestContext().addUIComponentToUpdateByAjax(uiMeetingList.getParent());
    }
  }
}
