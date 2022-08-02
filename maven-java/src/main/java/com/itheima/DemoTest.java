package com.itheima;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class DemoTest {
    public static void main(String[] args) throws ParseException {
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String b64encoded = Base64.getEncoder().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println("===================");
        byte[] output = Base64.getDecoder().decode("5Lit");
        System.out.println(Arrays.toString(output)); // [-28, -72, -83]
        System.out.println(Integer.toHexString("hello".hashCode()));
    }
}
