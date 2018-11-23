package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QRItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-23T14:33:33.583Z[GMT]")

public class QRItem   {
  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("qr")
  private String qr = null;

  public QRItem uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Get uri
   * @return uri
  **/
  @ApiModelProperty(example = "d290f1ee6c", required = true, value = "")
  @NotNull


  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public QRItem qr(String qr) {
    this.qr = qr;
    return this;
  }

  /**
   * Get qr
   * @return qr
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getQr() {
    return qr;
  }

  public void setQr(String qr) {
    this.qr = qr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QRItem qrItem = (QRItem) o;
    return Objects.equals(this.uri, qrItem.uri) &&
        Objects.equals(this.qr, qrItem.qr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri, qr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QRItem {\n");
    
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    qr: ").append(toIndentedString(qr)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

