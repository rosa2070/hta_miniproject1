package com.jhta.hta_miniproject1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 결제 고유 ID

    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "user_id", nullable = false)
    private Long userId; // 결제를 한 사용자 ID

    @Column(name = "order_id", nullable = false)
    private Long orderId; // 관련 주문 ID

    @Column(name = "payment_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal paymentAmount; // 결제 금액

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate; // 결제 일시

    @Column(name = "payment_method", length = 50)
    private String paymentMethod; // 결제 수단

    @Column(name = "pg_provider_id")
    private Long pgProviderId; // PG사 ID (결제 대행사 식별자, 계좌이체의 경우 NULL)

    @Column(name = "status", length = 20)
    private String status = "Completed"; // 결제 상태

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 레코드 생성 시각

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 레코드 업데이트 시각

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
