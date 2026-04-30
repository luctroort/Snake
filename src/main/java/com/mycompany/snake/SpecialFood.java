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
public class SpecialFood extends Food {

    private boolean visible = true;

    public SpecialFood(Snake snake, DrawSquareInterface drawSquareInterface) {
        super(snake, drawSquareInterface);

    }

    public void specialFoodAnimation() {
        visible = !visible;
    }

    @Override
    public void paint(Graphics g) {
        if (visible) {
            drawSquareInterface.drawSquare(g, getRow(), getCol(), SquareType.SPECIAL_FOOD);
        }
    }
}
