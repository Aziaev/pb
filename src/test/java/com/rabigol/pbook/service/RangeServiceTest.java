package com.rabigol.pbook.service;

import com.rabigol.pbook.orm.PhoneNumbersRange;
import com.rabigol.pbook.orm.Range;
import com.rabigol.pbook.orm.jpa.RangesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RangeServiceTest {
    @MockBean(RangesRepository.class)
    private RangesRepository rangesRepository;
    private RangeService rangeService;
    private Range range;

    @Before
    public void setUp() throws Exception {
        rangeService = new RangeService();
        rangeService.rangesRepository(rangesRepository);
        range = new Range();
    }

    @Test
    public void testCorrectRangeEmptyRange() throws Exception {
        boolean result = rangeService.correctRange(range);
        assertFalse(result);
    }

    @Test
    public void testCorrectRangeLongRange() throws Exception {
        range.setFromNumber("123456789011");
        range.setToNumber("123456789013");
        boolean result = rangeService.correctRange(range);
        assertFalse(result);
    }

    @Test
    public void testCorrectRangeGoodRange() throws Exception {
        range.setFromNumber("12345678901");
        range.setToNumber("12345678903");
        List<PhoneNumbersRange> ranges = new ArrayList<>();
        PhoneNumbersRange pRange = new PhoneNumbersRange(12345678991L, 12345678993L);
        ranges.add(pRange);
        when(rangesRepository.findAll()).thenReturn(ranges);
        boolean result = rangeService.correctRange(range);
        assertTrue(result);
    }
}