package com.pattern.creational.abstractfactory;

import com.pattern.creational.abstractfactory.factory.bartenderAFactory;
import com.pattern.creational.abstractfactory.factory.bartenderBFactory;
import com.pattern.creational.abstractfactory.type.BlendedWhiskey;
import com.pattern.creational.abstractfactory.type.SingleMoltWhiskey;

public class Client {
    public static void main(String[] args) {
        WhiskeyBar whiskeyBar = new bartenderAFactory();

        // A 바텐더가 글렌피딕 추천(클라이언트는 구체적인 구현을 모르고 인터페이스에 의존한다.)
        SingleMoltWhiskey glenfiddich = whiskeyBar.recommendSingleMoltWhiskey();
        System.out.println(glenfiddich.getClass().getName());

        whiskeyBar = new bartenderBFactory();

        // B 바텐더가 조니워커 추천(클라이언트는 구체적인 구현을 모르고 인터페이스에 의존한다.)
        BlendedWhiskey johnniewalker = whiskeyBar.recommendBlendedWhiskey();
        System.out.println(johnniewalker.getClass().getName());
    }
}
