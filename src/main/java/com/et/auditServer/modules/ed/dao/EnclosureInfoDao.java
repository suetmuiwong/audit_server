package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.dto.EnclosureInfoDTO;
import com.et.auditServer.modules.ed.entity.EnclosureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnclosureInfoDao extends CrudDao<EnclosureInfo> {
    /**
     *新增附件
     */
    int insert(EnclosureInfo approvalNodeInfo);

    /**
     * 根据Id查询附件
     */
    List<EnclosureInfoDTO> selectById(@Param("Id") int Id);
}
