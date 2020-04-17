package com.edu.web.action;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/Verification_CodeServlet.do")
public class Verification_CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 80;
        int height = 30;
        //步骤一：在内存中生成一张图片
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);


        //步骤二：操作该图片，设置背景色及绘制边框
        Graphics graphics = bufferedImage.getGraphics();
        //设置背景色
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        //绘制边框
        graphics.setColor(Color.white);
        graphics.drawRect(0,0,width-1,height-1);


        //步骤三：生成随机4个字母或者数字，写入到图片中
        //角度旋转方法
        Graphics2D g2d = (Graphics2D)graphics;
        //设置字体属性
        g2d.setFont(new Font("楷体",Font.BOLD,20));
        //生成随机字母或数字
        String words = "QWERTYUIOPALSKDJFHGZMXCNVBqpweorituylaksdjfghzmxncbv0123456789";
        //保存随机字母或数字
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        int x = 10;
        for (int i = 0; i < 4; i++){
            //随机设置字的颜色
            g2d.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110)
                    ,20 + random.nextInt(110)));
            int index = random.nextInt(words.length());
            //获得指定位置字符
            char ch = words.charAt(index);
            //将产生字符存入
            str.append(ch);
            //旋转角度 -30 到 30
            int jiaodu = random.nextInt(60) - 30;
            //将角度转化成弧度：
            double theta = jiaodu * Math.PI / 180;
            //旋转
            g2d.rotate(theta,x,20);
            g2d.drawString(String.valueOf(ch),x,20);
            //旋转到原来
            g2d.rotate(-theta,x,20);
            x += 18;
        }
        //将产生的字符串存入session
        req.getSession().setAttribute("verificationCode",str.toString());

        /*
        //绘制干扰线
        graphics.setColor(getRandColor(160,200));
        int x1,x2,y1,y2;
        for(int i = 0; i < 30; i++){
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1,y1,x1 + x2,x2 + y2);
        }
         */

        //步骤四：将内存中图片，进行输出
        graphics.dispose(); //释放资源
        ImageIO.write(bufferedImage,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private Color getRandColor(int fc,int bc){
        //取其随机颜色
        Random random = new Random();
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
