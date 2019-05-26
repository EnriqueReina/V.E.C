
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
    "listId",
    "textStyle",
    "nestingLevel"
})
public class Bullet {

    @JsonProperty("listId")
    private String listId;
    @JsonProperty("textStyle")
    private TextStyle_ textStyle;
    @JsonProperty("nestingLevel")
    private Integer nestingLevel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("listId")
    public String getListId() {
        return listId;
    }

    @JsonProperty("listId")
    public void setListId(String listId) {
        this.listId = listId;
    }

    @JsonProperty("textStyle")
    public TextStyle_ getTextStyle() {
        return textStyle;
    }

    @JsonProperty("textStyle")
    public void setTextStyle(TextStyle_ textStyle) {
        this.textStyle = textStyle;
    }

    @JsonProperty("nestingLevel")
    public Integer getNestingLevel() {
        return nestingLevel;
    }

    @JsonProperty("nestingLevel")
    public void setNestingLevel(Integer nestingLevel) {
        this.nestingLevel = nestingLevel;
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
