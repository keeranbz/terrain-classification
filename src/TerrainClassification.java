import java.io.*; 
import java.util.Scanner; 
import java.util.ArrayList;

/** Driver class for the Terrain Classification application.
 */
public class TerrainClassification {
    //#region Statif variables

    private static Terrain grid;
    private static ArrayList<GridCell> outputCells = new ArrayList<GridCell>();
    

    //#endregion
   
   //#region Static methods


    /** Generates Terrain from file input
    * 
    * @param filename Input file name of terrain data. 
    * @return True when terrain generation is complete.
    */
   public static void constructTerrain(String filepath) {
        try {
            // read in the file and create terrain object
            File inputFile = new File(filepath);
            Scanner sc = new Scanner(inputFile);
            grid = new Terrain(sc.nextInt(), sc.nextInt());
            String line =  sc.nextLine();
            System.out.println("Loading...");

            // Create GridCell objects and assign them to rows
            for (int r = 0; r < grid.getRows(); r++) {
                for(int c = 0; c < grid.getColumns(); c++) {
                    double height = Double.parseDouble(sc.next());
                    grid.setCell(new GridCell(height, r, c), r, c);
                             

                }
            }
            
            sc.close();
            System.out.println("Loading complete.");

        } catch (Exception e) {
            System.out.println(e);
        }          
   }


   /** Finding the basin cells sequentially.
    * 
    * @return
    */
   public static void findBasinSequential() {
    // adding heights of neighbouring cells to non-boundary cells.
    for (int r = 1; r < grid.getRows() - 1; r++) {
        for(int c = 1; c < grid.getColumns() - 1; c++) {
            double[] peerHeights = new double[8];
            peerHeights[0] = grid.getCell(r-1, c-1).getHeight();
            peerHeights[1] = grid.getCell(r-1, c).getHeight();
            peerHeights[2] = grid.getCell(r-1, c+1).getHeight();
            peerHeights[3] = grid.getCell(r, c-1).getHeight();
            peerHeights[4] = grid.getCell(r, c+1).getHeight();
            peerHeights[5] = grid.getCell(r+1, c-1).getHeight();
            peerHeights[6] = grid.getCell(r+1, c).getHeight();
            peerHeights[7] = grid.getCell(r+1, c+1).getHeight();

            grid.getCell(r, c).setBasin(true);
            for (double pHeight : peerHeights){
                if ( grid.getCell(r, c).getHeight() > pHeight ) {
                    grid.getCell(r, c).setBasin(false);
                }
            }
            outputCells.add(grid.getCell(r, c));
        }
    }
   }
   
   /**
    * Printing out basins and peaks.
    */
   public static void printOutput() {
       int basins = 0;
       for (GridCell cell: outputCells) {
           if (cell.isBasin()) {basins++;}
       }

       System.out.println(basins);

       for (GridCell cell: outputCells) {
        if (cell.isBasin()) {
            System.out.println(cell.getRow() + " " + cell.getColumn() );
        }
    }
   }

   /**
    * Printing out basins and peaks to file.
    */
    public static void printOutput(String filename) {
        int basins = 0;
        for (GridCell cell: outputCells) {
            if (cell.isBasin()) {basins++;}
        }
        
        try {
            File outputFile = new File(filename);
	        FileOutputStream fos = new FileOutputStream(outputFile);
 	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            bw.write(Integer.toString(basins));
            bw.newLine();
            
            for (GridCell cell: outputCells) {
                if (cell.isBasin()) {
                    bw.write(cell.getRow() + " " + cell.getColumn());
                    bw.newLine();
                }
            }
            
            bw.close();

            System.out.println("File " + filename + " has been created successfully.");



        }catch (Exception e){
            System.out.println(e);
        }
    }
   
   
   /**  Prints grid of cell with surrounding neighbours.
    *   
    * @param r Row of cell.
    * @param c Column of cell.
    */
   public static void printCellGrid(int r, int c) {
    System.out.print(grid.getCell(r-1, c-1).getHeight() + "\t\t");
    System.out.print(grid.getCell(r-1, c).getHeight() + "\t\t");
    System.out.println(grid.getCell(r-1, c+1).getHeight());
    
    System.out.print(grid.getCell(r, c-1).getHeight() + "\t\t");
    System.out.print(grid.getCell(r, c).getHeight() + "\t\t");
    System.out.println(grid.getCell(r, c+1).getHeight());
    
    System.out.print(grid.getCell(r+1, c-1).getHeight() + "\t\t");
    System.out.print(grid.getCell(r+1, c).getHeight() + "\t\t");
    System.out.println(grid.getCell(r+1, c+1).getHeight());
   }
   //#endregion
   


    //#region Main Method

    /** Java main method for driver class.
     * @param args List of arguments.
     */
    public static void main (String[] args) {
        String filepath = "data/small_in.txt";

        constructTerrain(filepath); // should be selected by GUI
        findBasinSequential();
        printOutput("data/test_out_small.txt");
    }

    //#endregion
}