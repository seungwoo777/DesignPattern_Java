package com.pattern.structural.composite;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Composite implements Component {
    // Leaf와 Composite 객체 모두 저장하여 관리하는 리스트
    List<Component> componentList = new ArrayList<>();

    public void add(Component component) {
        componentList.add(component);
    }

    public void remove(Component component) {
        componentList.remove(component);
    }

    @Override
    public void operation() {
        System.out.println(this + " call");

        // 내부 리스트를 순회하여 단일 Leaf이면 값을 출력하고 또다른 서브 복합 객체이면 다시 그 내부를 순회하는 재귀 함수로 동작한다.
        componentList.forEach(Component::operation);
    }
}
