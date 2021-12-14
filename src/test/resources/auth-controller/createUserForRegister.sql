DELETE FROM refresh_tokens WHERE TRUE;
DELETE FROM user_roles WHERE TRUE;
DELETE  FROM users WHERE TRUE;
INSERT INTO users(id, account_created_time, email, image, name, password) VALUES ('9abc89b27d81e3ec017d81e54d8312', '2021-12-12', 'existed@gmail.com', 'image cover', 'name', '1')