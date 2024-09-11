INSERT INTO payments (partner_id, user_id, order_id, payment_amount, payment_date, payment_method, pg_provider_id, status, created_at, updated_at)
VALUES (97, 5, 40, 143454.00, '2024-09-06 00:00:00', 'Credit Card', 13, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO payments (partner_id, user_id, order_id, payment_amount, payment_date, payment_method, pg_provider_id, status, created_at, updated_at)
VALUES
    (1, 1, 101, 2000.00, '2024-09-01 12:00:00', 'Credit Card', 1, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, 102, 1500.00, '2024-09-02 12:00:00', 'PayPal', 2, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 3, 103, 2500.00, '2024-09-03 12:00:00', 'Bank Transfer', 3, 'Pending', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 4, 104, 3000.00, '2024-09-04 12:00:00', 'Credit Card', 4, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 5, 105, 1000.00, '2024-09-05 12:00:00', 'Credit Card', 5, 'Failed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 6, 106, 500.00, '2024-09-06 12:00:00', 'PayPal', 6, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 7, 107, 750.00, '2024-09-07 12:00:00', 'Bank Transfer', 7, 'Pending', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 8, 108, 1250.00, '2024-09-08 12:00:00', 'Credit Card', 8, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 9, 109, 1750.00, '2024-09-09 12:00:00', 'Credit Card', 9, 'Completed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 10, 110, 2200.00, '2024-09-10 12:00:00', 'PayPal', 10, 'Failed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);