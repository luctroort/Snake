/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;

/**
 *
 * @author luctroort
 */
public class Food extends Node{
    
    private DrawSquareInterface drawSquareInterface;
    
    public Food(DrawSquareInterface drawSquareInterface) {
        super(0, 0);
        this.drawSquareInterface = drawSquareInterface;
        int row = (int) (Math.random() * Board.NUM_ROW);
        int col = (int) (Math.random() * Board.NUM_COL);
        setRow(row);
        setCol(col);
        
    }
    
    
    public void paint (Graphics g) {
        drawSquareInterface.drawSquare(g, getRow(), getCol(), true);
    }
}
