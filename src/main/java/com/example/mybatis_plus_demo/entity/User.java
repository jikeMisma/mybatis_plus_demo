package com.example.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author MaZhiCheng
 * @date 2020/11/11 - 16:54
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */

@Data
public class User {

    @TableId(type = IdType.ID_WORKER)//mybatisplus下的id生成策略
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;//版本号

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;//物理删除和逻辑删除的标记
}
