package com.joolun.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解防止表单重复提交
 * @author ruoyi
 */
@Inherited//作用：如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解,当用了@Inherited修饰的注解的@Retention是RetentionPolicy.RUNTIME，则增强了继承性，在反射中可以获取得到
@Target(ElementType.METHOD) //标记该用于什么地方，用于方法之上
@Retention(RetentionPolicy.RUNTIME) //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Documented //这个注解只是用来标注生成javadoc的时候是否会被记录。
public @interface RepeatSubmit
{

}
