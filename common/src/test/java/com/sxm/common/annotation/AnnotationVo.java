package com.sxm.common.annotation;

import lombok.Data;

/**
 * 注解实体类
 * @author zxl
 * @date 2021/4/9 18:28
 */
@Data
public class AnnotationVo {

    private String id;

    @IsNull
    private String name;

}
