package org.exoplatform.codefestH.listeners;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.BaseNotificationPlugin;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Retrieves information from the new notification type for the schedule a meeting.
 */
public class ScheduleMeetingPlugin extends BaseNotificationPlugin {

  public final static ArgumentLiteral<String> MEETING = new ArgumentLiteral<String>(String.class,
          "meeting");
  private static final Log LOG = ExoLogger.getLogger(ScheduleMeetingPlugin.class);
  public final static String ID = "ScheduleMeetingPlugin";

  public ScheduleMeetingPlugin(InitParams initParams) {
    super(initParams);
  }

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public boolean isValid(NotificationContext notificationContext) {
    return true;
  }

  @Override
  protected NotificationInfo makeNotification(NotificationContext ctx) {
    String meetingId = ctx.value(MEETING);
    MeetingService meetingService = WCMCoreUtils.getService(MeetingService.class);
    Meeting meeting = meetingService.getMeeting(meetingId);
    Set<String> receivers = new HashSet<String>();
    List<String> participants = meetingService.getParticipants(meetingId);
    for(String participant : participants) {
      receivers.add(participant);
    }
    return NotificationInfo.instance()
            .setFrom(meeting.getOwner())
            .to(new ArrayList<String>(receivers))
            .setTitle("The meeting poll has been created ")
            .key(getId());
  }
}
