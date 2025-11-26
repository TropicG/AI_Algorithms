public class MapColoringAustralia {

    // all the regions in Australia
    static public String[] regions = {"WA", "NT", "SA", "Q", "NSW", "V", "T"};
    // all the names of the colors
    static String[] colorName = {"No color", "Red", "Green", "Blue"};
    static int numColors = 4;

    // in this array the regions of Australia are painted with the specific color
    static int[] assignment = new int[7];

    // each region has its own neighbors, it is important on those indexes two different colors to be put
    static int[][] neighbors = {
            {1, 2},
            {0, 2, 3},
            {0, 1, 3, 4, 5},
            {1, 2, 4},
            {2, 3, 5},
            {2, 4},
            {}
    };

    public static void main(String[] main) {
        // algorithm is started with WA
        if (solveCSP(0)) {
            printSolution();
        } else {
            System.out.println("No solution");
        }
    }

    static boolean solveCSP(int regionIndex) {

        // end of recursion, regionIndex is equal to regions.length only if every region is painted without errors
        if(regionIndex == regions.length) {
            return true;
        }

        // trying each region with the colors: RED, GREEN, BLUE
        for(int color = 1; color < numColors; color++) {

            // if it safe to place this color to this region
            if(isSafe(regionIndex, color)) {

                // placing a color for the region
                assignment[regionIndex] = color;

                // trying for the next regions
                if(solveCSP(regionIndex + 1)) {
                    return true;
                }

                // Backtracking, reseting the color if in the future there is a conflict
                assignment[regionIndex] = 0;
            }
        }

        return false;
    }

    static boolean isSafe(int regionIndex, int color) {
        int[] currentNeighbors = neighbors[regionIndex];

        // checks for each neighbor do they have the same color with the current neighbor
        for(int neighborIndex : currentNeighbors) {

            if(assignment[neighborIndex] == color) {
                return false;
            }
        }

        return true;
    }

    static void printSolution() {
        System.out.println("Solution found");
        for (int i = 0; i < regions.length; i++) {
            System.out.println(regions[i] + ": " + colorName[assignment[i]]);
        }
    }

}
