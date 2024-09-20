create table order_items
(
    id              bigint unsigned NOT NULL AUTO_INCREMENT,
    quantity        integer,
    sale_price      numeric(38, 2),
    tax_amount      numeric(38, 2),
    tax_base_amount numeric(38, 2),
    tax_free_amount numeric(38, 2),
    order_id        bigint,
    product_id      bigint,
    seller_id       bigint,
    attribute       varchar(255),
    product_name    varchar(255),
    primary key (id)
);

create table orders
(
    id         bigint unsigned NOT NULL AUTO_INCREMENT,
    order_user varchar(255),
    primary key (id)
)
