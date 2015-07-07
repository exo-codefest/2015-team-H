package org.exoplatform.codefestH.webui.core;

import org.exoplatform.web.application.CompoundApplicationMessage;
import org.exoplatform.webui.form.validator.NumberFormatValidator;

/**
 * Created by dongpd on 7/7/15.
 */
public class Time24Validator extends NumberFormatValidator {
  private static final int MIN = 0;
  private static final int MAX = 23;

  protected String getMessageLocalizationKey() {
    return "NumberRangeValidator.msg.Invalid-number";
  }

  protected Integer validateInteger(String value, String label, CompoundApplicationMessage messages) {
    Integer integer = super.validateInteger(value, label, messages);
    if(integer == null) {
      return null;
    } else if(integer.intValue() >= this.MIN && integer.intValue() <= this.MAX) {
      return integer;
    } else {
      messages.addMessage(this.getMessageLocalizationKey(), new Object[]{label, Integer.valueOf(this.MIN), Integer.valueOf(this.MAX)});
      return null;
    }
  }
}
