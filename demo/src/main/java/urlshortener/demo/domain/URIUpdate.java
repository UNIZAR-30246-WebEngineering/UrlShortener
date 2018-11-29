package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * URIUpdate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class URIUpdate   {
  @JsonProperty("new-name")
  private String newName = null;

  @JsonProperty("hashpass")
  private String hashpass = null;

  public URIUpdate newName(String newName) {
    this.newName = newName;
    return this;
  }

  /**
   * Get newName
   * @return newName
  **/
  @ApiModelProperty(example = "newname4", value = "")


  public String getNewName() {
    return newName;
  }

  public void setNewName(String newName) {
    this.newName = newName;
  }

  public URIUpdate hashpass(String hashpass) {
    this.hashpass = hashpass;
    return this;
  }

  /**
   * Get hashpass
   * @return hashpass
  **/
  @ApiModelProperty(example = "skljdhgks", value = "")


  public String getHashpass() {
    return hashpass;
  }

  public void setHashpass(String hashpass) {
    this.hashpass = hashpass;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    URIUpdate urIUpdate = (URIUpdate) o;
    return Objects.equals(this.newName, urIUpdate.newName) &&
        Objects.equals(this.hashpass, urIUpdate.hashpass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newName, hashpass);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIUpdate {\n");
    
    sb.append("    newName: ").append(toIndentedString(newName)).append("\n");
    sb.append("    hashpass: ").append(toIndentedString(hashpass)).append("\n");
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

