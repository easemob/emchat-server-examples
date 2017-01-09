package im.baas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PayloadModel {

    @JsonProperty("bodies")
    private List<BodyModel> bodyModels;

    @JsonProperty("ext")
    private Map<String, String> ext;

    public List<BodyModel> getBodyModels() {
        return bodyModels;
    }

    public void setBodyModels(List<BodyModel> bodyModels) {
        this.bodyModels = bodyModels;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }

}
