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
package org.exoplatform.codefestH.service.test;

import java.util.Date;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.service.impl.Meetingimpl;
import org.exoplatform.services.jcr.config.RepositoryServiceConfiguration;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
public class TestMeetingService extends BaseTest {
  public void testSave(){
    MeetingService service = getService(MeetingService.class);
    RepositoryServiceConfiguration config = getService(RepositoryServiceConfiguration.class);
    String configXML = config.getConfigurationXML();
    Meeting meeting = new Meetingimpl("", new Date(), new Date(),"title", "description", "owner",  false);
    service.saveMeeting(meeting);
    Meeting test = service.getMeeting(meeting.getID());
    //assertEquals(test.getOwner(), meeting.getOwner());
  }
}
