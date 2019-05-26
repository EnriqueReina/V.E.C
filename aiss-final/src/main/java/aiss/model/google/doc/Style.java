
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
    "namedStyleType",
    "paragraphStyle",
    "textStyle"
})
public class Style {

    @JsonProperty("namedStyleType")
    private String namedStyleType;
    @JsonProperty("paragraphStyle")
    private ParagraphStyle__ paragraphStyle;
    @JsonProperty("textStyle")
    private TextStyle____ textStyle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("namedStyleType")
    public String getNamedStyleType() {
        return namedStyleType;
    }

    @JsonProperty("namedStyleType")
    public void setNamedStyleType(String namedStyleType) {
        this.namedStyleType = namedStyleType;
    }

    @JsonProperty("paragraphStyle")
    public ParagraphStyle__ getParagraphStyle() {
        return paragraphStyle;
    }

    @JsonProperty("paragraphStyle")
    public void setParagraphStyle(ParagraphStyle__ paragraphStyle) {
        this.paragraphStyle = paragraphStyle;
    }

    @JsonProperty("textStyle")
    public TextStyle____ getTextStyle() {
        return textStyle;
    }

    @JsonProperty("textStyle")
    public void setTextStyle(TextStyle____ textStyle) {
        this.textStyle = textStyle;
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
