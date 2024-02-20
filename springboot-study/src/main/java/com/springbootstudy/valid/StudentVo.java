package com.springbootstudy.valid;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class StudentVo {
    
    @NotBlank(message = "不能为空")
    private String name;

    @Pattern(regexp = "^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$", message = "格式为yyyy/mm/dd")
    private String date;

    @DecimalMin(value = "0",message = "[主机厂核销或支持费用]必须大于或等于0")
    private BigDecimal activity_cost;

//    @Size(min = 1, max = 1, message = "邮箱长度必须在6位到50位之间")
    @Pattern(regexp = "[男女]", message = "cc")
    private String sex;
}
