INSERT INTO users(id, account_created_time, email, image, name, password)
VALUES ('9abc89b27d81e3ec017d81e54d830002', '2021-12-13', 'user@gmail.com', 'cover', 'name2', '1');

INSERT INTO users(id, account_created_time, email, image, name, password)
VALUES ('9abc89b27d81e3ec017d81e54d830001', '2021-12-13', 'artist@gmail.com', 'cover', 'name2', '1');

INSERT INTO artists(id, monthly_listeners, number_of_subscribers, user_id)
VALUES ('9abc89b27d756a23017d756aa83c0000', 0, 0, '9abc89b27d81e3ec017d81e54d830001');


INSERT INTO user_roles(user_id, roles) VALUES
('9abc89b27d81e3ec017d81e54d830002', 'USER');

INSERT INTO user_roles(user_id, roles) VALUES
('9abc89b27d81e3ec017d81e54d830001', 'USER');

INSERT INTO user_roles(user_id, roles)
VALUES ('9abc89b27d81e3ec017d81e54d830001', 'ARTIST');