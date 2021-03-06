package com.starfire.familytree.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.starfire.familytree.sys.entity.MenuRight;
import com.starfire.familytree.sys.entity.RoleMenuRight;
import com.starfire.familytree.sys.service.IMenuRightService;
import com.starfire.familytree.sys.service.IMenuService;
import com.starfire.familytree.sys.service.IRoleMenuRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starfire.familytree.sys.entity.Menu;
import com.starfire.familytree.sys.mapper.MenuMapper;
import com.starfire.familytree.vo.MenuTree;
import com.starfire.familytree.vo.PageInfo;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luzh
 * @since 2019-03-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private IMenuRightService menuRightService;
    @Autowired
    private IRoleMenuRightService roleMenuRightService;
    @Override
    public PageInfo<Map<String, Object>, Menu> page(PageInfo<Map<String, Object>, Menu> pageInfo) {
        Map<String, Object> param = pageInfo.getParam();
        Page<Menu> page = pageInfo.toMybatisPlusPage();
        Page<Menu> selectPage = (Page<Menu>) baseMapper.queryPage(page, param);
        pageInfo.from(selectPage);
        return pageInfo;
    }

    @Override
    public List<Menu> getChildMenu(Long parentMenuId){
        return baseMapper.getChildMenu(parentMenuId);
    }

    @Override
    public List<Menu> getParentMenu(Long menuId){
        return baseMapper.getParentMenu(menuId);
    }

    @Override
    public List<Menu> getParentMenus() {
        return baseMapper.getParentMenus();
    }

    @Override
    public List<Menu> getByIds(List<Long> menuIds) {
        return baseMapper.getByIds(menuIds);
    }

    @Override
    public List<MenuTree> getMenusTree() {
        List<MenuTree> menuTree=new ArrayList<MenuTree>();
        List<Menu> parentMenus = baseMapper.getRootMenuTree();
        for (int i = 0; i < parentMenus.size(); i++) {

            Menu menu = parentMenus.get(i);
            Long id = menu.getId();
            String code = menu.getCode();
            String name = menu.getName();
            MenuTree parent=new MenuTree(name,id.toString(),id+"");
            List<Menu> childMenu = getChildMenu(menu.getId());
            //菜单权限
            for (int j = 0; j < childMenu.size(); j++) {
                Menu sub = childMenu.get(j);
                Long _id = sub.getId();
                String _code = sub.getCode();
                String _name = sub.getName();
                MenuTree child=new MenuTree(_name,_id.toString(),id+"-"+_id);
                parent.getChildren().add(child);
                List<MenuRight> menuRights = menuRightService.getList(_id);
                //以下为操作权限
                for (int k = 0; k < menuRights.size(); k++) {
                    MenuRight menuRight =  menuRights.get(k);
                    String code1 = menuRight.getCode();
                    String name1 = menuRight.getName();
                    Long menuId = menuRight.getMenuId();
                    Long menuRightId = menuRight.getId();
                    MenuTree children=new MenuTree(name1+"["+code1+"]",menuRightId.toString(),id+"-"+_id+"-"+menuRightId);
                    child.getChildren().add(children);
                }
            }
            menuTree.add(parent);
        }
        return menuTree;
    }

    @Override
    public List<MenuTree> getMenusTreeByRoleId(Long roleId) {
        List<MenuTree> menuTree=new ArrayList<MenuTree>();
        List<Menu> menuList = baseMapper.getRootMenuTreeByRoleId(roleId);
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            Long parentId = menu.getParent();
            Long menuId = menu.getId();
            String name = menu.getName();
            MenuTree parent=new MenuTree(name,menuId.toString(),parentId+"-"+menuId+"");
            //菜单权限
            List<RoleMenuRight> roleMenuRightList = roleMenuRightService.getRoleMenuRight(menuId);
            //以下为操作权限
            for (int k = 0; k < roleMenuRightList.size(); k++) {
                RoleMenuRight roleMenuRight =  roleMenuRightList.get(k);
                Long menuRightId = roleMenuRight.getMenuRightId();
                MenuTree children=new MenuTree("",menuRightId.toString(),parentId+"-"+menuId+"-"+menuRightId);
                parent.getChildren().add(children);
            }
            menuTree.add(parent);
        }
        return menuTree;
    }

    @Override
    public List<Menu> getMenusByRoleId(Long roleId){
        List<Menu> result=new ArrayList<>();
        //目录菜单
        List<Menu> parentMenus = baseMapper.getParentMenusByRoleId(roleId);
        result.addAll(parentMenus);
        //分配的菜单
        List<Menu> menus = baseMapper.getMenusByRoleId(roleId);
        //加载所有不可见的菜单，让页面控制权限
        List<Menu> invisibleMenus = baseMapper.getInvisibleMenus();
        result.addAll(menus);
        result.addAll(invisibleMenus);
        return  result;
    }

    @Override
    public List<String> getMenusIdByRoleId(Long roleId) {
        List<String> menuIds=new ArrayList<>();
        List<MenuTree> menuTrees = this.getMenusTreeByRoleId(roleId);
        for (int i = 0; i < menuTrees.size(); i++) {
            MenuTree menuTree =  menuTrees.get(i);
            String key = menuTree.getKey();
            List<MenuTree> children = menuTree.getChildren();
            for (int j = 0; j < children.size(); j++) {
                MenuTree tree =  children.get(j);
                menuIds.add(tree.getKey());
            }
        }
        return menuIds;
    }
    private void extractToList(List<MenuTree> menuTrees,List<String> menuIds){
        for (int i = 0; i < menuTrees.size(); i++) {
            MenuTree menuTree =  menuTrees.get(i);
            String key = menuTree.getKey();
            menuIds.add(key);

            extractToList(menuTree.getChildren(),menuIds);
        }
    }
    @Override
    public List<Menu> getMenusByUserId(Long userId){
        List<Menu> result=new ArrayList<>();
        //目录菜单
        List<Menu> parentMenus = baseMapper.getParentMenusByRoleId(userId);
        result.addAll(parentMenus);
        //分配的菜单
        List<Menu> menus = baseMapper.getMenusByUserId(userId);
        //加载所有不可见的菜单，让页面控制权限
        List<Menu> invisibleMenus = baseMapper.getInvisibleMenus();
        result.addAll(menus);
        result.addAll(invisibleMenus);
        return  result;
    }

    @Override
    public List<Menu> getMenusByAdmin(){
        return baseMapper.getMenusByAdmin();
    }

    @Override
    public Menu getMenuByCode(String code) {
        Menu menu = baseMapper.getMenuByCode(code);
        return menu;
    }

    @Override
    public List<Long> getParentMenuIds(List<Long> childMenuIds) {
        return baseMapper.getParentMenuIds(childMenuIds);
    }
}
