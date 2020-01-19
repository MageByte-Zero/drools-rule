package com.zero.framework.droolsrule.model;

import lombok.Data;

@Data
public class People {

    private int sex;

    private String name;

    private Integer age;

    private String drlType;

    private String sexString;

    public People(int sex, String name, String drlType) {
        this.sex = sex;
        this.name = name;
        this.drlType = drlType;
    }

    public People() {
    }
}