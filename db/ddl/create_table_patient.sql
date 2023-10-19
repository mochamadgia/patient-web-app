CREATE TABLE public.patient
(
    pid        bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname  varchar NULL,
    lastname   varchar NULL,
    dob        varchar NULL,
    gender     varchar NULL,
    phoneno    varchar NULL,
    address_id bigint NULL
);
