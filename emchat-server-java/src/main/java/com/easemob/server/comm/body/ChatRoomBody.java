package com.easemob.server.comm.body;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ChatRoomBody implements BodyWrapper {
    private String name;
    private String desc;
    private Long maxUsers;
    private String owner;
    private String[] members;

    public ChatRoomBody(String name, String desc, Long maxUsers, String owner, String[] members) {
        this.name = name;
        this.desc = desc;
        this.maxUsers = maxUsers;
        this.owner = owner;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Long getMaxUsers() {
        return maxUsers;
    }

    public String getOwner() {
        return owner;
    }

    public String[] getMembers() {
        return members;
    }

    public ContainerNode<?> getBody() {
        ObjectNode body = JsonNodeFactory.instance.objectNode();
        body.put("name", name).put("description", desc).put("owner", owner);
        if(null != maxUsers) {
            body.put("maxusers", maxUsers);
        }
        if(ArrayUtils.isNotEmpty(members)) {
            ArrayNode membersNode = body.putArray("members");
            for (String member : members) {
                membersNode.add(member);
            }
        }

        return body;
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(desc) && StringUtils.isNotBlank(owner);
    }
}
