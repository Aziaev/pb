package com.rabigol.pbook.controllers;

import com.rabigol.pbook.enums.RangeStatus;
import com.rabigol.pbook.orm.PhoneNumbersRange;
import com.rabigol.pbook.orm.Range;
import com.rabigol.pbook.orm.jpa.RangesRepository;
import com.rabigol.pbook.service.RangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiControllerTest {

    @MockBean(RangesRepository.class)
    private RangesRepository rangesRepository;
    private RestApiController restApiController;
    @MockBean(RangeService.class)
    private RangeService rangeService;

    private void init() {
        restApiController = new RestApiController();
        restApiController.rangeService(rangeService);
        restApiController.rangesRepository(rangesRepository);
    }

    @Test
    public void testGetRanges() {
        init();
        PhoneNumbersRange testRange = new PhoneNumbersRange(89999999901L, 89999999990L);
        List<PhoneNumbersRange> list = new ArrayList<>();
        list.add(testRange);
        when(rangesRepository.findAll()).thenReturn(list);
        Collection<String> rangeStatus = restApiController.getRanges();
        assertNotNull(rangeStatus);
    }

    @Test
    public void testAddOneSuccess() {
        init();
        Range range = new Range();
        range.setFromNumber("123");
        range.setToNumber("1234");
        when(rangeService.correctRange(range)).thenReturn(true);
        RangeStatus rangeStatus = restApiController.addOne(range);
        verify(rangesRepository).save(any(PhoneNumbersRange.class));
        assertEquals(rangeStatus, RangeStatus.STATUS_OK);
    }

    @Test
    public void testAddOneError() {
        init();
        Range range = new Range();
        when(rangeService.correctRange(range)).thenReturn(false);
        RangeStatus rangeStatus = restApiController.addOne(range);
        verify(rangesRepository, never()).save(any(PhoneNumbersRange.class));
        assertEquals(rangeStatus, RangeStatus.STATUS_ERROR);
    }
}