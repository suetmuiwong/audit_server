package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

import java.util.Date;

public class EnclosureInfo extends DataEntity<EnclosureInfo> {

    private int Id;
    private String EnclosureName;
    private String Path;
    private String ExpandedName;
    private String Creater;
    private Date CreateTime;


    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getEnclosureName() {
        return EnclosureName;
    }
    public void setEnclosureName(String EnclosureName) {
        this.EnclosureName = EnclosureName;
    }

    public String getPath() {
        return Path;
    }
    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getExpandedName() {
        return ExpandedName;
    }
    public void setExpandedName(String ExpandedName) {
        this.ExpandedName = ExpandedName;
    }

    public String getCreater() {
        return Creater;
    }
    public void setCreater(String Creater) {
        this.Creater = Creater;
    }

    public Date getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

}
