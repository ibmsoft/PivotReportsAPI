PivotReportsAPI
======

API for generating pivot reports.

## Run using [ant](http://ant.apache.org/) in project directory

```bash
$ ant query -Dtable=Country -Dcolumn=Year -Drow=Client -Dmeasure="Total Cost"
...
...
SQL> SELECT COUNTRY.Name, CLIENT.Name, YEAR.Name,
(
    SELECT IFNULL(SUM(TOTALCOST), 0) FROM SALES
    WHERE SALES.COUNTRYId = COUNTRY.Id AND SALES.CLIENTId = CLIENT.Id AND SALES.YEARId = YEAR.Id
) AS 'Measure'
FROM COUNTRY, CLIENT, YEAR
ORDER BY COUNTRY.Name, CLIENT.Name, YEAR.SortId


Grand Tobacco        | 0                    | 0                    | 34.0                 | 23.0                 |
SAS                  | 33.0                 | 0                    | 0                    | 0                    |
US Navy              | 109.0                | 0                    | 44.0                 | 0                    |
-------------------------------------------------------------------------------------------------------------------
Armenia              | 2011                 | 2014                 | 2012                 | 2013                 |
-------------------------------------------------------------------------------------------------------------------


Grand Tobacco        | 0                    | 0                    | 0                    | 22.0                 |
SAS                  | 0                    | 65.0                 | 0                    | 0                    |
US Navy              | 0                    | 0                    | 0                    | 233.0                |
-------------------------------------------------------------------------------------------------------------------
Brazil               | 2011                 | 2014                 | 2012                 | 2013                 |
-------------------------------------------------------------------------------------------------------------------


Grand Tobacco        | 0                    | 0                    | 0                    | 234.0                |
SAS                  | 0                    | 0                    | 78.0                 | 0                    |
US Navy              | 0                    | 88.0                 | 0                    | 0                    |
-------------------------------------------------------------------------------------------------------------------
USA                  | 2011                 | 2014                 | 2012                 | 2013                 |
-------------------------------------------------------------------------------------------------------------------
...
```
