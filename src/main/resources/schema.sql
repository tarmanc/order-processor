create table if not exists item
(
    id          varchar(255)   not null
        primary key,
    description varchar(255)   null,
    item_name   varchar(255)   null,
    item_price  decimal(19, 2) null,
    quantity    bigint         not null
);