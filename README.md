PivotReportsAPI
======

API for pivot report generation.

## Run using [ant](http://ant.apache.org/) in project directory

```bash
$ ant query -Dtable=Country -Dcolumn=Client -Drow=Year -Dmeasure="Total Cost"
...
...
SQL> SELECT COUNTRY.Name, YEAR.Name, CLIENT.Name,
(
    SELECT IFNULL(SUM(TOTALCOST), 0) FROM SALES
    WHERE SALES.COUNTRYId = COUNTRY.Id AND SALES.YEARId = YEAR.Id AND SALES.CLIENTId = CLIENT.Id
) AS 'Measure'
FROM COUNTRY, YEAR, CLIENT
ORDER BY COUNTRY.Name, YEAR.SortId, CLIENT.Name


2011                 | 0                    | 33.0                 | 109.0                |
2014                 | 0                    | 0                    | 0                    |
2012                 | 34.0                 | 0                    | 44.0                 |
2013                 | 23.0                 | 0                    | 0                    |
--------------------------------------------------------------------------------------------
Armenia              | Grand Tobacco        | SAS                  | US Navy              |
--------------------------------------------------------------------------------------------


2011                 | 0                    | 0                    | 0                    |
2014                 | 0                    | 65.0                 | 0                    |
2012                 | 0                    | 0                    | 0                    |
2013                 | 22.0                 | 0                    | 233.0                |
--------------------------------------------------------------------------------------------
Brazil               | Grand Tobacco        | SAS                  | US Navy              |
--------------------------------------------------------------------------------------------


2011                 | 0                    | 0                    | 0                    |
2014                 | 0                    | 0                    | 88.0                 |
2012                 | 0                    | 78.0                 | 0                    |
2013                 | 234.0                | 0                    | 0                    |
--------------------------------------------------------------------------------------------
USA                  | Grand Tobacco        | SAS                  | US Navy              |
--------------------------------------------------------------------------------------------
...
```

## For iteration interface

```bash
$ ant iterate -Dtable=Country -Dcolumn=Client -Drow=Year -Dmeasure="Total Cost"
...
...
SQL> SELECT COUNTRY.Name, YEAR.Name, CLIENT.Name,
(
    SELECT IFNULL(SUM(TOTALCOST), 0) FROM SALES
    WHERE SALES.COUNTRYId = COUNTRY.Id AND SALES.YEARId = YEAR.Id AND SALES.CLIENTId = CLIENT.Id
) AS 'Measure'
FROM COUNTRY, YEAR, CLIENT
ORDER BY COUNTRY.Name, YEAR.SortId, CLIENT.Name


Push enter to iterate, type '+' for full data or type 'QUIT' to exit

0
33.0
109.0
0
0
0
34.0
0
44.0
+
Armenia, 2013, Grand Tobacco, 23.0
+
Armenia, 2013, SAS, 0
+
Armenia, 2013, US Navy, 0
+
Brazil, 2011, Grand Tobacco, 0
+
Brazil, 2011, SAS, 0
+
Brazil, 2011, US Navy, 0
+
Brazil, 2014, Grand Tobacco, 0
65.0
0
...
DONE
```
## For sql interface

```bash
$ ant sql
...
...
SQL program initialized (to exit type 'QUIT').

SELECT * FROM PRODUCT

---------------------------------------------------------------------
Id                   | Name                 | ProductTypeId        |
---------------------------------------------------------------------
1                    | Coffee               | 1                    |
2                    | Coca-Cola            | 1                    |
3                    | Kebab                | 1                    |
4                    | Hat                  | 2                    |
5                    | Coat                 | 2                    |
6                    | Skirt                | 2                    |

...
```

## Requirements

 - **[Java](http://www.java.com/)** - Java is a general-purpose, concurrent, class-based, object-oriented computer programming language that is specifically designed to have as few implementation dependencies as possible.
 - **[ant](http://ant.apache.org/)** - Apache Ant is a Java library and command-line tool whose mission is to drive processes described in build files as targets and extension points dependent upon each other.
