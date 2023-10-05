package com.itheima.mybatis.dto;

import java.util.List;
import lombok.Data;

/**
 * 查询条件.
 *
 * @author 胡磊
 * @since 2023/9/22 15:28
 */
@Data
public class QueryVo {

    private List<Integer> ids;
    private UserDto userDto;
}
