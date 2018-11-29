package urlshortener.demo.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * ErrorItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class ErrorItem   {
  @JsonProperty("error-info")
  private String errorInfo = null;

  public ErrorItem errorInfo(String errorInfo) {
    this.errorInfo = errorInfo;
    return this;
  }

  /**
   * Get errorInfo
   * @return errorInfo
  **/
  @ApiModelProperty(example = "Error creating shorthed URI. Resource is already in use", required = true, value = "")
  @NotNull


  public String getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(String errorInfo) {
    this.errorInfo = errorInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorItem errorItem = (ErrorItem) o;
    return Objects.equals(this.errorInfo, errorItem.errorInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorItem {\n");
    
    sb.append("    errorInfo: ").append(toIndentedString(errorInfo)).append("\n");
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

