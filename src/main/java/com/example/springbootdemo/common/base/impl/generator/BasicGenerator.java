package com.example.springbootdemo.common.base.impl.generator;

import com.example.springbootdemo.common.base.Generator;

public class BasicGenerator<T> implements Generator<T> {

	private Class<T> type;
    public BasicGenerator(Class<T> type){
        this.type = type;
    }
    
    public T next(){
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     *
     * ��̬���ɣ�����Ҫnew
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }

}
