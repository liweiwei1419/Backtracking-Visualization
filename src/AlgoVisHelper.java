import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * 绘制图形的工具类
 */
public class AlgoVisHelper {

    private AlgoVisHelper() {
    }

    public static final Color GoogleBule = new Color(0x4688f1);
    public static final Color GoogleRed = new Color(0xe8453c);
    public static final Color GoogleGreen = new Color(0x3aa757);
    public static final Color GoogleYellow = new Color(0xfabb2d);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);

    // 这里是 Echarts 的配色方案（安琪给我的），因为查不到名字，用编号代替
    public static final Color EchartsColor1 = new Color(0xdcc192);
    public static final Color EchartsColor2 = new Color(0xb4c241);
    public static final Color EchartsColor3 = new Color(0x219876);
    public static final Color EchartsColor4 = new Color(0xe47d32);
    public static final Color EchartsColor5 = new Color(0x286871);
    public static final Color EchartsColor6 = new Color(0x277314);
    public static final Color EchartsColor7 = new Color(0x9dc969);
    public static final Color EchartsColor8 = new Color(0x64bfdd);
    public static final Color EchartsColor9 = new Color(0xf1a346);



    /**
     * 在屏幕坐标系中绘制矩形
     *
     * @param g
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public static void fillRectangle(Graphics2D g, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.fill(rectangle);
    }

    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D g, int w) {
        g.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void drawText(Graphics2D g, String text, int centerx, int centery) {
        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();

        // 设置字体和字号
        g.setFont(new Font("Menlo", Font.BOLD, 40));
        g.drawString(text, centerx - w / 2, centery + h);
    }
}