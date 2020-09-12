package com.jamie.lombok;

import lombok.Data;

/**
 * 演示：lombok @Data 注解
 * 大而全的注解：包含 @Getter、@Setter、@RequiredArgsConstructor、@ToString、@EqualsAndHashCode
 * @author jamie
 * @date 2020/9/12 21:26
 */
@Data
public class DataTest {

    private String field1;

    private String field2;

}
