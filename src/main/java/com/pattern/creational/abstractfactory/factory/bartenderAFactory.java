package com.pattern.creational.abstractfactory.factory;

import com.pattern.creational.abstractfactory.*;
import com.pattern.creational.abstractfactory.name.Ballantine;
import com.pattern.creational.abstractfactory.name.Glenfiddich;
import com.pattern.creational.abstractfactory.type.BlendedWhiskey;
import com.pattern.creational.abstractfactory.type.SingleMoltWhiskey;

// A 바텐더가 추천하는 글렌피딕, 발렌타인 객체를 생성하는 공장
public class bartenderAFactory implements WhiskeyBar {

    @Override
    public SingleMoltWhiskey recommendSingleMoltWhiskey() {
        return new Glenfiddich();
    }

    @Override
    public BlendedWhiskey recommendBlendedWhiskey() {
        return new Ballantine();
    }
}
