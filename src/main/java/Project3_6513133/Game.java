/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *
 * @author User
 */
class Game extends JFrame {
    private MENU m;
    private Cuisine food;
    private JFrame Currentframe;
    private MyImageIcon  backgroundImg,playerimg ,Tableimg ,selectedimg,Icon;
    private String[] BGM_country = {Audios.THAI,Audios.CHINA,Audios.JAPAN,Audios.KOREA,Audios.VIETNAM};
    private JPanel [] Contentpane = new JPanel[5];
    private JPanel currentPanel;
    private JLabel[] drawpane = new JLabel[5];
    private JLabel [] Table;
    private PlayerLabel Player; 
    private JLabel selected_food;
    private  int distant;
    private int CurrentTable_X;   
    private int WIDTH = backgrounds.BG_WIDTH; //Width of background
    private int HEIGHT = backgrounds.BG_HEIGHT;//Height of background
    private int[]BG = {0,1,2,3,4}; 
    private int Random_table;
    private JLabel R,Score;
    private int Reputation=100,currentscore=0;
    private int type=0;
    private SoundEffect BGM;
    
  
    private long latest_pass=0,latest_spawn=0;
    private  final long  duration=750 ;  
    private  long duration_res ;
   
   public Game(MENU menu){
        m = menu;
        food = new Cuisine(m,this);
        Create_Game();
  }
   public JLabel get_table(int i){
       return this.Table[i];
   }
   public void cooldown(int i,int t){
       
      
       long time = System.currentTimeMillis();//return the number of milliseconds passed since 1970 as 00:00 1 January 1970 
      // System.out.println(Player.getHeight());
         if (time > latest_pass + duration) {
          //   System.out.println("Create Food");
         
         food.setItemThread(Currentframe,drawpane[i],Contentpane[i],Player.getX()+(Player.getWidth()/2)-(Food.FOOD_WIDTH/2),Player.getY()+Player.getHeight(),this,t);
        latest_pass = time;
       }else{
           //  System.out.println("Cool down");
         }
     } 
   
