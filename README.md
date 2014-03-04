PivotReportsAPI
======

API for generating pivot reports.

## Run using [ant](http://ant.apache.org/) in project directory

```bash
$ ant query -Dtable=Country -Dcolumn=Year -Drow=Client -Dmeasure="Total Cost"
...
...
     [java] SQL> SELECT COUNTRY.Name, YEAR.Name, CLIENT.Name,
     [java] (
     [java]     SELECT IFNULL(SUM(TOTALCOST), 0) FROM SALES
     [java]     WHERE SALES.COUNTRYId = COUNTRY.Id AND SALES.YEARId = YEAR.Id AND SALES.CLIENTId = CLIENT.Id
     [java] ) AS 'Measure'
     [java] FROM COUNTRY, YEAR, CLIENT
     [java] ORDER BY COUNTRY.Name, YEAR.SortId, CLIENT.Name
     [java]
     [java]
     [java] 2011                 | 0                    | 33.0                 | 109.0                |
     [java] 2014                 | 0                    | 0                    | 0                    |
     [java] 2012                 | 34.0                 | 0                    | 44.0                 |
     [java] 2013                 | 23.0                 | 0                    | 0                    |
     [java] --------------------------------------------------------------------------------------------
     [java] Armenia              | Grand Tobacco        | SAS                  | US Navy              |
     [java] --------------------------------------------------------------------------------------------
     [java]
     [java]
     [java] 2011                 | 0                    | 0                    | 0                    |
     [java] 2014                 | 0                    | 65.0                 | 0                    |
     [java] 2012                 | 0                    | 0                    | 0                    |
     [java] 2013                 | 22.0                 | 0                    | 233.0                |
     [java] --------------------------------------------------------------------------------------------
     [java] Brazil               | Grand Tobacco        | SAS                  | US Navy              |
     [java] --------------------------------------------------------------------------------------------
     [java]
     [java]
     [java] 2011                 | 0                    | 0                    | 0                    |
     [java] 2014                 | 0                    | 0                    | 88.0                 |
     [java] 2012                 | 0                    | 78.0                 | 0                    |
     [java] 2013                 | 234.0                | 0                    | 0                    |
     [java] --------------------------------------------------------------------------------------------
     [java] USA                  | Grand Tobacco        | SAS                  | US Navy              |
     [java] --------------------------------------------------------------------------------------------
...
```
