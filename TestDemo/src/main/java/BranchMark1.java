import java.net.URL;
import java.net.URLConnection;

public class BranchMark1 {
    private static volatile int i = 0;

    public static void main(String[] args) throws Exception {

        /*while (true) {
            long start = System.currentTimeMillis();
            callShellByExec("curl localhost:18000/api/v1/image/check?debug=abc -X POST -d '{\"type\":\"1\",\"appId\":\"91000001\",\"cache\":\"1\",\"image\":\"https://scpic.chinaz.net/files/pic/pic9/202204/apic40322.jpg\",\"debug\":\"abc\"}' -H  \"X-AppId:91000001\" -H \"Content-Type: application/json;charset=UTF-8\"");
            System.out.println(System.currentTimeMillis() - start);
        }*/

        String url = "";
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();

    }

}
