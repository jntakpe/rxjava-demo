package com.github.jntakpe.reactiveapp.service;

import com.github.jntakpe.reactiveapp.exceptions.ClientNotFoundException;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Duration;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests associés au service {@link ClientService}
 *
 * @author jntakpe
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWireMock(stubs = "classpath:/stubs", port = 8081)
public class ClientServiceTest {

    private static final String SINGLE_ROOT_USER = "jntakpe";

    private static final String COUPLE_ROOTS_USER = "jguerrin";

    @Autowired
    private ClientService clientService;

    @Test(expected = ClientNotFoundException.class)
    public void soldeTotalByLogin_shouldFailCuzUnknownUser() throws Exception {
        clientService.soldeTotalByLogin("unknownuser");
        fail("should have failed at this point");
    }

    @Test
    public void soldeTotalByLogin_shouldBeZeroCuzUserHasNotAccounts() throws Exception {
        assertThat(clientService.soldeTotalByLogin("noacc")).isZero();
    }

    @Test
    public void soldeTotalByLogin_shouldCalcCorrectBalanceWithSingleRoot() throws Exception {
        assertThat(clientService.soldeTotalByLogin(SINGLE_ROOT_USER)).isEqualByComparingTo(new BigDecimal(11500));
    }

    @Test
    public void soldeTotalByLogin_shouldCalcCorrectBalanceWithSingleRootFasterThan500ms() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        assertThat(clientService.soldeTotalByLogin(SINGLE_ROOT_USER)).isNotNull();
        stopWatch.stop();
        assertThat(Duration.ofMillis(stopWatch.getTime())).isLessThan(Duration.ofMillis(500));
    }

    @Test
    public void soldeTotalByLogin_shouldCalcCorrectBalanceWithCoupleRoots() throws Exception {
        assertThat(clientService.soldeTotalByLogin(COUPLE_ROOTS_USER)).isEqualByComparingTo(new BigDecimal(10900));
    }

    @Test
    public void soldeTotalByLogin_shouldCalcCorrectBalanceWithCoupleRootsFasterThan500ms() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        assertThat(clientService.soldeTotalByLogin(COUPLE_ROOTS_USER)).isNotNull();
        stopWatch.stop();
        assertThat(Duration.ofMillis(stopWatch.getTime())).isLessThan(Duration.ofMillis(500));
    }



}