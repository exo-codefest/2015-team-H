package org.exoplatform.codefestH.webui.core;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormStringInput;
import org.exoplatform.webui.form.UIFormTextAreaInput;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/CreateMeetingForm.gtmpl",
        events = {
                @EventConfig(listeners = CreateMeetingForm.SaveMeetingActionListener.class)})
public class CreateMeetingForm extends UIForm {
  private static String FIELD_TITLE_TEXT_BOX = "titleTextBox";
  private static String FIELD_LOCATION_TEXT_BOX = "locationTextBox";
  private static String FIELD_DESCRIPTION_TEXT_AREA = "descriptionTextArea";
  private static String FIELD_DATE_TEXT_BOX = "dateTextBox";
  private static String FIELD_TIME1_TEXT_BOX = "time1TextBox";
  private static String FIELD_TIME2_TEXT_BOX = "time2TextBox";
  private static String FIELD_TIME3_TEXT_BOX = "time3TextBox";
  private static String FIELD_PARTICIPANTS_TEXT_BOX = "participantsTextBox";

  public CreateMeetingForm() {
    UIFormStringInput titleTextBox = new UIFormStringInput(FIELD_TITLE_TEXT_BOX, FIELD_TITLE_TEXT_BOX, null);
    this.addUIFormInput(titleTextBox);

    UIFormStringInput locationTextBox = new UIFormStringInput(FIELD_LOCATION_TEXT_BOX, FIELD_LOCATION_TEXT_BOX, null);
    this.addUIFormInput(locationTextBox);

    UIFormTextAreaInput descriptionTextArea = new UIFormTextAreaInput(FIELD_DESCRIPTION_TEXT_AREA,
            FIELD_DESCRIPTION_TEXT_AREA, null);
    this.addUIFormInput(descriptionTextArea);

    UIFormStringInput dateTextBox = new UIFormStringInput(FIELD_DATE_TEXT_BOX, FIELD_DATE_TEXT_BOX, null);
    this.addUIFormInput(dateTextBox);

    UIFormStringInput time1TextBox = new UIFormStringInput(FIELD_TIME1_TEXT_BOX, FIELD_TIME1_TEXT_BOX, null);
    this.addUIFormInput(time1TextBox);


    UIFormStringInput time2TextBox = new UIFormStringInput(FIELD_TIME2_TEXT_BOX, FIELD_TIME2_TEXT_BOX, null);
    this.addUIFormInput(time2TextBox);

    UIFormStringInput time3TextBox = new UIFormStringInput(FIELD_TIME3_TEXT_BOX, FIELD_TIME3_TEXT_BOX, null);
    this.addUIFormInput(time3TextBox);

    UIFormStringInput participantsTextBox = new UIFormStringInput(FIELD_PARTICIPANTS_TEXT_BOX,
            FIELD_PARTICIPANTS_TEXT_BOX, null);
    this.addUIFormInput(participantsTextBox);
  }

  static public class SaveMeetingActionListener extends EventListener<SaveMeetingActionListener> {
    public void execute(Event<SaveMeetingActionListener> event) throws Exception {

    }
  }
}
