package com.starfire.familytree.folk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.starfire.familytree.basic.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author luzh
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bs_partner")
public class Partner extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 丈夫ID
     */
    private Long husbandId;

    /**
     * 妻子ID
     */
    private Long wifeId;


    /**
     * 备注
     */
    private String remark;


}
