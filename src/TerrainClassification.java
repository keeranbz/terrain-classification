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
   public static boolean constructTerrain(String filepath) {
        try {
            // read in the file and create terrain object
            File inputFile = new File(filepath);
            Scanner sc = new Scanner(inputFile);
            grid = new Terrain(sc.nextInt(), sc.nextInt());
            String line =  sc.nextLine();
            System.out.println("Loading terrain...");

            // Create GridCell objects and assign them to rows
            for (int r = 0; r < grid.getRows(); r++) {
                for(int c = 0; c < grid.getColumns(); c++) {
                    double height = Double.parseDouble(sc.next());
                    grid.setCell(new GridCell(height, r, c), r, c);
                             

                }
            }
            
            System.out.println("Loading complete!");
            sc.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }          
   }


   /** Finding the basin cells sequentially.
    * 
    * @return
    */
   public static void findBasinSequential() {
    // adding heights of neighbouring cells to non-boundary cells.
    outputCells.clear();
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
                if ( grid.getCell(r, c).getHeight() + 0.01 > pHeight ) {
                    grid.getCell(r, c).setBasin(false);
                }
            }
            outputCells.add(grid.getCell(r, c));
        }
    }
   }


   /** Finding the basin cells concurrently.
    * 
    * @return
    */
    public static void findBasinConcurrent() {
        
    }

    /** Finding the peak cells sequentially.
    * 
    * @return
    */
   public static void findPeakSequential() {
    // adding heights of neighbouring cells to non-boundary cells.
    outputCells.clear();
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

            grid.getCell(r, c).setPeak(true);
            for (double pHeight : peerHeights){
                if ( grid.getCell(r, c).getHeight() < pHeight + 0.01 ) {
                    grid.getCell(r, c).setPeak(false);
                }
            }
            outputCells.add(grid.getCell(r, c));
        }
    }
   }
   
   /**
    * Printing out basins and peaks.
    */
   public static void printOutput(boolean b) {
       int basins = 0;
       int peaks = 0;
       for (GridCell cell: outputCells) {
            if (cell.isBasin() & b ) {basins++;}
            else if (cell.isPeak()){peaks++;}
        }

       System.out.println();
       if(b) {System.out.println(basins);}
        else {System.out.println(peaks);}
       

       for (GridCell cell: outputCells) {
        if (cell.isBasin() & b) {
            System.out.println(cell.getRow() + " " + cell.getColumn() );
        }else if (cell.isPeak()) {
            System.out.println(cell.getRow() + " " + cell.getColumn() );
        }
    }
   }



   

   /**
    * Printing out basins and peaks to file.
    */
    public static void printOutput(String filename, boolean b) {
        int basins = 0;
        int peaks = 0;
        for (GridCell cell: outputCells) {
            if (cell.isBasin() & b ) {basins++;}
            else if (cell.isPeak()){peaks++;}
        }
        
        try {
            File outputFile = new File(filename);
	        FileOutputStream fos = new FileOutputStream(outputFile);
 	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            if(b) {bw.write(Integer.toString(basins));}
            else {bw.write(Integer.toString(peaks));}
            bw.newLine();
            
            for (GridCell cell: outputCells) {
                if (cell.isBasin() & b) {
                    bw.write(cell.getRow() + " " + cell.getColumn());
                    bw.newLine();
                }else if (cell.isPeak()) {
                    bw.write(cell.getRow() + " " + cell.getColumn());
                    bw.newLine();
                }
            }
            
            bw.close();

            System.out.println();
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
        boolean isRunning = false;
        Scanner scan = new Scanner(System.in);
        String filepath = "";
        int choice = 0;
        
        System.out.println("|||   Terrain Classification   |||");
        
        // loop to load data
        while (!isRunning) {
            System.out.println();
            System.out.println();
            System.out.println("Enter input data filename: [Must be within data/ directory!]");
            filepath = scan.nextLine();
            //String filepath = "data/small_in.txt";
            
            isRunning = constructTerrain("data/"+filepath);
        }

        // loop to run calculations
        while (isRunning) {
            System.out.println();
            System.out.println();

            System.out.println("Enter number to select option: ");
            System.out.println("0. Exit");
            System.out.println("1. Run Basin Simulation");
            System.out.println("2. Run Peak Simulation");
            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 0: 
                    isRunning = false;
                    break;
                case 1: 
                    findBasinSequential();
                    //findBasinConcurrent();
                     //printOutput("data/"+"output_"+filepath, true);
                    //printOutput(true);
                    printCellGrid(177, 273);
                    break;
                case 2: 
                    findPeakSequential();
                    // findBasinConcurrent();
                    //printOutput("data/"+"output_"+filepath, false);
                    printOutput(false);
                    break;

            }
            
        }

        
    }

    //#endregion
}