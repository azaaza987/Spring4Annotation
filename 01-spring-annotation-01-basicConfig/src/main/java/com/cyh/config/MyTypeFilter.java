package com.cyh.config;

import java.io.IOException;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyTypeFilter implements TypeFilter {

    /**
     * metadataReader����ȡ���ĵ�ǰ����ɨ��������Ϣ
     * metadataReaderFactory:���Ի�ȡ�������κ�����Ϣ��
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
            throws IOException {

        //��ȡ��ǰ����ɨ����������Ϣ
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        System.out.println("className: " + className);
        return className.contains("er");
    }

}
