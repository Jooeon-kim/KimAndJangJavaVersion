package com.example.demo1;

import java.util.ArrayList;

public class Vip {
    String phone;
    String name;
    int point = 1000;
    ArrayList<Vip> vips = new ArrayList<>();
    Vip(){
        this.vips.add(new Vip("김주언","01099605629"));
        this.vips.add(new Vip("홍길동","01099605628"));
    }
    Vip(String name,String phone){
        this.phone = phone;
        this.name = name;
    }
    boolean searchVipPhone(String phone){
        for(Vip v : this.vips){
            if(phone.equals(v.phone)){
                return true;
            }
        }return false;
    }
    Vip setVip(String phone){
       return this.vips.stream().filter(e->e.phone.equals(phone)).findFirst().orElse(null);
    }

}