    public void  add_food(int i){
        selected_food = new JLabel();
        selectedimg = new MyImageIcon(Food.imageFiles[this.m.get_selected()][type]).resize(75,75);
        selected_food.setBounds(225,0,75,75);
        selected_food.setIcon(selectedimg);
        JLabel label = new JLabel("CURRENTLY FOOD");
        label.setFont(new Font("monospaced", Font.BOLD, 24));
        label.setBounds(10, 25, 250, 24);
        Contentpane[i].add(label);
        Contentpane[i].add(selected_food);
        Currentframe.addKeyListener(new KeyAdapter(){
             public void keyPressed ( KeyEvent e ){
                  switch(e.getKeyCode()){
                    case KeyEvent.VK_1:
                        type =0;
                          //  System.out.println(type);
                        break;
                    case KeyEvent.VK_2:
                        type =1;
                         //   System.out.println(type);
                        break;
                    case KeyEvent.VK_3:
                        type =2;
                          //  System.out.println(type);
                        break;
                    case KeyEvent.VK_SPACE:  
                        cooldown(i,type);
                        break;
                }
                selectedimg = new MyImageIcon(Food.imageFiles[m.get_selected()][type]).resize(75,75);
                selected_food.setIcon(selectedimg);
                repaint();
                validate(); 
                          
                 
             }
        });
            
    }
    public void Create_reputation(int i,int x,int y,int width,int height){
        R= new JLabel(String.valueOf(this.Reputation));
        R.setFont(new Font("monospaced", Font.BOLD, 24));
        JLabel repu = new JLabel("REPUTATION ");
        repu.setFont(new Font("monospaced", Font.BOLD, 24));
        repu.setBounds(x, y, width, height);
        R.setBounds(x+175, y, width-100, height);
     
        Contentpane[i].add(repu);
        Contentpane[i].add(R);
         
        
    }
    public void Create_Score(int i,int x,int y,int width,int height){
        Score= new JLabel(String.valueOf(this.currentscore));
        Score.setFont(new Font("monospaced", Font.BOLD, 24));
        Score.setBounds(x+100, y, width-100, height);
        JLabel s= new JLabel("SCORE");
        s.setFont(new Font("monospaced", Font.BOLD, 24));
        s.setBounds(x, y, width, height);
      JLabel s1 = new JLabel();
     
        Contentpane[i].add(s);
       Contentpane[i].add(Score);
       Contentpane[i].add(s1);
       
       
    }
    public synchronized void decrease_repu(int i){
        if(Reputation- i>0){
            this.Reputation-=i;
        }else{
            this.Reputation=0;
        }
        
        R.setText(String.valueOf(Reputation));
    }
    public synchronized void add_score(){
        currentscore++;
        Score.setText(String.valueOf(currentscore));
    } 
    public void add_customer(int i){
        Game g =this;  
        duration_res = this.m.spawn();
        Thread Respawn = new Thread(){
            public void run(){
                while(true){
                    if(food.get_stop()){
                        break;
                    }
                     Random random = new Random();
                     Random_table = random.nextInt(0, Table.length);
                    long  time = System.currentTimeMillis();
                    if(time > latest_spawn+duration_res){
                      food.Set_Customer_Thread(Currentframe, drawpane[i], Contentpane[i], Table[Random_table].getX()-Table[Random_table].getWidth()/4, Table[Random_table].getY()+Table[Random_table].getHeight(), g);
                      latest_spawn = time;
                    }
                    
                }
            }
                    
        }; Respawn.start();
    }
    public PlayerLabel get_player(){
        return this.Player;
    }
    public void Create_icon(int i,int x,int y,int width,int height,String fname){
        JLabel icon = new JLabel();
        JLabel icon2 = new  JLabel();
        Icon = new MyImageIcon(fname).resize(width,height);
        icon.setBounds(x,y,width,height);
        icon.setIcon(Icon);//seticon 
        Contentpane[i].add(icon);//add icon
        Contentpane[i].add(icon2);
        
        // layers : 1. Jframe -> 2. JPanel ( last add ( most backward)- > first add(most forward))
    }
    public void Create_Game(){
      this.setTitle("Game");
      this.setSize(WIDTH,HEIGHT);
      setLocationRelativeTo(null);
      setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ); // System.exit(0) call will be executed       
      Currentframe = this;
    // System.out.println(this.m.get_name());
      generate_Component();
      setPanel(Contentpane[BG[0]]);
      Currentframe.add(currentPanel);
      Currentframe.addWindowListener(new WindowAdapter(){
            @Override
          public void windowClosing(WindowEvent e){
              Game_OVER();
              stop_all();
              
              QuickDialog.show("NAME:  "+m.get_name()+ "\n\nTotal SCORE = "+ currentscore );
          }  
      });
      BGM();
      validate();
      setVisible(true); 
      try{
        Thread.sleep(1000);
      }catch(Exception e){
            
     }
     }  
    public void stop_all(){
        BGM.stop();
        food.set_stop(true);
    }
    public int  get_score(){
        return this.currentscore;
    }
    public void  generate_Component(){
        
      Create_BG(BG[0],backgrounds.Game_BG);
      Create_player(BG[0],533,460,Player_Cos.Player_cos_WIDTH-200,Player_Cos.Player_cos_HEIGHT-200);
      Create_set_of_tables();

      Contentpane[BG[0]].add(drawpane[BG[0]]);
      
      add_food(BG[0]);
      add_customer(BG[0]);
      Create_reputation(BG[0],600,25,200,30);
      Create_Score(BG[0],1200,25,200,30);
      Create_icon(BG[0],1325-ICON.MENU_WIDTH,725-ICON.LOGO_HEIGHT,ICON.MENU_WIDTH,ICON.LOGO_HEIGHT,ICON.MENU[this.m.get_selected()]);
    }
   
    public void BGM(){
      BGM= new SoundEffect(BGM_country[this.m.get_selected()]);
      BGM.setVolume(this.m.get_current_vol_BGM());
       BGM.playLoop(); 
    }
    public int get_current_Table_X(){
        return this.CurrentTable_X;
    }
    public void Create_set_of_tables(){
        Table = new JLabel[this.m.Number_of_Table()];
        for(int i=0; i<this.Table.length; i++){
            switch(this.Table.length){
            case 1: //create 1 table at the middle
                Create_Table(BG[0],i, (Currentframe.getWidth()/2)-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
                Player.setX_Y(Table[i].getX(), Table[i].getY()-(Player.getHeight()));
                this.CurrentTable_X=(Currentframe.getWidth()/2)-ICON.Table_WIDTH;
                break;
            case 2://create 2 tables at the one-third of frame's width
                if(i==0){
                Create_Table(BG[0],i, ((Currentframe.getWidth()/3)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
                Player.setX_Y(Table[i].getX(), Table[i].getY()-(Player.getHeight()));
                this.CurrentTable_X=((Currentframe.getWidth()/3)*(i+1))-ICON.Table_WIDTH;
                distant=((Currentframe.getWidth()/3));
            }else{
               Create_Table(BG[0],i,((Currentframe.getWidth()/3)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
            }
                break;
            case 3://create 3 tables at the one-third of frame's width
                if(i==0){
                Create_Table(BG[0],i, ((Currentframe.getWidth()/4)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
                Player.setX_Y(Table[i].getX(), Table[i].getY()-(Player.getHeight()));
                this.CurrentTable_X=((Currentframe.getWidth()/4)*(i+1))-ICON.Table_WIDTH;
                  distant=((Currentframe.getWidth()/4));
            }else{
               Create_Table(BG[0],i, ((Currentframe.getWidth()/4)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
            }
                break;
            case 4:
                if(i==0){
                Create_Table(BG[0],i, ((Currentframe.getWidth()/5)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
                Player.setX_Y(Table[i].getX(), Table[i].getY()-(Player.getHeight()));
                this.CurrentTable_X=((Currentframe.getWidth()/5)*(i+1))-ICON.Table_WIDTH;
                distant=((Currentframe.getWidth()/5));
            }else{
               Create_Table(BG[0],i, ((Currentframe.getWidth()/5)*(i+1))-ICON.Table_WIDTH,250,ICON.Table_WIDTH,ICON.Table_HEIGHT,ICON.LONG_TABLE);
            }
                break;
                
           }
            
         
        }
        
    }
    public void add_Table(JPanel p){
        for(int i=0; i<Table.length; i++){
             p.add(Table[i]);
        }
    }
    public void Create_Table(int i,int n,int x,int y,int width,int height,String fname){
        
        Table[n] = new JLabel();
        Tableimg = new MyImageIcon(fname).resize(width,height);
        Table[n].setIcon(Tableimg);
        Table[n].setBounds(x, y, width, height);
        Contentpane[i].add(Table[n]);
    }
     public void Create_BG(int i,String fname){
       
        Contentpane[i]=new JPanel();
        Contentpane[i].setLayout( new BorderLayout() ); 
        backgroundImg  = new MyImageIcon(fname).resize(WIDTH, HEIGHT); //create MainBG
        drawpane[i]= new JLabel();//create JLabel 
        drawpane[i].setLayout(null);
        drawpane[i].setBounds(0,0,WIDTH,HEIGHT);//bounds = BG size
	drawpane[i].setIcon(backgroundImg);
        
       
    }
    public void setPanel(JPanel p){
        currentPanel = p;
    }
    public void Create_player(int i,int x,int y,int width,int height){
      
        playerimg = this.m.get_p().resize(width,height);
        Player = new PlayerLabel(this.m,Currentframe,playerimg,x,y,width,height);
        Contentpane[i].add(Player);
      //  Player = this.m.get_player();
      
        Currentframe.addKeyListener(new KeyAdapter(){
             public void keyPressed ( KeyEvent e ){
                 if(!food.get_stop()){
                 if(e.getKeyCode()==KeyEvent.VK_LEFT ||e.getKeyCode()==KeyEvent.VK_A){
                     if(Player.getX()>Table[0].getX()){
                         
                         Player.moveleft(distant);
                        // System.out.printf("LEFT %d,%d\n",Player.getX(),Table[0].getX());
                     } 
                 }else if(e.getKeyCode()==KeyEvent.VK_RIGHT ||e.getKeyCode()==KeyEvent.VK_D){
                     if(Player.getX()<Table[Table.length-1].getX()-ICON.Table_WIDTH){
                   
                        Player.moveright(distant);
                            //System.out.printf("RIGHT %d,%d\n",Player.getX(),Table[Table.length-1].getX());
                     }
                 }
                 }
               /*  if(e.getKeyCode() == KeyEvent.VK_E){
                     if(!create){
                         System.out.println("Create Player");
                        food.Set_playerThread(Currentframe, drawpane[i],playerimg,Contentpane[i], x, y);
                        create = true;
                     }
              
                 }*/
        }
        });
    }
    public int get_repu(){
        return this.Reputation;
    }
    public void Player_hurt(){
        Player.hurt();
    }
    public void Game_OVER(){
        Player.death();
    }
}
