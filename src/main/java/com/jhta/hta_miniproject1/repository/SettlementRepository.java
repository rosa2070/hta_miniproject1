package com.jhta.hta_miniproject1.repository;

import com.jhta.hta_miniproject1.Entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    // 특정 partner_id와 날짜에 대한 정산 레코드 조회
    Settlement findByPartnerIdAndPaymentDate(LocalTime paymentDate, Long partnerId);
}
