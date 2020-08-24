/** Constructs the terrain from input data.
 * 
 */
public class Terrain {
    //#region Terrain fields

    private int rows;
    private int columns;
    private GridCell[][] cell;

    //#endregion

    //#region Terrain Constructors

    /** Empty constructor.
     * 
     */
    public Terrain() {
    }


    /** Parameterized constructor.
     * 
     * @param rows Rows of the terrain grid.
     * @param columns Columns of the terrain grid.
     */
    public Terrain(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cell = new GridCell[rows][columns];
    }
    //#endregion

    //#region Terrain Properties

    /**
     * 
     * @return rows Get int of rows.
     */
    public int getRows() {
        return this.rows;
    }


    /**
     * 
     * @param rows Set int of rows.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }


    /**
     * 
     * @return Get int of columns.
     */
    public int getColumns() {
        return this.columns;
    }


    /**
     * 
     * @param columns Set int of columns.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    
    /**
     * 
     * @return Get GridCell
     */
    public GridCell getCell(int row, int column) {
        return this.cell[row][column];
    }

    /**
     * 
     * @param cell Set GridCell.
     */
    public void setCell(GridCell gridCell, int row, int column) {
        this.cell[row][column] = gridCell;
    }
    //#endregion

    //#region Terrain Methods

    


    //#endregion

}