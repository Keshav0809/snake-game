/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakegame;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author theke
 */
public class SnakeGame extends JFrame {

    SnakeGame()
    {
        super("Snake Game");
        add(new Board());
        pack();
        
        setLocationRelativeTo(null);
        setResizable(false);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SnakeGame().setVisible(true);
        }
    
}
