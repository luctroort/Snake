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
    
    public Snake() {
        nodes = new ArrayList<>();
        direction = Direction.RIGHT;
        int intRow = Board.NUM_ROW / 2;
        int intCol = Board.NUM_COL / 2;
        for (int i = 0; i < 4; i++) {
            Node node = new Node(intRow, intCol +i);
            nodes.add(node);
        }
    }
    
    public void paint(Graphics g) {
        
    }
    
    
}
