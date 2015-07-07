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

import java.util.List;
import java.util.UUID;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 6, 2015  
 * 
 * Service handle CRUD operation of Meeting
 */
public interface MeetingService {

  public Meeting getMeeting(String path);
  public Meeting getMeeting(UUID id);
  public Meeting removeMeeting(String path);
  public Meeting removeMeeting(UUID id);
  public boolean saveMeeting(Meeting meeting);
  public List<Meeting> getMeetingByOwner(String username);
  public List<Meeting> getMeetingByParticipant(String username);

  /**
   * Remove a participant from teh poll
   * @param pollId Identify of a poll
   * @param username Username of participant who will be removed from the poll
   */
  public void removeParticipant(String pollId, String username);

  /**
   * Close the poll in the poll owner has enough response
   * @param pollId
   */
  public void closePoll(String pollId);

  /**
   * Reopen poll to vote again
   * @param pollId Identify of a Poll
   */
  public void reopenPoll(String pollId);

  /**
   * Return status of a poll
   * ex: closed, reopen, open
   * @param pollId
   * @return Status of a poll
   */
  public String getPollStatus(String pollId);
}
