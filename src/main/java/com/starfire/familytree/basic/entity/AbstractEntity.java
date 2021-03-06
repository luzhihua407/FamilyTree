package com.starfire.familytree.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.starfire.familytree.enums.ValidEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author luzh
 */
@Data
//@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * @author luzh
     */

    @JsonSerialize(using = ToStringSerializer.class)
	@TableId(type = IdType.AUTO)
    private Long id = null;

    /**
     * 创建人
     */
    private String creator="system";

    /**
     * 创建用户id
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 编辑人
     */
    private String editor;

    /**
     * 编辑用户
     */
    private Long editUser;
    /**
     * 编辑时间
     */
    private LocalDateTime editTime = LocalDateTime.now();

    private ValidEnum valid = ValidEnum.是;

    /**
     * 序号
     */
    private Integer orderno =1;

    @Version
    private int version=0;
}
