package com.cyh.bean;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class Person {

    //ʹ��@Value��ֵ��
    //1��������ֵ
    //2������дSpEL�� #{}
    //3������д${}��ȡ�������ļ���properties���е�ֵ�������л������������ֵ��

    @Value("CYH")
    private String name;
    @Value("#{27+1}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;


}
