package com.rabigol.pbook.controllers;

import com.rabigol.pbook.enums.RangeStatus;
import com.rabigol.pbook.orm.PhoneNumbersRange;
import com.rabigol.pbook.orm.Range;
import com.rabigol.pbook.orm.jpa.RangesRepository;
import com.rabigol.pbook.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.rabigol.pbook.enums.RangeStatus.STATUS_ERROR;
import static com.rabigol.pbook.enums.RangeStatus.STATUS_OK;

@RestController
@RequestMapping("/api")
public class RestApiController {
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RestApiController.class);
    private RangesRepository rangesRepository;
    private RangeService rangeService;

    @Autowired
    public void rangesRepository(RangesRepository rangesRepository) {
        this.rangesRepository = rangesRepository;
    }

    @Autowired
    public void rangeService(RangeService rangeService) {
        this.rangeService = rangeService;
    }

    @RequestMapping("/ranges")
    Collection<String> getRanges() {
        List<String> strings = new ArrayList<>();
        for (PhoneNumbersRange range : this.rangesRepository.findAll()) {
            strings.add(range.toString());
        }
        return strings;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public RangeStatus addOne(@RequestBody Range range) {
        if (rangeService.correctRange(range)) {
            long startNumber = Long.valueOf(range.getFromNumber());
            long endNumber = Long.valueOf(range.getToNumber());
            PhoneNumbersRange phoneNumbersRange = new PhoneNumbersRange(startNumber, endNumber);
            rangesRepository.save(phoneNumbersRange);
            logger.info("Все хорошо как в программе \"Время\"");
            return STATUS_OK;
        }
        logger.error("Неправильный запрос");
        return STATUS_ERROR;
    }
}
