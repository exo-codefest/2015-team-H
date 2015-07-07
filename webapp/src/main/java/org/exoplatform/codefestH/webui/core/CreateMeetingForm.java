package org.exoplatform.codefestH.webui.core;

import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingRoom;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.service.TimeRange;
import org.exoplatform.codefestH.service.mock.Meetingimpl;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.Event.Phase;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormDateTimeInput;
import org.exoplatform.webui.form.UIFormInputBase;
import org.exoplatform.webui.form.UIFormMultiValueInputSet;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/CreateMeetingForm.gtmpl",
        events = {
                @EventConfig(listeners = CreateMeetingForm.SaveMeetingActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.SelectMemberActionListener.class),
                @EventConfig(listeners = CreateMeetingForm.CancelMeetingActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.AddActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.RemoveActionListener.class),
        })
public class CreateMeetingForm extends UIForm {
  public static String FIELD_TITLE_TEXT_BOX = "titleTextBox";
  public static String FIELD_LOCATION_TEXT_BOX = "locationTextBox";
  public static String FIELD_DESCRIPTION_TEXT_AREA = "descriptionTextArea";
  public static String FIELD_DATE_TEXT_BOX = "dateTextBox";
  public static String FIELD_PARTICIPANTS_TEXT_BOX = "participantsTextBox";
  public static String FIELD_TIME_TEXT_BOX = "timeTextBox";

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

    UIFormMultiValueInputSet uiFormMValue =
            createUIComponent(UIFormMultiValueInputSet.class, null, FIELD_TIME_TEXT_BOX);
    this.addUIFormInput(uiFormMValue);
    uiFormMValue.setType(UIFormStringInput.class);
    uiFormMValue.setValue(new ArrayList(3));

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
      String participants = createMeetingForm.getUIStringInput(FIELD_PARTICIPANTS_TEXT_BOX).getValue();

      MeetingService meetingService = CommonsUtils.getService(MeetingService.class);

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

  static public class AddActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      CreateMeetingForm createMeetingForm = event.getSource();
      event.getRequestContext().addUIComponentToUpdateByAjax(createMeetingForm);
    }
  }

  static public class RemoveActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      CreateMeetingForm uiForm = event.getSource();
      UIFormMultiValueInputSet uiSet = uiForm.findFirstComponentOfType(UIFormMultiValueInputSet.class);
      List<UIComponent> children = uiSet.getChildren();
      for(int i = 0; i < children.size(); i ++) {
        UIFormInputBase<?> uiInput = (UIFormInputBase<?>)children.get(i);
        uiInput.setId(FIELD_TIME_TEXT_BOX + String.valueOf(i));
        uiInput.setName(FIELD_TIME_TEXT_BOX + String.valueOf(i));
      }
      event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
    }
  }
}
