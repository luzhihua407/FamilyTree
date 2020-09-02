package com.starfire.familytree.usercenter.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starfire.familytree.basic.entity.AbstractEntity;
import com.starfire.familytree.enums.BooleanEnum;
import com.starfire.familytree.enums.GenderEnum;
import com.starfire.familytree.enums.UserTypeEnum;
import com.starfire.familytree.security.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author luzh
 * @since 2019-03-03
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@Data
public class User extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @NotEmpty(message = "邮箱不能为空")
    @Email
    private String email;

    private BooleanEnum firstLogin=BooleanEnum.否;

    private LocalDateTime lastLoginTime;

    private String mobile;

    @TableField(exist = false)
    private String[] roles;

    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 32, message = "用户名长度为2-32个字符")
    private String username;


    /**
     * 值为空，忽略不更新
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//Jackson
    @TableField(strategy = FieldStrategy.NOT_NULL)
    private String password;

    private String realName;

    private LocalDateTime registerTime = LocalDateTime.now();

    private UserTypeEnum type= UserTypeEnum.普通用户;

    private Integer age;

    @NotNull
    private GenderEnum gender= GenderEnum.不清楚;

    private Integer province;

    private Integer city;

    private Integer district;

    private String address;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getValid().getValue()==1?true:false;
    }


}
