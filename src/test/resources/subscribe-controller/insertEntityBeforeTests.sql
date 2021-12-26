INSERT INTO users(id, account_created_time, email, image, name, password)
VALUES ('9abc89b27d8f87e3017d8f892c3c0001', '2021-12-12', 'user@gmail.com', 'image', 'user', '1');

INSERT INTO users(id, account_created_time, email, image, name, password)
VALUES ('9abc89b27d8f87e3017d8f892c3c0002', '2021-12-13', 'artist@gmail.com', 'image', 'artist', '1');

INSERT INTO user_roles(user_id, roles)
VALUES ('9abc89b27d8f87e3017d8f892c3c0001', 'USER');
INSERT INTO user_roles(user_id, roles)
VALUES ('9abc89b27d8f87e3017d8f892c3c0002', 'USER');
INSERT INTO user_roles(user_id, roles)
VALUES ('9abc89b27d8f87e3017d8f892c3c0002', 'ARTIST');

INSERT INTO artists(id, monthly_listeners, number_of_subscribers, user_id)
VALUES ('9abc89b27d8f87e3017d8f892c3c0003', 0, 0, '9abc89b27d8f87e3017d8f892c3c0002');

