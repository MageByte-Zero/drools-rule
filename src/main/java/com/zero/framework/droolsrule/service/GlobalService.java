package com.zero.framework.droolsrule.service;

import com.zero.framework.droolsrule.model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalService {

    public List<People> getPeoples() {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People(1, "春", "global"));
        peoples.add(new People(2, "夏", "global"));
        peoples.add(new People(3, "秋", "global"));
        peoples.add(new People(4, "冬", "global"));
        peoples.add(new People(5, "达", "global"));
        return peoples;
    }
}