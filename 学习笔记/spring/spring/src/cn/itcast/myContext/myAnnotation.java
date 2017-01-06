/**
 *
 */
package cn.itcast.myContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-17
 */
@Retention(RetentionPolicy.RUNTIME)  //作用范围在运行期
@Target({ElementType.FIELD, ElementType.METHOD})  //使用在字段跟方法上
public @interface myAnnotation {
	String name() default "";  //有一个属性，默认值是""
}
