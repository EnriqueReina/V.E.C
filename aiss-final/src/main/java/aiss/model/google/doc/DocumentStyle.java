
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
    "background",
    "marginBottom",
    "marginLeft",
    "marginRight",
    "marginTop",
    "pageNumberStart",
    "pageSize"
})
public class DocumentStyle {

    @JsonProperty("background")
    private Background background;
    @JsonProperty("marginBottom")
    private MarginBottom marginBottom;
    @JsonProperty("marginLeft")
    private MarginLeft marginLeft;
    @JsonProperty("marginRight")
    private MarginRight marginRight;
    @JsonProperty("marginTop")
    private MarginTop marginTop;
    @JsonProperty("pageNumberStart")
    private Integer pageNumberStart;
    @JsonProperty("pageSize")
    private PageSize pageSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("background")
    public Background getBackground() {
        return background;
    }

    @JsonProperty("background")
    public void setBackground(Background background) {
        this.background = background;
    }

    @JsonProperty("marginBottom")
    public MarginBottom getMarginBottom() {
        return marginBottom;
    }

    @JsonProperty("marginBottom")
    public void setMarginBottom(MarginBottom marginBottom) {
        this.marginBottom = marginBottom;
    }

    @JsonProperty("marginLeft")
    public MarginLeft getMarginLeft() {
        return marginLeft;
    }

    @JsonProperty("marginLeft")
    public void setMarginLeft(MarginLeft marginLeft) {
        this.marginLeft = marginLeft;
    }

    @JsonProperty("marginRight")
    public MarginRight getMarginRight() {
        return marginRight;
    }

    @JsonProperty("marginRight")
    public void setMarginRight(MarginRight marginRight) {
        this.marginRight = marginRight;
    }

    @JsonProperty("marginTop")
    public MarginTop getMarginTop() {
        return marginTop;
    }

    @JsonProperty("marginTop")
    public void setMarginTop(MarginTop marginTop) {
        this.marginTop = marginTop;
    }

    @JsonProperty("pageNumberStart")
    public Integer getPageNumberStart() {
        return pageNumberStart;
    }

    @JsonProperty("pageNumberStart")
    public void setPageNumberStart(Integer pageNumberStart) {
        this.pageNumberStart = pageNumberStart;
    }

    @JsonProperty("pageSize")
    public PageSize getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
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
