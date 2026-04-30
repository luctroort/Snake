/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import com.mycompany.snake.interfaces.DrawSquareInterface;
import java.awt.Graphics;

/**
 *
 * @author luctroort
 */
public class Food extends Node {

    DrawSquareInterface drawSquareInterface;

    public Food(Snake snake, DrawSquareInterface drawSquareInterface) {
        super(0, 0);
        this.drawSquareInterface = drawSquareInterface;
        do {
            int row = (int) (Math.random() * Board.NUM_ROW);
            int col = (int) (Math.random() * Board.NUM_COL);
            setRow(row);
            setCol(col);
        } while (snake.contains(this));

    }

    public void paint(Graphics g) {
        drawSquareInterface.drawSquare(g, getRow(), getCol(), SquareType.FOOD);
    }

}
