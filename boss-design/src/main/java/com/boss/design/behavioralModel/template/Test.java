package com.boss.design.behavioralModel.template;

public class Test {
    public static void main(String[] args) {
        BuyShoes buyShoes = new BuyShoes();
        buyShoes.buy();
        BuyTshirt buyTshirt = new BuyTshirt();
        buyShoes.buy();
    }
}
