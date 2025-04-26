package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloApplication extends Application {
    Products products = new Products();
    VBox cartList;
    Label total  = new Label("총 결제금액: 0원");
    Label total2 = new Label("총 결제금액: 0원");
    Scene scene2;
    VBox cartVList = new VBox();
    vip vip = new vip();
    @Override
    public void start(Stage stage) throws Exception {
        cartList = new VBox();
        cartList.setPrefSize(400, 300);
        cartVList.setPrefSize(600,600);
        total.setPrefSize(300, 50);  // <<< 크기 강제 설정
        total.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-font-size: 20px; -fx-alignment: center;");
        //+++++++++++++++++++++++++++++++++++Scene 첫번째++++++++++++++++++++++++++++++++++++++++++++++++
        Image image = new Image(getClass().getResource("/img/kimjang2.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(800);
        Label label = new Label();
        label.setGraphic(imageView);
        Scene scene = new Scene(label,600,800);
        //+++++++++++++++++++++++++++++++++++Scene 첫번째++++++++++++++++++++++++++++++++++++++++++++++++




        //+++++++++++++++++++++++++++++++++++Scene 두번째++++++++++++++++++++++++++++++++++++++++++++++++

        HBox hBox = new HBox(40);
        hBox.setPrefHeight(200);
        hBox.setPrefWidth(600);
        hBox.setAlignment(Pos.CENTER);

        Label coffeeLabel = new Label("커피");
        coffeeLabel.setPrefSize(80,30);
        coffeeLabel.setAlignment(Pos.CENTER);
        coffeeLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        Label nonCoffeeLabel = new Label("에이드");
        nonCoffeeLabel.setPrefSize(80,30);
        nonCoffeeLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        nonCoffeeLabel.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(coffeeLabel,nonCoffeeLabel);

        HBox coffeeMenu = new HBox(20);
        coffeeMenu.setStyle("-fx-border-color: black; -fx-border-width: 2px; ");
        coffeeMenu.setPrefHeight(300);



        for(Drink d : products.Coffee){
            VBox vBox = new VBox(10);
            ImageView imageView1 = new ImageView(d.getImg());
            imageView1.setFitHeight(120);
            imageView1.setOnMouseClicked(e->{
                products.addCart(d);
                updateCart();
            });
            imageView1.setPreserveRatio(true);
            Label name = new Label(d.getName());
            vBox.getChildren().addAll(imageView1,name);
            coffeeMenu.getChildren().add(vBox);
        }
        HBox cartListAndButton = new HBox(20);

        cartListAndButton.getChildren().add(cartList);
        VBox buttons = new VBox(20);
        buttons.setAlignment(Pos.CENTER);
        Button card = new Button("결제하기");
        card.setOnMouseClicked(e->stage.setScene(scene2));
        card.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        buttons.getChildren().addAll(card,this.total2);
        cartListAndButton.getChildren().add(buttons);
        VBox vBox = new VBox(hBox,coffeeMenu,cartListAndButton);
        Scene scene1 = new Scene(vBox,600,800);

        label.setOnMouseClicked(e->{
            stage.setScene(scene1);
        });
        coffeeLabel.setOnMouseClicked(e -> {
            setMenu(products.Coffee, coffeeMenu);
        });

        nonCoffeeLabel.setOnMouseClicked(e -> {
            setMenu(products.nonCoffee, coffeeMenu);
        });
        //+++++++++++++++++++++++++++++++++++Scene 두번째++++++++++++++++++++++++++++++++++++++++++++++++





        //+++++++++++++++++++++++++++++++++++Scene 세번째++++++++++++++++++++++++++++++++++++++++++++++++
        for(Drink d : products.cartList){
            HBox box = new HBox(20);
            ImageView imageView2 = new ImageView(d.getImg());
            imageView2.setFitHeight(80);
            imageView2.setPreserveRatio(true);
            box.getChildren().add(imageView2);
            box.getChildren().addAll(new Label(d.name),new Label(String.valueOf(d.amount*d.price)),new Label(String.valueOf(d.amount)));
            cartVList.getChildren().add(box);
        }


        VBox totalPriceVbox = new VBox();
        totalPriceVbox.getChildren().add(this.total);
        totalPriceVbox.setAlignment(Pos.CENTER);
        totalPriceVbox.setPrefSize(300,200);
        VBox vipAndCard = new VBox();
        Button btVip = new Button("적립하기");

        Button btCard = new Button("결제하기");

        Button btHome = new Button("이전으로");
        btHome.setOnMouseClicked(e->stage.setScene(scene1));

        vipAndCard.setAlignment(Pos.CENTER);
        vipAndCard.getChildren().addAll(btVip,btCard,btHome);
        vipAndCard.setPrefSize(300,300);
        HBox bottom = new HBox();
        bottom.getChildren().add(totalPriceVbox);
        bottom.getChildren().add(vipAndCard);
        bottom.setPrefWidth(600);
        bottom.setMinWidth(600);
        VBox vBoxScene2 = new VBox(20);
        vBoxScene2.getChildren().add(cartVList);
        vBoxScene2.getChildren().add(bottom);

        Scene scene2 = new Scene(vBoxScene2);
        this.scene2 = scene2;
        

        stage.setScene(scene);
        stage.show();
    }
    void updateCart(){
        cartList.getChildren().clear();
        cartVList.getChildren().clear();
        this.total2.setText("총 결제금액: 0");
        for(Drink d: this.products.cartList){

            Label name = new Label("상품이름: "+d.getName()+"가격: "+d.price);
            Button plus = new Button("+");
            plus.setOnMouseClicked(e->{
                d.setAmountPlus();
                updateCart();
            });
            Button minus = new Button("-");
            minus.setOnMouseClicked(e->{
                d.setAmountMinor();updateCart();
                if(d.amount==0){
                    this.products.cartList.remove(d);
                }
                updateCart();
            });
            Label amount = new Label(String.valueOf(d.amount));
            Button delete = new Button("삭제");
            delete.setOnMouseClicked(e->removeCart(d,cartList,total));
            HBox cartitem = new HBox(10);
            cartitem.getChildren().addAll(name,minus,amount,plus,delete);
            cartList.getChildren().add(cartitem);

            HBox box = new HBox(20);
            ImageView imageView2 = new ImageView(d.getImg());
            imageView2.setFitHeight(80);
            imageView2.setPreserveRatio(true);
            box.getChildren().add(imageView2);
            box.getChildren().addAll(new Label(d.name),new Label(String.valueOf(d.amount*d.price)),new Label(String.valueOf(d.amount)));
            cartVList.getChildren().add(box);

            this.total.setText("총 결제금액: "+products.countTotal());
            this.total2.setText("총 결제금액: "+products.countTotal());
        }
    }
    void removeCart(Drink d, VBox cartList,Label total){
        cartList.getChildren().clear();
        products.cartList.remove(d);
       updateCart();

        }
    void setMenu(ArrayList<Drink> drinksMenu, HBox coffeeMenu) {
        coffeeMenu.getChildren().clear(); // 기존 메뉴 지우기
        for (Drink d : drinksMenu) {
            VBox vBox = new VBox(10);
            ImageView imageView = new ImageView(d.getImg());
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(e -> {
                products.addCart(d);
                updateCart();
            });
            Label name = new Label(d.getName());
            vBox.getChildren().addAll(imageView, name);
            coffeeMenu.getChildren().add(vBox);
        }
    }
}