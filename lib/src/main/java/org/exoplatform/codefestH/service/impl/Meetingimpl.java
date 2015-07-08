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

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingComment;
import org.exoplatform.codefestH.service.MeetingRoom;
import org.exoplatform.codefestH.service.TimeRange;
import org.exoplatform.services.idgenerator.IDGeneratorService;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
public class Meetingimpl implements Meeting {


  private Date creatTime;
  private Date updateTime;
  private String title;
  private String description;
  private String owner;
  private List<String> participants;
  private List<TimeRange> timeSlot;
  private List<MeetingComment> comments;
  private boolean status;
  private TimeRange finalTime;
  private String location;
  private String id;

  public Meetingimpl(String id, Date creatTime, Date updateTime, String title, String description, String owner, boolean status){
    this.id = id;
    if(creatTime != null) this.creatTime = creatTime;
    else this.creatTime = new Date();
    if(updateTime != null) this.updateTime = updateTime;
    else this.updateTime = new Date();
    this.title = title;
    this.description = description;
    this.owner = owner;
    this.status = status;
  }
  @Override
  public String getID() {
    if(this.id.equals(""))
      this.id = UUID.randomUUID().toString().replace("-", "");
    return this.id;
  }


  @Override
  public Date getCreateTime() {
    return this.creatTime;
  }

  @Override
  public Date getUpdateTime() {
    return this.updateTime;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;

  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String getOwner() {
    return this.owner;
  }

  @Override
  public List<String> getParticipants() {
    return this.participants;
  }
  
  @Override
  public void setParticipants(List<String> participants) {
    this.participants = participants;
  }

  @Override
  public boolean addParticipants(String username) {
    for(String user : participants)
      if(user.equals(username)) return false;
    this.participants.add(username);
    return false;
  }

  @Override
  public List<TimeRange> getTimeSlot() {
    return this.timeSlot;
  }

  @Override
  public List<MeetingComment> getComments() {
    return this.comments;
  }

  @Override
  public boolean addComment(String username, String comment) {    
    this.comments.add(null);
    return true;
  }

  @Override
  public boolean removeComment(String ID, String user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setFinalTime(TimeRange time) {
    this.finalTime = time;

  }

  @Override
  public TimeRange getFinalTime() {
    return finalTime;
  }

  @Override
  public boolean isClose() {
    return this.status;
  }

  @Override
  public boolean setClose(boolean status) {
    this.status = status;
    return true;
  }

  @Override
  public String getLocation() {
    return this.location;
  }

  @Override
  public boolean setLocation(String room) {
    this.location = room;
    return true;
  }
  @Override
  public void setTimeRange(List<TimeRange> timeSlot) {
    this.timeSlot = timeSlot;
    
  }


}
