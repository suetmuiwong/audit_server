package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.entity.EnclosureInfo;
import org.apache.ibatis.annotations.Param;

public interface EnclosureInfoDao extends CrudDao<EnclosureInfo> {


    /**
     *新增附件
     */
    int insert(EnclosureInfo approvalNodeInfo);

    /**
     * 根据Id查询附件
     */
    int selectById(@Param("Id") int Id);
}
