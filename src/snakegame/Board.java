/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 

/**
 *
 * @author theke
 */
public class Board extends JPanel implements ActionListener {
    private int dots,apple_x,apple_y;
    private Image apple,dot,head;
    private final int ALL_DOTS=900,DOT_SIZE=10,x[]=new int[ALL_DOTS],y[]=new int[ALL_DOTS];
    private final int RANDOM_POSITION=29;
    private Timer timer;
    private boolean leftDirection=false,rightDirection=true,upDirection=false,downDirection=false;
    private boolean inGame=true;
    Board()
    {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
        loadImages();
        initGame();
    }
    public void loadImages()
    {
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple=i1.getImage();
        
        ImageIcon i2= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot=i2.getImage();
        
        ImageIcon i3= new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head=i3.getImage();
        
    }
    public void initGame()
    {
        
        dots=3;
        for(int i=0;i<dots;i++)
        {
            y[i]=50;
            x[i]=50-i*DOT_SIZE;
        }
        locateApple();
        timer=new Timer(150,this);
        timer.start();
    }
    public void locateApple()
    {
        int r=(int)(Math.random()*RANDOM_POSITION);
        apple_x=r*DOT_SIZE;
        r=(int)(Math.random()*RANDOM_POSITION);
        apple_y=r*DOT_SIZE;
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
        
    }
    public void draw(Graphics g)
    {
        if(inGame)
        {
            g.drawImage(apple,apple_x,apple_y,this);
        for(int i=0;i<dots;i++)
        {
            if(i==0)
                g.drawImage(head,x[i],y[i],this);
            else
                g.drawImage(dot,x[i],y[i],this);
            
        }
        Toolkit.getDefaultToolkit().sync();
        }
        else
            gameOver(g);
        
        
    }
    public void gameOver(Graphics g)
    {
        String msg="Game Over!";
        Font font=new Font("SAN_SERIF",Font.BOLD,14);
        FontMetrics metrices=getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg,(300-metrices.stringWidth(msg))/2,300/2);
        
        
    }
    public void move()
    {
        for(int i=dots-1;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
            
        }
        if(leftDirection)
            x[0]-=DOT_SIZE;
        if(rightDirection)
            x[0]+=DOT_SIZE;
        if(upDirection)
            y[0]-=DOT_SIZE;
        if(downDirection)
            y[0]+=DOT_SIZE;
        
    }
    public void checkApple()
    {
        if(x[0]==apple_x&&y[0]==apple_y)
        {
            dots++;
            locateApple();
        }
    }
    
    public void checkCollision()
    {
        
        for(int i=4;i<dots;i++)
            if(x[i]==x[0]&&y[i]==y[0])
                inGame=false;
        if(x[0]>=300||x[0]<0||y[0]<0||y[0]>=300)
            inGame=false;
        if(!inGame)
            timer.stop();
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(inGame)
        {    
        checkCollision();
        checkApple();
        move();
        }
        
        repaint();
    }
    public class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT&&(!rightDirection))
            {
                leftDirection=true;
                upDirection=false;
                downDirection=false;
                
            }
            if(key==KeyEvent.VK_RIGHT&&(!leftDirection))
            {
                rightDirection=true;
                upDirection=false;
                downDirection=false;
                
            }
            if(key==KeyEvent.VK_UP&&(!downDirection))
            {
                upDirection=true;
                leftDirection=false;
                rightDirection=false;
                
            }
            if(key==KeyEvent.VK_DOWN&&(!upDirection))
            {
                downDirection=true;
                leftDirection=false;
                rightDirection=false;
                
            }
            
        }
    }
}
