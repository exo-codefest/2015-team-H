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
  public Meeting getMeeting(String path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Meeting getMeeting(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Meeting removeMeeting(String path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Meeting removeMeeting(UUID id) {
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
  public void removeParticipant(String meetingId, String userName) {
    //Remove participant by username
  }

  @Override
  public void closePoll(String pollId) {

  }

  @Override
  public void reopenPoll(String pollId) {

  }

  @Override
  public String getPollStatus(String pollId) {
    return Meeting.OPEN;
  }

}
