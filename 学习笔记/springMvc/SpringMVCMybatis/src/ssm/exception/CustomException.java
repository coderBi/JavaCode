/**
 *
 */
package ssm.exception;

/**
 * <p>Title:CustomException</p>
 * <p>Description:系统自定义异常，针对预期异常，程序中需要抛出此异常</p>
 * @author Edwin
 * @since 2017-3-1
 * @version 1.0
 */
public class CustomException extends Exception{
	//异常信息
	private String message;
	
	public CustomException(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
}
