package com.sihai.test01;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

/**
 * @Author ouyangsihai
 * @Description 部署
 * @Date 16:24 2019/1/26
 * @Param
 * @return
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext-core.xml",
        "classpath:applicationContext-activiti.xml"
})
@Slf4j
public class test02_spring {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;

    /**
     * @return void
     * @Author ouyangsihai
     * @Description 部署流程实例
     * @Date 16:17 2018/12/19
     * @Param []
     **/
    @Test
    public void testTask() throws Exception {
        // 1 发布流程
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("/bpmn/test_01.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("/bpmn/test_01.png");
        processEngine.getRepositoryService()
                .createDeployment()
                .addInputStream("test_01.bpmn", inputStreamBpmn)
                .addInputStream("test_01.png", inputStreamPng)
                .deploy();

        ProcessInstance pi = processEngine.getRuntimeService()//
                .startProcessInstanceByKey("test_01");
        System.out.println("pid:" + pi.getId());
    }
}
