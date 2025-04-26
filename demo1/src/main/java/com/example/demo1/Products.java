package com.example.demo1;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Products {
    ArrayList<Drink> Coffee = new ArrayList<>();
    ArrayList<Drink> nonCoffee = new ArrayList<>();
    ArrayList<Drink> cartList = new ArrayList<>();

    Products() {
        Coffee.add(new Drink("/img/Coffee1.jpg", "아메리카노", 1000));
        Coffee.add(new Drink("/img/Coffee2.jpg", "뜨끈한아메리카노", 900));
        Coffee.add(new Drink("/img/Coffee3.jpg", "아이스라뗴", 700));
        Coffee.add(new Drink("/img/Coffee4.jpg", "따뜻한라떼", 500));
        nonCoffee.add(new Drink("/img/Ade1.jpg", "청포도에이드", 1000));
        nonCoffee.add(new Drink("/img/Ade2.jpg", "레몬에이드", 2000));
        nonCoffee.add(new Drink("/img/Ade3.jpg", "자몽에이드", 3000));
        nonCoffee.add(new Drink("/img/Latte2.jpg", "율무차", 4000));

    }

    void addCart(Drink other) {
        if (this.cartList.stream().noneMatch(e -> e.name.equals(other.getName()))) {
            Drink drink = new Drink(other);
            this.cartList.add(drink);
        } else {
            Drink drink = this.cartList.stream()
                    .filter(e -> e.name.equals(other.getName()))
                    .findFirst()
                    .orElse(null);
            if (drink != null) {
                drink.setAmountPlus();
            }

        }
    }

    void deleteCart(Drink drink) {
        this.cartList.remove(drink);
    }

    int countTotal(){
       int count = 0;
        for(Drink d:this.cartList){
           count+= d.amount*d.price;
       }return count;
    }
}

class Drink {
    Image img;
    String name;
    int price;
    int amount = 1;

    Drink(String imgPath, String name, int price) {
        this.img = new Image(getClass().getResource(imgPath).toExternalForm());
        this.name = name;
        this.price = price;
    }

    Drink(Drink other) {
        this.img = other.getImg();
        this.name = other.getName();
        this.price = other.getPrice();
        this.amount = 1;
    }

    public Image getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setAmountMinor() {
        this.amount--;
    }

    public void setAmountPlus() {
        this.amount++;
    }
}