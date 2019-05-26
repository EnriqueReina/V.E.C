
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
    "alignment",
    "avoidWidowAndOrphan",
    "borderBetween",
    "borderBottom",
    "borderLeft",
    "borderRight",
    "borderTop",
    "direction",
    "indentEnd",
    "indentFirstLine",
    "indentStart",
    "keepLinesTogether",
    "keepWithNext",
    "lineSpacing",
    "namedStyleType",
    "shading",
    "spaceAbove",
    "spaceBelow",
    "spacingMode"
})
public class ParagraphStyle_ {

    @JsonProperty("alignment")
    private String alignment;
    @JsonProperty("avoidWidowAndOrphan")
    private Boolean avoidWidowAndOrphan;
    @JsonProperty("borderBetween")
    private BorderBetween borderBetween;
    @JsonProperty("borderBottom")
    private BorderBottom borderBottom;
    @JsonProperty("borderLeft")
    private BorderLeft borderLeft;
    @JsonProperty("borderRight")
    private BorderRight borderRight;
    @JsonProperty("borderTop")
    private BorderTop borderTop;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("indentEnd")
    private IndentEnd indentEnd;
    @JsonProperty("indentFirstLine")
    private IndentFirstLine_ indentFirstLine;
    @JsonProperty("indentStart")
    private IndentStart_ indentStart;
    @JsonProperty("keepLinesTogether")
    private Boolean keepLinesTogether;
    @JsonProperty("keepWithNext")
    private Boolean keepWithNext;
    @JsonProperty("lineSpacing")
    private Integer lineSpacing;
    @JsonProperty("namedStyleType")
    private String namedStyleType;
    @JsonProperty("shading")
    private Shading shading;
    @JsonProperty("spaceAbove")
    private SpaceAbove spaceAbove;
    @JsonProperty("spaceBelow")
    private SpaceBelow spaceBelow;
    @JsonProperty("spacingMode")
    private String spacingMode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alignment")
    public String getAlignment() {
        return alignment;
    }

    @JsonProperty("alignment")
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @JsonProperty("avoidWidowAndOrphan")
    public Boolean getAvoidWidowAndOrphan() {
        return avoidWidowAndOrphan;
    }

    @JsonProperty("avoidWidowAndOrphan")
    public void setAvoidWidowAndOrphan(Boolean avoidWidowAndOrphan) {
        this.avoidWidowAndOrphan = avoidWidowAndOrphan;
    }

    @JsonProperty("borderBetween")
    public BorderBetween getBorderBetween() {
        return borderBetween;
    }

    @JsonProperty("borderBetween")
    public void setBorderBetween(BorderBetween borderBetween) {
        this.borderBetween = borderBetween;
    }

    @JsonProperty("borderBottom")
    public BorderBottom getBorderBottom() {
        return borderBottom;
    }

    @JsonProperty("borderBottom")
    public void setBorderBottom(BorderBottom borderBottom) {
        this.borderBottom = borderBottom;
    }

    @JsonProperty("borderLeft")
    public BorderLeft getBorderLeft() {
        return borderLeft;
    }

    @JsonProperty("borderLeft")
    public void setBorderLeft(BorderLeft borderLeft) {
        this.borderLeft = borderLeft;
    }

    @JsonProperty("borderRight")
    public BorderRight getBorderRight() {
        return borderRight;
    }

    @JsonProperty("borderRight")
    public void setBorderRight(BorderRight borderRight) {
        this.borderRight = borderRight;
    }

    @JsonProperty("borderTop")
    public BorderTop getBorderTop() {
        return borderTop;
    }

    @JsonProperty("borderTop")
    public void setBorderTop(BorderTop borderTop) {
        this.borderTop = borderTop;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("indentEnd")
    public IndentEnd getIndentEnd() {
        return indentEnd;
    }

    @JsonProperty("indentEnd")
    public void setIndentEnd(IndentEnd indentEnd) {
        this.indentEnd = indentEnd;
    }

    @JsonProperty("indentFirstLine")
    public IndentFirstLine_ getIndentFirstLine() {
        return indentFirstLine;
    }

    @JsonProperty("indentFirstLine")
    public void setIndentFirstLine(IndentFirstLine_ indentFirstLine) {
        this.indentFirstLine = indentFirstLine;
    }

    @JsonProperty("indentStart")
    public IndentStart_ getIndentStart() {
        return indentStart;
    }

    @JsonProperty("indentStart")
    public void setIndentStart(IndentStart_ indentStart) {
        this.indentStart = indentStart;
    }

    @JsonProperty("keepLinesTogether")
    public Boolean getKeepLinesTogether() {
        return keepLinesTogether;
    }

    @JsonProperty("keepLinesTogether")
    public void setKeepLinesTogether(Boolean keepLinesTogether) {
        this.keepLinesTogether = keepLinesTogether;
    }

    @JsonProperty("keepWithNext")
    public Boolean getKeepWithNext() {
        return keepWithNext;
    }

    @JsonProperty("keepWithNext")
    public void setKeepWithNext(Boolean keepWithNext) {
        this.keepWithNext = keepWithNext;
    }

    @JsonProperty("lineSpacing")
    public Integer getLineSpacing() {
        return lineSpacing;
    }

    @JsonProperty("lineSpacing")
    public void setLineSpacing(Integer lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    @JsonProperty("namedStyleType")
    public String getNamedStyleType() {
        return namedStyleType;
    }

    @JsonProperty("namedStyleType")
    public void setNamedStyleType(String namedStyleType) {
        this.namedStyleType = namedStyleType;
    }

    @JsonProperty("shading")
    public Shading getShading() {
        return shading;
    }

    @JsonProperty("shading")
    public void setShading(Shading shading) {
        this.shading = shading;
    }

    @JsonProperty("spaceAbove")
    public SpaceAbove getSpaceAbove() {
        return spaceAbove;
    }

    @JsonProperty("spaceAbove")
    public void setSpaceAbove(SpaceAbove spaceAbove) {
        this.spaceAbove = spaceAbove;
    }

    @JsonProperty("spaceBelow")
    public SpaceBelow getSpaceBelow() {
        return spaceBelow;
    }

    @JsonProperty("spaceBelow")
    public void setSpaceBelow(SpaceBelow spaceBelow) {
        this.spaceBelow = spaceBelow;
    }

    @JsonProperty("spacingMode")
    public String getSpacingMode() {
        return spacingMode;
    }

    @JsonProperty("spacingMode")
    public void setSpacingMode(String spacingMode) {
        this.spacingMode = spacingMode;
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
