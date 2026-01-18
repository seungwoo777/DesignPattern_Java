package com.pattern.creational.factorymethod.factory;

import com.pattern.creational.factorymethod.Whiskey;

import java.math.BigDecimal;

// Creator -> 팩토리 추상 클래스
// 팩토리 메소드: 구체적인 객체 생성 종류는 각 서브 클래스에 위임
public abstract class WhiskeyFactory {

    // protected로 선언하여 외부에 노출이 안된다.
    abstract protected Whiskey createWhiskey(String name, BigDecimal price);

    // 객체 생성 전처리 / 후처리 메소드(final로 Overriding 방지, Template 화)
    public final Whiskey distillation(String name, BigDecimal price) {
        Whiskey whiskey = createWhiskey(name, price);
        System.out.println("Created -> " + whiskey.getType());

        return whiskey;
    }
}
