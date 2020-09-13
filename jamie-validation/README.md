# jamie-validation

### Bean Validation与Hibernate Validator

- Bean Validation 1.0 参考实现 ：Hibernate Validator 4.3.1.Final
- Bean Validation 1.1 参考实现 ：Hibernate Validator 5.1.1.Final
- Bean Validation 2.0 参考实现 ：Hibernate Validator 6.0.1.Final

### Hibernate Validator与Spring Validation

- Spring Validation在Hibernate Validator的基础上，对其进行了二次封装，以满足在Spring环境中更简单、高效的堆数据进行验证

###  常用约束注解

- 空值校验类：@Null，@NotNull，@NotEmpty，@NotBlank等
- 范围校验类：@Min，@Size，@Digits，@Future，@Negative等
- 其他校验类：@Email，@URL，@AssertTrur，@Pattern等

### 完成验证的步骤

- 约束注解的定义
- 约束验证规则(约束验证器)
- 约束注解的声明
- 约束验证流程

### 实战案例：自定义手机号约束注解

- 定义@interface Phone注解
- 实现约束验证器PhoneValidator.java
- 声明@Phone约束验证
- 执行手机号约束验证流程