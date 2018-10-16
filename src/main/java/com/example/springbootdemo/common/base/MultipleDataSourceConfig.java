package com.example.springbootdemo.common.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Auther: yq
 * @Date: 2018-09-13 12:09
 * @Description:
 */
public interface MultipleDataSourceConfig {

    /**
     *
     * 配置数据源
     * @return
     */
    DataSource dataSource();

    /**
     * 配置事务管理器
     *
     * @return
     */
    DataSourceTransactionManager dataSourceTransactionManager();

    /**
     * 配置sqlsessionFactory
     *
     * @param dataSource 需要数据源，携带事务管理器
     *
     * @return 返回实例
     *
     * @throws Exception
     */
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception;

}
