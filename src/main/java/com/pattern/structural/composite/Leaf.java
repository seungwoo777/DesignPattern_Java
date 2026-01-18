package com.pattern.structural.composite;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Leaf implements Component {
    @Override
    public void operation() {
        System.out.println(this + " call");
    }
}
