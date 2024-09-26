/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;

/**
 *
 * @author User
 */
interface Audios {
        public static final String PATH_BGM = "src/main/java/Project3_6513133/resources/BGM/";//PATH VGM
        public static final String PATH_SOUNDFX = "src/main/java/Project3_6513133/resources/SoundFX/";//PATH DOUNDFX
        public static final String PATH_FOODSFX = "src/main/java/Project3_6513133/resources/Food/";//PATH DOUNDFX
        
        
        //BGM
        public static final String MENU = PATH_BGM +"MENU.wav";
        public static final String THAI = PATH_BGM +"Thai.wav";
        public static final String CHINA = PATH_BGM +"Chinese.wav";
        public static final String JAPAN = PATH_BGM +"Japanese.wav";
        public static final String KOREA = PATH_BGM +"Korean.wav";
        public static final String VIETNAM = PATH_BGM +"Vietnamese.wav";
        
        
        //SOUNDFX
        public static final String Arrow_Back = PATH_SOUNDFX  +"Arrow_Back.wav";
        public static final String Settings = PATH_SOUNDFX +"Setttings.wav";
        public static final String Play = PATH_SOUNDFX+"PLAY.wav";
        public static final String FILE_HIT1 = PATH_SOUNDFX+"HIT1.wav";
        public static final String FILE_HIT2 = PATH_SOUNDFX+"HIT2.wav";
        public static final String FILE_HIT3 = PATH_SOUNDFX+"HIT3.wav";
        public static final String FILE_RE1 = PATH_SOUNDFX+"Respawn1.wav";
        public static final String FILE_RE2 = PATH_SOUNDFX+"Respawn2.wav";
        public static final String FILE_RE3 = PATH_SOUNDFX+"Respawn3.wav";
        public static final String GO = PATH_SOUNDFX+"GO.wav";
        public static final String HTP = PATH_SOUNDFX+"HTP.wav";
        public static final String Custom = PATH_SOUNDFX+"Customized.wav";
        public static final String Credit = PATH_SOUNDFX+"Credit.wav";
        public static final String Hurt = PATH_SOUNDFX+"hurt.wav";
        public static final String GOVER = PATH_SOUNDFX+"GAMEOVER.wav";
         
        public static final String[] FILE_HIT = {FILE_HIT1,FILE_HIT2,FILE_HIT3};
        public static final String[] FILE_RE = {FILE_RE1,FILE_RE2,FILE_RE3};
}
