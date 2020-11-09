package com.et.auditServer.modules.sys.service;

import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.modules.sys.dao.MenuDao;
import com.et.auditServer.modules.sys.dao.MenuViewPermissionsDao;
import com.et.auditServer.modules.sys.entity.Menu;
import com.et.auditServer.modules.sys.entity.MenuViewPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuViewPermissionsService extends CrudService<MenuDao, Menu> {

    @Autowired
    MenuViewPermissionsDao menuViewPermissionsDao;

    /**
     * 获取菜单树数据
     * */
    public List<MenuViewPermissions> getByMenuCode(String menuCode){
        return menuViewPermissionsDao.getByMenuCode(menuCode);
    }

}
