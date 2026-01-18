package com.pattern.creational.factorymethod;

import com.pattern.creational.factorymethod.factory.WhiskeyFactory;
import com.pattern.creational.factorymethod.factory.BlendedFactory;
import com.pattern.creational.factorymethod.factory.SingleMoltFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Client
 *   ↓
 * WhiskeyFactory(Creator)
 *   ↓
 * createWhiskey <- Factory Method
 *   ↓
 * Whiskey(Interface)
 *   ↓
 * SingleMaltWhiskey / BlendedWhiskey
 */
public class Client {
    public static void main(String[] args) {
        WhiskeyFactory singleMoltFactory = new SingleMoltFactory();
        WhiskeyFactory blendedFactory = new BlendedFactory();

        Whiskey glenfiddich = singleMoltFactory.distillation("Glenfiddich 15", BigDecimal.valueOf(110_000));
        Whiskey johnnieWalker = blendedFactory.distillation("JohnnieWalker Blue label", BigDecimal.valueOf(330_000));

        System.out.println(glenfiddich.getName() + " / " + glenfiddich.getType() + " / " + glenfiddich.getPrice());
        System.out.println(johnnieWalker.getName() + " / " + johnnieWalker.getType() + " / " + johnnieWalker.getPrice());
    }
}
