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
package org.exoplatform.codefestH.service.mock;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;

import java.util.List;
import java.util.UUID;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class MeetingServiceimpl implements MeetingService {

  @Override
  public Meeting getMeeting(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Meeting removeMeeting(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean saveMeeting(Meeting meeting) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Meeting> getMeetingByOwner(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Meeting> getMeetingByParticipant(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Meeting> getAllMeeting() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean setMeetingClose(String meetingID) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeParticipant(String meetingID, String username) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addParticipant(String meetingID, String username) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<String> getParticipants(String meetingID) {
    // TODO Auto-generated method stub
    return null;
  }


}
