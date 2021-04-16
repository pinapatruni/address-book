package com.reece.customer.addressbook.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * DetailedErrorMessage
 */

public class DetailedErrorMessage   {
  @JsonProperty("attributeName")
  private String attributeName;

  @JsonProperty("errorDescription")
  private String errorDescription;

  @JsonProperty("userErrorDescription")
  private String userErrorDescription;

  public DetailedErrorMessage attributeName(String attributeName) {
    this.attributeName = attributeName;
    return this;
  }

  /**
   * Get attributeName
   * @return attributeName
  */


  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  public DetailedErrorMessage errorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
    return this;
  }

  /**
   * Get errorDescription
   * @return errorDescription
  */


  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public DetailedErrorMessage userErrorDescription(String userErrorDescription) {
    this.userErrorDescription = userErrorDescription;
    return this;
  }

  /**
   * Get userErrorDescription
   * @return userErrorDescription
  */


  public String getUserErrorDescription() {
    return userErrorDescription;
  }

  public void setUserErrorDescription(String userErrorDescription) {
    this.userErrorDescription = userErrorDescription;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailedErrorMessage detailedErrorMessage = (DetailedErrorMessage) o;
    return Objects.equals(this.attributeName, detailedErrorMessage.attributeName) &&
        Objects.equals(this.errorDescription, detailedErrorMessage.errorDescription) &&
        Objects.equals(this.userErrorDescription, detailedErrorMessage.userErrorDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributeName, errorDescription, userErrorDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailedErrorMessage {\n");
    
    sb.append("    attributeName: ").append(toIndentedString(attributeName)).append("\n");
    sb.append("    errorDescription: ").append(toIndentedString(errorDescription)).append("\n");
    sb.append("    userErrorDescription: ").append(toIndentedString(userErrorDescription)).append("\n");
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

