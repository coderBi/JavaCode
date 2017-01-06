/**
 * 注意： 
 * 	  ①：dos下面两个空格的占位跟一个中文一样，所以这里每一行内部多出的是两个空格。
 * 	  ②：myeclipse里面处理过 中英文的空格显示，所以效果不一样。所以这个程序要得在dos控制台下面显示效果。要在myeclipse控制台显示效果
 * 		目前没有看到相关配置（主要是空格被不同的处理的）
 */
package it.learn.sun;
import java.util.Scanner;

/**
 * <p>Title:Main</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-12-14
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // \t 水平制表符，相当于给了8 个占位符， 调到下一个制表符，如果前面字符串长度等于8倍数,这个\t就是在后面追加 8个空格
		//System.out.print("********---\t我不是二百\t你是二百五\n");System.out.print("\t傻X五\t二百五");
		///System.out.printf("%-2s%-2s", "中", "e");
		
		
		String str = "软工二零一六李四";
		Integer rows = null;
		Scanner scanner = new Scanner(System.in);
		rows = scanner.nextInt();
		
		Integer blankInside = 0;
		for(int i = 0; i < rows - 1; ++i){
			Integer blankLeft = rows -1 - i;
			for(int j = 0; j < blankLeft; ++j)
				System.out.printf("  "); //两个空格
			char cToPrint = str.charAt(i % str.length());
			System.out.print(cToPrint);
			for(int j = 0; j < blankInside; ++j)
				System.out.print("  ");
			if(blankInside != 0)
				System.out.print(cToPrint);
			System.out.println();
			if(i == 0){
				blankInside = 1;
			} else{
				blankInside += 2;
			}
		}
		//System.out.
		for(int i = (rows - 1) % str.length(), j = 0; j < blankInside + 2; j++, i = ++i < str.length() ? i : 0 ){
			System.out.print(str.charAt(i));
		}
	}

}
