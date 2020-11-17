package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

public class ApprovalNodeInfo extends DataEntity<ApprovalNodeInfo> {

    private int Id;
    private int processId;
    private String upNode;
    private String nextNode;
    private int node;
    private String category;


    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public int getProcessId() {
        return processId;
    }
    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getUpNode() {
        return upNode;
    }
    public void setUpNode(String upNode) {
        this.upNode = upNode;
    }

    public String getNextNode() {
        return nextNode;
    }
    public void setNextNode(String nextNode) {
        this.nextNode = nextNode;
    }

    public int getNode() {
        return node;
    }
    public void setNode(int node) {
        this.node = node;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
