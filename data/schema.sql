
-- Table: Client
CREATE TABLE Client ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);


-- Table: Country
CREATE TABLE Country ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);


-- Table: ProductType
CREATE TABLE ProductType ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);


-- Table: Year
CREATE TABLE Year ( 
    Id     INT            PRIMARY KEY
                          NOT NULL,
    Name   VARCHAR( 45 )  NOT NULL
                          UNIQUE,
    SortId INT            NOT NULL 
);


-- Table: Product
CREATE TABLE Product ( 
    Id            INT            PRIMARY KEY
                                 NOT NULL,
    Name          VARCHAR( 45 )  NOT NULL
                                 UNIQUE,
    ProductTypeId INT            NOT NULL
                                 REFERENCES ProductType ( Id ) ON DELETE RESTRICT
                                                               ON UPDATE RESTRICT 
);


-- Table: Sales
CREATE TABLE Sales ( 
    ProductId INT    NOT NULL
                     REFERENCES Product ( Id ) ON DELETE RESTRICT
                                               ON UPDATE RESTRICT,
    YearId    INT    NOT NULL
                     REFERENCES Year ( Id ) ON DELETE RESTRICT
                                            ON UPDATE RESTRICT,
    ClientId  INT    NOT NULL
                     REFERENCES Client ( Id ) ON DELETE RESTRICT
                                              ON UPDATE RESTRICT,
    CountryId INT    NOT NULL
                     REFERENCES Country ( Id ) ON DELETE RESTRICT
                                               ON UPDATE RESTRICT,
    Count     INT    NOT NULL
                     DEFAULT ( 0 ),
    TotalCost DOUBLE NOT NULL
                     DEFAULT ( 0 ),
    PRIMARY KEY ( ProductId, YearId, ClientId, CountryId ) 
);


-- Index: idx_Year
CREATE INDEX idx_Year ON Year ( 
    SortId ASC 
);

