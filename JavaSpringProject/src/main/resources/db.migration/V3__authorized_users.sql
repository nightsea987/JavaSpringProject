DROP TABLE IF EXISTS Authorized_Users;
CREATE TABLE Authorized_Users (
                         Id bigint unique PRIMARY KEY,
                         Username VARCHAR(255) NOT NULL,
                         Password VARCHAR(255) NOT NULL,
                         Role VARCHAR(255) NOT NULL
);

INSERT INTO Authorized_Users (Username, Password, Role)
VALUES ('admin', '1234', 'admin')