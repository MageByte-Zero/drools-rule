package com.zero.framework.droolsrule.utils;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * KieUtils 类中存储了对应的静态方法和静态属性，供其他使用的地方获取和更新。
 */
public class KieUtils {

    private KieUtils() {}

    private static KieContainer kieContainer;

    private static KieSession kieSession;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
        kieSession = kieContainer.newKieSession();
    }

    public static KieSession getKieSession() {
        return kieSession;
    }

    public static void setKieSession(KieSession kieSession) {
        KieUtils.kieSession = kieSession;
    }
}