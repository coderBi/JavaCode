/**
 *
 */
package ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>Title:CustomDateConverter</p>
 * <p>Description:自定义的日期转换器，需要是先converter接口</p>
 * @author Edwin
 * @since 2017-2-27
 * @version 1.0
 */
public class CustomDateConverter implements Converter<String, Date>{

	/**
	 * String 到  java.utils.Date
	 */
	@Override
	public Date convert(String source) {
		try {
			//转换成功直接返回
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//转换失败返回null
		return null;
	}

}
