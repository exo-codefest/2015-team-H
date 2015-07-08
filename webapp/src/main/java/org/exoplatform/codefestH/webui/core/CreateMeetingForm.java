package org.exoplatform.codefestH.webui.core;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.exoplatform.codefestH.service.Meeting;
import org.exoplatform.codefestH.service.MeetingRoom;
import org.exoplatform.codefestH.service.MeetingService;
import org.exoplatform.codefestH.service.TimeRange;
import org.exoplatform.codefestH.service.impl.TimeRangeimpl;
import org.exoplatform.codefestH.service.mock.Meetingimpl;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPortletApplication;
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
import org.exoplatform.webui.form.validator.MandatoryValidator;
import org.exoplatform.webui.form.validator.NumberRangeValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/CreateMeetingForm.gtmpl",
        events = {
                @EventConfig(listeners = CreateMeetingForm.SaveMeetingActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.SelectMemberActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.CancelMeetingActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.AddActionListener.class),
                @EventConfig(phase = Phase.DECODE, listeners = CreateMeetingForm.RemoveActionListener.class)
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
    titleTextBox.addValidator(MandatoryValidator.class);
    this.addUIFormInput(titleTextBox);

    UIFormStringInput locationTextBox = new UIFormStringInput(FIELD_LOCATION_TEXT_BOX, FIELD_LOCATION_TEXT_BOX, null);
    this.addUIFormInput(locationTextBox);

    UIFormTextAreaInput descriptionTextArea = new UIFormTextAreaInput(FIELD_DESCRIPTION_TEXT_AREA,
            FIELD_DESCRIPTION_TEXT_AREA, null);
    this.addUIFormInput(descriptionTextArea);

    UIFormDateTimeInput dateTextBox = new UIFormDateTimeInput(FIELD_DATE_TEXT_BOX, FIELD_DATE_TEXT_BOX, null);
    dateTextBox.setDisplayTime(false);
    dateTextBox.setReadOnly(true);
    dateTextBox.addValidator(MandatoryValidator.class);
    this.addUIFormInput(dateTextBox);

    UIFormMultiValueInputSet uiFormMValue =
            createUIComponent(UIFormMultiValueInputSet.class, null, FIELD_TIME_TEXT_BOX);
    uiFormMValue.setType(UIFormStringInput.class);
    List<String> values = new ArrayList<String>();
    values.add(StringUtils.EMPTY);
    values.add(StringUtils.EMPTY);
    values.add(StringUtils.EMPTY);
    uiFormMValue.setValue(values);
    uiFormMValue.addValidator(Time24Validator.class);
    uiFormMValue.addValidator(MandatoryValidator.class);
    this.addUIFormInput(uiFormMValue);

    UIFormStringInput participantsTextBox = new UIFormStringInput(FIELD_PARTICIPANTS_TEXT_BOX,
            FIELD_PARTICIPANTS_TEXT_BOX, null);

    participantsTextBox.setReadOnly(true);
    this.addUIFormInput(participantsTextBox);

  }

  static public class SaveMeetingActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      // Get data from screen
      CreateMeetingForm createMeetingForm = event.getSource();
      String title = createMeetingForm.getUIStringInput(FIELD_TITLE_TEXT_BOX).getValue();
      String location = createMeetingForm.getUIStringInput(FIELD_LOCATION_TEXT_BOX).getValue();
      String desc = createMeetingForm.getUIFormTextAreaInput(FIELD_DESCRIPTION_TEXT_AREA).getValue();
      Date date = createMeetingForm.getUIFormDateTimeInput(FIELD_DATE_TEXT_BOX).getCalendar().getTime();
      String participants = createMeetingForm.getUIStringInput(FIELD_PARTICIPANTS_TEXT_BOX).getValue();
      String userId = ConversationState.getCurrent().getIdentity().getUserId();
      List<UIComponent> timeSlots = createMeetingForm.getChild(UIFormMultiValueInputSet.class).getChildren();

      // Save
      MeetingService meetingService = CommonsUtils.getService(MeetingService.class);
      Meeting meeting = new org.exoplatform.codefestH.service.impl.Meetingimpl(
              "", null, null, title, desc, userId, false);
      meeting.setLocation(location);
      if (StringUtils.isNotEmpty(participants)) {
        meeting.setParticipants(Arrays.asList(participants.split(",")));
      }
      for (UIComponent uiFormStringInput : timeSlots) {
        String time24 = ((UIFormStringInput) uiFormStringInput).getValue();
        if (StringUtils.isNotEmpty(time24)) {
          TimeRange timeRange = new TimeRangeimpl();
          date.setHours(Integer.parseInt(time24));
          timeRange.setBegin((Date) date.clone());
        }
      }
      meetingService.saveMeeting(meeting);

      // Update screen
      createMeetingForm.setRendered(false);
      createMeetingForm.getAncestorOfType(UIPortletApplication.class).getChild(UIMeetingList.class).setRendered(true);

      event.getRequestContext().addUIComponentToUpdateByAjax(createMeetingForm.getParent());
    }
  }

  static public class CancelMeetingActionListener extends EventListener<CreateMeetingForm> {
    public void execute(Event<CreateMeetingForm> event) throws Exception {
      CreateMeetingForm createMeetingForm = event.getSource();
      createMeetingForm.setRendered(false);
      createMeetingForm.getAncestorOfType(UIPortletApplication.class).getChild(UIMeetingList.class).setRendered(true);

      event.getRequestContext().addUIComponentToUpdateByAjax(createMeetingForm.getParent());
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
      for (int i = 0; i < children.size(); i++) {
        UIFormInputBase<?> uiInput = (UIFormInputBase<?>) children.get(i);
        uiInput.setId(FIELD_TIME_TEXT_BOX + String.valueOf(i));
        uiInput.setName(FIELD_TIME_TEXT_BOX + String.valueOf(i));
      }
      event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
    }
  }
}
