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
 * Store data about TimeRange object
 */
public interface TimeRange extends Referenceable{
  /**
   * Get path of JCR Node store data of Time Range
   * @return
   */
  public String getTimeRangeNodePath();
  
  /**
   * Set path of JCR Node store data of Time Range
   * @param timeRangeNodePath
   * @return
   */
  public boolean setTimeRangeNodePath(String timeRangeNodePath);
  
  /**
   * get Start of Time Range
   * @return
   */
  public Date getBegin();
  
  /**
   * Set Start of Time Range
   * @param date
   */
  public void setBegin(Date date);
  
  /**
   * get End of Time Range
   * @return
   */
  public Date getEnd();
  
  /**
   * Set End of Time Range
   * @param date
   */
  public void setEnd(Date date);
  
  /**
   * Get list user vote for this Time range
   * @return
   */
  public List<String> getWhoVote();
  
  /**
   * Add vote for this Time Range
   * @param username user vote for this Time Range
   */
  public void addVote(String username);
  
  /**
   * Remove vote of user for this Time Range
   * @param username
   */
  public void removeVote(String username);
  
  /**
   * Check conflict with other Time Range
   * @param range
   * @return
   */
  public boolean isConflict(TimeRange range);
}
