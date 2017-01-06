package cn.itcast.action.typeConvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		if(toType == Date.class){
			//转为Date类型
			String[] params = (String[]) value; //这里的value 是struts通过request.getParameterValues()得到的。
			try {
				return dateFormat.parseObject(params[0]);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		} else if(toType == String.class){
			//转换为指定格式的字符串
			return dateFormat.format(value);
		}
		return null;
	}
}
