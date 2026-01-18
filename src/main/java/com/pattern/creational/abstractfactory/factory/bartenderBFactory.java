package com.pattern.creational.abstractfactory.factory;

import com.pattern.creational.abstractfactory.*;
import com.pattern.creational.abstractfactory.name.JohnnieWalker;
import com.pattern.creational.abstractfactory.name.Macallan;
import com.pattern.creational.abstractfactory.type.BlendedWhiskey;
import com.pattern.creational.abstractfactory.type.SingleMoltWhiskey;

// B 바텐더가 추천하는 맥캘란, 조니워커 객체를 생성하는 공장
public class bartenderBFactory implements WhiskeyBar {

    @Override
    public SingleMoltWhiskey recommendSingleMoltWhiskey() {
        return new Macallan();
    }

    @Override
    public BlendedWhiskey recommendBlendedWhiskey() {
        return new JohnnieWalker();
    }

}
