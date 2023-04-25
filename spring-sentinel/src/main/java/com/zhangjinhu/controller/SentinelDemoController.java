package com.zhangjinhu.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SentinelDemoController {

    static {
        List<FlowRule> rules = new ArrayList<FlowRule>();

        //创建一个流控规则
        FlowRule rule = new FlowRule();
        //对/sayHello这个资源限流
        rule.setResource("/sayHello");
        //基于qps限流
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //qps最大为2，超过2就要被限流
        rule.setCount(2);

        rules.add(rule);

        //设置规则
        FlowRuleManager.loadRules(rules);
    }

    @GetMapping("/sayHello")
    public String sayHello() throws InterruptedException {
        return "hello";
    }

}