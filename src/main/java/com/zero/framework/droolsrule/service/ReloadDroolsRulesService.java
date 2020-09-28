package com.zero.framework.droolsrule.service;

import com.zero.framework.droolsrule.model.Rule;
import com.zero.framework.droolsrule.repository.RuleRepository;
import com.zero.framework.droolsrule.utils.KieUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReloadDroolsRulesService {

    @Autowired
    private RuleRepository ruleRepository;

    public void reload() {
       loadContainerFromString(loadRules());
    }

    private List<Rule> loadRules() {
        return ruleRepository.findAll();
    }

    private void loadContainerFromString(List<Rule> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (Rule rule : rules) {
            String drl = rule.getContent();
            kfs.write("src/main/resources/rules/" + rule.getRuleKey() + ".drl", drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs).buildAll();

        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();

        KieUtils.setKieContainer(ks.newKieContainer(ks.getRepository().getDefaultReleaseId()));
        System.out.println("Time to load container: " + (endTime - startTime) + " ms");
    }
}