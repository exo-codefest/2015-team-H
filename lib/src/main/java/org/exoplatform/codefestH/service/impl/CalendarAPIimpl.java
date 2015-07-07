/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.codefestH.service.impl;

import java.util.List;

import org.exoplatform.calendar.service.Calendar;
import org.exoplatform.calendar.service.CalendarCollection;
import org.exoplatform.calendar.service.CalendarEvent;
import org.exoplatform.calendar.service.CalendarService;
import org.exoplatform.codefestH.service.CalendarAPI;
import org.exoplatform.codefestH.service.Meeting;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class CalendarAPIimpl implements CalendarAPI{

  private CalendarService calendarService;

  public CalendarAPIimpl(CalendarService calendarService){
    this.calendarService = calendarService;
  }
  /**
   * {@inheritDoc}}
   */
  @Override
  public boolean createEvent(Meeting meeting) {
    // TODO Auto-generated method stub
    List<String> userInvole = meeting.getParticipants();
    userInvole.add(meeting.getOwner());
    
    for(String username : userInvole){
      CalendarCollection<Calendar> collection = calendarService.getAllCalendars(username, 0, 0, 1);
      if(collection.size() < 1) return false;
      Calendar cal = calendarService.getAllCalendars(username, 0, 0, 1).get(0);
      CalendarEvent event = wrapMeetingToEvent(meeting,cal.getId());
      try {
        calendarService.saveUserEvent(username, cal.getId(), event, true);
        return true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}}
   */
  @Override
  public void removeEvent(Meeting meeting) {
    // TODO Auto-generated method stub
    
  }

  private CalendarEvent wrapMeetingToEvent(Meeting meeting, String calendarId){
    CalendarEvent event = new CalendarEvent();
    event.setDescription(meeting.getDescription());
    event.setFromDateTime(meeting.getFinalTime().getBegin());
    String[] participants = new String[meeting.getParticipants().size()];
    for(int i = 0; i < participants.length; i ++)
      participants[i] = meeting.getParticipants().get(i);
    event.setParticipant(participants);
    event.setCalendarId(calendarId);
    event.setToDateTime(meeting.getFinalTime().getEnd());

    return event;
  }

}
