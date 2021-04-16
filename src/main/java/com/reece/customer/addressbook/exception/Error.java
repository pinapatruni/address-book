package com.reece.customer.addressbook.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Error
 */

public class Error   {
  @JsonProperty("errorCode")
  private String errorCode;

  @JsonProperty("errorDesc")
  private String errorDesc;

  @JsonProperty("userErrorDesc")
  private String userErrorDesc;

  @JsonProperty("detailedErrorMessages")
  @Valid
  private List<DetailedErrorMessage> detailedErrorMessages = null;


  public Error errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Get errorCode
   * @return errorCode
  */
  @NotNull


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public Error errorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
    return this;
  }

  /**
   * Get errorDesc
   * @return errorDesc
  */
  @NotNull


  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  public Error userErrorDesc(String userErrorDesc) {
    this.userErrorDesc = userErrorDesc;
    return this;
  }

  /**
   * Get userErrorDesc
   * @return userErrorDesc
  */


  public String getUserErrorDesc() {
    return userErrorDesc;
  }

  public void setUserErrorDesc(String userErrorDesc) {
    this.userErrorDesc = userErrorDesc;
  }

  public Error detailedErrorMessages(List<DetailedErrorMessage> detailedErrorMessages) {
    this.detailedErrorMessages = detailedErrorMessages;
    return this;
  }

  public Error addDetailedErrorMessagesItem(DetailedErrorMessage detailedErrorMessagesItem) {
    if (this.detailedErrorMessages == null) {
      this.detailedErrorMessages = new ArrayList<DetailedErrorMessage>();
    }
    this.detailedErrorMessages.add(detailedErrorMessagesItem);
    return this;
  }

  /**
   * Get detailedErrorMessages
   * @return detailedErrorMessages
  */

  @Valid

  public List<DetailedErrorMessage> getDetailedErrorMessages() {
    return detailedErrorMessages;
  }

  public void setDetailedErrorMessages(List<DetailedErrorMessage> detailedErrorMessages) {
    this.detailedErrorMessages = detailedErrorMessages;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.errorCode, error.errorCode) &&
        Objects.equals(this.errorDesc, error.errorDesc) &&
        Objects.equals(this.userErrorDesc, error.userErrorDesc) &&
        Objects.equals(this.detailedErrorMessages, error.detailedErrorMessages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorDesc, userErrorDesc, detailedErrorMessages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorDesc: ").append(toIndentedString(errorDesc)).append("\n");
    sb.append("    userErrorDesc: ").append(toIndentedString(userErrorDesc)).append("\n");
    sb.append("    detailedErrorMessages: ").append(toIndentedString(detailedErrorMessages)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

