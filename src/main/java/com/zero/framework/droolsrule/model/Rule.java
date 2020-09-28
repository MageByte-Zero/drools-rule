package com.zero.framework.droolsrule.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author lijianqing
 * @date 2020/9/28 17:12
 */
@Data
@Entity
public class Rule implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String ruleKey;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false)
    private String updateTime;

    @Column(nullable = false)
    private String createTime;
}
