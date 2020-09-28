package com.zero.framework.droolsrule.fact;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lijianqing
 * @date 2020/9/28 14:34
 */
@Data
public class AddressCheckFact implements Serializable {

    private String postcode;

    private String street;

    private String state;

    private String ruleKey;

    /**
     * true:通过校验；false：未通过校验
     */
    private Boolean fact = false;
}
