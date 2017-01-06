import java.util.ArrayList;
import java.util.List;

/**
 *
 */

/**
 * <p>
 * Title:AutoIncrementAndPrint
 * </p>
 * <p>
 * Description:测试2016美团笔试题三第2题可能的打印效果
 * </p>
 * 
 * @author Edwin
 * @since 2016-10-4
 * @version 1.0
 */
public class AutoIncrementAndPrint {
	public static int a = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			a = 1;
			List<Thread> threads = new ArrayList<Thread>();
			for (int i = 0; i < 2; ++i) {
				threads.add(startNewThread());
			}

			// 等待子线程执行完毕
			for (Thread thread : threads) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("");
		}
	}

	public static Thread startNewThread() {
		Thread thread = getNewThread();
		thread.start();
		return thread;
	}

	public static Thread getNewThread() {
		return new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000); // 由于一个线程的操作太少导致下一个进程还没启动或者在启动过程中上一个已经执行完了，所以这里用sleep暂停一下
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++a;
				System.out.print(a + " ");
			}
		});
	}

}
