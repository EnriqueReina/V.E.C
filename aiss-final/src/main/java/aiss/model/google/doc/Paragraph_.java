
package aiss.model.google.doc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elements",
    "paragraphStyle"
})
public class Paragraph_ {

    @JsonProperty("elements")
    private List<Element_> elements = null;
    @JsonProperty("paragraphStyle")
    private ParagraphStyle_ paragraphStyle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("elements")
    public List<Element_> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<Element_> elements) {
        this.elements = elements;
    }

    @JsonProperty("paragraphStyle")
    public ParagraphStyle_ getParagraphStyle() {
        return paragraphStyle;
    }

    @JsonProperty("paragraphStyle")
    public void setParagraphStyle(ParagraphStyle_ paragraphStyle) {
        this.paragraphStyle = paragraphStyle;
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
