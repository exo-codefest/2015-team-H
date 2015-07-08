package org.exoplatform.codefestH.webui.core;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.service.TimeRange;
import org.exoplatform.codefestH.webui.portlet.MeetingPortlet;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;

import java.util.ArrayList;
import java.util.List;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/UIViewMeeting.gtmpl",
        events = {
                @EventConfig(listeners = UIViewMeeting.SaveActionListener.class),
                @EventConfig(listeners = UIViewMeeting.CancelActionListener.class)
        })
public class UIViewMeeting extends UIForm {

  private String meetingName = StringUtils.EMPTY;

  static public class SaveActionListener extends EventListener<UIViewMeeting> {
    public void execute(Event<UIViewMeeting> event) throws Exception {
      UIViewMeeting viewMeeting = event.getSource();
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

  public Meeting getMeeting() {
    if(StringUtils.isNotEmpty(meetingName)) {
      MeetingService meetingService = WCMCoreUtils.getService(MeetingService.class);
      return meetingService.getMeeting(meetingName);
    }
    return null;
  }

  public List<String> getParticipants() {
    MeetingService meetingService = WCMCoreUtils.getService(MeetingService.class);
    return meetingService.getParticipants(meetingName);
  }

  public List<String> getTimeSlot() {
    List<String> listTimeSlot = new ArrayList<String>();
    MeetingService meetingService = WCMCoreUtils.getService(MeetingService.class);
    Meeting meeting = meetingService.getMeeting(meetingName);
    for(TimeRange timeRange : meeting.getTimeSlot()) {
      listTimeSlot.add(timeRange.getBeginTime());
    }
    return listTimeSlot;
  }

  public String getDisplayName(String userName) throws Exception {
    OrganizationService organizationService = WCMCoreUtils.getService(OrganizationService.class);
    User user = organizationService.getUserHandler().findUserByName(userName);
    return user.getFullName();
  }

  public void setMeetingName(String meetingName) {
    this.meetingName = meetingName;
  }

}
