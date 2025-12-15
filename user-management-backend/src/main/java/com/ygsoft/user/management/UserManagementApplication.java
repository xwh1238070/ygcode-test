package com.ygsoft.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 用户管理系统启动类
 * 
 * @author developer
 * @date 2024-12-15
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ygsoft.user.management.dao")
@EnableTransactionManagement
public class UserManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
        System.out.println("========================================");
        System.out.println("用户管理系统启动成功！");
        System.out.println("访问地址: http://localhost:8080/user-management");
        System.out.println("Swagger文档: http://localhost:8080/user-management/swagger-ui.html");
        System.out.println("========================================");
    }
}
