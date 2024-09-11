package com.jhta.hta_miniproject1.repository;

import com.jhta.hta_miniproject1.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // 특정 날짜 범위에 대한 결제 내역 조회
    List<Payment> findByPaymentDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
