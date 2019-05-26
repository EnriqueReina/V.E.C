
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
    "blue",
    "green",
    "red"
})
public class RgbColor {

    @JsonProperty("blue")
    private Double blue;
    @JsonProperty("green")
    private Double green;
    @JsonProperty("red")
    private Double red;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("blue")
    public Double getBlue() {
        return blue;
    }

    @JsonProperty("blue")
    public void setBlue(Double blue) {
        this.blue = blue;
    }

    @JsonProperty("green")
    public Double getGreen() {
        return green;
    }

    @JsonProperty("green")
    public void setGreen(Double green) {
        this.green = green;
    }

    @JsonProperty("red")
    public Double getRed() {
        return red;
    }

    @JsonProperty("red")
    public void setRed(Double red) {
        this.red = red;
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
