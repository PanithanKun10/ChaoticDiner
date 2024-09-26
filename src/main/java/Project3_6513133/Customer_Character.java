/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
class CustomerLabel extends JLabel {
    private JFrame parentFrame;
    private  MyImageIcon CustomerImg;
    private MENU menu;
    private int type;
    private int type_of_food;
    private int selected;
    private final int width = HungryCustomer.HUNGRYCUSTOMER_WIDTH;
    private final int height = HungryCustomer.HUNGRYCUSTOMER_HEIGHT;
    private String [][] Customer = HungryCustomer.Customer;
    private String [] hit = Audios.FILE_HIT;
    private String [] Res = Audios.FILE_RE;
    private int curX ,curY;
    private SoundEffect HIT,RES ;
    private int speed;
    private boolean  move = true;

    public CustomerLabel(JFrame pf,int x,int y,int i,int s,MENU m) {
        parentFrame = pf;
        menu =m;
         Random random = new Random();
        //Random customer
        switch(i){
            case 0:
                selected = random.nextInt(0, 3);
                type_of_food = selected;
            break;
            case 1:
                selected = random.nextInt(3, 6);
                type_of_food = selected-3;
            break;
            case 2:
                selected = random.nextInt(6, 9);
                 type_of_food = selected-6;
            break;
            case 3:
                selected = random.nextInt(9, 12);
                 type_of_food = selected-9;
            break;
            case 4:
                selected = random.nextInt(12, 15);
                type_of_food = selected-12;
             break;
        }
        type = random.nextInt(0, 3);
        switch (type) {
            case 0:
                CustomerImg = new MyImageIcon(Customer[selected][type]).resize(width, height); 
                break;
            case 1:
                CustomerImg = new MyImageIcon(Customer[selected][type]).resize(width, height);
                break;
            case 2:
                CustomerImg = new MyImageIcon(Customer[selected][type]).resize(width, height);
                break;
            
        }
        curX =x;
        curY =y;
        speed =s;
        HIT = new SoundEffect(hit[type]);
        HIT.setVolume(menu.get_current_vol_SFX());
        RES = new SoundEffect(Res[type]);
        RES.setVolume(menu.get_current_vol_SFX());
        RES.playOnce();
        setIcon(CustomerImg);
        setBounds(curX, curY, width, height);
     //   System.out.println("Create Customer");
    }

    public void setMove(boolean m) {
        move = m;
    }

    public boolean isMove() {
        return move;
    }
    public int get_type_of_food(){
        return this.type_of_food;
    }
    public void updateLocation(JFrame pf,JLabel p) {
        this.curY -= 10;
       
        setLocation(curX, curY);
        repaint();
        validate();

        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void playHitSound() {HIT.playOnce();} 

} // end class CustomerLabel
