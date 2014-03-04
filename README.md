PivotReportsAPI
======

API for generating pivot reports.

## Run using [ant](http://ant.apache.org/) in project directory

```bash
$ ant query -Dtable=Country -Dcolumn=Year -Drow=Product -Dmeasure="Total Cost"
...
...
     [java] Program initialized with arguments [Table category: Country, Column category: Year, Row category: Product, Measure: Total Cost].
     [java]
     [java] SQL> SELECT COUNTRY.Name, YEAR.Name, PRODUCT.Name,
     [java] (
     [java]     SELECT IFNULL(SUM(TOTALCOST), 0) FROM SALES
     [java]     WHERE SALES.COUNTRYId = COUNTRY.Id AND SALES.YEARId = YEAR.Id AND SALES.PRODUCTId = PRODUCT.Id
     [java] ) AS 'Measure'
     [java] FROM COUNTRY, YEAR, PRODUCT
     [java] ORDER BY COUNTRY.Name, YEAR.SortId, PRODUCT.Name
     [java]
     [java]
     [java] 2011                 | 0                    | 33.0                 | 55.0                 | 54.0                 | 0                    | 0                    |
     [java] 2014                 | 0                    | 0                    | 0                    | 0                    | 0                    | 0                    |
     [java] 2012                 | 0                    | 0                    | 44.0                 | 0                    | 0                    | 34.0                 |
     [java] 2013                 | 0                    | 23.0                 | 0                    | 0                    | 0                    | 0                    |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
     [java] Armenia              | Coat                 | Coca-Cola            | Coffee               | Hat                  | Kebab                | Skirt                |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
     [java]
     [java]
     [java] 2011                 | 0                    | 0                    | 0                    | 0                    | 0                    | 0                    |
     [java] 2014                 | 0                    | 0                    | 0                    | 65.0                 | 0                    | 0                    |
     [java] 2012                 | 0                    | 0                    | 0                    | 0                    | 0                    | 0                    |
     [java] 2013                 | 233.0                | 0                    | 0                    | 0                    | 0                    | 22.0                 |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
     [java] Brazil               | Coat                 | Coca-Cola            | Coffee               | Hat                  | Kebab                | Skirt                |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
     [java]
     [java]
     [java] 2011                 | 0                    | 0                    | 0                    | 0                    | 0                    | 0                    |
     [java] 2014                 | 0                    | 0                    | 0                    | 0                    | 88.0                 | 0                    |
     [java] 2012                 | 33.0                 | 0                    | 45.0                 | 0                    | 0                    | 0                    |
     [java] 2013                 | 0                    | 0                    | 0                    | 0                    | 234.0                | 0                    |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
     [java] USA                  | Coat                 | Coca-Cola            | Coffee               | Hat                  | Kebab                | Skirt                |
     [java] -----------------------------------------------------------------------------------------------------------------------------------------------------------------
...
```
