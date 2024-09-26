/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
/**
 *
 * @author User
 */
 class MENU extends JFrame {
    private final MainApplication game;
    private Game g;
    
    private int WIDTH = backgrounds.BG_WIDTH; //Width of background
    private int HEIGHT = backgrounds.BG_HEIGHT;//Height of background
    private int LG_WIDTH = ICON.LOGO_WIDTH; // Width of LOGO
    private int LG_HEIGHT = ICON.LOGO_HEIGHT;//Height of LOGO
   
    private int Settings_W = Buttons.Settings_WIDTH;//Width of settings icon
    private int Settings_H = Buttons.Settings_HEIGHT;//Height of settings icon
    
    private String name= "unknown";
    private String [] Cos_name={"THAI","CHINA","JAPAN","KOREA","VIETNAM"};
    private String [] PCos ={Player_Cos.Costume1,Player_Cos.Costume2,Player_Cos.Costume3,Player_Cos.Costume4,Player_Cos.Costume5}; //costume player
    private String [] MUTE_UNMUTE = {Buttons.UNMUTE,Buttons.MUTE};
    private String [] Diffculty = {"BEGINNER","AMATEUR","INTERMEDIATE","PROFESSIONAL","ASIAN"}; 
    private JButton Settings,Arrow,Play,OK,GO,MUTE_BGM,MUTE_SFX,HTP,CREDIT;
    private JTextField username; // textfield for username
    private JComboBox Costume; //combox for costume of player
    private JToggleButton diff[];
    private ButtonGroup group;
    private MyImageIcon backgroundImg,Icon,Button_img,P,c;  //Background , icon , button image
    private MyImageIcon[] Sound= new MyImageIcon[2];
    private MENU currentframe; //this frame
    private DefaultListCellRenderer listRenderer;
    private JPanel [] Contentpane = new JPanel[7];
    private JLabel[] drawpane = new JLabel[7];
    private JPanel currentPanel;
    private JLabel player;
    private JSpinner DIFF;
    private JSlider[] sound_slider = new JSlider[2];
    private int NumofTable;
    private int current_sound_value,selected;
    private float volume = 0.085f;
    private float current_vol,current_BGM,Current_SFX;
    private int[]BG = {1,2,3,4,5,6};
    private int sound_bar_WIDTH=400;
    private int sound_bar_HEIGHT=30;
    private boolean mute_BGM =false;
    private boolean mute_SFX =false;
    private int speed,spawn;
    private SoundEffect Theme_sound,Setting_sound,Arrow_sound,Play_sound,go,how,credit,customized;
    public MENU(MainApplication gm){
        this.game = gm;
        Create_Main_MENU();
        
        
    }
    public String get_name(){
        return this.name;
    }
   public JLabel get_player(){
       return this.player;
   }
   public MyImageIcon get_p(){
       return this.P;
   }
   public int speed(){
       return this.speed;
   }
   public int spawn(){
       return this.spawn;
   }
     
    public void Create_Main_MENU(){
        setTitle("Chaotic Diner"); //title name
	setSize(WIDTH,HEIGHT ); //frame size
        setLocationRelativeTo(null);
        setLayout( new FlowLayout());
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ); // System.exit(0) call will be executed
        Contentpane[0]=(JPanel)getContentPane();
        drawpane[0]= new JLabel();
        currentframe = this;
        generate_Component(); // generate Component
        MENU_BGM();
        setPanel(Contentpane[BG[0]]);
        currentframe.add(currentPanel);
        validate();
        setVisible(true); 
     }
    public void setPanel(JPanel p){
        currentPanel = p;
    }
    public int get_selected(){
        return this.selected;
    }
    
    public void generate_Component()// generate Component
    {   
        this.Create_MENU();
        this.Create_Setting();
        this.Create_Customized();
        this.Create_DIFF();
        this.Create_HTP();
        this.Credit();
       
    }
    public void Create_MENU(){
         Create_BG(BG[0],backgrounds.MENU_BG);//create MENU's background
         Create_icon(BG[0],533,7,LG_HEIGHT,LG_WIDTH,ICON.LOGO);//create LOGO
         Create_Settings(BG[0],40,600,Settings_W,Settings_H,Buttons.Settings_ICON);//create Settins icon
         Create_play_button(BG[0],533,310,Buttons.Play_WIDTH,Buttons.Play_HEIGHT,Buttons.Play);//create play button
         Create_HTP_Button(BG[0],533,460,Buttons.Play_WIDTH,Buttons.Play_HEIGHT,Buttons.HTP);
        Create_credit_button(BG[0],533,610,Buttons.Play_WIDTH,Buttons.Play_HEIGHT,Buttons.Credit);
         Contentpane[BG[0]].add(drawpane[BG[0]]);// add MENU BG
    }
    public void Create_Setting(){
         //Settings Background
         Create_BG(BG[1],backgrounds.Setting_BG);
          Create_Arrow(BG[1],40,50,Buttons.Arrow_WIDTH,Buttons.Arrow_HEIGHT,Buttons.Arrow);
          Create_icon(BG[1],375,250,ICON.BGM_WIDTH,ICON.BGM_HEIGHT,ICON.BGM_ICON);
          Create_icon(BG[1],375,450,ICON.SFX_WIDTH,ICON.SFX_HEIGHT,ICON.SFX);
          Create_Sound_bar(BG[1],0,1,(currentframe.getWidth()-sound_bar_WIDTH)/2,300);//BGM sound bar
          Create_Sound_bar(BG[1],1,2,(currentframe.getWidth()-sound_bar_WIDTH)/2,500);//SFX sound bar
          Create_mute_BGM_button(BG[1],900,250,ICON.BGM_WIDTH,ICON.BGM_HEIGHT);
          Create_mute_SFX(BG[1],900,450,ICON.SFX_WIDTH,ICON.SFX_HEIGHT);
          
          Contentpane[BG[1]].add(drawpane[BG[1]]);
         
    }
    public void Create_Customized(){
         
          //Customized Background (After press Play button)
          Create_BG(BG[2],backgrounds.Custom_BG);
          Create_Arrow(BG[2],40,50,Buttons.Arrow_WIDTH,Buttons.Arrow_HEIGHT,Buttons.Arrow); //create arrow1`2w
          Create_TextField(BG[2],500,80,400, 40);
          Create_OK_button(BG[2],533,510,Buttons.Play_WIDTH,Buttons.Play_HEIGHT,Buttons.Customized);
          Create_ComboBox(BG[2],700,200,400,100);
          Contentpane[BG[2]].add(drawpane[BG[2]]);
    }
    public void Create_DIFF(){
          //Diff Background (After OK button)
         Create_BG(BG[3],backgrounds.Diff_BG);
         Create_Arrow(BG[3],40,50,Buttons.Arrow_WIDTH,Buttons.Arrow_HEIGHT,Buttons.Arrow);
         Create_GO_button(BG[3],533,410,Buttons.Play_WIDTH,Buttons.Play_HEIGHT,Buttons.GO);
         Create_Spinner(BG[3],currentframe.getWidth()/2+200,100,100,100);
         Create_diff_box(BG[3],300,200 ,200,50);
         Contentpane[BG[3]].add(drawpane[BG[3]]);
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
    public void Create_HTP(){
       Create_BG(BG[4],backgrounds.HTP_BG);
       Create_Arrow(BG[4],40,50,Buttons.Arrow_WIDTH,Buttons.Arrow_HEIGHT,Buttons.Arrow); 
        Contentpane[BG[4]].add(drawpane[BG[4]]);
    }
    public void Credit(){
       Create_BG(BG[5],backgrounds.Credit_BG);
       Create_Arrow(BG[5],40,50,Buttons.Arrow_WIDTH,Buttons.Arrow_HEIGHT,Buttons.Arrow); 
       Contentpane[BG[5]].add(drawpane[BG[5]]); 
    }
    public void Create_credit_button(int i,int x,int y,int width,int height,String fname){
         c = new MyImageIcon(fname).resize(width,height);
         CREDIT = new JButton(c);
          CREDIT.setBounds(x, y, width, height);
          CREDIT.setContentAreaFilled(false);
          CREDIT.setBorderPainted(false);
          Credit_SOUND();
         Contentpane[i].add(CREDIT);//add icon
         CREDIT.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
               credit.playOnce();
            }
         });
         CREDIT.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
              System.out.println(name);
               SceneChange(Contentpane[BG[5]]); // change to diff 
            }
           });
    }
    public void Create_HTP_Button(int i,int x,int y,int width,int height,String fname){
        Button_img = new MyImageIcon(fname).resize(width,height);
         HTP = new JButton(Button_img);
         HTP.setBounds(x, y, width, height);
         HTP.setContentAreaFilled(false);
         HTP.setBorderPainted(false);
         HTP_SOUND();
         Contentpane[i].add(HTP);//add icon
         HTP.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
               how.playOnce();
            }
         });
         HTP.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
              System.out.println(name);
               SceneChange(Contentpane[BG[4]]); // change to diff 
            }
           });
    }
    public void Set_MUTE_UNMUTE_Button(int width,int height){
          Sound[0] = new MyImageIcon(MUTE_UNMUTE[0]).resize(width,height);
          Sound[1] = new MyImageIcon(MUTE_UNMUTE[1]).resize(width,height);
    }
    public void Create_mute_BGM_button(int i,int x,int y,int width,int height){
        Set_MUTE_UNMUTE_Button(width, height);
        MUTE_BGM =new JButton(Sound[0]);
        MUTE_BGM.setBounds(x, y, width, height);
        MUTE_BGM.setContentAreaFilled(false);
        Contentpane[i].add(MUTE_BGM);
        MUTE_BGM.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
               //  MUTE_condition( sound_slider[0],MUTE_BGM,mute_BGM);
                if(!mute_BGM){
                    current_sound_value = sound_slider[0].getValue();
                    sound_slider[0].setValue(0);
                    Theme_sound.setVolume(0f); 
                    MUTE_BGM.setIcon(Sound[1]); 
                    mute_BGM =true;
                 }else{
                   sound_slider[0].setValue( current_sound_value);
                    Theme_sound.setVolume(current_vol);
                    if(current_vol>0){
                       MUTE_BGM.setIcon(Sound[0]); 
                    }
                    mute_BGM =false;  
                 }
                
                refresh();
            }
           });
    }
    public void MUTE_condition(JSlider s,JButton b,boolean m){
        if(!m){
                    current_sound_value = s.getValue();
                    s.setValue(0);
                    Theme_sound.setVolume(0f); 
                    b.setIcon(Sound[1]); 
                    m =true;
                 }else{
                   s.setValue( current_sound_value);
                    Theme_sound.setVolume(current_vol);
                    if(current_vol>0){
                       b.setIcon(Sound[0]); 
                    }
                    m=false;  
                 }
                
                refresh();
            
          
    }
    public void Create_mute_SFX(int i,int x,int y,int width,int height){
        MUTE_SFX=new JButton(Sound[0]);
        MUTE_SFX.setBounds(x, y, width, height);
        MUTE_SFX.setContentAreaFilled(false);
        Contentpane[i].add(MUTE_SFX);
        MUTE_SFX.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
              /* if(!mute){
                   
                    MUTE_SFX.setIcon(Sound[1]);
                    
                    mute =true;
                 }else if(mute&&current_vol>0){
                    MUTE_SFX.setIcon(Sound[0]);    
                    mute =false;  
                 }
               
                refresh();*/
                  if(!mute_SFX){
                    current_sound_value = sound_slider[1].getValue();
                    sound_slider[1].setValue(0);
                    Set_SFX(0f); 
                    MUTE_SFX.setIcon(Sound[1]); 
                    mute_SFX =true;
                 }else{
                   sound_slider[1].setValue( current_sound_value);
                    Set_SFX(current_vol); 
                    if(current_vol>0){
                       MUTE_SFX.setIcon(Sound[0]); 
                    }
                    mute_SFX =false;  
                 }
                
                refresh();
            }
           });
        
    }
    public void Create_icon(int i,int x,int y,int width,int height,String fname){
        JLabel icon = new JLabel();
        Icon = new MyImageIcon(fname).resize(width,height);
        icon.setBounds(x,y,width,height);
        icon.setIcon(Icon);//seticon 
        Contentpane[i].add(icon);//add icon
        
        // layers : 1. Jframe -> 2. JPanel ( last add ( most backward)- > first add(most forward))
    }
    public void Create_Sound_bar(int i,int in,int n,int x,int y){
         sound_slider[in] = new JSlider();
          sound_slider[in].setBounds(x,y,sound_bar_WIDTH,sound_bar_HEIGHT);
       //  sound.setOpaque(false);//make BG transparent
         Contentpane[i].add( sound_slider[in]);//add icon
          current_sound_value= (int)(100*volume);
          sound_slider[in].setValue((int)(current_sound_value));
          sound_slider[in].addChangeListener(new ChangeListener(){
          public void stateChanged(ChangeEvent e) {
            float sound = sound_slider[in].getValue();
          current_vol = ((float)sound/100);
          if(current_vol<=0){
              if(in==0){
                 MUTE_BGM.setIcon(Sound[1]);   
              }else{
                 MUTE_SFX.setIcon(Sound[1]);  
              }
            
             refresh();
             current_vol=0f; 
          }else if(current_vol>0){
              if(in==0){
                MUTE_BGM.setIcon(Sound[0]);    
              }else{
                MUTE_SFX.setIcon(Sound[0]);   
              }
             refresh();
              if(current_vol>100){
              current_vol =1f;
              }
          }
          switch(n){
              case 1:
                Theme_sound.setVolume(current_vol); //set BG vol
                current_BGM=current_vol;
                System.out.printf("Vol BGM %.2f \n",current_vol);
                  break;
              case 2:
                Set_SFX(current_vol); // set SFX Vol
                Current_SFX = current_vol;
                Play_sound.playOnce();
                System.out.printf("Vol SFX %.2f \n",current_vol);
              break;
              
          }
          
      }
    });

    }
    public void Create_OK_button(int i,int x,int y,int width,int height,String fname){
         Button_img = new MyImageIcon(fname).resize(width,height);
        OK = new JButton(Button_img);
         OK.setBounds(x, y, width, height);
        OK.setContentAreaFilled(false);
        OK.setBorderPainted(false);
        this.Customized_SOUND();
         Contentpane[i].add(OK);//add icon
         OK.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
                 customized.playOnce();
              System.out.println(name);
               SceneChange(Contentpane[BG[3]]); // change to diff 
            }
           });
         
    }
    public void Create_GAME(){
        this.Theme_sound.stop();
        this.g = new Game(this);
        this.dispose();
    }
    public void Create_GO_button(int i,int x,int y,int width,int height,String fname){
         Button_img = new MyImageIcon(fname).resize(width,height);
        GO = new JButton(Button_img);
        GO.setBounds(x, y, width, height);
        GO.setContentAreaFilled(false);
        GO.setBorderPainted(false);
         GO_SOUND();
        Contentpane[i].add(GO);//add icon
        GO.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
               go.playOnce();
            }
         });
        GO.addActionListener(new ActionListener()
          {
           public void actionPerformed(ActionEvent e)
             {
               Create_GAME();
            }
           });
    }
    public void Create_ComboBox(int i,int x,int y,int width,int height){
        Costume = new JComboBox(Cos_name);
        Costume.setBounds(x, y, width, height);
        Costume.setFont(new Font("monospaced", Font.BOLD, 48)); //set font
        Costume.setSelectedIndex(0);//selected first
        Create_player_costume(BG[2],200,200,Player_Cos.Player_cos_WIDTH,Player_Cos.Player_cos_HEIGHT,PCos[Costume.getSelectedIndex()]);//create costume 1
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        Costume.setRenderer(listRenderer);
        Contentpane[i].add(player);
        Contentpane[i].add(Costume);
        Costume.addItemListener(new ItemListener(){
            public void itemStateChanged ( ItemEvent e ){
                 selected = Costume.getSelectedIndex();
                switch(selected){
                    case 0:
                        change_cos(Contentpane[i],player,i,selected); //change to costume 1
                        break;
                    case 1:
                        change_cos(Contentpane[i],player,i,selected);//change to costume 2
                        break;
                    case 2:
                        change_cos(Contentpane[i],player,i,selected);//change to costume 3
                        break;
                    case 3:
                        change_cos(Contentpane[i],player,i,selected);//change to costume 4
                        break;
                    case 4:
                        change_cos(Contentpane[i],player,i,selected);//change to costume 5
                        break;
                }
                
            }
            
        });
    }
    public void Create_diff_box(int i,int x,int y,int width,int height){
        speed=100;
        spawn=2000;
        diff = new JToggleButton[this.Diffculty.length];
      
         JLabel label = new JLabel("SPEED");
         label.setFont(new Font("monospaced", Font.BOLD, 52));
         label.setBounds((currentframe.getWidth()/2)-275,y,width,height );
        for(int j=0; j<diff.length; j++){
            diff[j] = new JRadioButton(Diffculty[j]); diff[j].setContentAreaFilled(false); diff[j].setName(Diffculty[j]); diff[j].setFont(new Font("monospaced", Font.BOLD, 24));
            diff[j].setBounds(x+300, y+(35*j), width, height);
            if(j==2){
                diff[j].setSelected(true);
            }
        
            diff[j].addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
               JRadioButton temp = (JRadioButton)e.getItem();
                if(temp.isSelected()){
                    if(temp.getText().equalsIgnoreCase(Diffculty[0])){
                       speed=300;
                       spawn=4000;
                    }else if(temp.getText().equalsIgnoreCase(Diffculty[1])){
                        speed=200;
                        spawn=3000;
                    }else if(temp.getText().equalsIgnoreCase(Diffculty[2])){
                        speed=100;
                        spawn=2000;
                    }else if(temp.getText().equalsIgnoreCase(Diffculty[3])){
                        speed=75;
                        spawn=1000;
                    }else if(temp.getText().equalsIgnoreCase(Diffculty[4])){
                        speed=50;
                        spawn=750;
                    }
                }
            }
        });
        }
        group = new ButtonGroup();
        Contentpane[i].add(label);
           for(int j=0; j<diff.length; j++) group.add(diff[j]);
            for(int j=0; j<diff.length; j++) Contentpane[i].add(diff[j]);
       
    }
    public void change_cos(JPanel n,JLabel c,int i,int cos){ //change player's costume
        n.remove(c);
         Create_player_costume(BG[2],200,200,Player_Cos.Player_cos_WIDTH,Player_Cos.Player_cos_HEIGHT,PCos[cos]);
         Contentpane[i].add(player);
         Contentpane[BG[2]].add(drawpane[BG[2]]);
         repaint();
         validate();
    }
    public void Create_button(int i,int x,int y,int width,int height,String fname){   //button (dummy)
        Button_img = new MyImageIcon(fname).resize(width,height);
         JButton button = new JButton(Button_img);
         button.setBounds(x, y, width, height);
         button.setContentAreaFilled(false);
         Contentpane[i].add(button);//add icon
      
    }
    
    public int Number_of_Table(){
       return this.NumofTable;
    }
    public void set_num_of_Table(int t){
        this.NumofTable = t;
    }
    public void Create_player_costume(int i,int x,int y,int width,int height,String fname){//player costume
        player = new JLabel();
         P = new MyImageIcon(fname).resize(width,height);
        player.setIcon(P);
        player.setBounds(x, y, width, height);
    }
    public void Create_play_button(int i,int x,int y,int width,int height,String fname){ //play button
         Button_img = new MyImageIcon(fname).resize(width,height);
         Play = new JButton(Button_img);
         Play.setBorderPainted(false);
        /* Play.setBounds(x, y, width, height);
         Play.setContentAreaFilled(false);
         Contentpane[i].add(Play);//add icon*/
        add(Play,x,y,i,width,height);
        Play.setFocusPainted(false);
         Play_SOUND();
          Play.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
               Play_sound.playOnce();
            }
         });
         Play.addActionListener(new ActionListener()
          {
          public void actionPerformed(ActionEvent e)
           {
            
             SceneChange(Contentpane[BG[2]]);  //change to customized  
           }
           });
    }
      public void Create_Arrow(int i,int x,int y,int width,int height,String fname){   
        Button_img = new MyImageIcon(fname).resize(width,height);
         Arrow = new JButton(Button_img);
         Arrow.setBounds(x, y, width, height);
         Arrow.setContentAreaFilled(false);
         Arrow.setBorderPainted(false);
         Contentpane[i].add(Arrow);//add icon
         Arrow_SOUND();
         Arrow.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
                Arrow_sound.playOnce();
            }
         });
         Arrow.addActionListener(new ActionListener()
          {
          public void actionPerformed(ActionEvent e)
           {
               
               
             // SceneMENU();
             if(i==BG[3]){
                 SceneChange(Contentpane[BG[2]]); //change to customized
             }else{
                SceneChange(Contentpane[BG[0]]); //change to menu
             }
                
           }
           });
         
         
    }
    public void Create_Settings(int i,int x,int y,int width,int height,String fname){
         Button_img = new MyImageIcon(fname).resize(width,height);
         Settings = new JButton(Button_img);
         Settings.setBounds(x, y, width, height);
         Settings.setContentAreaFilled(false);
         Settings.setBorderPainted(false);
         Contentpane[i].add(Settings);//add icon
        Setting_SOUND();
         Settings.addMouseListener(new MouseAdapter(){
             public void mouseEntered(MouseEvent e) {
                Setting_sound.playOnce();
            }
         });
         Settings.addActionListener(new ActionListener()
          {
       public void actionPerformed(ActionEvent e)
       {
           
         //  SceneSettings();
         SceneChange(Contentpane[BG[1]]);
       }
         });
    }
    public void add(JButton b,int x,int y,int i,int w,int h){
        b.setBounds(x, y, w, h);
         b.setContentAreaFilled(false);
         Contentpane[i].add(b);//add icon
         
    }
    public void Create_TextField(int i,int x ,int y,int w,int h){
        username = new JTextField();
        JLabel label = new JLabel("ENTER THE PLAYER NAME"); //label enter the player name
        label.setFont(new Font("monospaced", Font.BOLD, 36)); //set font
        username.setBounds(x, y, w, h);
        username.setFont(new Font("monospaced", Font.BOLD, 36)); //set font
        label.setBounds((currentframe.getWidth()/2)-225,y-50, w+100,h+5 );
        Contentpane[i].add(username);
        Contentpane[i].add(label);
       
        username.addCaretListener(new CaretListener(){
           public void caretUpdate ( CaretEvent e ){
               if(username.getText()==null||username.getText()==""){
                    name="unknown";
               }else{
                    name=username.getText();//get text in textfield
               }
              
          }
        });
    }
    public void Create_Spinner(int i,int x ,int y,int w,int h){
        DIFF = new JSpinner(new SpinnerNumberModel(2,1,4,1));
        DIFF.setBounds(x, y, w ,h);
        DIFF.setFont(new Font("monospaced", Font.BOLD, 48));
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)DIFF.getEditor();
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER); //make not editable in spinner and center alignment
        JLabel label = new JLabel("DIFFICULTY"); //label enter the player name
        label.setFont(new Font("monospaced", Font.BOLD, 52)); //set font
        label.setBounds((currentframe.getWidth()/2)-150,y-100, w+400,h+5 );
        
        
         JLabel label2 = new JLabel("NUMBER OF TABLES");
         label2.setFont(new Font("monospaced", Font.BOLD, 48));
         label2.setBounds(x-475,y, w+400,h+5 );
         
        this.NumofTable=(int)DIFF.getValue();
         Contentpane[i].add(label);
          Contentpane[i].add(label2);
         Contentpane[i].add(DIFF);
         DIFF.addChangeListener(new ChangeListener(){
             public void stateChanged(ChangeEvent e) {
                  set_num_of_Table((int)DIFF.getValue());
                 
             }
             
         });
    }
    
    public void add_panel(){
      this.currentframe.add(currentPanel);//add Panel menu
       refresh();
    }
    public void refresh(){
        this.currentframe.repaint();//refresh 
      this.currentframe.validate();//reCalculate  
    }
    public float get_current_vol_BGM(){
        return this.current_BGM;
    }
    public float get_current_vol_SFX(){
         return this.Current_SFX;
    }
        
    
    
    //Change Scene
   public void SceneChange(JPanel n){
      this.currentframe.remove(currentPanel);//remoove current PANEL
      this.setPanel(n);//set Panel menu to currentPanel
      add_panel(); 
   }
   public void Set_Cos_name(){
       for(int i=0; i<Cos_name.length; i++){
           Cos_name[i] = " - ".repeat(3) + Cos_name[i] + " - ".repeat(3);
       }
   }
   public void MENU_BGM(){
       Theme_sound = new SoundEffect(Audios.MENU);
       Theme_sound.playLoop(); Theme_sound.setVolume(volume);
       current_BGM = volume;
   }
   public void Arrow_SOUND(){
       Arrow_sound = new SoundEffect(Audios.Arrow_Back);
       Arrow_sound.setVolume(volume);
   }
   public void Setting_SOUND(){
       Setting_sound = new SoundEffect(Audios.Settings);
       Setting_sound.setVolume(volume);
   }
   public void Play_SOUND(){
       Play_sound = new SoundEffect(Audios.Play);
        Play_sound.setVolume(volume);
        Current_SFX=volume;
   }
   public void Set_SFX(float v){
         Arrow_sound.setVolume(v);
         Play_sound.setVolume(v);
         Setting_sound.setVolume(v);
         Play_sound.setVolume(v);
         go.setVolume(v);
         how.setVolume(v);
         credit.setVolume(v);
         customized.setVolume(v);
   }
  public void GO_SOUND(){
        go = new SoundEffect(Audios.GO);
        go.setVolume(volume);
        Current_SFX=volume;
  }
  public void HTP_SOUND(){
       how = new SoundEffect(Audios.HTP);
        how.setVolume(volume);
        Current_SFX=volume;
  }
  public void Credit_SOUND(){
        credit = new SoundEffect(Audios.Credit);
        credit.setVolume(volume);
        Current_SFX=volume;
  }
  public void Customized_SOUND(){
      customized = new SoundEffect(Audios.Custom);
      customized.setVolume(volume);
      Current_SFX=volume;
      
  }
      
    
}
