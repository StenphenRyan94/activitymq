package com.example.activitymq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.List;

@Component
public class MyProducerForTopic {

    @Autowired
    JmsTemplate jmsTemplate;

     @Value("${spring.activemq.broker-url}")
     String activityMqUrl;



    //publisher
     void sender() throws JMSException {

        System.out.println("activityMqUrl=>>"+activityMqUrl);

         jmsTemplate.setConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616"));
         jmsTemplate.setDeliveryPersistent(true);
         //set destination
         jmsTemplate.setDestinationResolver(new DynamicDestinationResolver()); // set Destination解析器
         jmsTemplate.setPubSubDomain(true);//true topic  false quene

         System.out.println("@send start");
         jmsTemplate.send("topicName_test", session -> session.createTextMessage("第一个消费内容")


         );








/*

        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列目标,并标识队列名称，消费者根据队列名称接收数据
        Destination destination = session.createTopic("topicTest");
        // 创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        // 向队列推送10个文本消息数据
        for (int i = 1; i <= 10; i++) {
            // 创建文本消息
            TextMessage message = session.createTextMessage("第" + i + "个文本消息");
            //发送消息
            producer.send(message);
            //在本地打印消息
            System.out.println("已发送的消息：" + message.getText());
        }
        //关闭连接
        connection.close();

 */




    }


}
