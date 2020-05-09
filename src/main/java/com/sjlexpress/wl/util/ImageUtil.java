package com.sjlexpress.wl.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成验证码图片
 */
public final class ImageUtil {

    private static final char[] CHARS = {'3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    //符号数量
    private static final int SIZE = 4;
    private static final int LINES = 4;
    private static final int WIDTH = 160;
    private static final int HEIGHT = 40;
    // 字体大小干扰线数
    private static final int FONT_SIZE = 25;
    // 验证码干扰线数
    private static final int LINE_COUNT = 50;

    /**
     * 生成验证码图片，封装与Map中。
     * 其中Map的key是验证码，Map的value是验证码图片。
     */
    public static BufferedImage createImage(String code) {

        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;

        x = WIDTH / (SIZE + 1);// 每个字符的宽度
        fontHeight = HEIGHT - 2;// 字体的高度
        codeY = HEIGHT - 3;

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // 创建字体
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);
        for (int i = 0; i < LINE_COUNT; i++) {
            int xs = random.nextInt(WIDTH);
            int ys = random.nextInt(HEIGHT);
            int xe = xs + random.nextInt(WIDTH / 8);
            int ye = ys + random.nextInt(HEIGHT / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < code.length(); i++) {
            String strRand = code.substring(i,i+1);
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }

        return buffImg;
    }

    public static Map<String, BufferedImage> createImageHard() {
        StringBuffer sb = new StringBuffer();
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphic = image.getGraphics();
        graphic.setColor(Color.LIGHT_GRAY);
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        Random ran = new Random();
        // 画随机字符
        for (int i = 1; i <= SIZE; i++) {
            int r = ran.nextInt(CHARS.length);
            graphic.setColor(getRandomColorHard());
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            graphic.drawString(CHARS[r] + "", (i - 1) * WIDTH / SIZE,
                    HEIGHT / 2);
            sb.append(CHARS[r]);// 将字符保存，存入Session
        }
        // 画干扰线
        for (int i = 1; i <= LINES + 3; i++) {
            Graphics2D g2D = (Graphics2D) graphic;
            g2D.setColor(getRandomColor());
            g2D.setStroke(new BasicStroke(3.5f));
            g2D.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
        map.put(sb.toString(), image);
        return map;
    }


    /**
     * 将图片转换为输入流
     */
    public static InputStream getInputStream(BufferedImage image)
            throws IOException {
        ByteArrayOutputStream out = null;
        byte[] b = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
            b = out.toByteArray();
            out.close();
            image.flush();
            image = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(100), ran.nextInt(100),
                ran.nextInt(100));
        return color;
    }

    private static Color getRandomColorHard() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(255), ran.nextInt(255),
                ran.nextInt(255));
        return color;
    }

    public static void main(String[] args) {
        //createImage();

    }
}
