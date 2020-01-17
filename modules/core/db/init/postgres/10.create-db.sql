-- begin CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER
create table CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    --
    primary key (ID)
)^
-- end CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER
-- begin CHECKCOMPOSITIONOFFRAGMENTS_ORDER
create table CHECKCOMPOSITIONOFFRAGMENTS_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    CUSTOMER_ID uuid,
    --
    primary key (ID)
)^
-- end CHECKCOMPOSITIONOFFRAGMENTS_ORDER
-- begin CHECKCOMPOSITIONOFFRAGMENTS_ORDER_ITEM
create table CHECKCOMPOSITIONOFFRAGMENTS_ORDER_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid,
    ORDER_ID uuid,
    --
    primary key (ID)
)^
-- end CHECKCOMPOSITIONOFFRAGMENTS_ORDER_ITEM
-- begin CHECKCOMPOSITIONOFFRAGMENTS_PRODUCT
create table CHECKCOMPOSITIONOFFRAGMENTS_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end CHECKCOMPOSITIONOFFRAGMENTS_PRODUCT
-- begin CUBADBVIEWS_ORDER_WITH_PRODUCTS
create or replace view CHECKCOMPOSITIONOFFRAGMENTS_ORDER_WITH_CUSTOMER_VIEW as
select o.*,
       c.first_name || ' ' || c.last_name as customer
from CHECKCOMPOSITIONOFFRAGMENTS_ORDER as o
         left join CHECKCOMPOSITIONOFFRAGMENTS_CUSTOMER c on o.customer_id = c.id^
-- end CUBADBVIEWS_ORDER_WITH_PRODUCTS