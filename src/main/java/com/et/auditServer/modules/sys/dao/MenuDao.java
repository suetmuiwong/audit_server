package com.et.auditServer.modules.sys.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.sys.entity.Menu;
import com.et.auditServer.modules.sys.entity.MenuTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends CrudDao<Menu> {

    int deleteByPrimaryKey(@Param("menuCode")String menuCode);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(@Param("menuCode")String menuCode);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<MenuTree> findParentList(@Param("menuName") String menuName);

    List<MenuTree> findChildList(@Param("menuName") String menuName);

    MenuTree findByCode(@Param("menuCode")String menuCode);

    int updateBatch(@Param("menuList") List<Menu> menuList);

    void deleteRoleMenu(@Param("menuCode")String menuCode);
}
