package com.et.auditServer.modules.sys.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.sys.entity.Menu;
import com.et.auditServer.modules.sys.entity.MenuViewPermissions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuViewPermissionsDao extends CrudDao<Menu> {

    int deleteByPrimaryKey(@Param("menuCode") String menuCode);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(@Param("menuCode") String menuCode);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuViewPermissions> getByMenuCode(@Param("menuCode") String menuCode);
}
