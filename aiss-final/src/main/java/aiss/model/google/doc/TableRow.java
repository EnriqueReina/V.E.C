
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
    "endIndex",
    "startIndex",
    "tableCells",
    "tableRowStyle"
})
public class TableRow {

    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("tableCells")
    private List<TableCell> tableCells = null;
    @JsonProperty("tableRowStyle")
    private TableRowStyle tableRowStyle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("tableCells")
    public List<TableCell> getTableCells() {
        return tableCells;
    }

    @JsonProperty("tableCells")
    public void setTableCells(List<TableCell> tableCells) {
        this.tableCells = tableCells;
    }

    @JsonProperty("tableRowStyle")
    public TableRowStyle getTableRowStyle() {
        return tableRowStyle;
    }

    @JsonProperty("tableRowStyle")
    public void setTableRowStyle(TableRowStyle tableRowStyle) {
        this.tableRowStyle = tableRowStyle;
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
