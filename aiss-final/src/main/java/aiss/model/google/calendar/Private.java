
package aiss.model.google.calendar;

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
    "everyoneDeclinedDismissed"
})
public class Private {

    @JsonProperty("everyoneDeclinedDismissed")
    private String everyoneDeclinedDismissed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("everyoneDeclinedDismissed")
    public String getEveryoneDeclinedDismissed() {
        return everyoneDeclinedDismissed;
    }

    @JsonProperty("everyoneDeclinedDismissed")
    public void setEveryoneDeclinedDismissed(String everyoneDeclinedDismissed) {
        this.everyoneDeclinedDismissed = everyoneDeclinedDismissed;
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
