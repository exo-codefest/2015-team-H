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
 */
public interface MeetingRoom {
  /**
   * Get name of Meeting Room
   * @return
   */
  public String getName();
  
  /**
   * Get list Meeting at Meeting room 
   * @return
   */
  public List<Meeting> getMeetings();
  
  /**
   * Get busy time of Meeting Room from specific moment
   * @param fromTime
   * @return
   */
  public List<TimeRange> getBusyTime(Date fromTime);
  
  /**
   * Add one meeting to Meeting room, if schedule of meeting conflict with other meeting, 
   * this action cannot perform
   * @param meeting
   * @return false if conflict with other meeting
   */
  public boolean addMeeting(Meeting meeting);
}
