import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 */

/**
 * <p>Title:Main</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-12-21
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "激动暗室逢灯美国留学积分都是<a>美国留学</a>用正则匹配美国留学，但是不匹配a标签中的";
		String regex = "(?!<a>)(美国留学)(?!</a>)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		int count = 0;
		while(m.find()){ //通过打印发现匹配到了不在a标签里面的“美国留学”
			System.out.println("{{第" + (++count) +"个匹配，内容   : "+ m.group()
					+ "}  {" + "start index: " + m.start()
					+ "}  {" + "end index: " + m.end() + "}}");
		}
	}

}
