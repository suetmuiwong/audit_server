package com.et.auditServer.modules.sys.service;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.common.utils.StringUtil;
import com.et.auditServer.modules.sys.cache.errorCode.service.RetCodeTransferService;
import com.et.auditServer.modules.sys.dao.MenuDao;
import com.et.auditServer.modules.sys.dao.UserDao;
import com.et.auditServer.modules.sys.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends CrudService<MenuDao, Menu> {
    @Autowired
    UserDao userDao;
    @Autowired
    RetCodeTransferService retCodeTransferService;

    /**
     * 获取菜单树数据
     * */
    public List<MenuTree> menuTree(String menuName){
        logger.info("[menuTree] menu.menuName={}", menuName);
        Short level = null;
        List<MenuTree> parentList = dao.findParentList(menuName);
        if (parentList.size() > 0) {
            level = parentList.get(0).getTreeLevel();
        }
        List<MenuTree> childList = dao.findChildList(menuName);//所有子标签
        for (int i = 0; i < parentList.size(); i++) {
            if (level == parentList.get(i).getTreeLevel()) {
                parentList.get(i).setChildList(getChild(parentList.get(i).getMenuCode(), childList));
            }
            else {
                parentList.remove(i);
                i--;
            }
        }
        return parentList;
    }



    /**
     * 递归查找子菜单
     *
     * @param id
     * @param rootlabelTree
     * @return
     */
    private List<MenuTree> getChild(String id, List<MenuTree> rootlabelTree) {
        // 子菜单
        List<MenuTree> childList = new ArrayList<>();
        for (MenuTree menuTree : rootlabelTree) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menuTree.getParentCode())) {
                if (menuTree.getParentCode().equals(id)) {
                    childList.add(menuTree);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuTree menuTree : childList) {
            // 递归
            menuTree.setChildList(getChild(menuTree.getMenuCode(), rootlabelTree));
        }
        return childList;
    }

    /**
     * 批量更新菜单排序
     * */
    @Transactional(readOnly = false)
    public JsonResult updateTreeSort(List<MenuTreeSort> menuTreeSorts) {
        logger.info("[updateTreeSort] menuTreeSorts={}", menuTreeSorts);
        int count = 0;
        if (menuTreeSorts != null && menuTreeSorts.size() > 0) {
            List<Menu> menuList = new ArrayList<Menu>();
            for (MenuTreeSort menuTreeSort: menuTreeSorts) {
                Menu menu = new Menu();
                menu.setMenuCode(menuTreeSort.getId());
                menu.setTreeSort(Long.parseLong(menuTreeSort.getSort()));
                menu.preUpdate(loginCode());
                menuList.add(menu);
            }
            count = dao.updateBatch(menuList);

        }
        if (count > 0) {
            return JsonResult.success(JsonReturnCode.SAVE_SUCCESS.getDesc());
        }
        else {
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.SAVE_FAIL));
        }
    }

    /**
     * 保存、修改菜单基本信息
     * */
    @Transactional(readOnly = false)
    public JsonResult saveMenu(Menu menu) {
        logger.info("[saveMenu] menu.menuCode={}", menu.getMenuCode());
        MenuTree m = dao.findByCode(menu.getMenuCode());
        int count = 0;
        if (!menu.getIsNewRecord()) {
            logger.info("[saveMenu] update...m={}",m);
            if (m == null || StringUtil.isBlank(m.getMenuCode()) ) {
                logger.error("[saveMenu] 菜单不存在。。");
                throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.MENU_NOTEXISTS));
            }
            updateMenuData(menu);
            menu.preUpdate(loginCode());
            count = dao.update(menu);
        }
        else {
            logger.info("[saveMenu] save...menu={}",m);
            if (m != null) {
                logger.error("[saveMenu] 菜单已存在。。");
                throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.MENU_EXISTS));
            }
            MenuTree parentMenu = dao.findByCode(menu.getParentCode());
            if (StringUtil.isNotBlank(menu.getParentCode()) && parentMenu == null) {
                logger.error("[saveMenu] 父菜单不存在。。");
                throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.PARENT_NOTEXISTS));
            }
            updateMenuData(menu);
            menu.setMenuCode("1027" + StringUtil.getRandomCode(15, 0));
            menu.setTreeLeaf("1");
            menu.preInsert(loginCode());
            count = dao.insert(menu);
        }

        if (count > 0) {
            return JsonResult.success(JsonReturnCode.SAVE_SUCCESS.getDesc());
        }
        else {
            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.SAVE_FAIL));
        }
    }

    /**
     * 根据传过来的数据修改部分字段数据
     * */
    private void updateMenuData(Menu menu) {
        Menu parentMenu = dao.findByCode(menu.getParentCode());
        if (parentMenu != null && StringUtil.isNotBlank(parentMenu.getMenuCode())) {//有上级机构
            menu.setTreeLevel((short)(parentMenu.getTreeLevel() + Constant.ONE));
            parentMenu.setTreeLeaf("0");
            parentMenu.preUpdate(loginCode());
            menu.setTreeNames(parentMenu.getTreeNames() + "/" + menu.getMenuName());
            dao.update(parentMenu);
        }
        else {
            menu.setParentCode("0");
            menu.setTreeLeaf("0");
            menu.setTreeLevel(Constant.ZERO);
            menu.setTreeNames(menu.getMenuName());
        }
    }
    /**
     * 获取用户菜单树
     * */
//    public List<MenuTree> getUserMenuTree(){
//        User activeUser= UserUtils.getActiveUser();
//        activeUser = userDao.getUserByUserName(activeUser.getUserName());
//        if (activeUser == null) {
//            throw new BusinessException(JsonReturnCode.USER_NOTFOUND);
//        }
//        String userCode = activeUser.getUserName();
//        List<Role> selectedRoleList = userDao.getRolelist(userCode);
//        if(selectedRoleList == null || selectedRoleList.size() == Constant.ZERO) {
//            throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.ACCESS_ERROR));
//        }
//        List<MenuTree> parentList = dao.findUserParentList(selectedRoleList);
//        List<MenuTree> childList = dao.findUserChildList(selectedRoleList);
//        if(parentList != null && parentList.size() > 0){
//            for (MenuTree menuTree : parentList) {
//                menuTree.setChildList(getChild(menuTree.getMenuCode(), childList));
//            }
//
//        }
//        return parentList;
//    }

    /**
     * 删除菜单
     * */
    @Transactional
    public JsonResult deleteByMenuCode(String menuCode) {
        //先删除角色关联菜单
        dao.deleteRoleMenu(menuCode);
        //再删除菜单以及下级菜单
        dao.deleteByPrimaryKey(menuCode);
        return JsonResult.success();
    }

    /**
     * 查询菜单信息
     * */
    public JsonResult findByMenuCode(String menuCode) {
        return JsonResult.success(dao.findByCode(menuCode));
    }
}
