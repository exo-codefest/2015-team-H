package org.exoplatform.codefestH.listeners;

import org.apache.commons.chain.Context;
import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.PluginKey;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.services.command.action.Action;
import org.exoplatform.services.ext.action.InvocationContext;

import javax.jcr.Node;

/**
 * An action will be executed right after the poll created
 */
public class ScheduleMeetingListener implements Action {

  @Override
  public boolean execute(Context context) throws Exception {
    Node meetingNode = (Node)context.get(InvocationContext.CURRENT_ITEM);
    NotificationContext ctx = NotificationContextImpl.cloneInstance().append(ScheduleMeetingPlugin.MEETING,
            meetingNode.getName());
    ctx.getNotificationExecutor().with(ctx.makeCommand(PluginKey.key(ScheduleMeetingPlugin.ID))).execute(ctx);
    return false;
  }
}
