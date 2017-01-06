import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */

/**
 * <p>
 * Title:Main
 * </p>
 * <p>
 * 太久没有用过正则表达式，发现忘记的差不多，进行一次复习练习:
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
		String pattern = null, findIn = null;

		// 练习1：找到连续出现 5 个元音（aeiou）的单词
		System.out.println("练习1：");
		pattern = "[aeiou]{5}";
		findIn = "aeio";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn += "uxx";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习2：匹配日期格式 YYYY-MM-DD
		System.out.println("练习2：");
		pattern = "[1-9]\\d{3}-((1[0-2])|(0[1-9]))-((3[0-1])|([1-2]\\d)|(0[1-9]))";
		findIn = "2016-01-1";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn += "0";
		printMatchResult(pattern, findIn); // 找到，打印true
		printMatchResult(pattern, "2016-13-10"); // 月份是13，不匹配，打印false

		// 练习3：在字母e之前有i，但是没有c
		System.out.println("练习3：");
		pattern = "[^c]{0,}ie";
		findIn = "ce";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "ie";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习4：找到形如 abba baab
		System.out.println("练习4：");
		pattern = "(.)(.)\\2\\1";
		findIn = "aabb";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "abba";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习5：找到双引号，并且里面不能包含内容
		System.out.println("练习5：");
		pattern = "\"[.]{0}\""; // 注意之前在中间写了 [^.]，
								// 但是这个的意思是包含一个字符，并且不能是任何字符，本身语意已经有问题
		findIn = "\"hellow world\"";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "\"\"";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习6：? * +
		System.out.println("练习6：");
		// ? 表示次数 0-1
		pattern = "((.)(.)\\2\\1)?";
		findIn = "aabb";
		printMatchResult(pattern, findIn); // 找到，因为可以找到 0 个匹配
		findIn = "abba";
		printMatchResult(pattern, findIn); // 找到，打印true

		// * 表示 0到 n, 跟上面的类似，这里不重复

		// + 表示 1到n
		pattern = "((.)(.)\\3\\2)+"; // \数字 引用前面的子表达式，计数从左括号数
		findIn = "aabb";
		printMatchResult(pattern, findIn); // 打印false
		findIn = "abba";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习7：找到至少有三个单词，并且单词之间用空分割
		System.out.println("练习7：");
		pattern = "\\w+(\\s\\w+){2,}"; // \w等价于[a-zA-Z0-9] \w+表示多个字符
										// \s表示空格(\s等价于 [ \t\r\n])
		findIn = "hellow world";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "hellow world, wellcome to java";
		printMatchResult(pattern, findIn); // 找到，打印true
		findIn = "hellow world, wellcome";
		printMatchResult(pattern, findIn); // 打印false

		// 练习8：找到至少有三个单词，本题接着上面练习7
		System.out.println("练习8：");
		pattern = "(.*\\b\\w+\\b.*){3,}"; // \W表示[^0-9a-zA-Z],
											// \b表示单词分割，其本身不表示任何字符，而是表示“一个位置”，这个位置要么前面是[_0-9a-zA-Z]后面不是[_0-9a-zA-Z],要么后面是前面不是

		findIn = "hellow world";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "hellow world wellcome";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习9：替换
		System.out.println("练习9：");
		pattern = "(.)(.)\\2\\1";
		findIn = "abba";
		Matcher m = Pattern.compile(pattern).matcher(findIn);
		while (m.find()) {
			// 利用stringbuilder的方法进行指定位置的替换
			StringBuilder temp = new StringBuilder(findIn.substring(m.start(),
					m.end()));
			temp.replace(1, 2,
					String.valueOf((char) (temp.charAt(1) ^ temp.charAt(3))));
			temp.replace(3, 4,
					String.valueOf((char) (temp.charAt(1) ^ temp.charAt(3))));
			temp.replace(1, 2,
					String.valueOf((char) (temp.charAt(1) ^ temp.charAt(3))));
			StringBuilder sb = new StringBuilder(findIn);
			sb.replace(m.start(), m.end(), temp.toString());
			findIn = sb.toString();
		}
		System.out.println("替换后的字符串：" + findIn);

		// 练习10：利用string.replace 替换位指定的字符串
		System.out.println("练习10：");
		pattern = "(.)(.)\\2\\1";
		findIn = "abbabaab";
		findIn = findIn.replaceFirst(pattern, "替换的部分"); // 替换匹配的第一个子串
		System.out.println("替换后的字符串：" + findIn);

		// 练习11： 找到前面有<a>标签后面没有</a>的“hellow”
		System.out.println("练习11：");
		pattern = "<a>(hellow)(?!</a>)";
		findIn = "<a>hellow</a>";
		printMatchResult(pattern, findIn); // 打印false
		findIn = "<a>hellow";
		printMatchResult(pattern, findIn); // 打印true
		findIn = "hellow";
		printMatchResult(pattern, findIn); // 打印false
		findIn = "hellow</a>";
		printMatchResult(pattern, findIn); // 打印false

		// 练习12： 区分 ?: 与 ?=
		System.out.println("练习12：");
		System.out.println("?=：");
		pattern = "hellow.*(?=world)"; // ?= 跟
										// ?!都是非获取性匹配，就是说不会获取后面的world，匹配得到的只是hellow
		findIn = "hellow world";
		printMatchSubString(pattern, findIn);
		System.out.println("?:：");
		pattern = "hellow.*(?:world)"; // 会获取到 world， 得到的匹配字符串是 hellow world。
										// 但是跟 ?= ?!一样，不会返回这个分组的匹配结果
		printMatchSubString(pattern, findIn);

		// 练习13： 非贪婪匹配
		System.out.println("练习13：");
		System.out.println("贪婪匹配：");
		pattern = "<input.*hidden.*/>"; // 这里面的 .*会“尽可能的”匹配字符
		findIn = "<input type=\"hidden\" /><input type='hidden' />";
		printMatchSubString(pattern, findIn);
		System.out.println("非贪婪匹配：");
		pattern = "<input.*?hidden.*?/>"; // .*? 会尽可能少的匹配
		printMatchSubString(pattern, findIn);

		// 练习14： 接练习13，添加条件，input内部不能有其他标签
		System.out.println("练习14：");
		pattern = "<input.*?hidden.*?(/>|>(?!<(.+).*(/>|>.*</\\3>))</input>)"; //
		findIn = "<input type=\"hidden\" /><input type='hidden'><a href='www.baidu.com'>百度</a></input>"
				+ "<input type='hidden'></input>";
		printMatchSubString(pattern, findIn);

		// 练习15：判断是否存在配对标签，例如<input /> <input></input>
		System.out.println("练习15：");
		pattern = "<(.+?).*?>.*?</\\1>|<[^>]+?/>"; // 这里没有考虑标签的嵌套问题，例如<div><div></div></div>会导致第一个跟第三个div结合
		findIn = "<hellow>";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "hellow";
		printMatchResult(pattern, findIn); // 没找到，打印false
		findIn = "<input>hellow</input>";
		printMatchResult(pattern, findIn); // 找到，打印true
		findIn = "<a>hellow</>";
		printMatchResult(pattern, findIn); // 打印false
		findIn = "<a href='baidu.com'/>";
		printMatchResult(pattern, findIn); // 找到，打印true

		// 练习16： 测试 + * 的贪婪性
		System.out.println("练习16.：");
		System.out.println("+?：");
		pattern = ".+?"; // 非贪婪，下面依次打印 "h" "e" .... "w"
		findIn = "hellow";
		printMatchSubString(pattern, findIn);

		System.out.println("+：");
		pattern = ".+"; // 贪婪的，会尽可能多的匹配，打印"hellow"
		printMatchSubString(pattern, findIn);

		System.out.println("*?：");
		pattern = ".*?"; // 非贪婪，这个比较特殊，会打印7个“”（原因是每次找到空字符串已经匹配，并且m.find()会将内部指针下一一位（具体内部细节不是很清楚））
		printMatchSubString(pattern, findIn);

		System.out.println("*：");
		pattern = ".*"; // 贪婪的，会尽可能多的匹配，打印"hellow" 跟""(因为空字符串也能匹配)
		printMatchSubString(pattern, findIn);

		
	}

	/**
	 * 打印是否找到了对应的匹配，如果找到打印true 否则false
	 * 
	 * @param pattern
	 *            匹配规则
	 * @param findIn
	 *            在这个字符串里面查找
	 */
	static void printMatchResult(String pattern, String findIn) {
		Pattern p = Pattern.compile(pattern); // 模式
		Matcher m = p.matcher(findIn);
		boolean toPrint = m.find();
		System.out.println(toPrint);
	}

	static void printMatchSubString(String pattern, String findIn) {
		Pattern p = Pattern.compile(pattern); // 模式
		Matcher m = p.matcher(findIn);
		int iCount = 0;
		while (m.find()) {
			System.out.println("start: " + m.start() + " end: " + m.end()
					+ "  value: " + m.group());
			iCount++;
		}
		System.out.println("一共找到 " + iCount + " 条记录");
	}
}
