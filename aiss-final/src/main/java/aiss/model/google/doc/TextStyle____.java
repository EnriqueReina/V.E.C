
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
    "fontSize",
    "foregroundColor",
    "italic",
    "weightedFontFamily"
})
public class TextStyle____ {

    @JsonProperty("fontSize")
    private FontSize fontSize;
    @JsonProperty("foregroundColor")
    private ForegroundColor foregroundColor;
    @JsonProperty("italic")
    private Boolean italic;
    @JsonProperty("weightedFontFamily")
    private WeightedFontFamily weightedFontFamily;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fontSize")
    public FontSize getFontSize() {
        return fontSize;
    }

    @JsonProperty("fontSize")
    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    @JsonProperty("foregroundColor")
    public ForegroundColor getForegroundColor() {
        return foregroundColor;
    }

    @JsonProperty("foregroundColor")
    public void setForegroundColor(ForegroundColor foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    @JsonProperty("italic")
    public Boolean getItalic() {
        return italic;
    }

    @JsonProperty("italic")
    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    @JsonProperty("weightedFontFamily")
    public WeightedFontFamily getWeightedFontFamily() {
        return weightedFontFamily;
    }

    @JsonProperty("weightedFontFamily")
    public void setWeightedFontFamily(WeightedFontFamily weightedFontFamily) {
        this.weightedFontFamily = weightedFontFamily;
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
