
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
    "paragraphStyle",
    "bullet"
})
public class Paragraph {

    @JsonProperty("elements")
    private List<Element> elements = null;
    @JsonProperty("paragraphStyle")
    private ParagraphStyle paragraphStyle;
    @JsonProperty("bullet")
    private Bullet bullet;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("elements")
    public List<Element> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @JsonProperty("paragraphStyle")
    public ParagraphStyle getParagraphStyle() {
        return paragraphStyle;
    }

    @JsonProperty("paragraphStyle")
    public void setParagraphStyle(ParagraphStyle paragraphStyle) {
        this.paragraphStyle = paragraphStyle;
    }

    @JsonProperty("bullet")
    public Bullet getBullet() {
        return bullet;
    }

    @JsonProperty("bullet")
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
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
