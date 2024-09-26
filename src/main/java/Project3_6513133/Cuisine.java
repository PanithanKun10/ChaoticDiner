/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//Food threads
 class Cuisine  {
    private JTextField scoreText;
    private MENU menu;
    private Game game;
    private int score;
    private int selected_country;
    private boolean stop = false;
   public Cuisine(MENU m,Game g){
       menu=m;
       game=g;
   }
   // private CustomerLabel CustomerLabel;

    //Copied from Worm
    public void setItemThread(JFrame f,JLabel d,JPanel p,int x ,int y,Game g,int type) {
        selected_country = this.menu.get_selected();
        Thread itemThread = new Thread() {
            @Override
            public void run() {
                // (7) Create a new FoodLabel, add it to drawpane
                FoodLabel item = new FoodLabel(f,x,y,selected_country,type);
               // System.out.println("FOOD");
                 
                p.add(item);
                g.add_Table(p);
                p.add(d);
                  
                while (item.get_move()) {
                    //     - Keep updating its location
                    item.updateLocation(f);
                     if(stop|| g.get_repu()<=0){
                         g.stop_all();
                       break;
                   }
                    //     - Check whether it collides with Customer. If it does, 
                 /* if (item.getBounds().intersects(CustomerLabel.getBounds())) {
                        item.setIcon(null);
                        //       play hit sound and update score
                        //item.playHitSound();
                        updateScore(item.getHitPoints());
                        check = false;
                    } else {

                    }*/
                }
                //     - Once reaching the top/bottom or colliding with Customer, 
                //       remove the item from drawpane and end this thread
               // b.remove(item);
            } // end run
        }; // end thread creation
        itemThread.start();
    }
    public void Set_Customer_Thread(JFrame f,JLabel d,JPanel p,int x ,int y,Game g){
          selected_country = this.menu.get_selected();
           Thread CustomerThread = new Thread() {
                public void run() {
                    CustomerLabel customer = new CustomerLabel(f,x,y,selected_country,menu.speed(),menu);
                p.add( customer);
                g.add_Table(p);
                p.add(d);
                
                while(customer.isMove()){
                     if(stop || g.get_repu()<=0){
                         g.Game_OVER();
                           g.stop_all();
                           QuickDialog.show("NAME:  "+menu.get_name()+ "\n\nTotal SCORE = "+ g.get_score() );
                          System.exit(0);
                       break;
                   }
                customer.updateLocation(f,game.get_player());
                 if(game.get_player().getBounds().intersects(customer.getBounds())||customer.getY()<game.get_player().getY()+game.get_player().getHeight()){
                          g.Player_hurt();
                          game.decrease_repu(5);
                          customer.setMove(false);
                          p.remove(customer);
                          f.repaint();
                          f.validate();
                   }
               for (Component component : p.getComponents()) {
                   if(component instanceof FoodLabel) {
                    FoodLabel food = (FoodLabel) component;
                    //   System.out.printf("%d,%d\n",customer.get_type_of_food(),food.get_type());
                    if(food.getBounds().intersects(customer.getBounds())&& customer.get_type_of_food()==food.get_type()){
                          customer.playHitSound();
                          game.add_score();
                          food.set_move(false);
                          p.remove(food);
                          customer.setMove(false);
                          p.remove(customer);
                          f.repaint();
                          f.validate();
                   }
                }
               }
                
                
                
                }
                }
           }; CustomerThread.start();
    }
    


  
   public void set_stop(boolean m){
           stop = m;        
    } 
   public boolean get_stop(){
       return this.stop;
   }
}



class FoodLabel extends JLabel {

    private  JFrame parentFrame;

    private int type;                // 0-2 = Food, 3-5 = Drinks
    private final MyImageIcon itemImg;
    //private final SoundEffect hitSound;

    String[][]imageFiles = Food.imageFiles;
   // String[] soundFiles = {Audios.FILE_HIT};
    int[] hitpoints = {1, -1};

    private int WIDTH = Food.FOOD_WIDTH; //Width of food
    private int HEIGHT = Food.FOOD_HEIGHT;//Height of food
    private int curX, curY;
    private int speed = 100;
    private boolean move = true;

    public FoodLabel(JFrame pf,int x,int y,int i,int t) {
        parentFrame = pf;
        //Random food type
        curX = x;
        curY = y;
        type=t;
           itemImg = new MyImageIcon(imageFiles[i][t]).resize(WIDTH, HEIGHT);  
  
        
        //hitSound = new SoundEffect(soundFiles[type]);
        setIcon(itemImg);
        setBounds(curX, curY, WIDTH, HEIGHT);
    }
    public boolean get_move(){
        return this.move;
    }
    public void set_move(boolean m){
        this.move = m;
    }
    public void setSpeed(int s) {
        speed = s;
    }

    public JFrame getParentFrame() {
        return parentFrame;
    }
    
    /*
    public void playHitSound() {
        hitSound.playOnce();
    }
     */
    public int getHitPoints() {
        return hitpoints[type];
    }
    public int get_type(){
        return this.type;
    }

    public void updateLocation(JFrame pf) {
        //Food will run from right to left
        this.curY += 10;
        if (curY > pf.getHeight()+Food.FOOD_HEIGHT) {
             set_move(false);
            setIcon(null);
        }

       setLocation(curX, curY);
        parentFrame.repaint();
        parentFrame.validate();
       try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

} // end class FoodLabel
