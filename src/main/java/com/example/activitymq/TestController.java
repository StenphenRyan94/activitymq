package com.example.activitymq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.JMSException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/TestController")
public class TestController {

    @Autowired
    JmsTemplate myjmsTemple;

    @Autowired
    SendPOJO sendPOJO;

    @RequestMapping("/sender")
    @ResponseBody
   public  void test(){
        //parameters ready todo

        System.out.println("异步发送默认地址@-》"+ myjmsTemple.getDefaultDestination());//topicName_test
        //发送消息
//        Map testMap=new HashMap<String,String>();
//        testMap.put("1","test1");
        sendPOJO.setImel("Imel@123");
        sendPOJO.setSerialNumber("serial_nummber");
        sendPOJO.setUserName("zhujie");
//        myjmsTemple.convertAndSend(myjmsTemple.getDefaultDestination(),"测试数据内容");//第二个参数为自己的参数类型，通过消息转换器编程jms的5种类型之一
      //  myjmsTemple.convertAndSend(myjmsTemple.getDefaultDestination(),testMap);//第二个参数为自己的参数类型，通过消息转换器编程jms的5种类型之一
        myjmsTemple.convertAndSend(myjmsTemple.getDefaultDestination(),sendPOJO);


//        List<String> provinces={"jiangsu","hubei"};



//        System.out.println("---ready----");
//        try {
//            myProducerForTopic.sender();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }


    }

}
