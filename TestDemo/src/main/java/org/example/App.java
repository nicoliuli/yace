package org.example;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Hello world!
 */
public class App {


    /*public static void main(String[] args) throws IOException {
        String exec = "curl localhost:18000/api/v1/image/check -X POST -d '{\"type\":\"1\",\"appId\":\"91000001\",\"cache\":\"1\",\"image\":\"https://img2.woyaogexing.com/2022/04/20/0abbbfdf39ff4f2dbd4d32959d890e61!400x400.jpeg\"}' -H  \"X-AppId:91000001\" -H \"Content-Type: application/json;charset=UTF-8\"";
        int i = 0;
        while (true){
            Runtime.getRuntime().exec(exec);
            System.out.println(i++);
        }

    }*/

    private final static Scheduler scheduler = Schedulers.newBoundedElastic(Runtime.getRuntime().availableProcessors(), 10000, "image-service-scheduler");

    /*public static void main0(String[] args) {
        String secret = "ydcypszbjssbjh";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,10);
        Map<String,Object> headers = new HashMap<>();
        headers.put("appId","123");
        headers.put("device","android");

        String token = JWT.create()
                .withHeader(headers)
                .withClaim("userId",21)
                .withClaim("userName","zhangsan")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(secret));

        System.out.println(token);

        // token = token + "1";
        JWTVerifier jwt = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT decodedJWT = jwt.verify(token);
        Claim userId = decodedJWT.getClaim("userId");
        Claim userName = decodedJWT.getClaim("userName");

        System.out.println(userId.asInt());
        System.out.println(userName.asString());
        String header = decodedJWT.getHeader();
        System.out.println(header);
        Claim appId = decodedJWT.getHeaderClaim("appId");
        Claim device = decodedJWT.getHeaderClaim("device");
        System.out.println(appId.asString());
        System.out.println(device.asString());
    }*/

    public static void main(String[] args) {
        /*Mono.just("hello").map(a -> {
            return a + " world";
        }).subscribe(System.out::println);

        Mono.zip(one(), two()).subscribe(System.out::println);

        Mono.zip(one(), two()).zipWhen(tuple -> {
            return Mono.just("ccc");
        }).subscribe(System.out::println);

        Mono.zip(one(), two()).zipWith(Mono.just("ccc")).subscribe(System.out::println);

        Mono.from(Mono.just("ddd")).zipWith(Mono.just("eee")).zipWith(Mono.just("fff")).subscribe(System.out::println);

        long start = System.currentTimeMillis();
        Mono.zip(one(), two()).publishOn(scheduler).map(tuple -> {
            return three(tuple.getT1());
        }).subscribe(System.out::println);

        Mono.zip(one(), two()).publishOn(scheduler).map(tuple -> {
            return three(tuple.getT1());
        }).subscribe(System.out::println);

        Mono.zip(one(),two()).doOnNext(t -> {});
        System.out.println(System.currentTimeMillis() - start);*/

        Mono.just("just").flatMap(e -> fun(e, "aaa", "bbb")).subscribe(System.out::println);


    }

    private static Mono fun(String s, String s1, String s2) {
        System.out.println("s = " + s + ",s1 = " + s1 + ", s2 = " + s2);
        return Mono.just("fun");
    }


    private static Mono<String> common(String str) {
        return Mono.just(str).publishOn(scheduler).map(s -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return s;
        });
    }

    private static Mono<String> one() {
        return common("one");
    }

    private static Mono<String> two() {
        return common("two");
    }

    private static String three(String str) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return str;
    }
}
