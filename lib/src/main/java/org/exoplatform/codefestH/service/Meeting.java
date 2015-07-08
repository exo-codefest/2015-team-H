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


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 * Store data about Meeting Object
 */
public interface Meeting extends Referenceable{


  public final static String OPEN = "open";
  public final static String CLOSED = "closed";
  public final static String REOPEN = "reopen";


  /**
   * Get Id of meeting
   * @return
   */
  public String getID();
  /**
   * Get Create time of Meeting
   * @return
   */
  public Date getCreateTime();
  
  /**
   * Get last update of Meeting
   * @return
   */
  public Date getUpdateTime();
  
  /**
   * Get Title of Meeting
   * @return
   */
  public String getTitle();
  
  /**
   * Set Title of Meeting
   * @param title
   */
  public void setTitle(String title);
  
  /**
   * Get description of Meeting
   * @return
   */
  public String getDescription();
  
  /**
   * Set Description of Meeting
   * @param description
   */
  public void setDescription(String description);
  /**
   * Get Owner of meeting, only Owner can close and modify Meeting
   * @return username of Owner
   */
  public String getOwner();
  
  /**
   * Get list username of participants
   * @return
   */
  public List<String> getParticipants();
  
  /**
   * Add one username to Participants list
   * @param username
   * @return false if user already in Participants list
   */
  public boolean addParticipants(String username);
  
  /**
   * Get all time slot of Meeting
   * @return
   */
  public List<TimeRange> getTimeSlot();
  
  /**
   * get all comments of Meeting
   * @return
   */
  public List<MeetingComment> getComments();
  
  /**
   * Add one comment to Meeting
   * @param username owner of comment
   * @param comment detail of comment
   * @return false if user doesn't exist in Participants list
   */
  public boolean addComment(String username,String comment);
  
  /**
   * Remove comment of Meeting
   * @param id ID of JCR Node store data of comment
   * @param user user perform action
   * @return false if comment not belong to meeting or user is not Meeting owner or Comment owner
   */
  public boolean removeComment(String id, String user);
  
  /**
   * Set conclude time of Meeting, after closing poll
   * @param time
   */
  public void setFinalTime(TimeRange time);
  
  
  /**
   * Get conclude time of Meeting, after closing poll
   * @return
   */
  public TimeRange getFinalTime();
  
  /**
   * Get status of Meeting, after closed, Meeting will have final time and create an event
   * of Calendar with all Participants
   * @return
   */
  public boolean isClose();
  
  /**
   * Set status of Meeting, when closing, Meeting will create Event of Calendar with all participants.
   * When reopening, Meeting will delete Event.
   * @param status true if close or reopen successful, false if set close Meeting but final time is null
   * @return
   */
  public boolean setClose(boolean status);
  
  /**
   * Get location of Meeting
   * @return
   */
  public String getLocation();
  
  /**
   * Set location of Meeting
   * @param room
   * @return
   */
  public boolean setLocation(String room);




  void setParticipants(List<String> participants);
  public void setTimeRange(List<TimeRange> timeSlot);
  

}
