package com.example.activitymq;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

public class MessageConverterSender implements MessageConverter {

    //发送使用，将java对象封装成jms对象
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        System.out.println("--->开始数据转换<-----");
        Message returnMessage=null;
        if(o instanceof String){
            System.out.println("---转换string---");
            returnMessage = session.createTextMessage("toMessage转换后Message对象-》"+o.toString());
         }else if(o instanceof HashMap){
            System.out.println("---转换map---");
            returnMessage=session.createMapMessage();
            Map map= (Map) o;
            returnMessage.setStringProperty("map",map.get("1").toString());

        }else{//对于pojo的处理
            System.out.println("处理pojo");
          returnMessage=  session.createObjectMessage(Object.class);


        }

        return returnMessage;
}

        @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return null;
    }
}
