package PixelArt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PixelArt extends MouseAdapter {
    Color defaultColor = null;
    Panel canvas;
    JPanel tools;
    JFrame frame;
    JButton clear;
    JButton eraser;
    JButton pen;
    JButton pickColor;
    JButton save;
    JButton grid;
    JPanel colorDisplay;
    Color currColor;
    char currTool;
    int dim = 16;
    boolean held = false;
    ArrayList<JPanel> pixels;

    PixelArt() {
        pixels = new ArrayList<>();

        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        canvas = new Panel(new GridLayout(dim,dim));
        fillGrid();

        setClear();
        setEraser();
        setPen();
        setPickColor();
        setSave();
        setGrid();

        colorDisplay = new JPanel();

        tools = new JPanel();
        tools.setLayout(new GridLayout(7,1));
        tools.add(clear);
        tools.add(pen);
        tools.add(eraser);
        tools.add(pickColor);
        tools.add(save);
        tools.add(colorDisplay);
        tools.add(grid);

        frame.add(canvas, BorderLayout.CENTER);
        frame.add(tools, BorderLayout.WEST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        currColor = new Color(0,0,0);
    }

    private void fillGrid(){
        int count = 0;
        int dims = dim*dim;
        while(count < dims) {
            JPanel pixel = new JPanel();
            pixels.add(pixel);
            pixel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pixel.addMouseListener(this);
            canvas.add(pixel);
            count ++;
        }
    }

    private void setClear(){
        clear = new JButton();
        clear.setText("Clear");
        clear.setFocusable(false);
        clear.addActionListener(e -> {
            if (e.getSource() == clear) {
                for(JPanel pixel : pixels) {
                    pixel.setBackground(defaultColor);
                }
            }
        });
    }

    private void setEraser(){
        eraser = new JButton();
        eraser.setText("Erase");
        eraser.setFocusable(false);
        eraser.addActionListener(e -> {
            if (e.getSource() == eraser) {
                currTool = 'e';
            }
        });
    }

    private void setPen(){
        pen = new JButton();
        pen.setText("Pen");
        pen.setFocusable(false);
        pen.addActionListener(e -> {
            if(e.getSource() == pen) {
                currTool = 'p';
            }
        });
    }

    private void setPickColor(){
        pickColor = new JButton();
        pickColor.setText("Color");
        pickColor.setFocusable(false);
        pickColor.addActionListener(e -> {
            if(e.getSource() == pickColor) {
                new JColorChooser();
                currColor = JColorChooser.showDialog(null, "Select a color.", Color.BLACK);
                colorDisplay.setBackground(currColor);
            }
        });
    }

    private void setSave(){
        save = new JButton();
        save.setText("Save");
        save.setFocusable(false);
        save.addActionListener(e -> {
            if(e.getSource() == save) {
                canvas.saveImage("test", "png");
            }
        });
    }
    private void setGrid(){
        grid = new JButton();
        grid.setText("Grid");
        grid.setFocusable(false);
        grid.addActionListener( e -> {
            if (e.getSource() == grid) {
                for(JPanel pixel : pixels) {
                    pixel.setBorder(BorderFactory.createEmptyBorder());
                }
            }
        });
    }
    public void mousePressed(MouseEvent e) {
        JPanel clickPanel = (JPanel) e.getSource();
        switch (currTool) {
            case 'p':
                clickPanel.setBackground(currColor);
                break;
            case 'e':
                clickPanel.setBackground(defaultColor);
        }
        held = true;
    }

    public void mouseReleased(MouseEvent e) {
        held = false;
    }

    public void mouseEntered(MouseEvent e) {
        if(held) {
            JPanel clickPanel = (JPanel) e.getSource();
            switch (currTool) {
                case 'p':
                    clickPanel.setBackground(currColor);
                    break;
                case 'e':
                    clickPanel.setBackground(defaultColor);
            }
        }
    }


}
