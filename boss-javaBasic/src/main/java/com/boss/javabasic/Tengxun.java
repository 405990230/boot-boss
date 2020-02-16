package com.boss.javabasic;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Tengxun {
    static volatile int num= 0;
    public static void main(String[] args) {
        Tengxun tengxun = new Tengxun();
        for(int i=0;i<50;i++){
            new Thread(tengxun :: getImage,"test"+i).start();
        }
    }
    public byte[] getImage(){
        List<String> lists = getImageList();
        Random random = new Random();
        int i = random.nextInt(7);
        String imageUrl = lists.get(i);
        System.out.println();
        InputStream inputStream = null;
        ByteArrayOutputStream output = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            System.out.println(connection.getResponseCode() +"---:"+i+"：---"+Thread.currentThread().getName());
            BufferedImage originalImage;
            ImageInputStream stream = ImageIO.createImageInputStream(inputStream);
            Iterator iterator = ImageIO.getImageReaders(stream);
            if (!iterator.hasNext()) {
                return null;
            }
            ImageReader reader = (ImageReader) iterator.next();
            ImageReadParam param = reader.getDefaultReadParam();
            reader.setInput(stream, true, true);
            originalImage = reader.read(0, param);

            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImage = resizeImage(originalImage, type, 115, 72);;
            output = new ByteArrayOutputStream();
            ImageIO.write(resizeImage, "png", output);
            return output.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            num++;
        }finally {
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }
        return null;
    }

    public List<String> getImageList(){
        List<String> lists =  new ArrayList<>();
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11240217320_870492/0/668781780760788993.png");
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11238383538_870492/0/668505069481623553.jpeg");
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11239156741_870492/0/668666016208781313.jpeg");
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11238627138_870492/0/668555294241980417.jpeg");
        lists.add("https://inews.gtimg.com/newsapp_match/0/11237422204/0/668464840351678464.jpeg");
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11239368178_870492/0/668666018238824449.jpeg");
        lists.add("https://inews.gtimg.com/newsapp_ls/0/11237234324_870492/0/668424540207972353.jpeg");
        return lists;
    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height){

        Dimension newDimension = getScaledDimension(new Dimension(originalImage.getWidth(), originalImage.getHeight()),
                new Dimension(width, height));
        width = newDimension.width;
        height = newDimension.height;
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

}
