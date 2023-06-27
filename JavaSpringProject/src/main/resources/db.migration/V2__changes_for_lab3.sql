DROP TABLE IF EXISTS Сomment;
CREATE TABLE Сomment (
                         Id bigint unique PRIMARY KEY,
                         Сontent VARCHAR NOT NULL,
                         Author VARCHAR NOT NULL,
                         CreationDate DATE NOT NULL,
                         TaskId int references Task(Id)
);

ALTER TABLE Task
    ADD COLUMN IF NOT EXISTS Author VARCHAR;