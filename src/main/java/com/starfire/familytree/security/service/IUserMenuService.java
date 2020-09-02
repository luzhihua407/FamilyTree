package com.starfire.familytree.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starfire.familytree.security.entity.UserMenu;
import com.starfire.familytree.vo.PageInfo;
import com.starfire.familytree.vo.UserMenuVO;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author luzh
 * @since 2019-03-03
 */
public interface IUserMenuService extends IService<UserMenu> {
    public PageInfo<Map<String, Object>, UserMenu> page(PageInfo<Map<String, Object>, UserMenu> pageInfo);

    public void addOrUpdateUserMenu(UserMenuVO userMenuVO);

    public UserMenuVO getUserMenuByUserId(Long userId);
}
