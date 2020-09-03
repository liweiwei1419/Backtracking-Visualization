import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Frame 相当于 MVC 中的视图层，负责展示的相关逻辑
 */
public class NQueensFrame extends JFrame {

    private final int canvasWidth;
    private final int canvasHeight;

    public NQueensFrame(String title, int canvasWidth, int canvasHeight) {
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

    private void saveImage(int count) {
        // 得到窗口内容面板
        Container content = this.getContentPane();
        // 创建缓冲图片对象
        BufferedImage img = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        // 得到图形对象
        Graphics2D g2d = img.createGraphics();
        // 将窗口内容面板输出到图形对象中
        content.printAll(g2d);
        // 保存为图片
        File f = new File(data.N() + "-queens-solution" + "-" + count + ".jpg");
        try {
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 释放图形对象
        g2d.dispose();
    }

    private NQueensBoard data;

    public void render(NQueensBoard data) {
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
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.GoogleBule);
                    if (c == 'G') {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.GoogleGreen);
                    }
                    if (c == 'Q') {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.GoogleRed);
                    }
                    AlgoVisHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                }
            }

            // 如果已经得到了一个解，就输出图片
            if (data.isSaveImg()) {
                saveImage(data.getCount());
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}