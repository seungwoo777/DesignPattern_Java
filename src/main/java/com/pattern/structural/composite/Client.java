package com.pattern.structural.composite;

public class Client {
    public static void main(String[] args) {
        // 1. 최상위 복합체 생성
        Composite parent = new Composite();

        // 2. 최상위 복합체에 저장할 Leaf와 또 다른 서브 복합체 생성
        Leaf parentLeaf = new Leaf();
        Composite child = new Composite();

        // 3. 최상위 복합체에 객체들을 등록
        parent.add(parentLeaf);
        child.add(child);
        
        // 4. 서브 복합체에 저장할 Leaf 생성
        Leaf childLeaf1 = new Leaf();
        Leaf childLeaf2 = new Leaf();
        Leaf childLeaf3 = new Leaf();

        // 5. 서브 복합체에 객체들을 등록
        child.add(childLeaf1);
        child.add(childLeaf2);
        child.add(childLeaf3);

        // 6. 최상위 복합체에 모든 자식 노드 들을 출력
        parent.operation();
    }
}
