package com.easemob.server.comm.body;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

public abstract class MessageBody implements BodyWrapper {
	private ObjectNode msgBody;

    private String targetType;

    private String[] targets;

    private String from;

    private Map<String, String> ext;

    private boolean init = false;

    public MessageBody(String targetType, String[] targets, String from, Map<String, String> ext) {
        this.targetType = targetType;
        this.targets = targets;
        this.from = from;
        this.ext = ext;
    }

    public String getTargetType() {
        return targetType;
    }


    public String[] getTargets() {
        return targets;
    }

    public String getFrom() {
        return from;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    protected ObjectNode getMsgBody() {
        if(null == this.msgBody) {
            this.msgBody = JsonNodeFactory.instance.objectNode();
            msgBody.put("target_type", targetType);
            ArrayNode targetsNode = msgBody.putArray("target");
            for (String target: targets ) {
                targetsNode.add(target);
            }
            msgBody.put("from", from);

            if(null != ext) {
                ObjectNode extNode = msgBody.putObject("ext");
                Iterator<String> iter = ext.keySet().iterator();
                while(iter.hasNext()){
                    String key = iter.next();
                    extNode.put(key, ext.get(key));
                }
            }
        }
		return msgBody;
	}

    public Boolean validate() {
        return StringUtils.isNotBlank(targetType) && isValidTargetType() && ArrayUtils.isNotEmpty(targets) && StringUtils.isNotBlank(from);
    }

    private boolean isValidTargetType() {
        return "users".equals(targetType) || "chatgroups".equals(targetType) || "chatrooms".equals(targetType);
    }
}
