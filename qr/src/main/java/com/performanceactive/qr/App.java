package com.performanceactive.qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class App {

    private static Qr qr;

    public static void main(String[] args) {

        File inLogo = new File("logo.png");
        BufferedImage logo = null;
        try {
            logo = ImageIO.read(inLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        qr = new Qr("Round", logo, "Round", "Round");
        qr.generateQr("https://tastefinder.app/#/AA4D7", "#d12f2f", "#990007", "#990007");
        qr.getQrImage(true);

    }
}
