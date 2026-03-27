/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luctroort
 */
public class Snake {

    private List<Node> nodes;
    private Direction direction;
    private DrawSquareInterface drawSquareInterface;
    private int nodesToGrow;

    public boolean canMove() {
        switch (direction) {
            case UP:
                if (nodes.getFirst().getRow() - 1 >= 0) {
                    return false;
                }
                break;
            case DOWN:
                if (nodes.getFirst().getRow() + 1 < Board.NUM_ROW) {
                    return false;
                }
                break;
            case RIGHT:
                if (nodes.getFirst().getCol() + 1 < Board.NUM_COL) {
                    return false;
                }
                break;
            case LEFT:
                if (nodes.getFirst().getCol() - 1 >= 0) {
                    return false;
                }
                break;
        }
        return true;
    }

    public void move() {
        int row = nodes.getFirst().getRow();
        int col = nodes.getFirst().getCol();
        Node node = null;
        switch (direction) {
            case UP:
                node = new Node(row - 1, col);
                break;
            case DOWN:
                node = new Node(row + 1, col);
                break;
            case LEFT:
                node = new Node(row, col - 1);
                break;
            case RIGHT:
                node = new Node(row, col + 1);
                break;
        }
        nodes.addFirst(node);
        if (nodesToGrow == 0) {
            nodes.removeLast();
        } else {
            nodesToGrow--;
        }
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake(DrawSquareInterface drawSquareInterface) {
        nodes = new ArrayList<>();
        direction = Direction.LEFT;
        this.drawSquareInterface = drawSquareInterface;
        int row = Board.NUM_ROW / 2;
        int col = Board.NUM_COL / 2;
        for (int i = 0; i < 4; i++) {
            Node node = new Node(row, col + i);
            nodes.add(node);
        }
        nodesToGrow = 0;
    }

    public void paint(Graphics g) {
        boolean first = true;
        for (Node node : nodes) {
            drawSquareInterface.drawSquare(g, node.getRow(), node.getCol(), first);
            if (first) {
                first = false;
            }
        }
    }

}
