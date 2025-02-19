CREATE TABLE user_type
(
    id         INT          NOT NULL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    is_archive BOOLEAN      NOT NULL DEFAULT FALSE
);

COMMENT ON TABLE user_type IS 'User type';
COMMENT ON COLUMN user_type.id IS 'Type Id';
COMMENT ON COLUMN user_type.name IS 'Type name';
COMMENT ON COLUMN user_type.is_archive IS 'Indicates whether the type is archived: true - archived, false - active';

INSERT INTO user_type (id, name, is_archive)
VALUES (1, 'CUSTOMER', false),
       (2, 'SELLER', false);

CREATE TABLE "user"
(
    id             BIGSERIAL    NOT NULL PRIMARY KEY,
    email          VARCHAR(100),
    phone_number   VARCHAR(100) NOT NULL,
    type_id        INT          NOT NULL,
    creation_date  TIMESTAMP    NOT NULL DEFAULT NOW(),
    update_date    TIMESTAMP    NOT NULL DEFAULT NOW(),
    is_archive     BOOLEAN      NOT NULL DEFAULT FALSE,
    is_blocked     BOOLEAN      NOT NULL DEFAULT FALSE,
    blocked_reason VARCHAR(255),
    UNIQUE (phone_number, type_id),
    CONSTRAINT type_fk FOREIGN KEY (type_id) REFERENCES user_type (id)
);

COMMENT ON TABLE "user" IS 'User table';
COMMENT ON COLUMN "user".id IS 'User Id';
COMMENT ON COLUMN "user".email IS 'User email';
COMMENT ON COLUMN "user".phone_number IS 'User phone number';
COMMENT ON COLUMN "user".type_id IS 'User type id';
COMMENT ON COLUMN "user".creation_date IS 'User creation date';
COMMENT ON COLUMN "user".update_date IS 'User update date';
COMMENT ON COLUMN "user".is_archive IS 'Indicates whether the user is archived: true - archived, false - active';
COMMENT ON COLUMN "user".is_blocked IS 'Indicates whether the user is blocked: true - blocked, false - active';
COMMENT ON COLUMN "user".blocked_reason IS 'The reason for the user blocking';

CREATE TABLE user_setting
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    user_id BIGINT    NOT NULL,
    setting pg_catalog.jsonb,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user" (id)
);

COMMENT ON TABLE user_setting IS 'User UI settings';
COMMENT ON COLUMN user_setting.id IS 'Setting Id';
COMMENT ON COLUMN user_setting.user_id IS 'Owner of user settings';
COMMENT ON COLUMN user_setting.setting IS 'JSON settings';

CREATE TABLE user_seller_info
(
    id           BIGSERIAL    NOT NULL PRIMARY KEY,
    user_id      BIGINT       NOT NULL,
    inn          VARCHAR(10) UNIQUE,
    rating       NUMERIC(2, 1),
    company_name VARCHAR(255) NOT NULL UNIQUE,
    update_date  TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user" (id)
);

COMMENT ON TABLE user_seller_info IS 'User seller info';
COMMENT ON COLUMN user_seller_info.id IS 'Setting Id';
COMMENT ON COLUMN user_seller_info.user_id IS 'Owner of user settings';
COMMENT ON COLUMN user_seller_info.inn IS 'Taxpayer identification number';
COMMENT ON COLUMN user_seller_info.rating IS 'Seller rating';
COMMENT ON COLUMN user_seller_info.company_name IS 'Name of the company';
COMMENT ON COLUMN user_seller_info.update_date IS 'Timestamp of the last update';

CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE');

CREATE TABLE user_customer_info
(
    id          BIGSERIAL    NOT NULL PRIMARY KEY,
    user_id     BIGINT       NOT NULL,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100),
    middle_name VARCHAR(100),
    sex         gender_enum  NOT NULL,
    update_date TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user" (id)
);

COMMENT ON TABLE user_customer_info IS 'User customer info';
COMMENT ON COLUMN user_customer_info.id IS 'Customer id';
COMMENT ON COLUMN user_customer_info.user_id IS 'Reference to the user';
COMMENT ON COLUMN user_customer_info.first_name IS 'User''s first name';
COMMENT ON COLUMN user_customer_info.last_name IS 'User''s last name';
COMMENT ON COLUMN user_customer_info.middle_name IS 'User''s middle name';
COMMENT ON COLUMN user_customer_info.sex IS 'User''s gender';
COMMENT ON COLUMN user_customer_info.update_date IS 'Timestamp of the last update';

CREATE TABLE user_address
(
    id                    BIGSERIAL    NOT NULL PRIMARY KEY,
    user_customer_info_id BIGINT       NOT NULL,
    address               VARCHAR(255) NOT NULL,
    creation_date         TIMESTAMP    NOT NULL DEFAULT NOW(),
    update_date           TIMESTAMP    NOT NULL DEFAULT NOW(),
    is_archive            BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT user_customer_info_id_fk FOREIGN KEY (user_customer_info_id) REFERENCES user_customer_info (id)
);

COMMENT ON TABLE user_address IS 'Table storing user addresses';
COMMENT ON COLUMN user_address.id IS 'User address id';
COMMENT ON COLUMN user_address.user_customer_info_id IS 'Reference to the user_customer_info table';
COMMENT ON COLUMN user_address.address IS 'Full address';
COMMENT ON COLUMN user_address.creation_date IS 'Timestamp when the address was created';
COMMENT ON COLUMN user_address.update_date IS 'Timestamp when the address was last updated';
COMMENT ON COLUMN user_address.is_archive IS 'Indicates if the address is archived';