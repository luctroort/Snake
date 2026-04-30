/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake;

import com.mycompany.snake.interfaces.DrawSquareInterface;
import static com.mycompany.snake.Direction.DOWN;
import static com.mycompany.snake.Direction.LEFT;
import static com.mycompany.snake.Direction.RIGHT;
import static com.mycompany.snake.Direction.UP;
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
        if (node.getRow() < 0 || node.getRow() >= Board.NUM_ROW
                || node.getCol() < 0 || node.getCol() >= Board.NUM_COL || colidesWithItself(node)) {
            return false;
        }

        return true;
    }

    public boolean colidesWithItself(Node nodeX) {
        for (Node node : nodes) {
            if (nodeX.getRow() == node.getRow() && nodeX.getCol() == node.getCol()) {
                return true;
            }
        }
        return false;
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

    public boolean eats(Food food) {
        int row = nodes.getFirst().getRow();
        int col = nodes.getFirst().getCol();
        return (food.getRow() == row && food.getCol() == col);
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

    public void grow(int amount) {
        nodesToGrow += amount;
    }

    public boolean contains(Node node) {
        for (Node n : nodes) {
            if (node.getRow() == n.getRow() && node.getCol() == n.getCol()) {
                return true;
            }
        }
        return false;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            drawSquareInterface.drawSquare(
                    g,
                    node.getRow(),
                    node.getCol(),
                    i == 0 ? SquareType.HEAD : SquareType.BODY
            );
        }
    }

}
