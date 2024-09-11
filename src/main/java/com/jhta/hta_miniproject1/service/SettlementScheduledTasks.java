package com.jhta.hta_miniproject1.service;

import com.jhta.hta_miniproject1.Entity.Payment;
import com.jhta.hta_miniproject1.Entity.Settlement;
import com.jhta.hta_miniproject1.repository.PaymentRepository;
import com.jhta.hta_miniproject1.repository.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SettlementScheduledTasks {

    private final PaymentRepository paymentRepository;
    private final SettlementRepository settlementRepository;

    @Autowired
    public SettlementScheduledTasks(PaymentRepository paymentRepository, SettlementRepository settlementRepository) {
        this.paymentRepository = paymentRepository;
        this.settlementRepository = settlementRepository;
    }

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    public void dailySettlement() {
        // 전날의 날짜 계산
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        // 전날의 결제 내역 조회
        LocalTime startOfDay = LocalTime.MIN;
        LocalTime endOfDay = LocalTime.MAX;

        // 전날의 결제 내역 조회
        List<Payment> payments = paymentRepository.findByPaymentDateBetween(
                LocalDateTime.of(yesterday, startOfDay),
                LocalDateTime.of(yesterday, endOfDay)
        );

        // partner_id 별로 결제 금액 집계
        Map<Long, BigDecimal> partnerTotalAmounts = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getPartnerId,
                        Collectors.mapping(
                                Payment::getPaymentAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));

        // 정산 테이블에 삽입 또는 업데이트
        for (Map.Entry<Long, BigDecimal> entry : partnerTotalAmounts.entrySet()) {
            Long partnerId = entry.getKey();
            BigDecimal totalAmount = entry.getValue();

            // 해당 partner_id와 날짜에 대한 정산 레코드 조회
            List<Settlement> settlements = settlementRepository.findByPartnerIdAndPaymentDateBetween(
                    partnerId,
                    startOfDay,
                    endOfDay
            );

            if (!settlements.isEmpty()) {
                // 기존 정산 레코드 업데이트
                Settlement existingSettlement = settlements.get(0); // Assume single record
                existingSettlement.setTotalAmount(totalAmount);
                existingSettlement.setUpdatedAt(LocalDateTime.now());
                settlementRepository.save(existingSettlement);
            } else {
                // 새로운 정산 레코드 삽입
                Settlement newSettlement = new Settlement();
                newSettlement.setPartnerId(partnerId);
                newSettlement.setTotalAmount(totalAmount);
                newSettlement.setStatus("pending");
                newSettlement.setPaymentDate(LocalTime.MIDNIGHT); // paymentDate를 LocalTime으로 설정
                settlementRepository.save(newSettlement);
            }
        }

        System.out.println("정산 집계가 완료되었습니다: " + yesterday);
    }
}
