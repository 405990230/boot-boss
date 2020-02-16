package com.boss.design.creationMode.builder;

/**
 * Created by yd on 2019/3/26.
 */
public class PersonDirector {
    public PersonDress constructPerson(PersonBuilder personBuilder) {
        //按照穿衣顺序 裤子--->衣服--->鞋子 的顺序装扮
        personBuilder.buildTrousers();
        personBuilder.buildJacket();
        personBuilder.buildShoes();
        return personBuilder.buildPersonDress();
    }

    //测试
    public static void main(String[] args) {
        PersonDirector personDirector = new PersonDirector();
        PersonDress personDress = personDirector.constructPerson(new StarBuilder());
        System.out.println(personDress.getShoes());
        System.out.println(personDress.getTrousers());
        System.out.println(personDress.getJacket());
    }
}
