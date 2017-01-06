/**
 *
 */
package find.input.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title:Main
 * </p>
 * <p>
 * 正则查找被input标签内部包含hidden，例如<input type="hidden" />, 但是不要获取到 <input
 * type="hidden" /><input type='text' />:
 * </p>
 * 
 * @author Edwin
 * @since 2016-12-22
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String regex = "<input((?!<input).)*?hidden.*?/>|<input((?!<input).)*?hidden.*?>.*?</input>";
		String findIn = "<input type=\"hidden\" /><input type='text' /><input type='hidden'></input>";
		
		Matcher m = Pattern.compile(regex).matcher(findIn);
		while(m.find()){
			System.out.println("start: " + m.start() + " end: "
					+ m.end() + "  value: " + m.group());
		}

	}

}
