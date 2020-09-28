package com.zero.framework.droolsrule.repository;

import com.zero.framework.droolsrule.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Rule findByRuleKey(String ruleKey);
}