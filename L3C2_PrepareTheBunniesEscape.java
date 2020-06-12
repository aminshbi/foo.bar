
public class L3C2_PrepareTheBunniesEscape {
    /*
    Solution:  1. BFS from source(0,0), when node is 1 BFS stops, when node is 0 BFS continue.
               2. BFS from source(w-1,h-1).
               3. For each "1 node" in original map, check if shortestFromStart + shortestFromEnd -1 is the minimum.
    Params: map
    Return: int result - shortest path
    */
    public static int solution(int[][] map) {
        if(map == null) return 0;

        int height = map.length;
        int width = map[0].length;
        final int MAX_VALUE = width * height;

        int[][] shortestFromStart = shortestPathBFS(map, 0);
        int[][] shortestFromEnd = shortestPathBFS(map, (height*width)-1);

        int min = shortestFromStart[height-1][width-1] < 0 ? MAX_VALUE : shortestFromStart[height-1][width-1];

        for(int i = 0 ; i< map.length ; i++){
            for(int j = 0; j<map[i].length;j++){
                if(map[i][j] == 1 && shortestFromStart[i][j] > 0 && shortestFromEnd[i][j] > 0 ){
                    int temp = shortestFromStart[i][j] + shortestFromEnd[i][j] - 1;
                    min = temp < min ? temp : min;
                }
            }
        }
        return min;
    }
    /*
    Solution:   1. BFS from wanted source.
                2. When node is 1 BFS stops (node is not added to queue).
                3. When node is zero, BFS continue normally.
    Params: map
    Return: matrix with shortest path to each node
    */
    public static int[][] shortestPathBFS(int[][] map, int src){
        if(map == null) return null;
        int height = map.length;
        int width = map[0].length;

        int[] queue = new int[width * height];
        int[][] result = new int[height][width];
        int currentQue = 0, endQue =0;

        int[] addRow = {1,-1,0,0};
        int[] addCol = {0,0,1,-1};
        // Initialize matrix
        for(int i = 0; i<height;i++){
            for(int j=0;j<width;j++){
                result[i][j]= -1;
            }
        }
        // Add source to queue
        queue[endQue++] = src;
        result[src/width][src%width] = 1;

        while(currentQue < endQue){
            int current = queue[currentQue];
            int currentRow = current/width;
            int currentCol = current%width;
            int row,col;
            // Check all available moves in matrix
            for(int i = 0;i<addRow.length;i++){
                row = currentRow + addRow[i];
                col = currentCol + addCol[i];
                // Check if row and col are valid, and if node has not been visited yet
                if(row >= 0 && row < height && col >= 0 && col < width){
                    if(result[row][col] == -1){
                        result[row][col] = result[currentRow][currentCol] + 1 ;
                        if(map[row][col] == 0) {
                            queue[endQue++] = row*width + col;
                        }
                    }
                }
            }
            currentQue++;
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] test1 = new int[][]{{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}};
        int[][] test2 = new int[][]{{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};
        int[][] test3 = new int[][]{{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}, {1,1,1,0}};
        int result1 = solution(test1);
        int result2 = solution(test2);
        int result3 = solution(test3);
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
        System.out.println("result3: " + result3);
    }
}
