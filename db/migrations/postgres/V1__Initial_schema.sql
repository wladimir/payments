-- https://www.forbes.com/advisor/banking/what-are-the-different-types-of-bank-accounts/
create type account_type as enum ('checking', 'savings', 'money market', 'certificate of deposit');
create type payment_status as enum ('created', 'sent', 'accepted', 'rejected');

create table customers
(
    id         uuid primary key,
    name       text        not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table accounts
(
    id          uuid primary key,
    customer_id uuid         not null,
    type        account_type not null,
    number      text         not null unique,
    created_at  timestamptz  not null default now(),
    updated_at  timestamptz  not null default now(),
    foreign key (customer_id) references customers (id)
);

create table payments
(
    id          uuid primary key,
    currency    char(3)     not null,
    amount      bigint      not null, -- in cents
    originator  uuid        not null,
    beneficiary uuid        not null,
    sender      uuid        not null,
    receiver    uuid        not null,
    created_at  timestamptz not null default now(),
    updated_at  timestamptz not null default now(),
    foreign key (originator) references customers (id),
    foreign key (beneficiary) references customers (id),
    foreign key (sender) references accounts (id),
    foreign key (receiver) references accounts (id)
);
