package ru.simplewebapp.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SimpleRepositoryTest {

    public static final String TEST_ACCOUNT_NUMBER = "1111111111111111";
    public static final Integer ACCOUNTS_SIZE = 7;

    private final Logger LOG = LoggerFactory.getLogger(SimpleRepositoryTest.class);

    @Autowired
    protected CardService service;

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(service.getAll().size(), ACCOUNTS_SIZE.intValue());
    }

    @Test
    public void testGetOne() throws Exception {
        Assert.assertEquals(service.getOneByNumber(TEST_ACCOUNT_NUMBER).getNumber(), TEST_ACCOUNT_NUMBER);
    }


}
