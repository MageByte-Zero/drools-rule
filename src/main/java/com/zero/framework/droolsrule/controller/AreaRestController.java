package com.zero.framework.droolsrule.controller;

import com.zero.framework.droolsrule.fact.AddressCheckFact;
import com.zero.framework.droolsrule.model.AreaPin;
import com.zero.framework.droolsrule.service.ReloadDroolsRulesService;
import com.zero.framework.droolsrule.utils.KieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = {"规则引擎"})
@RestController
public class AreaRestController {

    @Autowired
    private ReloadDroolsRulesService reloadDroolsRulesService;

    @ApiOperation("校验地区")
    @GetMapping("/area/name/{pinCode}")
    public ResponseEntity<String> getAreaByPinCode(@PathVariable int pinCode) {
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        AreaPin areaPin = new AreaPin();
        areaPin.setCode(pinCode);

        kieSession.insert(areaPin); // which object to validate
        kieSession.fireAllRules(); // fire all rules defined into drool file (drl)
        kieSession.dispose();
        return new ResponseEntity<String>(getAreaByCode(pinCode), HttpStatus.OK);
    }

    @ApiOperation("地址")
    @GetMapping("/address")
    public void address(@RequestParam int num) {

        // 构建 fact
        AddressCheckFact addressCheckFact = new AddressCheckFact();
        addressCheckFact.setRuleKey("address");
        addressCheckFact.setState("2455");
        addressCheckFact.setStreet("码哥字节大街");
        addressCheckFact.setPostcode(generateRandom(num));

        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        kieSession.setGlobal("log", log);

        // 将 fact 插入 工作内存
        kieSession.insert(addressCheckFact);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();

        System.out.println("触发了" + ruleFiredCount + "条规则." + addressCheckFact.getRuleKey() + "的结果为"
                + addressCheckFact.getFact());

    }

    @ApiOperation("重新加载所有规则")
    @GetMapping("/reload")
    public void reload() {
        reloadDroolsRulesService.reload();
    }

    private String getAreaByCode(int pin) {
        final Map<Integer, String> areaMap = new HashMap<>();
        areaMap.put(700001, "BBD Bag");
        areaMap.put(700010, "Beliaghata");
        areaMap.put(700105, "Nabapally");
        areaMap.put(700098, "Sukanta Nagar");
        return areaMap.getOrDefault(pin, "Area not found");
    }

    /**
     * 生成随机数
     *
     * @param num
     * @return
     */
    private String generateRandom(int num) {
        String chars = "0123456789";
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number.append(chars.charAt(rand));
        }
        return number.toString();
    }
}