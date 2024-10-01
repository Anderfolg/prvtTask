CREATE TABLE payments (
                          id BIGSERIAL PRIMARY KEY,
                          payer_name VARCHAR(255) NOT NULL,       -- ФИО плательщика
                          payer_inn BIGINT NOT NULL,              -- ИНН плательщика
                          payer_card_no BIGINT NOT NULL,          -- No Карты плательщика
                          recipient_account BIGINT NOT NULL,      -- Расчетный счет получателя
                          recipient_mfo VARCHAR(50) NOT NULL,     -- МФО получателя
                          recipient_okpo BIGINT NOT NULL,         -- ОКПО получателя
                          recipient_name VARCHAR(255) NOT NULL,   -- Наименование получателя
                          payment_period BIGINT NOT NULL,         -- Период списания (N минут/часов/дней)
                          payment_amount NUMERIC(19, 2) NOT NULL  -- Сумма платежа
);
