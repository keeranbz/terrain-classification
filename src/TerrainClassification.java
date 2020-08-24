import java.io.File; 
import java.util.Scanner; 

/** Driver class for the Terrain Classification application.
 */
public class TerrainClassification {
    //#region Statif variables

    private static Terrain grid;

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
            System.out.println("Loading...");

            // Create GridCell objects and assign them to rows
            for (int r = 0; r < grid.getRows(); r++) {
                for(int c = 0; c < grid.getColumns(); c++) {
                    double height = Double.parseDouble(sc.next());
                    grid.setCell(new GridCell(height, r, c), r, c);
                }
            }
            
            System.out.println("Loading complete.");
            sc.close();

            return true;

        } catch (Exception e) {
            System.out.println(e);


            return false;
        }          
   }

   //#endregion
   


    //#region Main Method

    /** Java main method for driver class.
     * @param args List of arguments.
     */
    public static void main (String[] args) {
        String filepath = "data/small_in.txt";
        boolean gridGenerated = false;

        gridGenerated = constructTerrain(filepath); // should be selected by GUI
        System.out.println(gridGenerated);
    }

    //#endregion
}