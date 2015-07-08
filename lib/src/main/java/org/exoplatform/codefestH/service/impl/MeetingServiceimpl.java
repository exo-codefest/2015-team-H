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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.exoplatform.codefestH.service.CalendarAPI;
import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.service.Referenceable;
import org.exoplatform.codefestH.service.TimeRange;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
public class MeetingServiceimpl implements MeetingService {

  private static final String MEETING_TYPE = "teamH:meeting";
  private final String TITLE = "mt:title";
  private final String DESCRIPTION = "mt:description";
  private final String OWNER = "mt:creator";
  private final String UPDATE_TIME = "mt:update";
  private final String STATUS = "mt:status";
  private final String PARTICIPANTS = "mt:participants";
  private final String COMMENTS = "mt:comment";
  private final String LOCATION = "mt:location";
  private final String TIME_SLOT = "mt:timeslot";
  private final String CREATE_DATE = "mt:date";

  private Log log = ExoLogger.getExoLogger(MeetingServiceimpl.class);
  private RepositoryService repoService;
  private SessionProviderService sessionProviderService;
  private String repo = "repository";
  private String ws = "collaboration";
  private String rootMeetingPath = "meeting";
  private CalendarAPI calendarAPI;

  public MeetingServiceimpl(RepositoryService repoService,
                            SessionProviderService sessionProviderService,
                            CalendarAPI calendarAPI){
    this.repoService = repoService;
    this.sessionProviderService = sessionProviderService;
    this.calendarAPI = calendarAPI;
    try {
      this.repo = repoService.getCurrentRepository().getConfiguration().getName();

    } catch (Exception ex) {
      if (log.isErrorEnabled()) {
        log.error("Using default repository & workspace", ex.getMessage());
      }
    }
  }
  private Session getSession() throws Exception {

    ManageableRepository repository = repoService.getCurrentRepository();
    return WCMCoreUtils.getUserSessionProvider().getSession(ws, repository);
  }



