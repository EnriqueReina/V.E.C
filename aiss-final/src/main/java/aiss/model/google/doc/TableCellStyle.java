
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
    "backgroundColor",
    "columnSpan",
    "contentAlignment",
    "paddingBottom",
    "paddingLeft",
    "paddingRight",
    "paddingTop",
    "rowSpan"
})
public class TableCellStyle {

    @JsonProperty("backgroundColor")
    private BackgroundColor_ backgroundColor;
    @JsonProperty("columnSpan")
    private Integer columnSpan;
    @JsonProperty("contentAlignment")
    private String contentAlignment;
    @JsonProperty("paddingBottom")
    private PaddingBottom paddingBottom;
    @JsonProperty("paddingLeft")
    private PaddingLeft paddingLeft;
    @JsonProperty("paddingRight")
    private PaddingRight paddingRight;
    @JsonProperty("paddingTop")
    private PaddingTop paddingTop;
    @JsonProperty("rowSpan")
    private Integer rowSpan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("backgroundColor")
    public BackgroundColor_ getBackgroundColor() {
        return backgroundColor;
    }

    @JsonProperty("backgroundColor")
    public void setBackgroundColor(BackgroundColor_ backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @JsonProperty("columnSpan")
    public Integer getColumnSpan() {
        return columnSpan;
    }

    @JsonProperty("columnSpan")
    public void setColumnSpan(Integer columnSpan) {
        this.columnSpan = columnSpan;
    }

    @JsonProperty("contentAlignment")
    public String getContentAlignment() {
        return contentAlignment;
    }

    @JsonProperty("contentAlignment")
    public void setContentAlignment(String contentAlignment) {
        this.contentAlignment = contentAlignment;
    }

    @JsonProperty("paddingBottom")
    public PaddingBottom getPaddingBottom() {
        return paddingBottom;
    }

    @JsonProperty("paddingBottom")
    public void setPaddingBottom(PaddingBottom paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    @JsonProperty("paddingLeft")
    public PaddingLeft getPaddingLeft() {
        return paddingLeft;
    }

    @JsonProperty("paddingLeft")
    public void setPaddingLeft(PaddingLeft paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    @JsonProperty("paddingRight")
    public PaddingRight getPaddingRight() {
        return paddingRight;
    }

    @JsonProperty("paddingRight")
    public void setPaddingRight(PaddingRight paddingRight) {
        this.paddingRight = paddingRight;
    }

    @JsonProperty("paddingTop")
    public PaddingTop getPaddingTop() {
        return paddingTop;
    }

    @JsonProperty("paddingTop")
    public void setPaddingTop(PaddingTop paddingTop) {
        this.paddingTop = paddingTop;
    }

    @JsonProperty("rowSpan")
    public Integer getRowSpan() {
        return rowSpan;
    }

    @JsonProperty("rowSpan")
    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
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
