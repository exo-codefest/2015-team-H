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

import org.exoplatform.codefestH.service.TimeRange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 */
public class TimeRangeimpl implements TimeRange {

  private Date begin;
  private Date end;
  private String beginTime;
  private String endTime;
  private List<String> whoVote = new ArrayList<String>();

  @Override
  public Date getBegin() {
    return this.begin;
  }

  @Override
  public void setBegin(Date date) {
    this.begin = date;
  }

  @Override
  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }

  @Override
  public String getBeginTime() {
    return this.beginTime;
  }

  @Override
  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Override
  public String getEndTime() {
    return this.endTime;
  }

  @Override
  public Date getEnd() {
    return this.end;
  }

  @Override
  public void setEnd(Date date) {
    this.end = date;
  }

  @Override
  public List<String> getWhoVote() {
    return this.whoVote;
  }

  @Override
  public void addVote(String username) {
    this.whoVote.add(username);

  }

  @Override
  public void removeVote(String username) {
    this.whoVote.remove(username);
  }

  @Override
  public boolean isConflict(TimeRange range) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getID() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public String toString() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/hh:mm:ss");
    StringBuilder builder = new StringBuilder();
    if(begin != null)
    builder.append(formatter.format(begin));
    builder.append(",");
    if(end != null)
    builder.append(formatter.format(end));
          builder.append(",");
    for(String s : whoVote){
      builder.append(s)
              .append(",");
    }
    return builder.toString();
  }
}
