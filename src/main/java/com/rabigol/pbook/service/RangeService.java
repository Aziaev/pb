package com.rabigol.pbook.service;

import com.rabigol.pbook.controllers.RestApiController;
import com.rabigol.pbook.orm.PhoneNumbersRange;
import com.rabigol.pbook.orm.Range;
import com.rabigol.pbook.orm.jpa.RangesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RangeService {
    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RestApiController.class);
    private RangesRepository rangesRepository;

    @Autowired
    public void rangesRepository(RangesRepository rangesRepository) {
        this.rangesRepository = rangesRepository;
    }

    public boolean correctRange(Range range) {
        long startNumber;
        long endNumber;
        try {
            if (checkNumbers(range)) {
                startNumber = Long.valueOf(range.getFromNumber());
                endNumber = Long.valueOf(range.getToNumber());
                PhoneNumbersRange phoneNumbersRange = new PhoneNumbersRange(startNumber, endNumber);
                if (checkNumbers(range) && checkRanges(phoneNumbersRange)) {
                    return true;
                } else return false;
            }
        } catch (Exception n) {
            logger.error("Ошибка входных данных : " + n);
            return false;
        }
        return false;
    }


    private boolean checkNumbers(Range range) {
        if ((range.getFromNumber().length() == 11 && range.getToNumber().length() == 11)) {
            try {
                long startNumber = Long.valueOf(range.getFromNumber());
                long endNumber = Long.valueOf(range.getToNumber());
                if (startNumber < endNumber) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException n) {
                logger.error("Ошибка входных данных : " + n);
                return false;
            }
        }
        return false;
    }

    private boolean checkRanges(PhoneNumbersRange phoneNumbersRange) {
        for (PhoneNumbersRange range : rangesRepository.findAll()) {
            if ((range.getStartNumber() <= phoneNumbersRange.getStartNumber() && phoneNumbersRange.getStartNumber() <= range.getEndNumber())
                    || (phoneNumbersRange.getEndNumber() >= range.getStartNumber() && phoneNumbersRange.getEndNumber() <= range.getEndNumber())) {
                return false;
            }
        }
        return true;
    }
}
