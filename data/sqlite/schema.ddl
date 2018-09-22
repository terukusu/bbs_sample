DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS message;
CREATE TABLE person (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mail VARCHAR(255) UNIQUE, password VARCHAR(255), created_at INTEGER, updated_at INTEGER);
CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT, writer_id INTEGER, message TEXT, created_at INTEGER, updated_at INTEGER);
CREATE INDEX idx_message_writer_id ON message(writer_id)
