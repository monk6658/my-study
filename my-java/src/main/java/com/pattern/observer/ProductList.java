package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ProductList extends Observable {

    private List<String> productList = null;// 产品列表

    private volatile static ProductList instance;// 类唯一变量

    private ProductList(){

    }

    /**
     * 获取唯一实例
     * @return 产品列表唯一实例
     */
    public static ProductList getInstance(){
        if(instance == null){
            instance = new ProductList();
            instance.productList = new ArrayList<>();
        }
        return instance;
    }

    /**
     * 增加观察者
     * @param observer 观察者
     */
    public void addProductListObserver(Observer observer){
        this.addObserver(observer);
    }

    /**
     * 新增产品
     * @param newProduct 新产品
     */
    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品：" + newProduct);
        this.setChanged();//设置被观察对象发生变化
        this.notifyObservers(newProduct);//通知观察者，并传递新产品
    }


}
