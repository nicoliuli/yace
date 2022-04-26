package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base {
    public static void main(String[] args) {

        System.out.println(imgToBase64String("/Users/admin/Desktop/mmexport1650953597941.png"));
    }

    public static String imgToBase64String(String imgFile) {
        InputStream inputStream;
        byte[] data = null;
        // 读取图片字节数组
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];

            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(data);
    }

}
