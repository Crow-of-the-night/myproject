package org.sheehan.fortune.entity;

public class Person {
    private int num;
    private String name;
    private int arraylength;

    public int getArraylength() {
        return arraylength;
    }

    public void setArraylength(int arraylength) {
        this.arraylength = arraylength;
    }

    public Person() {
    }

    public Person(int num, String name, int arraylength) {
        this.num = num;
        this.name = name;
        this.arraylength = arraylength;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
