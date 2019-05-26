
package aiss.model.google.doc;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "body",
    "documentId",
    "documentStyle",
    "lists",
    "namedStyles",
    "revisionId",
    "suggestionsViewMode",
    "title"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Document {

    @JsonProperty("body")
    private Body body;
    @JsonProperty("documentId")
    private String documentId;
    @JsonProperty("documentStyle")
    private DocumentStyle documentStyle;
    @JsonProperty("lists")
    private Lists lists;
    @JsonProperty("namedStyles")
    private NamedStyles namedStyles;
    @JsonProperty("revisionId")
    private String revisionId;
    @JsonProperty("suggestionsViewMode")
    private String suggestionsViewMode;
    @JsonProperty("title")
    private String title;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("body")
    public Body getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(Body body) {
        this.body = body;
    }

    @JsonProperty("documentId")
    public String getDocumentId() {
        return documentId;
    }

    @JsonProperty("documentId")
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    @JsonProperty("documentStyle")
    public DocumentStyle getDocumentStyle() {
        return documentStyle;
    }

    @JsonProperty("documentStyle")
    public void setDocumentStyle(DocumentStyle documentStyle) {
        this.documentStyle = documentStyle;
    }

    @JsonProperty("lists")
    public Lists getLists() {
        return lists;
    }

    @JsonProperty("lists")
    public void setLists(Lists lists) {
        this.lists = lists;
    }

    @JsonProperty("namedStyles")
    public NamedStyles getNamedStyles() {
        return namedStyles;
    }

    @JsonProperty("namedStyles")
    public void setNamedStyles(NamedStyles namedStyles) {
        this.namedStyles = namedStyles;
    }

    @JsonProperty("revisionId")
    public String getRevisionId() {
        return revisionId;
    }

    @JsonProperty("revisionId")
    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    @JsonProperty("suggestionsViewMode")
    public String getSuggestionsViewMode() {
        return suggestionsViewMode;
    }

    @JsonProperty("suggestionsViewMode")
    public void setSuggestionsViewMode(String suggestionsViewMode) {
        this.suggestionsViewMode = suggestionsViewMode;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
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
