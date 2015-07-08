package org.exoplatform.codefestH.webui.core;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponentDecorator;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

@ComponentConfig(
        template = "app:/groovy/meeting/webui/component/UIMeetingList.gtmpl",
        events = {
                @EventConfig(listeners = UIMeetingList.ViewActionListener.class),
                @EventConfig(listeners = UIMeetingList.AddNewActionListener.class)
        }
)
public class UIMeetingList extends UIComponentDecorator {

  static public class ViewActionListener extends EventListener<UIMeetingList> {
    public void execute(Event<UIMeetingList> event) throws Exception {
    }
  }

  static public class AddNewActionListener extends EventListener<UIMeetingList> {
    public void execute(Event<UIMeetingList> event) throws Exception {
    }
  }
}
