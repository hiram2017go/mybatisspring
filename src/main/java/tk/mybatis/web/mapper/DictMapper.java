package tk.mybatis.web.mapper;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.web.model.SysDict;

import java.util.List;

public interface DictMapper {

    //根据主键查询
    SysDict selectByPrimaryKey(Long id);

    //根据条件查询
    List<SysDict> selectBySysDict(SysDict sysDict, RowBounds rowBounds);

    //新增
    int insert(SysDict sysDict);

    //更新
    int updateById(SysDict sysDict);

    //根据主键删除
    int deleteById(Long id);
}
