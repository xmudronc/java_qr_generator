package com.performanceactive.qr;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Part {

    private BufferedImage shape = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
    private Graphics2D g = (Graphics2D) shape.getGraphics();
    private Color color;

    public Part() {

    }

    public Part(boolean top, boolean bottom, boolean left, boolean right, String shapestyle, String color) {
        if (color != null) {
            this.color = Color.decode(color);
        } else {
            this.color = Color.BLACK;
        }
        if (top && !bottom && !left && !right) {
            getShape(shapestyle, 1, 90);
        } else if (top && bottom && !left && !right) {
            getShape(shapestyle, 3, 0);
        } else if (top && !bottom && left && !right) {
            getShape(shapestyle, 2, 180);
        } else if (top && !bottom && !left && right) {
            getShape(shapestyle, 2, 90);
        } else if (top && bottom && left && !right) {
            getShape(shapestyle, 3, 0);
        } else if (top && bottom && !left && right) {
            getShape(shapestyle, 3, 0);
        } else if (top && bottom && left && right) {
            getShape(shapestyle, 3, 0);
        } else if (top && !bottom && left && right) {
            getShape(shapestyle, 3, 0);
        } else if (!top && bottom && !left && !right) {
            getShape(shapestyle, 1, 270);
        } else if (!top && bottom && left && !right) {
            getShape(shapestyle, 2, 270);
        } else if (!top && bottom && !left && right) {
            getShape(shapestyle, 2, 0);
        } else if (!top && bottom && left && right) {
            getShape(shapestyle, 3, 0);
        } else if (!top && !bottom && left && !right) {
            getShape(shapestyle, 1, 180);
        } else if (!top && !bottom && left && right) {
            getShape(shapestyle, 3, 0);
        } else if (!top && !bottom && !left && right) {
            getShape(shapestyle, 1, 0);
        } else if (!top && !bottom && !left && !right) {
            getShape(shapestyle, 0, 0);
        }
    }

    public Part(String shapestyle) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 20, 20);
    }

    public void getShape(String shapestyle, Integer neighbors, Integer rotation) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, 20, 20);
        if (shapestyle != null) {
            if (shapestyle.equals("Round")) {
                if (neighbors == 0) {
                    g.setColor(color);
                    g.fillOval(0, 0, 20, 20);
                } else if (neighbors == 1) {
                    g.setColor(color);
                    g.fillOval(0, 0, 20, 20);
                    if (rotation == 90) {
                        g.fillRect(0, 0, 10, 20);
                    } else if (rotation == 180) {
                        g.fillRect(0, 0, 20, 10);
                    } else if (rotation == 270) {
                        g.fillRect(10, 0, 10, 20);
                    } else {
                        g.fillRect(0, 10, 20, 10);
                    }
                } else if (neighbors == 2) {
                    g.setColor(color);
                    if (rotation == 90) {
                        g.fillOval(-20, 0, 40, 40);
                    } else if (rotation == 180) {
                        g.fillOval(-20, -20, 40, 40);
                    } else if (rotation == 270) {
                        g.fillOval(0, -20, 40, 40);
                    } else {
                        g.fillOval(0, 0, 40, 40);
                    }
                } else if (neighbors == 3) {
                    g.setColor(color);
                    g.fillRect(0, 0, 20, 20);
                }
            }
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 20, 20);
        }
    }

    public BufferedImage getShape() {
        return this.shape;
    }

    public BufferedImage getCorner(String color, String shapestyle) {
        BufferedImage bmpHere = new BufferedImage(180, 180, BufferedImage.TYPE_INT_ARGB);
        if (shapestyle.equals("Round")) {
            Graphics2D gHere = bmpHere.createGraphics();

            gHere.setColor(Color.WHITE);
            gHere.fillRect(0, 0, 180, 180);

            if (color != null) {
                gHere.setColor(Color.decode(color));
            } else {
                gHere.setColor(Color.BLACK);
            }

            gHere.fillOval(20, 20, 60, 60);
            gHere.fillOval(bmpHere.getWidth() - 80, 20, 60, 60);
            gHere.fillOval(20, bmpHere.getHeight() - 80, 60, 60);
            gHere.fillOval(bmpHere.getWidth() - 80, bmpHere.getHeight() - 80, 60, 60);
            gHere.fillRect(50, 20, 80, 140);
            gHere.fillRect(20, 50, 140, 80);

            gHere.setColor(Color.WHITE);
            gHere.fillOval(40, 40, 40, 40);
            gHere.fillOval(bmpHere.getWidth() - 80, 40, 40, 40);
            gHere.fillOval(40, bmpHere.getHeight() - 80, 40, 40);
            gHere.fillOval(bmpHere.getWidth() - 80, bmpHere.getHeight() - 80, 40, 40);
            gHere.fillRect(60, 40, 60, 100);
            gHere.fillRect(40, 60, 100, 60);

            if (color != null) {
                gHere.setColor(Color.decode(color));
            } else {
                gHere.setColor(Color.BLACK);
            }

            gHere.fillOval(60, 60, 20, 20);
            gHere.fillOval(bmpHere.getWidth() - 80, 60, 20, 20);
            gHere.fillOval(60, bmpHere.getHeight() - 80, 20, 20);
            gHere.fillOval(bmpHere.getWidth() - 80, bmpHere.getHeight() - 80, 20, 20);
            gHere.fillRect(70, 60, 40, 60);
            gHere.fillRect(60, 70, 60, 40);
        }
        return bmpHere;
    }

    public BufferedImage getEye(String color, String shapestyle) {
        BufferedImage bmpHere = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);
        if (shapestyle.equals("Round")) {
            Graphics2D gHere = bmpHere.createGraphics();

            gHere.setColor(Color.WHITE);
            gHere.fillRect(0, 0, 70, 70);

            if (color != null) {
                gHere.setColor(Color.decode(color));
            } else {
                gHere.setColor(Color.BLACK);
            }

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    gHere.fillOval(5 + (x*20), 5 + (y*20), 20, 20);
                }
            }
        }
        return bmpHere;
    }
}