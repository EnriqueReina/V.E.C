
package aiss.model.bitbucket;

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
    "watchers",
    "branches",
    "tags",
    "commits",
    "clone",
    "self",
    "source",
    "html",
    "avatar",
    "hooks",
    "forks",
    "downloads",
    "issues",
    "pullrequests"
})
public class Links {

    @JsonProperty("watchers")
    private Watchers watchers;
    @JsonProperty("branches")
    private Branches branches;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("commits")
    private Commits commits;
    @JsonProperty("clone")
    private List<Clone> clone = null;
    @JsonProperty("self")
    private Self self;
    @JsonProperty("source")
    private Source source;
    @JsonProperty("html")
    private Html html;
    @JsonProperty("avatar")
    private Avatar avatar;
    @JsonProperty("hooks")
    private Hooks hooks;
    @JsonProperty("forks")
    private Forks forks;
    @JsonProperty("downloads")
    private Downloads downloads;
    @JsonProperty("issues")
    private Issues issues;
    @JsonProperty("pullrequests")
    private Pullrequests pullrequests;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("watchers")
    public Watchers getWatchers() {
        return watchers;
    }

    @JsonProperty("watchers")
    public void setWatchers(Watchers watchers) {
        this.watchers = watchers;
    }

    @JsonProperty("branches")
    public Branches getBranches() {
        return branches;
    }

    @JsonProperty("branches")
    public void setBranches(Branches branches) {
        this.branches = branches;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("commits")
    public Commits getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    @JsonProperty("clone")
    public List<Clone> getClone() {
        return clone;
    }

    @JsonProperty("clone")
    public void setClone(List<Clone> clone) {
        this.clone = clone;
    }

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(Source source) {
        this.source = source;
    }

    @JsonProperty("html")
    public Html getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html html) {
        this.html = html;
    }

    @JsonProperty("avatar")
    public Avatar getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("hooks")
    public Hooks getHooks() {
        return hooks;
    }

    @JsonProperty("hooks")
    public void setHooks(Hooks hooks) {
        this.hooks = hooks;
    }

    @JsonProperty("forks")
    public Forks getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Forks forks) {
        this.forks = forks;
    }

    @JsonProperty("downloads")
    public Downloads getDownloads() {
        return downloads;
    }

    @JsonProperty("downloads")
    public void setDownloads(Downloads downloads) {
        this.downloads = downloads;
    }

    @JsonProperty("issues")
    public Issues getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(Issues issues) {
        this.issues = issues;
    }

    @JsonProperty("pullrequests")
    public Pullrequests getPullrequests() {
        return pullrequests;
    }

    @JsonProperty("pullrequests")
    public void setPullrequests(Pullrequests pullrequests) {
        this.pullrequests = pullrequests;
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
