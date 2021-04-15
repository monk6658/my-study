package com.pattern.observer;

public class Test {

    public static void main(String[] args) {
        ProductList productList = ProductList.getInstance();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        JingDongObserver jingDongObserver = new JingDongObserver();
        productList.addObserver(taoBaoObserver);
        productList.addObserver(jingDongObserver);
        productList.addProduct("新产品1");
        
    }

}
