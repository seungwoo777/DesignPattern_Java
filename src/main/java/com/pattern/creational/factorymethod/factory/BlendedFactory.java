package com.pattern.creational.factorymethod.factory;

import com.pattern.creational.factorymethod.type.BlendedWhiskey;
import com.pattern.creational.factorymethod.Whiskey;

import java.math.BigDecimal;

// Concrete Creator -> 팩토리
public class BlendedFactory extends WhiskeyFactory {

    @Override
    protected Whiskey createWhiskey(String name, BigDecimal price) {
        return new BlendedWhiskey(name, price);
    }
}
