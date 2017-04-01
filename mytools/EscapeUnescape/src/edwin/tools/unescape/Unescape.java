/**
 *
 */
package edwin.tools.unescape;

/**
 * <p>
 * Title:Unescape
 * </p>
 * <p>
 * Description:介绍
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class Unescape {
	public static String unescape(String src) {
		if (null == src)
			return null;
		StringBuilder stringBuilder = new StringBuilder();
		int lastPos = 0, pos = 0;
		while (lastPos < src.length()) {
			pos = src.indexOf('%', lastPos);
			if (lastPos > pos) {
				// 由于是从index为lastPos开始查找的，出现lastPos > pos也就是说 pos == -1。
				// 上面也可以写成条件 pos == -1
				pos = src.length();
			}
			stringBuilder.append(src.substring(lastPos, pos)); // 将lastPos到当前pos中间的字符直接拷贝过来
			if (pos == src.length())
				break;
			if (src.charAt(pos + 1) == 'u') {
				// 要转成unicode字符
				stringBuilder.append((char) Integer.parseInt(
						src.substring(pos + 2, pos + 6), 16));
				lastPos = pos + 6;
			} else {
				// 非Unicode字符
				stringBuilder.append((char) Integer.parseInt(
						src.substring(pos + 1, pos + 3), 16));
				lastPos = pos + 3;
			}
		}
		return stringBuilder.toString();
	}
}
