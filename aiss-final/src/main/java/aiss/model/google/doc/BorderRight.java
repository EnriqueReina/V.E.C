
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
    "color",
    "dashStyle",
    "lineStyle",
    "padding",
    "width"
})
public class BorderRight {

    @JsonProperty("color")
    private Color___ color;
    @JsonProperty("dashStyle")
    private String dashStyle;
    @JsonProperty("lineStyle")
    private String lineStyle;
    @JsonProperty("padding")
    private Padding___ padding;
    @JsonProperty("width")
    private Width___ width;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("color")
    public Color___ getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(Color___ color) {
        this.color = color;
    }

    @JsonProperty("dashStyle")
    public String getDashStyle() {
        return dashStyle;
    }

    @JsonProperty("dashStyle")
    public void setDashStyle(String dashStyle) {
        this.dashStyle = dashStyle;
    }

    @JsonProperty("lineStyle")
    public String getLineStyle() {
        return lineStyle;
    }

    @JsonProperty("lineStyle")
    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
    }

    @JsonProperty("padding")
    public Padding___ getPadding() {
        return padding;
    }

    @JsonProperty("padding")
    public void setPadding(Padding___ padding) {
        this.padding = padding;
    }

    @JsonProperty("width")
    public Width___ getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Width___ width) {
        this.width = width;
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
