package com.zero.framework.droolsrule;

import com.zero.framework.droolsrule.model.Cat;
import com.zero.framework.droolsrule.model.People;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DroolsRuleApplicationTests {

    @Autowired
    private KieSession session;


    @Test
    public void people() {

        People people = new People();
        people.setName("达");
        people.setSex(1);
        people.setDrlType("people");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
        System.out.println(people.toString());
    }

    @Test
    public void collect() {
        session.insert(new People(1, "达", "collect"));
        session.insert(new People(0, "秋", "collect"));
        session.insert(new People(0, "春", "collect"));
        session.insert(new People(1, "夏", "collect"));
        session.insert(new People(0, "冬", "collect"));
        session.insert(new People(3, "金", "collect"));

        session.fireAllRules();//执行规则
    }

    @Test
    public void cat() {
        Cat cat = new Cat();
        cat.setName("金");
        cat.setSex(1);
        session.insert(cat);//插入
        session.fireAllRules();//执行规则
    }

    @Test
    public void update() {

        People people = new People();
        people.setName("达");
        people.setSex(0);
        people.setAge(17);
        people.setDrlType("update");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
        System.out.println("test执行====" + people.toString());
    }

    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }

}
