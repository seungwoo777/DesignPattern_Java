package com.pattern.creational.builder;


import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        Whiskey whiskey = Whiskey.builder()
                .name("조니워커블루")
                .type("블랜디드")
                .price(BigDecimal.valueOf(330_000))
                .taste("바닐라")
                .year("NAS")
                .build();
        System.out.println(whiskey);
    }


}
