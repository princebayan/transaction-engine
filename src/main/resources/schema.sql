BEGIN ;

-- Create currency Tables
CREATE TABLE IF NOT EXISTS currency(
                         id               serial PRIMARY KEY,
                         code             VARCHAR(3) UNIQUE NOT NULL
);

-- Create transaction table
CREATE TABLE IF NOT EXISTS transaction(
                            id                                serial PRIMARY KEY,
                            customer_id                       Integer NOT NULL,
                            source_account                    varchar(50),
                            destination_account               varchar(50),
                            created_date                      timestamp without time zone,
                            updated_date                      timestamp without time zone,
                            amount                            numeric,
                            total_debited_from_source         numeric,
                            total_credited_to_destination     numeric,
                            source_currency_id                integer references currency(id),
                            destination_currency_id           integer references currency(id)
);

-- Seed the currency with USD
insert into currency ( code ) values('USD');
-- Seed the currency with EUR
insert into currency ( code ) values('EUR');


COMMIT ;