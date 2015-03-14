import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake{
    //Initial values here.
    private int sPX = 100;
    private int sPY = 100;
    private boolean sUp = false;
    private boolean sDown = false;
    private boolean sLeft = false;
    private boolean sRight = true;
    private ArrayList<Rectangle> list = new ArrayList<>();
    
    public ArrayList<Rectangle> getList(){
        return list;
    }
    public void setList(ArrayList<Rectangle> list){
        this.list = list;
    }
    public int getSPX(){
        return sPX;
    }
    public void setSPX(int sX){
        sPX = sX;
    }
    public int getSPY(){
        return sPY;
    }
    public void setSPY(int sY){
        sPY = sY;
    }
    public boolean isSU(){
        return sUp;
    }
    public boolean isSD(){
        return sDown;
    }
    public boolean isSR(){
        return sRight;
    }
    public boolean isSL(){
        return sLeft;
    }
    public void setSU(boolean sUp){
        this.sUp = sUp;
    }
    public void setSD(boolean sDown){
        this.sDown = sDown;
    }
    public void setSR(boolean sRight){
        this.sRight = sRight;
    }
    public void setSL(boolean sLeft){
        this.sLeft = sLeft;
    }
}
