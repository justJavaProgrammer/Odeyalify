DELETE FROM playlists_songs WHERE TRUE;
DELETE FROM playlists_users WHERE TRUE;
DELETE FROM playlists WHERE TRUE;
DELETE from artists_albums WHERE TRUE;
DELETE from albums_songs WHERE TRUE;
DELETE from songs WHERE TRUE;
DELETE FROM albums WHERE TRUE;
DELETE FROM artists WHERE TRUE;
DELETE FROM users WHERE TRUE;

INSERT INTO users(id, account_created_time, email, image, name, password)
VALUES ('9abc89b27d81e3ec017d81e54d830000', '2021-12-12', '1@gmail.com', 'image cover', 'name', '1');
INSERT INTO users(id, account_created_time, email, image, name, password) VALUES ('9abc89b27d81e3ec017d81e54d830002', '2021-12-13', '212@gmail.com', 'cover', 'name2', '1');
INSERT INTO artists(id, monthly_listeners, number_of_subscribers, user_id) VALUES
('9abc89b27d756a23017d756aa83c0000', 0, 0, '9abc89b27d81e3ec017d81e54d830000');
INSERT INTO albums(id, album_name, cover_image_url, song_count, year_issue, artist_id) VALUES
('9abc89b27d8f8227017d8f8375d00001', 'Album name', 'cover',
 2, 2019, '9abc89b27d756a23017d756aa83c0000');

INSERT INTO songs(song_id, auditions, file_path, name, song_cover, album_id, artist_id)
VALUES ('9abc89b27d8f8227017d8f8375d10002', 0, 'C:\Users\thepr_2iz2cnv\IdeaProjects\spotify\src\main\resources\music\0a20fcf9f4dc4410a52b0c297f63a5ccfileFILE_ID9abc89b27d61dcc9017d61dcee640000.mp3',
        'Song_name', 'cover', '9abc89b27d8f8227017d8f8375d00001', '9abc89b27d756a23017d756aa83c0000');
INSERT INTO songs(song_id, auditions, file_path, name, song_cover, album_id, artist_id)
VALUES ('9abc89b27d8f8227017d8f8375d1001', 0, 'C:\Users\thepr_2iz2cnv\IdeaProjects\spotify\src\main\resources\music\0b76670deb914301a9035261770054effilesFILE_ID9abc89b27d755ca9017d755d3e160000.mp3',
        'Song_name', 'cover', '9abc89b27d8f8227017d8f8375d00001', '9abc89b27d756a23017d756aa83c0000');




