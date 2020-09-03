import javax.swing.*;
import java.awt.*;

/**
 * Frame 相当于 MVC 中的视图层，负责展示的相关逻辑
 */
public class SudokuFrame extends JFrame {

    private final int canvasWidth;
    private final int canvasHeight;

    private final Color[] sudokuBoardColor = new Color[]{
            AlgoVisHelper.EchartsColor1,
            AlgoVisHelper.EchartsColor2,
            AlgoVisHelper.EchartsColor3,
            AlgoVisHelper.EchartsColor4,
            AlgoVisHelper.EchartsColor5,
            AlgoVisHelper.EchartsColor6,
            AlgoVisHelper.EchartsColor7,
            AlgoVisHelper.EchartsColor8,
            AlgoVisHelper.EchartsColor9
    };

    public SudokuFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private SudokuBoard data;

    public void render(SudokuBoard data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {
        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            // 具体绘制的逻辑在这里
            int w = canvasWidth / data.N();
            int h = canvasHeight / data.N();
            for (int i = 0; i < data.N(); i++) {
                for (int j = 0; j < data.N(); j++) {
                    char c = data.get(i, j);
                    AlgoVisHelper.setColor(g2d, sudokuBoardColor[i / 3 * 3 + j / 3]);

                    AlgoVisHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);

                    // 如果是原来就有的数字，显示黑色，当前正在探测的数字，显示红色
                    if (data.getOrigin()[i][j]) {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.Black);
                    } else {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.GoogleRed);
                    }
                    String text = c + "";
                    AlgoVisHelper.drawText(g2d, text, j * h + h / 2, i * w + w / 2);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}