/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake;

import com.mycompany.snake.interfaces.Incrementer;
import com.mycompany.snake.interfaces.DrawSquareInterface;
import com.mycompany.snake.interfaces.GameOverInterface;
import com.mycompany.snake.interfaces.InitGamer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author luctroort
 */
public class Board extends javax.swing.JPanel implements InitGamer, DrawSquareInterface {

    private Snake snake;
    private Timer timer;
    private Timer specialTimer;
    private KeyAdapter keyAdapter;
    private Food food;
    private SpecialFood specialFood;
    private Incrementer incrementer;
    private GameOverInterface gameOverInterface;
    private int deltaTime = 200;
    private String playerName;

    public static final int NUM_ROW = 30;
    public static final int NUM_COL = 30;
    public static final int MIN_SPECIAL_TIME = 10000;
    public static final int MAX_SPECIAL_TIME = 30000;

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.changeDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.changeDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.changeDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.changeDirection(Direction.DOWN);
                    }
                    break;
            }
        }
    }

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        keyAdapter = new MyKeyAdapter();
        snake = new Snake(this);
        food = new Food(snake, this);
        specialFood = new SpecialFood(snake, this);
        setFocusable(true);
        addKeyListener(keyAdapter);
        timer = new Timer(deltaTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
        int specialTime = (int) (Math.random() * (MAX_SPECIAL_TIME - MIN_SPECIAL_TIME)) + MIN_SPECIAL_TIME;
        specialTimer = new Timer(specialTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                specialFood = new SpecialFood(snake, Board.this);
            }
        });
        initGame();
    }

    public void initGame() {
        snake = new Snake(this);
        if (timer != null) {
            timer.setDelay(deltaTime);
            timer.setInitialDelay(0);
            timer.restart();
        }
        specialTimer.start();
        if (incrementer != null) {
            incrementer.reset();
        }
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIncrementer(Incrementer incrementer) {
        this.incrementer = incrementer;
    }

    public void setGameOverInterface(GameOverInterface gmInterface) {
        this.gameOverInterface = gmInterface;

    }

    private void processGameOver() {
        timer.stop();
        if (incrementer != null) {
            incrementer.saveHighScore();
        }
        gameOverInterface.setVisible(this);
    }

    private void tick() {
        if (snake.canMove()) {
            snake.move();
            if (snake.eats(food)) {
                snake.grow(1);
                food = new Food(snake, this);
                incrementer.incrementScore(1);
            }
            if (snake.eats(specialFood)) {
                snake.grow(3);
                specialFood = new SpecialFood(snake, this);
                incrementer.incrementScore(3);
            }
            if (specialFood != null) {
                specialFood.specialFoodAnimation();
            }
        } else {
            //Game over
            processGameOver();
        }
        repaint();
    }

    public void pause() {
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBorderBoard(g);
        snake.paint(g);
        food.paint(g);
        specialFood.paint(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void paintBorderBoard(Graphics g) {
        g.setColor(Color.black);
        int width = squareWidth() * NUM_COL;
        int height = squareHeight() * NUM_ROW;
        g.drawRect(0, 0, width, height);
    }

    public void drawSquare(Graphics g, int row, int col,
            SquareType type) {
        int x = col * squareWidth();
        int y = row * squareHeight();
        Color color;
        switch (type) {
            case HEAD:
                color = new Color(204, 102, 102);
                break;
            case BODY:
                color = new Color(102, 204, 102);
                break;
            case FOOD:
                color = Color.BLUE;
                break;
            case SPECIAL_FOOD:
                color = Color.YELLOW;
                break;
            default:
                color = Color.WHITE;
        }
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2,
                squareHeight() - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1,
                y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private int squareWidth() {
        return getWidth() / NUM_COL;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROW;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
