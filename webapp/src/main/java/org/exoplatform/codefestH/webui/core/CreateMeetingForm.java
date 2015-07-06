package org.exoplatform.codefestH.webui.core;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.form.UIForm;

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/meeting/webui/component/CreateMeetingForm.gtmpl")
public class CreateMeetingForm extends UIForm {
  private static String FIELD_TITLE_TEXT_BOX = "titleTextBox";
  private static String FIELD_LOCATION_TEXT_BOX = "locationTextBox";
  private static String FIELD_DESCRIPTION_TEXT_AREA = "descriptionTextArea";
  private static String FIELD_DATE_TEXT_BOX = "dateTextBox";
  private static String FIELD_TIME1_TEXT_BOX = "time1TextBox";
  private static String FIELD_TIME2_TEXT_BOX = "time2TextBox";
  private static String FIELD_TIME3_TEXT_BOX = "time3TextBox";
  private static String FIELD_PARTICIPANTS = "participants";

  public CreateMeetingForm() {

  }

}
