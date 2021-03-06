package com.starfire.familytree.folk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starfire.familytree.folk.entity.Children;
import com.starfire.familytree.folk.entity.Member;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author luzh
 * @since 2019-08-15
 */
public interface ChildrenMapper extends BaseMapper<Children> {

    public List<Member> getChildrenList(Long parentId);

    /**
     * 根据孩子找父母
     * @param childId
     * @return
     */
    public Member getParent(Long childId);

    public Children getEntityByChildrenId(Long childrenId);
}
