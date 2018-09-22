DELETE FROM person;
DELETE FROM message;
INSERT INTO person (id, name, mail, password, created_at, updated_at) VALUES (1,'山田 太郎', 'foo1@bar.com', 'hogehoge', 1536960887, 1536961887),(2,'渡辺 花子', 'foo2@bar.com', 'hogehoge', 1536970887, 1536971887),(3,'カルロス マルチネス', 'foo3@bar.com', 'hogehoge', 1536980887, 1536980897);
INSERT INTO message (id, writer_id, message, created_at, updated_at) VALUES (1, 1, 'テスト書き込みその1です。', 1537628110, 1537628110),(2, 1, 'テスト書き込みその2です。', 1537628115, 1537628115),(3, 2, 'テスト書き込みその3です。', 1537628210, 1537628210);
