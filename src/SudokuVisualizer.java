import java.awt.*;

public class SudokuVisualizer {

    /**
     * 建议设置成 100 左右，便于观察
     */
    private static int DELAY = 300;
    private static int blockSide = 100;

    private SudokuBoard data;
    private SudokuFrame frame;

    public SudokuVisualizer(char[][] board) {
        data = new SudokuBoard(board);
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new SudokuFrame("数独问题回溯算法执行流程", sceneWidth, sceneHeight);

            new Thread(() -> {
                data.solveSudoku(this);
            }).start();
        });
    }

    public void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuVisualizer(board);
    }
}
