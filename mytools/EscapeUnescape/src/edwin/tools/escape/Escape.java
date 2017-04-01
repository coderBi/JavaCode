/**
 *
 */
package edwin.tools.escape;

import java.util.regex.Pattern;

/**
 * <p>
 * Title:Escape
 * </p>
 * <p>
 * Description:对字符串进行escape操作
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class Escape {
	// 不进行escape的字符，包括*，+，-，.，/，@，_，0-9，a-z，A-Z
	private static String noEscapePattern = "^[+|-|*|/|.|@|_|\\d|a-zA-Z]$";

	public static String escape(String src) {
		if (null == src)
			return null;
		char c;
		StringBuilder sb = new StringBuilder();
		sb.ensureCapacity(6 * src.length());

		for (int i = 0; i < src.length(); ++i) {
			c = src.charAt(i);
			if (Pattern.compile(noEscapePattern).matcher(String.valueOf(c))
					.matches()) {
				// 如果在不需要escape的列表里面，就直接append到builder
				sb.append(c);
				continue;
			}
			sb.append(c < 256 ? '%' : "%u"); // 如果c > 256 就是Unicode字符
			sb.append(c < 16 ? "0" + Integer.toString(c, 16).toUpperCase()
					: Integer.toString(c, 16).toUpperCase()); // 如果c < 16
																// 就需要在前面补0，因为16进制只有一位。
		}
		return sb.toString();
	}
}
