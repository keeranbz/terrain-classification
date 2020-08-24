/**
 * GridCell class containing cell information.
 */
public class GridCell {
    //#region Fields

    private double height;
    private int row;
    private int column;
    private boolean basin;
    private boolean peak;

    //#endregion

    //#region Constructors
    
    /** Empty Constructor
     * 
     */
    public GridCell() {
        this.basin = false;
        this.peak = false;
    }


    /** Parameterized Cosntructor
     *  
     * @param height Height of the cell.
     * @param row Cell row location.
     * @param column Cell column location.
     */
    public GridCell(double height, int row, int column) {
        this.height = height;
        this.row = row;
        this.column = column;
        this.basin = false;
        this.peak = false;
    }


    //#endregion

    //#region Property methods

    /**
     *  Returns height of cell.
     * @return
     */
    public double getHeight() {
        return this.height;
    }

    
    /**
     *  Sets height of cell.
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    
    /**
     *   Returns row of cell.
     * @return
     */
    public int getRow() {
        return this.row;
    }

    
    /**
     * Sets row of cell.
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    
    /**
     *  Returns column of cell.
     * @return
     */
    public int getColumn() {
        return this.column;
    }

    /**
     *  Sets column of cell.
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Return basin value.
     * @return
     */
    public boolean isBasin() {
        return this.basin;
    }

    /**
     * Sets basin value.
     * @param basin
     */
    public void setBasin(boolean basin) {
        this.basin = basin;
    }

    
    /**
     *  Returns peak value.
     * @return
     */
    public boolean isPeak() {
        return this.peak;
    }

    /**
     * Sets peak value.
     * @param peak
     */
    public void setPeak(boolean peak) {
        this.peak = peak;
    }

    //#endregion


    //#region Methods

    /** String summary of fields.
     *  
     */
    @Override
    public String toString() {
        return "{" +
            " height='" + getHeight() + "'" +
            ", row='" + getRow() + "'" +
            ", column='" + getColumn() + "'" +
            ", basin='" + isBasin() + "'" +
            ", peak='" + isPeak() + "'" +
            "}";
    }
    //#endregion


}