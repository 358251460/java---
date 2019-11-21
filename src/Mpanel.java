import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Mpanel extends JPanel  implements KeyListener,ActionListener{
    ImageIcon title = new ImageIcon("title.jpg");
    ImageIcon body = new ImageIcon("body.png");
    ImageIcon up = new ImageIcon("up.png");
    ImageIcon down = new ImageIcon("down.png");
    ImageIcon right = new ImageIcon("right.png");
    ImageIcon left = new ImageIcon("left.png");
    ImageIcon food = new ImageIcon("food.png");

    int len =3;
    int[] snakey = new int[750];
    int[] snakex=new int[750];
    String fx="U";
    boolean isStarted = false;
    Timer timer = new Timer(100,this);
    int foodx;
    int foody;
    int score;
 boolean   isFailed =false;

    Random rand = new Random();
    public Mpanel()  //构造方法
    {
     initSnake();
     this.setFocusable(true);
     this.addKeyListener(this);
     timer.start();
    }
    public void  paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);//设置画布北京颜色
        title.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);   //建立整个画布
       g.setColor(Color.white);  //设置字体颜色
       g.drawString("Len :"+len,750,35);
        g.drawString("Score :"+score,750,50);
        if (fx == "R") {
            right.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (fx == "L") {
            left.paintIcon(this, g, snakex[0], snakey[0]);

        } else if (fx == "D") {
            down.paintIcon(this, g, snakex[0], snakey[0]);

        } else if (fx == "U") {
            up.paintIcon(this, g, snakex[0], snakey[0]);
        }

        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        food.paintIcon(this,g,foodx,foody);
        if (isStarted == false) {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("press Space to start", 300, 300);
        }
        if (isFailed ) {
            g.setColor(Color.red);
            g.setFont(new Font("arial", Font.BOLD, 40));
            g.drawString("Restart", 300, 300);
        }
    }
    public void initSnake()
    {
        len =3;
        snakey[2]=100;
        snakey[1]=100;
        snakey[0]=100;
        snakex[2]=50;
        snakex[1]=75;
        snakex[0]=100;
        this.foodx = 25 +25*rand.nextInt(34);
        this.foody = 75 +25*rand.nextInt(24);
         fx="R";
         score = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
   int keyCode = e.getKeyCode();
   if (keyCode == KeyEvent.VK_SPACE){
       if (isFailed){
           isFailed =false;
           initSnake();
       }else {
           isStarted = !isStarted;
       }
       repaint();
   }else if (keyCode == KeyEvent.VK_LEFT){
       fx="L";
   }
   else if (keyCode == KeyEvent.VK_RIGHT){
       fx="R";
   }
   else if (keyCode == KeyEvent.VK_UP){
       fx="U";
   }else if (keyCode == KeyEvent.VK_DOWN){
       fx="D";
   }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStarted && !isFailed) {
            for (int i = len - 1; i > 0; i--) {
                snakey[i] = snakey[i - 1];
                snakex[i] = snakex[i - 1];
            }


            if (fx == "R"){
                snakex[0]= snakex[0]+25;
                if (snakex[0]>850)snakex[0]=25;
            }else if (fx =="L"){
                snakex[0]= snakex[0]-25;
                if (snakex[0]<25)snakex[0]=850;
            }
            else if (fx =="U"){
                snakey[0]= snakey[0]-25;
                if (snakey[0]<75)snakey[0]=650;
            }
            else if (fx =="D"){
                snakey[0]= snakey[0]+25;
                if (snakey[0]>670)snakey[0]=75;
            }
            if (snakex[0]==foodx && snakey[0]==foody){
                len++;
                score++;
                this.foodx = 25 +25*rand.nextInt(34);
                this.foody = 75 +25*rand.nextInt(24);
            }
            for (int i =1;i<len;i++)
            {
                if (snakex[i]==snakex[0] && snakey[i] ==snakey[0]){
                    isFailed =true;
                }
            }
            repaint();

        }
        timer.start();

    }
}
