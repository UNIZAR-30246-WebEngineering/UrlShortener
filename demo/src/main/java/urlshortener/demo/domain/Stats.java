package urlshortener.demo.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Stats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class Stats   {
  @JsonProperty("redirected-uris")
  private Integer redirectedUris = null;

  @JsonProperty("server-load")
  private BigDecimal serverLoad = null;

  @JsonProperty("generated-qr")
  private Integer generatedQr = null;

  public Stats redirectedUris(Integer redirectedUris) {
    this.redirectedUris = redirectedUris;
    return this;
  }

  /**
   * Get redirectedUris
   * @return redirectedUris
  **/
  @ApiModelProperty(example = "3457", required = true, value = "")
  @NotNull


  public Integer getRedirectedUris() {
    return redirectedUris;
  }

  public void setRedirectedUris(Integer redirectedUris) {
    this.redirectedUris = redirectedUris;
  }

  public Stats serverLoad(BigDecimal serverLoad) {
    this.serverLoad = serverLoad;
    return this;
  }

  /**
   * Get serverLoad
   * @return serverLoad
  **/
  @ApiModelProperty(example = "0.79", required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getServerLoad() {
    return serverLoad;
  }

  public void setServerLoad(BigDecimal serverLoad) {
    this.serverLoad = serverLoad;
  }

  public Stats generatedQr(Integer generatedQr) {
    this.generatedQr = generatedQr;
    return this;
  }

  /**
   * Get generatedQr
   * @return generatedQr
  **/
  @ApiModelProperty(example = "1243", required = true, value = "")
  @NotNull


  public Integer getGeneratedQr() {
    return generatedQr;
  }

  public void setGeneratedQr(Integer generatedQr) {
    this.generatedQr = generatedQr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stats stats = (Stats) o;
    return Objects.equals(this.redirectedUris, stats.redirectedUris) &&
        Objects.equals(this.serverLoad, stats.serverLoad) &&
        Objects.equals(this.generatedQr, stats.generatedQr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(redirectedUris, serverLoad, generatedQr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stats {\n");
    
    sb.append("    redirectedUris: ").append(toIndentedString(redirectedUris)).append("\n");
    sb.append("    serverLoad: ").append(toIndentedString(serverLoad)).append("\n");
    sb.append("    generatedQr: ").append(toIndentedString(generatedQr)).append("\n");
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

