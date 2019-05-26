
package aiss.model.google.drive;

import java.io.Serializable;
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
    "kind",
    "replyId",
    "createdDate",
    "modifiedDate",
    "author",
    "htmlContent",
    "content",
    "deleted"
})
public class Reply implements Serializable
{

    @JsonProperty("kind")
    private String kind = "drive#commentReply";
    @JsonProperty("replyId")
    private String replyId;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("modifiedDate")
    private String modifiedDate;
    @JsonProperty("author")
    private ReplyAuthor author;
    @JsonProperty("htmlContent")
    private String htmlContent;
    @JsonProperty("content")
    private String content;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6571402518609378818L;

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("replyId")
    public String getReplyId() {
        return replyId;
    }

    @JsonProperty("replyId")
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("modifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    @JsonProperty("modifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @JsonProperty("author")
    public ReplyAuthor getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(ReplyAuthor author) {
        this.author = author;
    }

    @JsonProperty("htmlContent")
    public String getHtmlContent() {
        return htmlContent;
    }

    @JsonProperty("htmlContent")
    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
