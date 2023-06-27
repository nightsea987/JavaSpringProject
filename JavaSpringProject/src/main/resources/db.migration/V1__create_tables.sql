DROP TABLE IF EXISTS Employee;
CREATE TABLE Employee (
                          Id bigint unique PRIMARY KEY,
                          Name VARCHAR NOT NULL,
                          Birthday int NOT NULL
);

DROP TABLE IF EXISTS Task;
CREATE TABLE Task (
                      Id bigint unique PRIMARY KEY,
                      Name VARCHAR NOT NULL,
                      Deadline DATE NOT NULL,
                      Description int NOT NULL,
                      Type VARCHAR NOT NULL,
                      EmployeeId int references Employee(Id)
);