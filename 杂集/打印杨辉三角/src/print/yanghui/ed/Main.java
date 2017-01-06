/**
 *
 */
package print.yanghui.ed;
import java.util.*;

/**
 * <p>Title:Main</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-12-15
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	static int maxLength;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int rows = scanner.nextInt();
		String[][] result = getYanghuiTrangle(rows); //这里得到的是直角的杨辉三角
		for(int i = 0; i < result.length; ++i){
			for(int j = 0; j <= i; ++j)
				System.out.print(result[i][j]);
			System.out.println();
		}
		System.out.println("-------------------------------------------");
		//打印等腰三角的杨辉三角，这里的方法是在直角的基础上，在每一行左边打印一定数量的空格。
		for(int i = 0; i < result.length; ++i){
			int iBlankLeft = (rows -1 - i) * maxLength / 2 ;
			for(int j = 0; j < iBlankLeft; ++j)
					System.out.print(" ");
			for(int j = 0; j <= i; ++j)
				System.out.print(result[i][j]);
			System.out.println();
		}
		
	}
	
	static String[][] getYanghuiTrangle(int rows){
		int[][] iArr = new int[rows][rows];
		for(int i = 0; i < rows; ++i){ //杨辉三角 第 i层有 i个数字, 将每一层的 第 1 个跟 最后一个赋值 1
			iArr[i][0] = iArr[i][i] = 1; 
		}
		int maxNum = 1;
		for(int i = 2; i < rows; ++i){
			for(int j = 1; j < i; ++j){
				iArr[i][j] = iArr[i-1][j-1] + iArr[i-1][j];  //从第三层开始计算中间值等于两肩之和
				if(maxNum < iArr[i][j])
					maxNum = iArr[i][j];
			}
		}
		String[][] toReturn = new String[rows][rows];
		maxLength = String.valueOf(maxNum).length() + 1; //至少两个数字之间存在一个空格
		for(int i = 0; i < rows; ++i){
			for(int j = 0; j <= i; ++j){
				toReturn[i][j] = "" + iArr[i][j];
				int iBlank = maxLength - toReturn[i][j].length();
				for(int k = 0; k < iBlank; ++k){
					toReturn[i][j] += " ";  //在右边填充空格
				}
			}
		}
		return toReturn;
	}

}
