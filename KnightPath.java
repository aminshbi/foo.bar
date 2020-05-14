
public class KnightPath {

    public static int solution(int src, int dest) {

        // if input is invalid
        if(src < 0 || src > 63 || dest < 0 || dest > 63 || src == dest) return 0;

        // BFS Algorithm: "allNodes" to mark all chosen nodes
        int[] allNodes = new int[64];
        int[] queue = new int[64];

        // All possible ways for Knight moves
        int[] rowAdd = new int[]{1,1,-1,-1,2,2,-2,-2};
        int[] colAdd = new int[]{2,-2,2,-2,1,-1,1,-1};

        int current = src, queCurrentIndex = 0, queEndIndex = 0;

        // Initialize arrays
        for(int i=0;i<allNodes.length;i++){
            allNodes[i] = -1;
            queue[i] = 0;
        }

        // Mark firs node (source)
        allNodes[src] = 0;
        queue[queEndIndex++] = src;

        int currentRow;
        int currentCol;

        // Find dest
        while(current != dest){

            currentRow = current / 8;
            currentCol = current % 8;

            // for each node - find all reachable nodes
            for(int i=0 ; i < rowAdd.length ; i++){
                int row = currentRow + rowAdd[i];
                int col = currentCol + colAdd[i];

                // check if that row or col are valid
                if(isValid(row,col)){
                    int newFound = row * 8 + col;

                    // check if this node have not been chosen before
                    if(allNodes[newFound] < 0){
                        allNodes[newFound] = allNodes[current] +1;
                        queue[queEndIndex] = newFound;
                        queEndIndex++;
                    }
                }
            }
            // check the next node
            current = queue[queCurrentIndex++];
        }
        // return the value of how many steps from src to dest
        return allNodes[current];
    }

    public static boolean isValid(int row, int col){
        if(row < 0 || row > 7 || col < 0 || col > 7 ) return false;
        else return true;
    }

    public static void main(String[] args) throws Exception {
        // Your code here!

        int result = solution(0,1);


        System.out.println("\n Result is: " +result);


    }
}