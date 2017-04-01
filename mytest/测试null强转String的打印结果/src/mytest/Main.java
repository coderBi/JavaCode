/**
 *
 */
package mytest;

/**
 * <p>Title:Main</p>
 * <p>Description:测试null强转String的打印结果</p>
 * @author Edwin
 * @since 2017-3-6
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = (String)null;
		System.out.println(str);
		
		//将str追加一部分信息  再进行打印
		str += "追加的信息";
		System.out.println(str); //可以看到这里打印的结果是 "null追加的信息"。也即是将null可以强转成String，但是转成了 "null"
	}

}
