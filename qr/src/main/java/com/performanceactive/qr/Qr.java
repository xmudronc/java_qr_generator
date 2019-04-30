package com.performanceactive.qr;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Qr {

    private BufferedImage logo;
    private String shape;
    private List<List<Part>> qrMatrix;
    private BufferedImage bmp;
    private Graphics2D g;
    private String drawEye;
    private String drawCorner;

    public Qr() {
        this.shape = null;
        this.logo = null;
        this.drawCorner = null;
        this.drawEye = null;
    }

    public Qr(String shape, BufferedImage logo, String corner, String eye) {
        this.shape = shape;
        this.logo = logo;
        this.drawCorner = corner;
        this.drawEye = eye;
    }

    public void drawCorner(String color) {
        BufferedImage bmpHere = new Part().getCorner(color, drawCorner);

        g.drawImage(bmpHere, 0, 0, null);
        g.drawImage(bmpHere, bmp.getWidth() - 180, 0, null);
        g.drawImage(bmpHere, 0, bmp.getHeight() - 180, null);
    }

    public void drawEye(String color) {
        BufferedImage bmpHere = new Part().getEye(color, drawEye);

        g.drawImage(bmpHere, 55, 55, null);
        g.drawImage(bmpHere, bmp.getWidth() - 125, 55, null);
        g.drawImage(bmpHere, 55, bmp.getHeight() - 125, null);
    }

    public void drawLogo(Graphics2D g) {
        g = (Graphics2D) bmp.getGraphics();
        g.drawImage(logo, 240, 240, 220, 220, null);
    }

    public BitMatrix encodeToQrCode(String text, int width, int height){
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = null;
        HashMap hints = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public void generateQr(String code, String mainColor, String cornerColor, String eyeColor) {
        BitMatrix matrix = encodeToQrCode(code, 1, 1); 
        System.out.println(matrix.getHeight() + " " + matrix.getWidth());
        System.out.println(matrix.getRowSize());
        bmp = new BufferedImage((matrix.getWidth() - 6) * 20, (matrix.getHeight() - 6) * 20, BufferedImage.TYPE_INT_ARGB);
        qrMatrix = new ArrayList<>();

        if (logo != null) {
            for (int x = 15; x < matrix.getWidth() - 15; x++){
                for (int y = 15; y < matrix.getHeight() - 15; y++){
                    matrix.set(x, y);
                    matrix.flip(x, y);
                }
            }
        }
        
        for (int x = 3; x < matrix.getWidth() - 3; x++){
            List<Part> qrPart = new ArrayList<>();
            for (int y = 3; y < matrix.getHeight() - 3; y++){
                qrPart.add(matrix.get(x,y) ? new Part(matrix.get(x-1,y), matrix.get(x+1,y), matrix.get(x,y-1), matrix.get(x,y+1), shape, mainColor) : new Part(shape));
            }
            qrMatrix.add(qrPart);
        }

        g = (Graphics2D) bmp.getGraphics();
        for (int x = 0; x < qrMatrix.size(); x++){
            for (int y = 0; y < qrMatrix.get(0).size(); y++){
                g.drawImage(qrMatrix.get(x).get(y).getShape(), x*20, y*20, null);
            }
        }

        if (drawCorner != null) {
            if (cornerColor != null) {
                drawCorner(cornerColor);
            } else {
                drawCorner(mainColor);
            }
        }
        if (drawEye != null) {
            if (eyeColor != null) {
                drawEye(eyeColor);
            } else {
                drawEye(mainColor);
            }
        }
        if (logo != null) {
            drawLogo(g);
        }       
    }

    public BufferedImage getQrImage(boolean write) {
        if (write) {
            writeQrImage("qr_out.png", bmp);
        }
        return bmp;
    }

    public void writeQrImage(String path, BufferedImage bmp) {
        File out = new File(path);
        try {
            ImageIO.write(bmp, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}