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
 * Service handle CRUD operation of MeetingComment
 */
public interface MeetingCommentService {
  public MeetingComment getMeetingComment(String path);
  public MeetingComment getMeetingComment(UUID id);
  public MeetingComment removeMeetingComment(String path);
  public MeetingComment removeMeetingComment(UUID id);
  public boolean saveMeetingComment(MeetingComment meeting);
}
