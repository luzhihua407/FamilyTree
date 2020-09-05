package com.starfire.familytree.usercenter.controller;

import com.starfire.familytree.enums.MenuTypeEnum;
import com.starfire.familytree.response.Response;
import com.starfire.familytree.sys.entity.Menu;
import com.starfire.familytree.sys.entity.Role;
import com.starfire.familytree.sys.service.*;
import com.starfire.familytree.usercenter.entity.User;
import com.starfire.familytree.usercenter.service.IUserService;
import com.starfire.familytree.vo.*;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author luzh
 * @since 2019-03-03
 */
@RestController
@RequestMapping("/usercenter/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private IMenuService menuService;


    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMenuRightService roleMenuRightService;

    @Autowired
    private IUserMenuRightService userMenuRightService;

    @Autowired
    private IUserRoleService userRoleService;

//    @RequestMapping("/current")
//    public PrincipalVO user(Principal principal) {
//        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;
//        User user = (User) auth.getPrincipal();
//        Long userId = user.getId();
//        PrincipalVO principalVO=new PrincipalVO();
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user, userVO);
//        Collection<GrantedAuthority> authorities = auth.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            String roleCode = grantedAuthority.getAuthority();
//            if(roleCode.startsWith("ROLE_")){
//                roleCode=roleCode.replace("ROLE_","");
//            }
//            List<Menu> menus=new ArrayList<>();
//            List<String> permission = new ArrayList<>();
//            //这里按父-子 父-子 顺序取菜单，不然antd 无法正常显示菜单
//            if(roleCode.equals("admin")){
//                menus= menuService.getParentMenusByAdmin();
//                findSubMenus(principalVO, menus);
//                permission.add("admin");
//            }else{
//                Role role = roleService.getRoleByCode(roleCode);
//                Long roleId=role.getId();
//                permission = roleMenuRightService.getPermission(roleId);
//                List<String> menuRightServicePermission = userMenuRightService.getPermission(userId);
//                for (int j = 0; j < menuRightServicePermission.size(); j++) {
//                    String s2 =  menuRightServicePermission.get(j);
//                    if(!permission.contains(s2)){
//                        permission.add(s2);
//                    };
//                }
//                userVO.setRoleId(roleId);
//                //分配给角色的菜单集合
//                menus = menuService.getMenusByRoleId(roleId);
//                //分配给用户的菜单集合
//                List<Menu> menuList = menuService.getMenusByUserId(userId);
//                //去重
//                for (int i = 0; i < menuList.size(); i++) {
//                    Menu menu =  menuList.get(i);
//                    if(!menus.contains(menu)){
//                        menus.add(menu);
//                    }
//                }
//
//                for (Menu menu : menus) {
//                    if(menu==null){
//                        break;
//                    }
//                    MenuTypeEnum type = menu.getType();
//                    RouteVO  route=new RouteVO();
//                    route.setIcon(menu.getIcon());
//                    route.setId(menu.getId().toString());
//                    route.setName(menu.getName());
//                    route.setRoute(menu.getUrl());
//                    if(type==MenuTypeEnum.不可见菜单){
//
//                        route.setMenuParentId("-1");
//                    }else{
//
//                        route.setMenuParentId(menu.getParent()==null?"":menu.getParent().toString());
//                    }
//                    route.setBreadcrumbParentId(menu.getParent()==null?"":menu.getParent().toString());
//                    principalVO.getMenus().add(route);
//                }
//            }
//            principalVO.setPermission(permission);
//
//        }
//        principalVO.setUser(userVO);
//        return principalVO;
//    }

//
//    @RequestMapping("/current")
//    public PrincipalVO principal(Principal principal) {
//        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;
//        User user = (User) auth.getPrincipal();
//        Long userId = user.getId();
//        PrincipalVO principalVO=new PrincipalVO();
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user, userVO);
//        Collection<GrantedAuthority> authorities = auth.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            String roleCode = grantedAuthority.getAuthority();
//            if(roleCode.startsWith("ROLE_")){
//                roleCode=roleCode.replace("ROLE_","");
//            }
//            List<Menu> menus=new ArrayList<>();
//            List<String> permission = new ArrayList<>();
//            //这里按父-子 父-子 顺序取菜单，不然antd 无法正常显示菜单
//            if(roleCode.equals("admin")){
//                menus= menuService.getParentMenusByAdmin();
//                findSubMenus(principalVO, menus);
//                permission.add("admin");
//            }else{
//                Role role = roleService.getRoleByCode(roleCode);
//                Long roleId=role.getId();
//                permission = roleMenuRightService.getPermission(roleId);
//                List<String> menuRightServicePermission = userMenuRightService.getPermission(userId);
//                for (int j = 0; j < menuRightServicePermission.size(); j++) {
//                    String s2 =  menuRightServicePermission.get(j);
//                    if(!permission.contains(s2)){
//                        permission.add(s2);
//                    };
//                }
//                userVO.setRoleId(roleId);
//                //分配给角色的菜单集合
//                menus = menuService.getMenusByRoleId(roleId);
//                //分配给用户的菜单集合
//                List<Menu> menuList = menuService.getMenusByUserId(userId);
//                //去重
//                for (int i = 0; i < menuList.size(); i++) {
//                    Menu menu =  menuList.get(i);
//                    if(!menus.contains(menu)){
//                        menus.add(menu);
//                    }
//                }
//
//                for (Menu menu : menus) {
//                    if(menu==null){
//                        break;
//                    }
//                    MenuTypeEnum type = menu.getType();
////                    RouteVO  route=new RouteVO();
////                    route.setIcon(menu.getIcon());
////                    route.setId(menu.getId().toString());
////                    route.setName(menu.getName());
////                    route.setRoute(menu.getUrl());
////                    if(type==MenuTypeEnum.不可见菜单){
////
////                        route.setMenuParentId("-1");
////                    }else{
////
////                        route.setMenuParentId(menu.getParent()==null?"":menu.getParent().toString());
////                    }
////                    route.setBreadcrumbParentId(menu.getParent()==null?"":menu.getParent().toString());
//
//                    NavMenuTree menuTree = new NavMenuTree();
//                    menuTree.setName(menu.getName());
//                    if(type==MenuTypeEnum.不可见菜单){
//                        menuTree.setParentId(-1l);
//                    }else{
//                        menuTree.setParentId(menu.getParent()==null?0:menu.getParent());
//                    }
//                    menuTree.getMeta().setTitle(menu.getName());
//                    menuTree.getMeta().setIcon(menu.getIcon());
//                    menuTree.getMeta().setShow(true);
//                    menuTree.setRedirect(menu.getUrl());
//                    principalVO.getMenus().add(menuTree);
//                }
//            }
//            principalVO.setPermission(permission);
//
//        }
//        principalVO.setUser(userVO);
//        return principalVO;
//    }

