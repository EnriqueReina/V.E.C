
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
    "bulletAlignment",
    "glyphFormat",
    "glyphSymbol",
    "indentFirstLine",
    "indentStart",
    "startNumber",
    "textStyle",
    "glyphType"
})
public class NestingLevel {

    @JsonProperty("bulletAlignment")
    private String bulletAlignment;
    @JsonProperty("glyphFormat")
    private String glyphFormat;
    @JsonProperty("glyphSymbol")
    private String glyphSymbol;
    @JsonProperty("indentFirstLine")
    private IndentFirstLine__ indentFirstLine;
    @JsonProperty("indentStart")
    private IndentStart__ indentStart;
    @JsonProperty("startNumber")
    private Integer startNumber;
    @JsonProperty("textStyle")
    private TextStyle___ textStyle;
    @JsonProperty("glyphType")
    private String glyphType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bulletAlignment")
    public String getBulletAlignment() {
        return bulletAlignment;
    }

    @JsonProperty("bulletAlignment")
    public void setBulletAlignment(String bulletAlignment) {
        this.bulletAlignment = bulletAlignment;
    }

    @JsonProperty("glyphFormat")
    public String getGlyphFormat() {
        return glyphFormat;
    }

    @JsonProperty("glyphFormat")
    public void setGlyphFormat(String glyphFormat) {
        this.glyphFormat = glyphFormat;
    }

    @JsonProperty("glyphSymbol")
    public String getGlyphSymbol() {
        return glyphSymbol;
    }

    @JsonProperty("glyphSymbol")
    public void setGlyphSymbol(String glyphSymbol) {
        this.glyphSymbol = glyphSymbol;
    }

    @JsonProperty("indentFirstLine")
    public IndentFirstLine__ getIndentFirstLine() {
        return indentFirstLine;
    }

    @JsonProperty("indentFirstLine")
    public void setIndentFirstLine(IndentFirstLine__ indentFirstLine) {
        this.indentFirstLine = indentFirstLine;
    }

    @JsonProperty("indentStart")
    public IndentStart__ getIndentStart() {
        return indentStart;
    }

    @JsonProperty("indentStart")
    public void setIndentStart(IndentStart__ indentStart) {
        this.indentStart = indentStart;
    }

    @JsonProperty("startNumber")
    public Integer getStartNumber() {
        return startNumber;
    }

    @JsonProperty("startNumber")
    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    @JsonProperty("textStyle")
    public TextStyle___ getTextStyle() {
        return textStyle;
    }

    @JsonProperty("textStyle")
    public void setTextStyle(TextStyle___ textStyle) {
        this.textStyle = textStyle;
    }

    @JsonProperty("glyphType")
    public String getGlyphType() {
        return glyphType;
    }

    @JsonProperty("glyphType")
    public void setGlyphType(String glyphType) {
        this.glyphType = glyphType;
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