  @Override
  public Meeting getMeeting(String id) {
    Session session;
    try {
      session = this.getSession();
      if(!session.getRootNode().hasNode(rootMeetingPath)) return null;
      Node rootMeeting = session.getRootNode().getNode(rootMeetingPath);
      if(rootMeeting == null) return null;
      Node meetingNode = rootMeeting.getNode("/" + id);
      if(meetingNode == null) return null;
      Meeting result = new Meetingimpl(id, 
                                       meetingNode.getProperty(CREATE_DATE).getDate().getTime(), 
                                       meetingNode.getProperty(UPDATE_TIME).getDate().getTime(), 
                                       meetingNode.getProperty(TITLE).getString(), 
                                       meetingNode.getProperty(DESCRIPTION).getString(), 
                                       meetingNode.getProperty(OWNER).getString(), 
                                       meetingNode.getProperty(STATUS).getBoolean());
      return result;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Meeting removeMeeting(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean saveMeeting(Meeting meeting) {
    try {
      Session session = this.getSession();
      Node rootNode = session.getRootNode();
      Node meetingFolder = null;
      if(rootNode.hasNode(rootMeetingPath))
        meetingFolder = session.getRootNode().getNode(rootMeetingPath);
      else{
        meetingFolder = rootNode.addNode(this.rootMeetingPath,"nt:unstructured");
        rootNode.save();
      }
      Node meetingNode = null;
      if(!meetingFolder.hasNode(meeting.getID())) {
        //create new
        meetingNode = meetingFolder.addNode(meeting.getID(),MEETING_TYPE);
        meetingFolder.save();
      } else{
        meetingNode = meetingFolder.getNode(meeting.getID());
      }
      wrapMeetingToNode(meeting, meetingNode);
      meetingNode.save();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<Meeting> getMeetingByOwner(String username) {
    // Should refactor to improve performance
    List<Meeting> temp = getAllMeeting();
    ArrayList<Meeting> result = new ArrayList<Meeting>();
    for(Meeting m : temp){
      if(m.getOwner().equals(username)) result.add(m);
    }
    return result;
  }


  @Override
  public List<Meeting> getAllMeeting() {
    Session session;
    try {
      session = this.getSession();
      Node meetingRoot = session.getRootNode().getNode(this.rootMeetingPath);
      if(meetingRoot == null) {
        meetingRoot = session.getRootNode().addNode(this.rootMeetingPath);
        meetingRoot.save();
        return null;
      }
      ArrayList<Meeting> result = new ArrayList<Meeting>();
      NodeIterator iterator = meetingRoot.getNodes();
      while(iterator.hasNext()){
        Node tempNode = iterator.nextNode();
        Meeting tempMeeting = wrapNodeToMeeting(tempNode);
        if(tempMeeting != null) result.add(tempMeeting);
      }
      return result;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
  @Override
  public List<Meeting> getMeetingByParticipant(String username) {
    List<Meeting> temp = getAllMeeting();
    ArrayList<Meeting> result = new ArrayList<Meeting>();
    for(Meeting m : temp){
      if(m.getParticipants().contains(username)) result.add(m);
    }
    return result;
  }

  private Meeting wrapNodeToMeeting(Node n){

    try {
      Meeting result = new Meetingimpl(n.getName(), 
                                       n.getProperty(CREATE_DATE).getDate().getTime(), 
                                       n.getProperty(UPDATE_TIME).getDate().getTime(), 
                                       n.getProperty(TITLE).getString(), 
                                       n.getProperty(DESCRIPTION).getString(), 
                                       n.getProperty(OWNER).getString(), 
                                       n.getProperty(STATUS).getBoolean());
      if(result.isClose()){
        result.setFinalTime(wrapStringToTimeSlot(n.getProperty(TIME_SLOT).getString()).get(0));
      }
      else{
        result.setTimeRange(wrapStringToTimeSlot(n.getProperty(TIME_SLOT).getString()));
      }
      return result;
    } catch (ValueFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (PathNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RepositoryException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  private void wrapMeetingToNode(Meeting m, Node n){
    //n.setProperty(name, value)
    try {
      n.setProperty(TITLE,m.getTitle());
      n.setProperty(DESCRIPTION,m.getDescription());
      n.setProperty(OWNER,m.getOwner());
      n.setProperty(UPDATE_TIME,"");
      n.setProperty(STATUS,m.isClose());
      if(m.getParticipants() != null){      
        StringBuilder participants = new StringBuilder();
        for(String p : m.getParticipants()) participants.append(p + ",");      
        n.setProperty(PARTICIPANTS,participants.toString());      

      }
      if(m.getComments() != null)
        n.setProperty(COMMENTS,getIDToString(m.getComments()));

      n.setProperty(LOCATION,m.getLocation());
      if(m.isClose()){
        ArrayList<TimeRange> temp = new ArrayList<TimeRange>();
        temp.add(m.getFinalTime());
        n.setProperty(TIME_SLOT,wrapTimeSlotToString(temp));
      }else{
        n.setProperty(TIME_SLOT,wrapTimeSlotToString(m.getTimeSlot()));
      }
    } catch (ValueFormatException e) {
      e.printStackTrace();
    } catch (VersionException e) {
      e.printStackTrace();
    } catch (LockException e) {
      e.printStackTrace();
    } catch (ConstraintViolationException e) {
      e.printStackTrace();
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
  }
  private String wrapTimeSlotToString(List<TimeRange> timeSlot) {
    StringBuilder builder = new StringBuilder();
    for(TimeRange t : timeSlot){
      builder.append(t.toString())
      .append(";");
    }
    return builder.toString();
  }
  private List<TimeRange> wrapStringToTimeSlot(String data){
    SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
    String[] listSlot = data.split(";");
    List<TimeRange> result = new ArrayList<TimeRange>();
    for(int i = 0 ; i < listSlot.length; i ++){
      TimeRange tr = new TimeRangeimpl();
      String[] tr_data = listSlot[i].split(",");
      try {
        tr.setBegin(formatter.parse(tr_data[0]));
        tr.setEnd(formatter.parse(tr_data[1]));
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if(tr_data.length > 2){
        for(int j = 2; j < tr_data.length; j ++)
          tr.addVote(tr_data[j]);
      }
      result.add(tr);
    }
    return result;
  }
  private <T extends Referenceable> String getIDToString(List<T> list) {
    StringBuilder result = new StringBuilder();
    for(T p : list) result.append(p.getID() + ",");      
    return result.toString();
  }

  @Override
  public List<String> getParticipants(String meetingID) {

    Node meetingNode = getMeetingNode(meetingID);
    if(meetingNode == null) return null;
    String data = "";
    try {
      data = meetingNode.getProperty(PARTICIPANTS).getString();
    } catch (ValueFormatException e) {
      e.printStackTrace();
    } catch (PathNotFoundException e) {
      e.printStackTrace();
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    if(data.equals("")) return null;
    String[] temp = data.split(",");
    ArrayList<String> result = new ArrayList<String>();
    for(int i = 0; i < temp.length; i++)
      if(!temp[i].equals("")) result.add(temp[i]);
    return result;
  }
  private Node getMeetingNode(String meetingID) {
    Session session;
    try {
      session = this.getSession();
      Node meetingRoot = session.getRootNode().getNode(this.rootMeetingPath);
      if(meetingRoot == null) meetingRoot = session.getRootNode().addNode(this.rootMeetingPath);
      Node meetingNode = meetingRoot.getNode(meetingID);
      return meetingNode;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  @Override
  public boolean addParticipant(String meetingID, String username) {
    Node meetingNode = getMeetingNode(meetingID);
    if(meetingNode == null) return false;
    try {
      String data = meetingNode.getProperty(PARTICIPANTS).getString();
      if(data.contains(username)) return false;
      data += username + ",";
      meetingNode.setProperty(PARTICIPANTS, data);
      meetingNode.getSession().save();
    } catch (ValueFormatException e) {
      e.printStackTrace();
    } catch (PathNotFoundException e) {
      e.printStackTrace();
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return true;
  }
  @Override
  public boolean removeParticipant(String meetingID, String username) {
    Node meetingNode = getMeetingNode(meetingID);
    if(meetingNode == null) return false;
    try {
      String data = meetingNode.getProperty(PARTICIPANTS).getString();
      if(!data.contains(username)) return false;
      data.replace(username + ",", "");
      meetingNode.setProperty(PARTICIPANTS, data);
      meetingNode.getSession().save();
    } catch (ValueFormatException e) {
      e.printStackTrace();
    } catch (PathNotFoundException e) {
      e.printStackTrace();
    } catch (RepositoryException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public boolean setMeetingClose(String meetingID){
    Meeting meeting = getMeeting(meetingID);
    if(calendarAPI.createEvent(meeting)){
      meeting.setClose(true);
      saveMeeting(meeting);
      return true;
    }
    else
      return false;
  }

}
