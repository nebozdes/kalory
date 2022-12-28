-- Products table
CREATE SEQUENCE IF NOT EXISTS product_id_sequence;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('product_id_sequence'),
    product_name varchar(32) NOT NULL,
    base_amount DECIMAL(15,5) DEFAULT '1' NOT NULL CHECK(base_amount > 0),
    base_amount_type VARCHAR(100) DEFAULT 'GRAM' NOT NULL,
    base_calories DECIMAL(15,5) NOT NULL CHECK(base_calories > 0),
    base_carbs DECIMAL(15,5) NOT NULL CHECK(base_carbs >= 0),
    base_lipids DECIMAL(15,5) NOT NULL CHECK(base_lipids >= 0),
    base_proteins DECIMAL(15,5) NOT NULL CHECK(base_proteins >= 0)
);

-- Users table
CREATE SEQUENCE IF NOT EXISTS user_id_sequence;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('user_id_sequence'),
    login VARCHAR(64) NOT NULL,
    password VARCHAR(256) NOT NULL,
    status VARCHAR(16) NOT NULL,
    roles VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

-- Consumed products table
CREATE SEQUENCE IF NOT EXISTS consumed_product_id_sequence;

CREATE TABLE IF NOT EXISTS consumed_products (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('consumed_product_id_sequence'),
    product_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    consumption_date DATE NOT NULL,
    consumption_amount DECIMAL(15,5) NOT NULL CHECK(consumption_amount > 0),
    calculated_calories DECIMAL(15,5) NOT NULL CHECK(calculated_calories > 0),
    calculated_carbs DECIMAL(15,5) NOT NULL CHECK(calculated_carbs >= 0),
    calculated_lipids DECIMAL(15,5) NOT NULL CHECK(calculated_lipids >= 0),
    calculated_proteins DECIMAL(15,5) NOT NULL CHECK(calculated_proteins >= 0),
    comment TEXT,

    CONSTRAINT consumed_products_product_id_fk FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT consumed_products_user_id_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tags table
CREATE SEQUENCE IF NOT EXISTS tag_id_sequence;

CREATE TABLE IF NOT EXISTS tags (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('tag_id_sequence'),
    tag_name VARCHAR(32)
);

CREATE INDEX IF NOT EXISTS tags_name_ids ON tags (tag_name);

-- Weight check table
CREATE SEQUENCE IF NOT EXISTS weight_check_id_sequence;

CREATE TABLE IF NOT EXISTS weight_checks(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('weight_check_id_sequence'),
    user_id BIGINT NOT NULL,
    weight_check_value DECIMAL(15,5) NOT NULL CHECK(weight_check_value > 0),
    check_time TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT weight_checks_user_id_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Weight target table
CREATE SEQUENCE IF NOT EXISTS weight_target_id_sequence;

CREATE TABLE IF NOT EXISTS weight_targets (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('weight_target_id_sequence'),
    user_id BIGINT NOT NULL,
    weight_target_value DECIMAL(15,5) NOT NULL CHECK(weight_target_value > 0),
    deadline DATE,

    CONSTRAINT weight_targets_user_id_fk FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Product <-> tags relation table
CREATE TABLE IF NOT EXISTS products_tags (
    product_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT products_tags_product_id_fk FOREIGN KEY(product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT weight_targets_tag_id_fk FOREIGN KEY(tag_id) REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, tag_id)
);