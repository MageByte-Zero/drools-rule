package com.zero.framework.droolsrule.model;

import lombok.Data;

@Data
public class NumCount {

    private int count;

    public void plus() {
        count = count + 1;
    }

}