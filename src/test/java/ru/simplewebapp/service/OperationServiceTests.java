package ru.simplewebapp.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class OperationServiceTests {

    @Autowired
    protected AccountServiceImpl accountService;

    @Autowired
    protected OperationService operationService;


    @Test
    public void testThatAfterBalanceRequestWeHaveNewOperationRecord() throws Exception {
        int optSize = operationService.getAll().size();
        accountService.getBalance(AccountServiceTests.ACCOUNT1_NUMBER);
        int optSizeAfter = operationService.getAll().size();
        Assert.assertEquals(optSize, optSizeAfter - 1);
    }

}
