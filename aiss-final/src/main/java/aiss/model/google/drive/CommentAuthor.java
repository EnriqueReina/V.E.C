
package aiss.model.google.drive;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "kind",
    "displayName",
    "picture",
    "isAuthenticatedUser"
})
public class CommentAuthor implements Serializable
{

    @JsonProperty("kind")
    private String kind;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("picture")
    private Picture picture;
    @JsonProperty("isAuthenticatedUser")
    private Boolean isAuthenticatedUser;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6774756952473235361L;

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("picture")
    public Picture getPicture() {
        return picture;
    }

    @JsonProperty("picture")
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @JsonProperty("isAuthenticatedUser")
    public Boolean getIsAuthenticatedUser() {
        return isAuthenticatedUser;
    }

    @JsonProperty("isAuthenticatedUser")
    public void setIsAuthenticatedUser(Boolean isAuthenticatedUser) {
        this.isAuthenticatedUser = isAuthenticatedUser;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
