
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
    "columnSeparatorStyle",
    "contentDirection"
})
public class SectionStyle {

    @JsonProperty("columnSeparatorStyle")
    private String columnSeparatorStyle;
    @JsonProperty("contentDirection")
    private String contentDirection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("columnSeparatorStyle")
    public String getColumnSeparatorStyle() {
        return columnSeparatorStyle;
    }

    @JsonProperty("columnSeparatorStyle")
    public void setColumnSeparatorStyle(String columnSeparatorStyle) {
        this.columnSeparatorStyle = columnSeparatorStyle;
    }

    @JsonProperty("contentDirection")
    public String getContentDirection() {
        return contentDirection;
    }

    @JsonProperty("contentDirection")
    public void setContentDirection(String contentDirection) {
        this.contentDirection = contentDirection;
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
