import org.example.conf.Cli;

public class BranchMark {
    private static volatile int i = 0;

    public static void main(String[] args) throws Exception {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(999L);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

        Cli cli = new Cli();
        String s = cli.doPostJson("http://localhost:18000/api/v1/image/check?debug=abc", "{\"type\":\"1\",\"appId\":\"91000001\",\"cache\":\"1\",\"image\":\"https://scpic.chinaz.net/files/pic/pic9/202204/apic40322.jpg\",\"debug\":\"abc\"}");
        System.out.println(s);

        /*while (true) {
            long start = System.currentTimeMillis();
            callShellByExec("curl localhost:18000/api/v1/image/check?debug=abc -X POST -d '{\"type\":\"1\",\"appId\":\"91000001\",\"cache\":\"1\",\"image\":\"https://scpic.chinaz.net/files/pic/pic9/202204/apic40322.jpg\",\"debug\":\"abc\"}' -H  \"X-AppId:91000001\" -H \"Content-Type: application/json;charset=UTF-8\"");
            System.out.println(System.currentTimeMillis() - start);
        }*/
    }

    public static void callShellByExec(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            i++;
        } catch (Throwable e) {

        }
    }
}