//    private void findSubMenus(PrincipalVO principalVO, List<Menu> menus) {
//        RouteVO route;
//        for (Menu menu : menus) {
//            MenuTypeEnum type = menu.getType();
////            route=new RouteVO();
////            route.setIcon(menu.getIcon());
////            route.setId(menu.getId().toString());
////            route.setName(menu.getName());
////            route.setRoute(menu.getUrl());
////            if(type==MenuTypeEnum.不可见菜单){
////
////            route.setMenuParentId("-1");
////            }else{
////
////            route.setMenuParentId(menu.getParent()==null?"":menu.getParent().toString());
////            }
////            route.setBreadcrumbParentId(menu.getParent()==null?"":menu.getParent().toString());
////
//
//            NavMenuTree menuTree = new NavMenuTree();
//            menuTree.setName(menu.getName());
//            if(type==MenuTypeEnum.不可见菜单){
//                menuTree.setParentId(-1l);
//            }else{
//                menuTree.setParentId(menu.getParent()==null?0:menu.getParent());
//            }
//            menuTree.getMeta().setTitle(menu.getName());
//            menuTree.getMeta().setIcon(menu.getIcon());
//            menuTree.getMeta().setShow(true);
//            menuTree.setRedirect(menu.getUrl());
//            principalVO.getMenus().add(menuTree);
//            menus = menuService.getChildMenu(menu.getId());
//            if(menus!=null && menus.size()>0){
//                findSubMenus(principalVO, menus);
//            }
//        }
//    }


    /**
     * 新增或修改
     *
     * @param user
     * @return
     * @author luzh
     */
    @PostMapping("/addOrUpdate")
    public Response<User> addOrUpdateUser(@RequestBody(required = false) @Valid User user) {
        String username = user.getUsername();
        User byUserName = userService.getUserByUserName(username);
        if(byUserName!=null && user.getId()==null){
            throw  new  RuntimeException("该用户名已存在，请换一个用户名");
        }
        userService.saveOrUpdateUser(user);
        Response<User> response = new Response<User>();
        return response.success(user);

    }

    /**
     * 删除
     *
     * @return
     * @author luzh
     */
    @PostMapping("/delete")
    public Response<String> deleteUser(@RequestBody DeleteVO<Long[]> deleteVO) {
        boolean flag = false;
        Long[] ids = deleteVO.getIds();
        flag = userService.removeByIds(Arrays.asList(ids));
        Response<String> response = new Response<String>();
        if (!flag) {
            return response.failure();
        }
        return response.success();

    }
    /**
     * 获取单个用户
     *
     * @return
     * @author luzh
     */
    @GetMapping("/get")
    public Response<User> getUser(Long id) {
        User user = userService.getById(id);
        List<Long> userId = userRoleService.getRoleIdsByUserId(user.getId());
        for (int i = 0; i < userId.size(); i++) {
            Long aLong =  userId.get(i);
            String roles=String.valueOf(aLong);
            user.getRoles().add(roles);
        }
        Response<User> response = new Response<>();
        return response.success(user);

    }

    /**
     * 分页
     *
     * @param page
     * @return
     * @author luzh
     */
    @PostMapping("/page")
    public Response<PageInfo<Map<String, Object>, User>> page(@RequestBody(required = false)  PageInfo<Map<String, Object>, User> page) {
        page=page==null?new PageInfo<>():page;
        PageInfo<Map<String, Object>, User> pageInfo = userService.page(page);
        Response<PageInfo<Map<String, Object>, User>> response = new Response<PageInfo<Map<String, Object>, User>>();
        return response.success(pageInfo);

    }

    @PostMapping("/resetPassword")
    public Response resetPassword(@RequestBody ResetPasswordVO resetPasswordVO) {
       String password = resetPasswordVO.getPassword();
         String againPassword = resetPasswordVO.getAgainPassword();
         if(!password.equals(againPassword)){
             throw new RuntimeException("两次密码不一致，请检查");
         }
        userService.resetPassword(resetPasswordVO);
        Response response = new Response();
        return response.success();

    }


}
