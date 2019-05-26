
package aiss.model.google.drive;

import java.io.Serializable;
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
    "kind",
    "commentId",
    "createdDate",
    "modifiedDate",
    "author",
    "htmlContent",
    "content",
    "deleted",
    "status",
    "context",
    "anchor",
    "fileId",
    "fileTitle",
    "replies"
})
public class Comment implements Serializable
{

    @JsonProperty("kind")
    private String kind;
    @JsonProperty("commentId")
    private String commentId;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("modifiedDate")
    private String modifiedDate;
    @JsonProperty("author")
    private CommentAuthor author;
    @JsonProperty("htmlContent")
    private String htmlContent;
    @JsonProperty("content")
    private String content;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("status")
    private String status;
    @JsonProperty("context")
    private Context context;
    @JsonProperty("anchor")
    private String anchor;
    @JsonProperty("fileId")
    private String fileId;
    @JsonProperty("fileTitle")
    private String fileTitle;
    @JsonProperty("replies")
    private List<Reply> replies = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7044817807812700844L;

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("commentId")
    public String getCommentId() {
        return commentId;
    }

    @JsonProperty("commentId")
    public void setCommentId(String commentId) {
        this.commentId = commentId;
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
    public CommentAuthor getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(CommentAuthor author) {
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

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("context")
    public Context getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(Context context) {
        this.context = context;
    }

    @JsonProperty("anchor")
    public String getAnchor() {
        return anchor;
    }

    @JsonProperty("anchor")
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    @JsonProperty("fileId")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("fileId")
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @JsonProperty("fileTitle")
    public String getFileTitle() {
        return fileTitle;
    }

    @JsonProperty("fileTitle")
    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    @JsonProperty("replies")
    public List<Reply> getReplies() {
        return replies;
    }

    @JsonProperty("replies")
    public void setReplies(List<Reply> replies) {
        this.replies = replies;
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
