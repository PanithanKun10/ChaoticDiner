/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 class PlayerLabel extends JLabel {
     private MENU Menu;
     private int curX,curY;
     private String name;
     private MyImageIcon Player;
     private boolean move=true;
     private JFrame parentFrame;
     private SoundEffect hurt,Death;
     
     public PlayerLabel(MENU m,JFrame pf,MyImageIcon p,int x ,int y,int width,int height){
         parentFrame = pf;
         Menu = m;
         Player = p;
         curX=x;
         curY=y;
         hurt = new SoundEffect(Audios.Hurt);
         hurt.setVolume(m.get_current_vol_SFX()+0.2f);
         Death = new SoundEffect(Audios.GOVER);
         Death.setVolume(m.get_current_vol_SFX());
         setIcon(Player);
         setBounds(curX,curY,width,height);
     }
     public void setMove(boolean m){ move =m;}
     public void setName(String n){name= n;}
     public void setX_Y(int x,int y){curX=x; curY=y;  this.updateLocation();}
     public void moveleft(int i){
      //   System.out.println("Move left");
         this.curX-=i;
         this.updateLocation();
     }
     public void moveright(int i){
       // System.out.println("Move Right");
         this.curX+=i;
         this.updateLocation();
     }
     public void hurt(){hurt.playOnce();};
     public void death(){Death.playOnce();};
    
     public boolean ismove(){return move;}
       public void updateLocation() {
        //Food will run from right to left
       
       setLocation(curX, curY);
        parentFrame.repaint();
        parentFrame.validate();
          
       }
     public String get_name(){ return name;}  
}
