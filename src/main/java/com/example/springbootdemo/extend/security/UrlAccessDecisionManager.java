package com.example.springbootdemo.extend.security;


import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;

import java.util.List;


/**
 * 继承全票通过决策管理器
 * 决策管理器
 */
public class UrlAccessDecisionManager extends UnanimousBased {

    public UrlAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {

        super(decisionVoters);
    }

}
