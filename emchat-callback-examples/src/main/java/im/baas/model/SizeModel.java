package im.baas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SizeModel {
    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
