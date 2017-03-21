package im.baas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MsgModel {
    @JsonProperty("chat_type")
    private String targetType;

    @JsonProperty("to")
    private String target;

    @JsonProperty("from")
    private String from;

    @JsonProperty("eventType")
    private String type;

    @JsonProperty("payload")
    private PayloadModel payloadModel;

    public String getTargetType() {
        return targetType;
    }

    public String getTarget() {
        return target;
    }

    public String getFrom() {
        return from;
    }

    public String getType() {
        return type;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PayloadModel getPayloadModel() {
        return payloadModel;
    }

    public void setPayloadModel(PayloadModel payloadModel) {
        this.payloadModel = payloadModel;
    }


}
