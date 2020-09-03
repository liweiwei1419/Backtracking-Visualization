import java.util.Arrays;

public class SudokuBoard {

    private char[][] board;
    private int N = 9;

    private boolean[][] origin = new boolean[N][N];

    private SudokuVisualizer visualizer;

    public boolean[][] getOrigin() {
        return origin;
    }

    public int N() {
        return N;
    }

    public SudokuBoard(char[][] board) {
        this.board = board;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != '.') {
                    origin[i][j] = true;
                }
            }
        }
    }

    public char get(int x, int y) {
        // 忽略参数检查
        return board[x][y];
    }

    private boolean[][] row;
    private boolean[][] col;
    private boolean[][] box;

    public void solveSudoku(SudokuVisualizer sudokuVisualizer) {
        visualizer = sudokuVisualizer;
        row = new boolean[9][9];
        col = new boolean[9][9];
        box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int index = board[i][j] - '1';

                    row[i][index] = true;
                    col[j][index] = true;
                    box[i / 3 * 3 + j / 3][index] = true;
                }
            }
        }
        dfs(board, 0, 0);
        // 输出结果
        printResult(board);
    }

    private boolean dfs(char[][] board, int i, int j) {
        if (i > 8) {
            visualizer.setData();
            return true;
        }
        if (board[i][j] == '.') {
            for (char ch = '1'; ch <= '9'; ch++) {
                int index = ch - '1';

                board[i][j] = ch;
                visualizer.setData();

                if (row[i][index] || col[j][index] || box[i / 3 * 3 + j / 3][index]) {
                    board[i][j] = '.';
                    visualizer.setData();
                    continue;
                }

                board[i][j] = ch;
                row[i][index] = true;
                col[j][index] = true;
                box[i / 3 * 3 + j / 3][index] = true;

                visualizer.setData();
                if (dfs(board, i + (j + 1) / 9, (j + 1) % 9)) {
                    return true;
                }


                board[i][j] = '.';
                row[i][index] = false;
                col[j][index] = false;
                box[i / 3 * 3 + j / 3][index] = false;
                visualizer.setData();
            }
        } else {
            return dfs(board, i + (j + 1) / 9, (j + 1) % 9);
        }
        return false;
    }

    private void printResult(char[][] board) {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("数独问题结果输出完毕。");
    }
}
