/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project3_6513133;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
class QuickDialog
         {
     
        public static void show(String message)
        {
	JOptionPane.showMessageDialog(new JFrame(), message, "GAME OVER!",
			              JOptionPane.INFORMATION_MESSAGE );
          }
         };
