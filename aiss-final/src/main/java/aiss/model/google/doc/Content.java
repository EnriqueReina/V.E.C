
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
    "endIndex",
    "sectionBreak",
    "paragraph",
    "startIndex",
    "table"
})
public class Content {

    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonProperty("sectionBreak")
    private SectionBreak sectionBreak;
    @JsonProperty("paragraph")
    private Paragraph paragraph;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("table")
    private Table table;
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

    @JsonProperty("sectionBreak")
    public SectionBreak getSectionBreak() {
        return sectionBreak;
    }

    @JsonProperty("sectionBreak")
    public void setSectionBreak(SectionBreak sectionBreak) {
        this.sectionBreak = sectionBreak;
    }

    @JsonProperty("paragraph")
    public Paragraph getParagraph() {
        return paragraph;
    }

    @JsonProperty("paragraph")
    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    @JsonProperty("startIndex")
    public Integer getStartIndex() {
        return startIndex;
    }

    @JsonProperty("startIndex")
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @JsonProperty("table")
    public Table getTable() {
        return table;
    }

    @JsonProperty("table")
    public void setTable(Table table) {
        this.table = table;
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
