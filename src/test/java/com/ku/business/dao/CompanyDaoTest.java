package com.ku.business.dao;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class CompanyDaoTest {

    @RepeatedTest(100)
    void findById() {
        //given
        CompanyDao companyDao = new CompanyDao(HibernateUtil.getSessionFactory());
        Long id = (long) (Math.random() * (1000 - 1)) + 1;
        Company company = companyDao.findById(id);

        //when
        boolean isIdEqual = (Objects.equals(company.getId(), id));
        System.out.println(company);

        //then
       Assertions.assertTrue(isIdEqual);
        }


    @Test
    void findAll() {
            CompanyDao companyDao = new CompanyDao(HibernateUtil.getSessionFactory());
            List<Company> companies = companyDao.findAll();
             for (Company c: companies
                          ) {
                     System.out.println(c);
                 }

            //when
            boolean isNotEmpty = (companies.isEmpty());

            //then
            Assertions.assertFalse(isNotEmpty);
        }


    @Test
    void save() {
        //given
        Company first = new Company(1004L, "Bank", "389665779", true, 324L, null, null);
        CompanyDao companyDao = new CompanyDao(HibernateUtil.getSessionFactory());
        companyDao.save(first);
       // Company second = companyRepository.findById(1004L);
       // companyRepository.delete(1003L);

        //when
       // boolean isEqual = (Objects.equals(first.getTaxNumber(), second.getTaxNumber()));

        //then
       // Assertions.assertTrue(isEqual);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}