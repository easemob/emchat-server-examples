package im.baas;

public class ResponseBody {
    private String callId;
    private String accpet;
    private String reason;
    private String security;

    public String getCallId() {
        return callId;
    }

    public String getAccpet() {
        return accpet;
    }

    public String getReason() {
        return reason;
    }

    public String getSecurity() {
        return security;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public void setAccpet(String accpet) {
        this.accpet = accpet;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setSecurity(String security) {
        this.security = security;
    }


}
