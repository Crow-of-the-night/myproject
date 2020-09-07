package org.sheehan.fortune.service.Impl;

import org.sheehan.fortune.service.PictureCurator;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class PictureCuratorImpl implements PictureCurator {

    @Override
    public void CuratePicture(String[] participants) throws IOException {
        Color[] colors = {new Color(110,109,109),new Color(255,170,113),new Color(255,113,113)};
        BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        // create graphics
        Graphics2D graphics = image.createGraphics();
        //set graphics profile
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        if(participants.length%2==0){
            for(int i = 0; i<participants.length; i++){
                graphics.setPaint(colors[i%2]);
                graphics.drawArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
                graphics.fillArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
            }
        } else if((participants.length-1)%3==0){
            for(int i = 0; i<participants.length-1; i++){
                graphics.setPaint(colors[i%3]);
                graphics.drawArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
                graphics.fillArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
            }
            graphics.setPaint(colors[1]);
            graphics.drawArc(0,0,400,400,90+((participants.length-1)*360/participants.length),360/participants.length);
            graphics.fillArc(0,0,400,400,90+((participants.length-1)*360/participants.length),360/participants.length);
        } else {
            for(int i = 0; i<participants.length; i++){
                graphics.setPaint(colors[i%3]);
                graphics.drawArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
                graphics.fillArc(0,0,400,400,90+(i*360/participants.length),360/participants.length);
            }
        }

        graphics.rotate(Math.PI/participants.length,200,200);
        graphics.setFont(new Font("宋体",Font.PLAIN,20));
        graphics.setPaint(Color.BLACK);
        for(int p = 0; p<participants.length; p++){
            char[] strcha = participants[p].toCharArray();
            int strWidth = graphics.getFontMetrics().charsWidth(strcha, 0, participants[p].length());
            graphics.drawString(participants[p],200-strWidth/2,20);
            graphics.rotate((2*Math.PI)/participants.length,200,200);
        }
        graphics.dispose();
        ImageIO.write(image, "png", new File("result.png"));
    }

    @Override
    public void CurateRectanglePicture(String[] participants) {
        BufferedImage image = new BufferedImage(100, 30*participants.length, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics.setFont(new Font("宋体",Font.PLAIN,20));
        graphics.setPaint(Color.BLACK);
        for(int p = 0; p<participants.length; p++) {
            char[] strcha = participants[p].toCharArray();
            int strWidth = graphics.getFontMetrics().charsWidth(strcha, 0, participants[p].length());
            graphics.drawString(participants[p], 50 - strWidth / 2, (p+1)*30-10);
        }
        graphics.dispose();
        try {
            ImageIO.write(image, "png", new File("result.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
