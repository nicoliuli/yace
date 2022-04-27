import org.example.conf.Cli;

public class BranchMark {
    private static volatile int count = 0;
    private static int preCount = 0;

    private static volatile int nThread = 0;

    public static void main(String[] args) throws Exception {
        nThread = Integer.parseInt(args[0]);
        Runnable r = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(999L);
                        System.out.println("当前线程数：" + nThread + ",当前请求量：" + (count - preCount));
                        preCount = count;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

        Cli cli = new Cli();
        for (int i = 0; i < nThread; i++) {
            new Thread(() -> {
                while (true) {
                    String s = cli.doPostJson();
                    count++;
                    if (!s.contains("\"code\":0")) {
                        System.out.println(s);
                    }
                }
            }).start();
        }

    }
}
