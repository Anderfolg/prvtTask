CREATE TABLE payment_entries (
                                 id BIGSERIAL PRIMARY KEY,
                                 entry_time TIMESTAMP NOT NULL,                  -- Время проведения проводки
                                 payment_id BIGINT REFERENCES payments(id),      -- Внешний ключ на платеж
                                 amount NUMERIC(19, 2) NOT NULL,                 -- Сумма проводки
                                 status VARCHAR(50) NOT NULL                     -- Статус проводки (ACTIVE, NOTED)
);
