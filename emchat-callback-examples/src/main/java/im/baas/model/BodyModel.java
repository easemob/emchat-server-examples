package im.baas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BodyModel {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("action")
    private String action;

    @JsonProperty("filename")
    private String fileName;

    @JsonProperty("size")
    private SizeModel sizeModel;

    @JsonProperty("url")
    private String url;

    @JsonProperty("thumb")
    private String thumb;

    @JsonProperty("secret")
    private String secret;

    @JsonProperty("length")
    private Integer length;

    @JsonProperty("file_length")
    private Integer fileLength;

    @JsonProperty("thumb_secret")
    private String thumbSecert;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SizeModel getSizeModel() {
        return sizeModel;
    }

    public void setSizeModel(SizeModel sizeModel) {
        this.sizeModel = sizeModel;
    }

    public Integer getFileLength() {
        return fileLength;
    }

    public void setFileLength(Integer fileLength) {
        this.fileLength = fileLength;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSecret() {
        return secret;
    }

    public Integer getLength() {
        return length;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setThumbSecert(String thumbSecert) {
        this.thumbSecert = thumbSecert;
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public String getThumb() {
        return thumb;
    }

    public String getThumbSecert() {
        return thumbSecert;
    }
}
