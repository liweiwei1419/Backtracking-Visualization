import java.awt.*;

/**
 * Visualizer 相当于 MVC 中的控制层，它是程序的入口
 */
public class NQueensVisualizer {

    /**
     * 建议设置成 100 左右，便于观察
     */
    private static int DELAY = 100;
    private static int blockSide = 100;

    private NQueensBoard data;
    private NQueensFrame frame;

    public NQueensVisualizer(int N) {
        data = new NQueensBoard(N);
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new NQueensFrame(N + " 皇后问题回溯算法执行流程", sceneWidth, sceneHeight);

            new Thread(() -> {
                data.solveNQueens(this);
            }).start();
        });
    }

    public void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        // 这个数值不宜太大
        int N = 4;
        new NQueensVisualizer(N);
    }
}
