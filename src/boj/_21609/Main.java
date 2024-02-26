package _21609;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/21609">상어 중학교</a>
 * @category 
 * @Note
 */
class GameManager {
    private int[][] blockMap;
    private boolean[][] isVisited;
    private int score;

    static public GameManager createGameEngineByInput(int[][] inputColorMap){
        return new GameManager(inputColorMap);
    }

    public void autoPlay(){
        while(true){
            BlockGroup blockGroup = findMaxBlockGroup();
            if(blockGroup==null) break;
            updateScore(blockGroup);
            deleteBlockGroup(blockGroup);
            applyGravity();
            applyLeftRotation();
            applyGravity();
        }
    }

    private void applyGravity(){
        int bottomRow;
        int height;

        for(int col = 0; col<blockMap.length; col++){
            bottomRow = blockMap.length-1;
            height = 0;
            for(int row = blockMap.length-1; row>=0; row--){
                if(blockMap[row][col]== EMPTY_BLOCK) continue;
                if(blockMap[row][col]== BLACK_BLOCK){
                    bottomRow = row-1;
                    height = 0;
                    continue;
                }

                blockMap[bottomRow-height][col] = blockMap[row][col];
                if(bottomRow-height!=row) blockMap[row][col]=EMPTY_BLOCK;
                height++;
            }
        }
    }

    private void applyLeftRotation(){
        int[][] rotatedBlockMap = new int[blockMap.length][blockMap.length];
        for(int row=0; row<blockMap.length; row++){
            for(int col=0; col<blockMap.length; col++){
                rotatedBlockMap[row][col]=blockMap[col][blockMap.length-1-row];
            }
        }
        this.blockMap = rotatedBlockMap;
    }


    private BlockGroup findMaxBlockGroup(){
        BlockGroup maxBlockGroup = null;
        isVisited = new boolean[blockMap.length][blockMap.length];

        for(int row = 0; row<blockMap.length; row++){
            for(int col = 0; col<blockMap.length; col++){
                if(isVisited[row][col]) continue;
                if(blockMap[row][col]==RAINBOW_BLOCK) continue;
                if(blockMap[row][col]==BLACK_BLOCK) continue;
                if(blockMap[row][col]==EMPTY_BLOCK) continue;

                maxBlockGroup = maxBlockGroup(maxBlockGroup,findBlockGroupFrom(new BlockPosition(row,col)));
            }
        }

        return maxBlockGroup;
    }

    private BlockGroup findBlockGroupFrom(BlockPosition baseBlockPosition){
        boolean[][] isUsedRainbow = new boolean[blockMap.length][blockMap.length];
        int totalCount = 1;
        int rainbowCount = 0;
        int baseColor = blockMap[baseBlockPosition.row][baseBlockPosition.col];

        Queue<BlockPosition> q = new ArrayDeque<>();
        BlockPosition nowBlockPosition;
        int nextRow,nextCol;

        q.offer(baseBlockPosition);
        isVisited[baseBlockPosition.row][baseBlockPosition.col] = true;
        while(!q.isEmpty()){
            nowBlockPosition = q.poll();

            for(int i=0; i<4;i++){
                nextRow = nowBlockPosition.row+dRow[i];
                nextCol = nowBlockPosition.col+dCol[i];

                if(isIn(nextRow,nextCol) && blockMap[nextRow][nextCol]!= BLACK_BLOCK && blockMap[nextRow][nextCol]!= EMPTY_BLOCK){
                    if(blockMap[nextRow][nextCol]== RAINBOW_BLOCK && !isUsedRainbow[nextRow][nextCol]){
                        rainbowCount++;
                        totalCount++;

                        isUsedRainbow[nextRow][nextCol] = true;
                        q.offer(new BlockPosition(nextRow,nextCol));
                    }else if(blockMap[nextRow][nextCol]==baseColor && !isVisited[nextRow][nextCol]){
                        totalCount++;

                        isVisited[nextRow][nextCol] = true;
                        q.offer(new BlockPosition(nextRow,nextCol));
                    }
                }
            }
        }

        if(totalCount<2) return null;

        return new BlockGroup(totalCount,rainbowCount,baseBlockPosition);
    }

    private BlockGroup maxBlockGroup(BlockGroup bg1, BlockGroup bg2){
        if(bg1==null) return bg2;
        if(bg2==null) return bg1;

        if(bg1.totalCount==bg2.totalCount) {
            if(bg1.rainbowCount==bg2.rainbowCount) return bg2;

            return bg1.rainbowCount>bg2.rainbowCount ? bg1:bg2;
        }

        return bg1.totalCount>bg2.totalCount ? bg1:bg2;
    }

    private void updateScore(BlockGroup blockGroup){
        this.score += (blockGroup.totalCount*blockGroup.totalCount);
    }

    private void deleteBlockGroup(BlockGroup blockGroup){
        Queue<BlockPosition> q = new ArrayDeque<>();
        int baseColor = blockMap[blockGroup.baseBlockPosition.row][blockGroup.baseBlockPosition.col];

        q.offer(blockGroup.baseBlockPosition);
        blockMap[blockGroup.baseBlockPosition.row][blockGroup.baseBlockPosition.col] = EMPTY_BLOCK;

        BlockPosition nowBlockPosition;
        int nextRow,nextCol;
        while(!q.isEmpty()){
            nowBlockPosition = q.poll();
            for(int i=0; i<4;i++){
                nextRow = nowBlockPosition.row+dRow[i];
                nextCol = nowBlockPosition.col+dCol[i];

                if(isIn(nextRow,nextCol)
                        && blockMap[nextRow][nextCol]!= BLACK_BLOCK
                        && blockMap[nextRow][nextCol]!= EMPTY_BLOCK
                        && (blockMap[nextRow][nextCol]== RAINBOW_BLOCK || blockMap[nextRow][nextCol]==baseColor)){
                    blockMap[nextRow][nextCol] = EMPTY_BLOCK;
                    q.offer(new BlockPosition(nextRow,nextCol));
                }
            }
        }
    }

    private void printMap(){
        for(int i=0; i<blockMap.length; i++){
            System.out.println(Arrays.toString(blockMap[i]));
        }
    }
    private static int[] dRow ={1,0,-1,0};
    private static int[] dCol ={0,1,0,-1};
    private boolean isIn(int nextRow,int nextCol){
        return 0<= nextRow && nextRow< blockMap.length && 0<=nextCol && nextCol<blockMap.length;
    }

    static class BlockPosition{
        int row,col;

        public BlockPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class BlockGroup {
        private int totalCount;
        private int rainbowCount;
        private BlockPosition baseBlockPosition;

        public BlockGroup(int totalCount, int rainbowCount, BlockPosition baseBlockPosition) {
            this.totalCount = totalCount;
            this.rainbowCount = rainbowCount;
            this.baseBlockPosition = baseBlockPosition;
        }
    }

    static final int RAINBOW_BLOCK = 0;
    static final int BLACK_BLOCK = -1;
    static final int EMPTY_BLOCK = -9;

    private GameManager(int[][] blockMap) {
        this.blockMap = blockMap;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }
}

public class Main {
    static int answer;

    static int N,M;
    static int[][] inputColorMap;

    static void solution(){
        GameManager gameManager = GameManager.createGameEngineByInput(inputColorMap);
        gameManager.autoPlay();
        answer = gameManager.getScore();
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputColorMap = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                inputColorMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
