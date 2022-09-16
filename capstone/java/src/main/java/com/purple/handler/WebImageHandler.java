package com.purple.handler;

import com.purple.dao.WebImageDao;
import com.purple.model.WebImage;
import ij.IJ;
import ij.ImagePlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedString;
import java.util.List;
@Service
public class WebImageHandler implements WebImageHandlerable {
    private final WebImageDao webImageDao;
    @Autowired
    public WebImageHandler(WebImageDao webImageDao){
        this.webImageDao = webImageDao;
    }
    @Override
    public WebImage getWebImage(Long id){
        return webImageDao.getWebImage(id);
    }
    @Override
    public List<WebImage> getAllWebImages(){
        return webImageDao.getWebImages();
    }
    @Override
    public WebImage saveWebImage(WebImage webImage){
        return webImageDao.saveWebImage(webImage);
    }

    public WebImage getWebImageWithMeme(Long memeId){

        //get meme stuff with memeId
        String text = "Super Chicken";

        //get imag
        WebImage baseImage = webImageDao.getWebImage(memeId);
        InputStream targetStream = new ByteArrayInputStream(baseImage.getImage());
        BufferedImage image = null;
        try {
            image = ImageIO.read(targetStream);

            Font font = new Font("Arial", Font.BOLD, 100);


            Graphics g = image.getGraphics();
//            g.setFont(font);
//            g.setColor(Color.magenta);
//            g.drawString(text, 0, 20);


            AttributedString attributedText = new AttributedString(text);
            attributedText.addAttribute(TextAttribute.FONT, font);
            attributedText.addAttribute(TextAttribute.FOREGROUND, Color.magenta);


            FontMetrics metrics = g.getFontMetrics(font);
            int positionX = (image.getWidth() - metrics.stringWidth(text)) / 2;
            int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

            g.drawString(attributedText.getIterator(), positionX, positionY);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ImageIO.write(image, "png", outputStream);

            byte[] imageBytes = outputStream.toByteArray();
            baseImage.setImage(imageBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baseImage;
    }
}
