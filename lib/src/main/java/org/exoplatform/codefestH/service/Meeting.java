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
package org.exoplatform.codefestH.service;

import java.util.Date;
import java.util.List;

import javax.jcr.Node;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public interface Meeting {
  public Node getMeetingNode();
  public boolean setMeetingNode(Node meetingNode);
  public Date getCreateTime();
  public Date getUpdateTime();
  public String getDescription();
  public void setDescription(String description);
  public String getOwner();
  public List<String> getParticipants();
  public boolean addParticipants(String username);
  public List<TimeRange> getTimeSlot();
  public List<String> getComments();
  public boolean addComment(String username,String comment);
  public void setFinalTime(TimeRange time);
  public TimeRange getFinalTime();
  public boolean isClose();
  public MeetingRoom getLocation();
  public boolean setLocation(MeetingRoom room);
  
}
