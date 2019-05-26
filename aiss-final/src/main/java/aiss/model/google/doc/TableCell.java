
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
    "content",
    "endIndex",
    "startIndex",
    "tableCellStyle"
})
public class TableCell {

    @JsonProperty("content")
    private List<Content_> content = null;
    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("tableCellStyle")
    private TableCellStyle tableCellStyle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("content")
    public List<Content_> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<Content_> content) {
        this.content = content;
    }

    @JsonProperty("endIndex")
    public Integer getEndIndex() {
        return endIndex;
    }

    @JsonProperty("endIndex")
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    @JsonProperty("startIndex")
    public Integer getStartIndex() {
        return startIndex;
    }

    @JsonProperty("startIndex")
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @JsonProperty("tableCellStyle")
    public TableCellStyle getTableCellStyle() {
        return tableCellStyle;
    }

    @JsonProperty("tableCellStyle")
    public void setTableCellStyle(TableCellStyle tableCellStyle) {
        this.tableCellStyle = tableCellStyle;
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
