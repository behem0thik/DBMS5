DROP TABLE IF EXISTS public.posts;
DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users
(
    user_id    VARCHAR(255) PRIMARY KEY            NOT NULL,
    username   VARCHAR(255)                        NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMP DEFAULT current_timestamp NOT NULL
);

CREATE TABLE public.posts
(
    post_id      VARCHAR(255) PRIMARY KEY            NOT NULL,
    user_id      VARCHAR(255)                        NOT NULL,
    article      VARCHAR(255)                        NOT NULL,
    post_content VARCHAR(255)                        NOT NULL,
    image        BYTEA                               NOT NULL,
    created_at   TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated_at   TIMESTAMP DEFAULT current_timestamp NOT NULL,
    post_category     VARCHAR                             NOT NULL,
    CONSTRAINT FK_posts_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);



