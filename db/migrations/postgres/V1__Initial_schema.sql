create table customers
(
    id   uuid primary key,
    name text not null
);

create table accounts
(
    id          uuid primary key,
    customer_id uuid not null,
    type        text not null,
    number      text not null unique,
    foreign key (customer_id) references customers (id)
);

create table payments
(
    id          uuid primary key,
    key         text    not null unique,
    currency    char(3) not null,
    amount      bigint  not null, -- in cents
    originator  uuid    not null,
    beneficiary uuid    not null,
    sender      uuid    not null,
    receiver    uuid    not null,
    status      text    not null,
    foreign key (originator) references customers (id),
    foreign key (beneficiary) references customers (id),
    foreign key (sender) references accounts (id),
    foreign key (receiver) references accounts (id)
);
