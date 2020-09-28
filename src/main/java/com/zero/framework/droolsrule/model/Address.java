package com.zero.framework.droolsrule.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lijianqing
 * @date 2020/9/28 12:02
 */
@Data
public class Address implements Serializable {
    private String postcode;

    private String street;

    private String state;
}
