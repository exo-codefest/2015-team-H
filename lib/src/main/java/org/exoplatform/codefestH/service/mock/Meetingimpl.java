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

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingRoom;
import org.exoplatform.codefestH.service.TimeRange;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class Meetingimpl implements Meeting {

  @Override
  public String getMeetingNodePath() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean setMeetingNodePath(String meetingNodePath) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Date getCreateTime() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Date getUpdateTime() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDescription(String description) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getOwner() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getParticipants() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addParticipants(String username) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<TimeRange> getTimeSlot() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getComments() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addComment(String username, String comment) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeComment(String path, String user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setFinalTime(TimeRange time) {
    // TODO Auto-generated method stub

  }

  @Override
  public TimeRange getFinalTime() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isClose() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean setClose(boolean status) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public MeetingRoom getLocation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean setLocation(MeetingRoom room) {
    // TODO Auto-generated method stub
    return false;
  }

}
