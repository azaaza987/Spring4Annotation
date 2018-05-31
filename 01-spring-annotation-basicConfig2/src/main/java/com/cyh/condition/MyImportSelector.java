package com.cyh.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    //����ֵ�����ǵ����뵽�����е����ȫ����
    //AnnotationMetadata:��ǰ��ע@Importע����������ע����Ϣ
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //������Ҫ����nullֵ�����Է��� new String[] {}
        return new String[] {"com.cyh.bean.Blue", "com.cyh.bean.Yellow"};
    }

}
