/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Project3_6513133;

/**
 *
 * @author Meena
 */
public interface Food {
    static final String PATH = "src/main/java/Project3_6513133/resources/Food/";
    //Food path: No item but ready to be added
    //Thai FOOD
    static final String FILE_F11 = PATH + "F11.png";
    static final String FILE_F12 = PATH + "F12.png";
    static final String FILE_F13 = PATH + "F13.png";
    //Chinese FOOD
    static final String FILE_F21 = PATH + "F21.png";
    static final String FILE_F22 = PATH + "F22.png";
    static final String FILE_F23 = PATH + "F23.png";
    //Japanese FOOD
    static final String FILE_F31 = PATH + "F31.png";
    static final String FILE_F32 = PATH + "F32.png";
    static final String FILE_F33 = PATH + "F33.png";
    //KOrean FOOD
    static final String FILE_F41 = PATH + "F41.png";
    static final String FILE_F42 = PATH + "F42.png";
    static final String FILE_F43 = PATH + "F43.png";
    //Viwtnamese Food
    static final String FILE_F51 = PATH + "F51.png";
    static final String FILE_F52 = PATH + "F52.png";
    static final String FILE_F53 = PATH + "F53.png";
    
    
    static final String[][]imageFiles = {{Food.FILE_F11,Food.FILE_F12,Food.FILE_F13},{Food.FILE_F21,Food.FILE_F22,Food.FILE_F23},{Food.FILE_F31,Food.FILE_F32,Food.FILE_F33}
     ,{Food.FILE_F41,Food.FILE_F42,Food.FILE_F43},{Food.FILE_F51,Food.FILE_F52,Food.FILE_F53}};
    
    public static final int FOOD_WIDTH = 60;
    public static final int FOOD_HEIGHT = 60;
}
