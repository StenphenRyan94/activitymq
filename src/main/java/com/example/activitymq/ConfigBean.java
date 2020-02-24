package com.example.activitymq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.*;


//异步发送配置bean
@Configuration
public class ConfigBean {




    @Value("${spring.activemq.broker-url}")
    String ActiveMQ_URL;

    @Value("${spring.activemq.user}")
    String ActiveMQ_USER;

    @Value("${spring.activemq.password}")
    String ActiveMQ_PASSWORD;



    /*
    <beanid="XX" class="ActiveMQConnectionFactory">
    <property>brokenUrl value=''</pro..>

     */





    //发送地址

    @Bean
    Topic topic(){
        return  new ActiveMQTopic("topicName_test");
    }



    //连接工厂
    @Bean
    public ConnectionFactory connectionFactory(){

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(ActiveMQ_URL);
        System.out.println("ActiveMQ_URL@"+ActiveMQ_URL);
        connectionFactory.setUserName(ActiveMQ_USER);
        System.out.println("ActiveMQ_USER@"+ActiveMQ_USER);
        connectionFactory.setPassword(ActiveMQ_PASSWORD);
        System.out.println("ActiveMQ_PASSWORD@"+ActiveMQ_PASSWORD);
        connectionFactory.setDispatchAsync(true);//同步 S/异步发送A
        return connectionFactory;

    }

    //发送消息转换器
    //java对象-->JMS对象
    @Bean
    MessageConverter messageConverter(){
        MessageConverter messageConverter=new MessageConverterSender();
        return   messageConverter;
    }


//    //构造发送消息内容bean
//    @Bean
//    MessageCreator messageCreator(){
//
//
//        return new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage("测试消息内容");
//            }
//        };
//    }


    @Bean
    JmsTemplate myjmsTemple(Topic topic ,ConnectionFactory connectionFactory/*,MessageCreator messageCreator*/,MessageConverter messageConverter){


        JmsTemplate myjmsTemple=new JmsTemplate();
        myjmsTemple.setPubSubDomain(true);//topic
        myjmsTemple.setDefaultDestination(topic);//默认发送地址//topicName_test
        myjmsTemple.setConnectionFactory(connectionFactory);
        myjmsTemple.setMessageConverter(messageConverter);

        return myjmsTemple;
    }










}
