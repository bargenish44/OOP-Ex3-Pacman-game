package Map;

public class test {
	public static void main(String[] args) {
		Runnable target = new Runnable() {

			@Override
			public void run() {
				for(int i=10;i>=1;i--) {
					try {
						Thread.sleep(490);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i);
				}
			}
		};
		Thread thread=new Thread(target);
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("hi");
	}
}

