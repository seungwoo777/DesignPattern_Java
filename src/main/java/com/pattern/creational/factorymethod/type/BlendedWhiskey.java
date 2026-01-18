package com.pattern.creational.factorymethod.type;

import com.pattern.creational.factorymethod.Whiskey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

// Concrete Product -> 구현체
@Getter
@AllArgsConstructor
public class BlendedWhiskey implements Whiskey {

    private final String name;
    private final BigDecimal price;

    @Override
    public String getType() {
        return "Blended";
    }
}
