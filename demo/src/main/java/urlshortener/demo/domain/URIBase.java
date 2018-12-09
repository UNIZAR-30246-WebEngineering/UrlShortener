package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class URIBase {

    @JsonProperty("hashpass")
    private String hashpass = null;

    public URIBase hashpass(String hashpass) {
        this.hashpass = hashpass;
        return this;
    }

    /**
     * Get hashpass
     * @return hashpass
     **/
    @ApiModelProperty(example = "adsgv674fjhag", required = true, value = "")
    @NotNull


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
        URIBase urIItem = (URIItem) o;
        return Objects.equals(this.hashpass, urIItem.hashpass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHashpass());
    }

    public boolean checkHashPass(String hashpass) {
        return this.hashpass != null && this.hashpass.equals(hashpass);
    }
}
