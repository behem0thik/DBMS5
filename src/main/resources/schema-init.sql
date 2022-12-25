DROP TABLE IF EXISTS public.posts_tags;
DROP TABLE IF EXISTS public.posts;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.tags;

CREATE TABLE public.users
(
    user_id    VARCHAR(255) PRIMARY KEY            NOT NULL,
    username   VARCHAR(255)                        NOT NULL,
    password   VARCHAR(255)                        NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated_at TIMESTAMP DEFAULT current_timestamp NOT NULL
);

CREATE TABLE public.posts
(
    post_id      VARCHAR(255) PRIMARY KEY            NOT NULL,
    user_id      VARCHAR(255)                        NOT NULL,
    article      VARCHAR(100)                        NOT NULL,
    post_content OID                                 NOT NULL,
    image        BYTEA                               NULL,
    created_at   TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated_at   TIMESTAMP DEFAULT current_timestamp NOT NULL,
    CONSTRAINT FK_posts_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE public.tags
(
    tag_id   VARCHAR(255) PRIMARY KEY NOT NULL,
    tag_name VARCHAR(255)             NOT NULL
);

CREATE TABLE public.posts_tags
(
    tag_id  VARCHAR(255) NOT NULL,
    post_id VARCHAR(255) NOT NULL,
    CONSTRAINT FK1_posts_tags FOREIGN KEY (post_id) REFERENCES posts (post_id),
    CONSTRAINT FK2_posts_tags FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);

