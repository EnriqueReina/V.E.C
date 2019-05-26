
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
    "kix.717rkbf3o6mp"
})
public class Lists {

    @JsonProperty("kix.717rkbf3o6mp")
    private Kix717rkbf3o6mp kix717rkbf3o6mp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("kix.717rkbf3o6mp")
    public Kix717rkbf3o6mp getKix717rkbf3o6mp() {
        return kix717rkbf3o6mp;
    }

    @JsonProperty("kix.717rkbf3o6mp")
    public void setKix717rkbf3o6mp(Kix717rkbf3o6mp kix717rkbf3o6mp) {
        this.kix717rkbf3o6mp = kix717rkbf3o6mp;
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
