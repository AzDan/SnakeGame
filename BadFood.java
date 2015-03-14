public class BadFood{
    
    private int bfX = 0;
    private int bfY = 0;
    private boolean bfPlaced = false;
    private BadFood bf;
    
    public int getBFoodX(){
        return bfX;
    }
    public void setBFoodX(int posX){
        this.bfX = posX;
    }
    public int getBFoodY(){
        return bfY;
    }
    public void setBFoodY(int posY){
        this.bfY = posY;
    }
    public boolean isBFoodPlaced(){
        return bfPlaced;
    }
    public void setBFoodPlaced(boolean bfoodPlaced){
        this.bfPlaced = bfoodPlaced;
    }
    public BadFood getBFood(){
        return bf;
    }
    public void setBFood(BadFood bf){
        this.bf = bf;
    }
}
