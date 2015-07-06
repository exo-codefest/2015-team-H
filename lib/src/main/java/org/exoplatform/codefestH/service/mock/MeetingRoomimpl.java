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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingRoom;
import org.exoplatform.codefestH.service.TimeRange;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class MeetingRoomimpl implements MeetingRoom {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Meeting> getMeetings() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TimeRange> getBusyTime(Date fromTime) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addMeeting(Meeting meeting) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UUID getID() {
    // TODO Auto-generated method stub
    return null;
  }

}
