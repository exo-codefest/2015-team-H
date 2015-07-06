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

import org.exoplatform.codefestH.service.TimeRange;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class TimeRangeimpl implements TimeRange {

  @Override
  public String getTimeRangeNodePath() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean setTimeRangeNode(String timeRangeNodePath) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Date getBegin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setBegin(Date date) {
    // TODO Auto-generated method stub

  }

  @Override
  public Date getEnd() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setEnd(Date date) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<String> getWhoVote() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addVote(String username) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeVote(String username) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isConflict(TimeRange range) {
    // TODO Auto-generated method stub
    return false;
  }

}
