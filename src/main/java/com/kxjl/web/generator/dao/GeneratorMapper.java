package com.kxjl.web.generator.dao;



import java.util.List;

import com.kxjl.web.generator.pojo.AField;
import com.kxjl.web.generator.pojo.Generator;

public interface GeneratorMapper {
    List<Generator> selectGeneratorList(Generator generator);
    
    
    /**
     * 查询表字段
     * @param generator
     * @return
     * @author zj
     * @date 2019年1月8日
     */
    List<AField> selectTableColList(Generator generator);
}
