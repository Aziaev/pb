package com.rabigol.pbook.orm.jpa;

import com.rabigol.pbook.orm.PhoneNumbersRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RangesRepository extends JpaRepository<PhoneNumbersRange, Long> {
    Collection<PhoneNumbersRange> findById(long id);

    PhoneNumbersRange save(PhoneNumbersRange phoneNumbersRange);
}
