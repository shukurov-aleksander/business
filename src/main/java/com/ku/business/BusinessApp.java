package com.ku.business;

import com.ku.business.config.JavaConfig;
import com.ku.business.entity.Company;
import com.ku.business.service.impl.CompanyServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BusinessApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        CompanyServiceImpl service = context.getBean(CompanyServiceImpl.class);
        List<Company> companies = service.findAll();
        for (Company company : companies) {
            System.out.println(company);
        }
    }
}

