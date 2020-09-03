import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * NQueensBoard 相当于 MVC 中的数据层（Model），负责处理数据
 */
public class NQueensBoard {

    private boolean[] col;
    private boolean[] main;
    private boolean[] sub;
    private List<List<String>> res;
    private char[][] board;
    private int N;

    private boolean saveImg;

    /**
     * 计数器
     */
    private int count;

    public int getCount() {
        return count;
    }

    public boolean isSaveImg() {
        return saveImg;
    }

    private NQueensVisualizer visualizer;

    public NQueensBoard(int N) {
        this.N = N;
        board = new char[N][N];
    }

    public int N() {
        return N;
    }

    public List<List<String>> solveNQueens(NQueensVisualizer nQueensVisualizer) {
        this.visualizer = nQueensVisualizer;
        res = new ArrayList<>();
        col = new boolean[N];
        main = new boolean[2 * N - 1];
        sub = new boolean[2 * N - 1];
        Deque<Integer> path = new ArrayDeque<>(N);
        dfs(0, path);

        printResult(res);
        return res;
    }

    private void printResult(List<List<String>> res) {
        int size = res.size();
        for (int i = 0; i < size; i++) {
            System.out.println("结果 " + (i + 1) + "：");
            List<String> rows = res.get(i);
            for (String row : rows) {
                System.out.println(row);
            }
        }
        System.out.println(N + " 皇后问题结果输出完毕。");
    }

    private void dfs(int row, Deque<Integer> path) {
        if (row == N) {
            // 找到了一个解以后，暂停 3 秒
            AlgoVisHelper.pause(1000);
            count++;
            saveImg = true;
            visualizer.setData();
            saveImg = false;
            List<String> board = convert2board(path, N);
            res.add(board);
            return;
        }

        for (int i = 0; i < N; i++) {
            board[row][i] = 'G';
            visualizer.setData();
            if (col[i] || main[row + i] || sub[row - i + N - 1]) {
                board[row][i] = ' ';
                visualizer.setData();
                continue;
            }
            board[row][i] = ' ';
            visualizer.setData();

            path.addLast(i);
            col[i] = true;
            main[row + i] = true;
            sub[row - i + N - 1] = true;
            board[row][i] = 'Q';
            visualizer.setData();

            dfs(row + 1, path);

            sub[row - i + N - 1] = false;
            main[row + i] = false;
            col[i] = false;
            path.removeLast();

            board[row][i] = ' ';
            visualizer.setData();
        }
        visualizer.setData();
    }

    public char get(int x, int y) {
        // 不越界检查
        return board[x][y];
    }

    private java.util.List<String> convert2board(Deque<Integer> path, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : path) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(".");
            }
            stringBuilder.replace(num, num + 1, "Q");
            board.add(stringBuilder.toString());
        }
        return board;
    }
}
