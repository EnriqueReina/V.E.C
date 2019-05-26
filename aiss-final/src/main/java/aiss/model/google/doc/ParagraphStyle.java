
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
    "namedStyleType",
    "indentFirstLine",
    "indentStart",
    "headingId"
})
public class ParagraphStyle {

    @JsonProperty("direction")
    private String direction;
    @JsonProperty("namedStyleType")
    private String namedStyleType;
    @JsonProperty("indentFirstLine")
    private IndentFirstLine indentFirstLine;
    @JsonProperty("indentStart")
    private IndentStart indentStart;
    @JsonProperty("headingId")
    private String headingId;
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

    @JsonProperty("namedStyleType")
    public String getNamedStyleType() {
        return namedStyleType;
    }

    @JsonProperty("namedStyleType")
    public void setNamedStyleType(String namedStyleType) {
        this.namedStyleType = namedStyleType;
    }

    @JsonProperty("indentFirstLine")
    public IndentFirstLine getIndentFirstLine() {
        return indentFirstLine;
    }

    @JsonProperty("indentFirstLine")
    public void setIndentFirstLine(IndentFirstLine indentFirstLine) {
        this.indentFirstLine = indentFirstLine;
    }

    @JsonProperty("indentStart")
    public IndentStart getIndentStart() {
        return indentStart;
    }

    @JsonProperty("indentStart")
    public void setIndentStart(IndentStart indentStart) {
        this.indentStart = indentStart;
    }

    @JsonProperty("headingId")
    public String getHeadingId() {
        return headingId;
    }

    @JsonProperty("headingId")
    public void setHeadingId(String headingId) {
        this.headingId = headingId;
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
