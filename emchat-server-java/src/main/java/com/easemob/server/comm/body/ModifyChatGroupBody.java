package com.easemob.server.comm.body;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.commons.lang3.StringUtils;

public class ModifyChatGroupBody implements BodyWrapper {
    private String groupName;
    private String desc;
    private Long maxUsers;

    public ModifyChatGroupBody(String groupName, String desc, Long maxUsers) {
        this.groupName = groupName;
        this.desc = desc;
        this.maxUsers = maxUsers;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDesc() {
        return desc;
    }

    public Long getMaxUsers() {
        return maxUsers;
    }

    public ContainerNode<?> getBody() {
        return JsonNodeFactory.instance.objectNode().put("groupname", groupName).put("description", desc).put("maxusers", maxUsers);
    }

    public Boolean validate() {
        return StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(desc) && null != maxUsers;
    }
}
