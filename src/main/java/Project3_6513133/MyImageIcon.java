
package Project3_6513133;
import java.awt.Image;
import javax.swing.ImageIcon;

// Auxiliary class to resize image
 class MyImageIcon extends ImageIcon {
      public MyImageIcon(String fname)  { super(fname); }
      public MyImageIcon(Image image)   { super(image); }
      public MyImageIcon resize(int width, int height)
     {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}
