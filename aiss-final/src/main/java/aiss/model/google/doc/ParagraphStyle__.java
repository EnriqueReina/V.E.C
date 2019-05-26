
package aiss.model.google.doc;

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
    "direction",
    "keepLinesTogether",
    "keepWithNext",
    "namedStyleType",
    "spaceAbove",
    "spaceBelow"
})
public class ParagraphStyle__ {

    @JsonProperty("direction")
    private String direction;
    @JsonProperty("keepLinesTogether")
    private Boolean keepLinesTogether;
    @JsonProperty("keepWithNext")
    private Boolean keepWithNext;
    @JsonProperty("namedStyleType")
    private String namedStyleType;
    @JsonProperty("spaceAbove")
    private SpaceAbove_ spaceAbove;
    @JsonProperty("spaceBelow")
    private SpaceBelow_ spaceBelow;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("keepLinesTogether")
    public Boolean getKeepLinesTogether() {
        return keepLinesTogether;
    }

    @JsonProperty("keepLinesTogether")
    public void setKeepLinesTogether(Boolean keepLinesTogether) {
        this.keepLinesTogether = keepLinesTogether;
    }

    @JsonProperty("keepWithNext")
    public Boolean getKeepWithNext() {
        return keepWithNext;
    }

    @JsonProperty("keepWithNext")
    public void setKeepWithNext(Boolean keepWithNext) {
        this.keepWithNext = keepWithNext;
    }

    @JsonProperty("namedStyleType")
    public String getNamedStyleType() {
        return namedStyleType;
    }

    @JsonProperty("namedStyleType")
    public void setNamedStyleType(String namedStyleType) {
        this.namedStyleType = namedStyleType;
    }

    @JsonProperty("spaceAbove")
    public SpaceAbove_ getSpaceAbove() {
        return spaceAbove;
    }

    @JsonProperty("spaceAbove")
    public void setSpaceAbove(SpaceAbove_ spaceAbove) {
        this.spaceAbove = spaceAbove;
    }

    @JsonProperty("spaceBelow")
    public SpaceBelow_ getSpaceBelow() {
        return spaceBelow;
    }

    @JsonProperty("spaceBelow")
    public void setSpaceBelow(SpaceBelow_ spaceBelow) {
        this.spaceBelow = spaceBelow;
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
