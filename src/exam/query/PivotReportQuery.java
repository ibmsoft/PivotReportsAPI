package exam.query;

/**
 * Created by gevorg on 3/3/14.
 */
public class PivotReportQuery {
    private static final String SQL_TEMPLATE_FOR_PIVOT = "SELECT TABLE.Name, ROW.Name, COLUMN.Name, \n" +
            "(\n" +
            "\tSELECT IFNULL(SUM(MEASURE), 0) FROM SALES \n" +
            "\tWHERE SALES.TABLEId = TABLE.Id AND SALES.ROWId = ROW.Id AND SALES.COLUMNId = COLUMN.Id\n" +
            ") AS 'Measure'\n" +
            "FROM TABLE, ROW, COLUMN\n" +
            "ORDER BY TABLE.Name, ROW.Name, COLUMN.Name";

    private static final String SQL_PRODUCT_TYPE_INJECTION = "\tJOIN PRODUCT ON SALES.PRODUCTId = PRODUCT.Id\n";

    private Category tableCategory;
    private Category columnCategory;
    private Category rowCategory;
    private Measure measure;

    public PivotReportQuery(Category tableCategory, Category columnCategory, Category rowCategory, Measure measure) {
        this.tableCategory = tableCategory;
        this.columnCategory = columnCategory;
        this.rowCategory = rowCategory;
        this.measure = measure;
    }

    private String injectYearSortIdOrdering(String sql) {
        return sql.replace("YEAR.Name", "YEAR.SortId").replaceFirst("YEAR.SortId", "YEAR.Name");
    }

    private String injectProductTypeLogic(String sql) {
        if ( sql.contains("SALES.PRODUCTTYPEId") ) { // Injection required.
            if ( sql.contains("SALES.PRODUCTId") ) { // Validation.
                throw new IllegalArgumentException("Product Type and Product should not be used together!");
            }

            int injectionIndex = sql.indexOf("\tWHERE SALES.");
            sql = sql.substring(0, injectionIndex) + SQL_PRODUCT_TYPE_INJECTION +
                  sql.substring(injectionIndex).replace("SALES.PRODUCTTYPEId", "PRODUCT.PRODUCTTYPEId");
        }

        return sql;
    }

    public String getQueryReportSQL() {
        String queryReportSQL =  SQL_TEMPLATE_FOR_PIVOT.replaceAll("TABLE", tableCategory.toString()).
                replaceAll("COLUMN", columnCategory.toString()).replaceAll("ROW", rowCategory.toString()).
                replaceAll("MEASURE", measure.toString());

        // Injecting special cases.
        queryReportSQL = injectYearSortIdOrdering(queryReportSQL);
        queryReportSQL = injectProductTypeLogic(queryReportSQL);

        return queryReportSQL;
    }
}
