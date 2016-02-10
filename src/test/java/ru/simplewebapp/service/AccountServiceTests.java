package ru.simplewebapp.service;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.simplewebapp.model.Account;
import ru.simplewebapp.util.exception.AtmException;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AccountServiceTests {

    public static final String ACCOUNT1_NUMBER = "1111111111111111";
    public static final String ACCOUNT1_WRONG_PIN = "-100000";

    public static final Integer ACCOUNT1_BALANCE = 10000;
    public static final Integer ACCOUNTS1_SIZE = 7;

    public static final Integer ONE_CENT = 1;

    private final Logger LOG = LoggerFactory.getLogger(AccountServiceTests.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    protected AccountServiceImpl accountService;

    @Autowired
    protected OperationService operationService;

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(accountService.getAll().size(), ACCOUNTS1_SIZE.intValue());
    }

    @Test
    public void testGetOne() throws Exception {
        Assert.assertEquals(accountService.getAccount(ACCOUNT1_NUMBER).getNumber(), ACCOUNT1_NUMBER);
    }


    @Test
    public void testWithdrawOneCent() throws Exception {
        Account account = accountService.getAccount(ACCOUNT1_NUMBER);
        Integer minusOneCent = account.getBalance() - ONE_CENT;
        Account result = accountService.withdraw(ACCOUNT1_NUMBER, ONE_CENT);
        Assert.assertEquals(result.getBalance(), minusOneCent);
    }

    @Test
    public void testWithdrawAllMoney() throws Exception {
        Account account = accountService.getAccount(ACCOUNT1_NUMBER);
        Account result = accountService.withdraw(ACCOUNT1_NUMBER, account.getBalance());
        Assert.assertEquals(result.getBalance(), new Integer(0));
    }

    @Test
    public void testWithdrawMoreThanOnBalance() throws Exception {
        thrown.expect(AtmException.class);
        Account account = accountService.getAccount(ACCOUNT1_NUMBER);
        accountService.withdraw(ACCOUNT1_NUMBER, account.getBalance() + 1);
    }
/*

    @Test
    public void testRejectAfterWrongPinAttempts() throws Exception {
        thrown.expect(AtmException.class);
        accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
    }


    @Transactional
    @Test
    public void testAccountBlockedAfter4WrongAttempts() throws Exception {
        thrown.expect(AtmException.class);
        try {
            accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
        } catch (AtmException exc) {
            LOG.info("Wrong pin attempt during test 1");
        }

        try {
            accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
        } catch (AtmException exc) {
            LOG.info("Wrong pin attempt during test 2");
        }

        try {
            accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
        } catch (AtmException exc) {
            LOG.info("Wrong pin attempt during test 3");
        }

        try {
            accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
        } catch (AtmException exc) {
            LOG.info("Wrong pin attempt during test 4");
        }

        try {
            accountService.checkAndGetAccount(ACCOUNT1_NUMBER, ACCOUNT1_WRONG_PIN);
        } catch (AtmException exc) {
            LOG.info("Wrong pin attempt during test 5 - should be LockedAccountException thrown");
        }
    }
*/


}
