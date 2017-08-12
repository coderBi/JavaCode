/**
 *
 */
package privilege.test;

/**
 * <p>Title:Test</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2017-5-26
 * @version 1.0
 */
public class Test extends Parent{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		//可以正常打印   实际上默认权限是包内访问  控制权在private与protected之间
		test.printMessage();
		test.protectedPrint();
		//直接编译错误，可以看到提示 没有访问权限
		//test.privPrint();
	}

}

class Parent{
	void printMessage(){
		System.out.println("message text");
	}
	private void privPrint(){
		System.out.println("private func");
	}
	protected void protectedPrint(){
		System.out.println("protected func");
	}
}
