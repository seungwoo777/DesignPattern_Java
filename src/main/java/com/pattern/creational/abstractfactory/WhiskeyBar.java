package com.pattern.creational.abstractfactory;

import com.pattern.creational.abstractfactory.type.BlendedWhiskey;
import com.pattern.creational.abstractfactory.type.SingleMoltWhiskey;

public interface WhiskeyBar {

    SingleMoltWhiskey recommendSingleMoltWhiskey();
    BlendedWhiskey recommendBlendedWhiskey();

}
