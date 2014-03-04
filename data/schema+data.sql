
-- Table: Client
CREATE TABLE Client ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);

INSERT INTO [Client] ([Id], [Name]) VALUES (1, 'US Navy');
INSERT INTO [Client] ([Id], [Name]) VALUES (2, 'SAS');
INSERT INTO [Client] ([Id], [Name]) VALUES (3, 'Grand Tobacco');

-- Table: Country
CREATE TABLE Country ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);

INSERT INTO [Country] ([Id], [Name]) VALUES (1, 'Armenia');
INSERT INTO [Country] ([Id], [Name]) VALUES (2, 'USA');
INSERT INTO [Country] ([Id], [Name]) VALUES (3, 'Brazil');

-- Table: ProductType
CREATE TABLE ProductType ( 
    Id   INT            PRIMARY KEY
                        NOT NULL,
    Name VARCHAR( 45 )  NOT NULL
                        UNIQUE 
);

INSERT INTO [ProductType] ([Id], [Name]) VALUES (1, 'Food');
INSERT INTO [ProductType] ([Id], [Name]) VALUES (2, 'Clothing');

-- Table: Year
CREATE TABLE Year ( 
    Id     INT            PRIMARY KEY
                          NOT NULL,
    Name   VARCHAR( 45 )  NOT NULL
                          UNIQUE,
    SortId INT            NOT NULL 
);

INSERT INTO [Year] ([Id], [Name], [SortId]) VALUES (1, 2011, 1);
INSERT INTO [Year] ([Id], [Name], [SortId]) VALUES (2, 2012, 3);
INSERT INTO [Year] ([Id], [Name], [SortId]) VALUES (3, 2013, 4);
INSERT INTO [Year] ([Id], [Name], [SortId]) VALUES (4, 2014, 2);

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

INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (1, 'Coffee', 1);
INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (2, 'Coca-Cola', 1);
INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (3, 'Kebab', 1);
INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (4, 'Hat', 2);
INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (5, 'Coat', 2);
INSERT INTO [Product] ([Id], [Name], [ProductTypeId]) VALUES (6, 'Skirt', 2);

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

INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (1, 1, 1, 1, 11, 55.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (1, 2, 2, 2, 4, 45.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (2, 3, 3, 1, 6, 23.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (3, 4, 1, 2, 5, 88.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (2, 1, 2, 1, 8, 33.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (3, 3, 3, 2, 4, 234.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (1, 2, 1, 1, 2, 44.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (4, 1, 1, 1, 2, 54.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (5, 2, 2, 2, 5, 33.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (6, 3, 3, 3, 1, 22.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (4, 4, 2, 3, 12, 65.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (5, 3, 1, 3, 23, 233.0);
INSERT INTO [Sales] ([ProductId], [YearId], [ClientId], [CountryId], [Count], [TotalCost]) VALUES (6, 2, 3, 1, 6, 34.0);

-- Index: idx_Year
CREATE INDEX idx_Year ON Year ( 
    SortId ASC 
);

