package org.exoplatform.codefestH.webui.core;

import org.exoplatform.codefestH.webui.portlet.MeetingPortlet;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.ecm.webui.selector.UISelectable;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPopupWindow;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.Event.Phase;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormDateTimeInput;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/CreateMeetingForm.gtmpl",
        events = {
                @EventConfig(listeners = CreateMeetingForm.SaveMeetingActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.SelectMemberActionListener.class),
                @EventConfig(listeners = CreateMeetingForm.CancelMeetingActionListener.class)


        })
public class CreateMeetingForm extends UIForm {
  public static String FIELD_TITLE_TEXT_BOX = "titleTextBox";
  public static String FIELD_LOCATION_TEXT_BOX = "locationTextBox";
  public static String FIELD_DESCRIPTION_TEXT_AREA = "descriptionTextArea";
  public static String FIELD_DATE_TEXT_BOX = "dateTextBox";
  public static String FIELD_TIME1_TEXT_BOX = "time1TextBox";
  public static String FIELD_TIME2_TEXT_BOX = "time2TextBox";
  public static String FIELD_TIME3_TEXT_BOX = "time3TextBox";
  public static String FIELD_PARTICIPANTS_TEXT_BOX = "participantsTextBox";

  public CreateMeetingForm() throws Exception {
    UIFormStringInput titleTextBox = new UIFormStringInput(FIELD_TITLE_TEXT_BOX, FIELD_TITLE_TEXT_BOX, null);
    this.addUIFormInput(titleTextBox);

    UIFormStringInput locationTextBox = new UIFormStringInput(FIELD_LOCATION_TEXT_BOX, FIELD_LOCATION_TEXT_BOX, null);
    this.addUIFormInput(locationTextBox);

    UIFormTextAreaInput descriptionTextArea = new UIFormTextAreaInput(FIELD_DESCRIPTION_TEXT_AREA,
            FIELD_DESCRIPTION_TEXT_AREA, null);
    this.addUIFormInput(descriptionTextArea);

    UIFormDateTimeInput dateTextBox = new UIFormDateTimeInput(FIELD_DATE_TEXT_BOX, FIELD_DATE_TEXT_BOX, null);
    this.addUIFormInput(dateTextBox);

    UIFormStringInput time1TextBox = new UIFormStringInput(FIELD_TIME1_TEXT_BOX, FIELD_TIME1_TEXT_BOX, null);
    this.addUIFormInput(time1TextBox);


    UIFormStringInput time2TextBox = new UIFormStringInput(FIELD_TIME2_TEXT_BOX, FIELD_TIME2_TEXT_BOX, null);
    this.addUIFormInput(time2TextBox);

    UIFormStringInput time3TextBox = new UIFormStringInput(FIELD_TIME3_TEXT_BOX, FIELD_TIME3_TEXT_BOX, null);
    this.addUIFormInput(time3TextBox);

    UIFormStringInput participantsTextBox = new UIFormStringInput(FIELD_PARTICIPANTS_TEXT_BOX,
            FIELD_PARTICIPANTS_TEXT_BOX, null);
    participantsTextBox.setReadOnly(true);
    this.addUIFormInput(participantsTextBox);

  }

  static public class SaveMeetingActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      CreateMeetingForm createMeetingForm = event.getSource();
      String title = createMeetingForm.getUIStringInput(FIELD_TITLE_TEXT_BOX).getValue();
      String location = createMeetingForm.getUIStringInput(FIELD_LOCATION_TEXT_BOX).getValue();
      String desc = createMeetingForm.getUIFormTextAreaInput(FIELD_DESCRIPTION_TEXT_AREA).getValue();
      String date = createMeetingForm.getUIFormDateTimeInput(FIELD_DATE_TEXT_BOX).getValue();
      String time1 = createMeetingForm.getUIStringInput(FIELD_TIME1_TEXT_BOX).getValue();
      String time2 = createMeetingForm.getUIStringInput(FIELD_TIME2_TEXT_BOX).getValue();
      String time3 = createMeetingForm.getUIStringInput(FIELD_TIME3_TEXT_BOX).getValue();
      String participants = createMeetingForm.getUIStringInput(FIELD_PARTICIPANTS_TEXT_BOX).getValue();
    }
  }

  static public class CancelMeetingActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {

    }
  }

  static public class SelectMemberActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      CreateMeetingForm uiForm = event.getSource();
      UIUserContainer uiUserContainer = uiForm.createUIComponent(UIUserContainer.class, null, null);
      UIPopupContainer uiPopupContainer = uiForm.getParent().findFirstComponentOfType(UIPopupContainer.class);
      uiPopupContainer.activate(uiUserContainer, 700, 400, true);
      event.getRequestContext().addUIComponentToUpdateByAjax(uiPopupContainer);
    }
  }
}
