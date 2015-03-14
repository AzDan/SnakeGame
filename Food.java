public class Food{
    
    private int fX = 0;
    private int fY = 0;
    private boolean fPlaced = false;
    private Food f;
    
    public int getFoodX(){
        return fX;
    }
    public void setFoodX(int pX){
        fX = pX;
    }
    public int getFoodY(){
        return fY;
    }
    public void setFoodY(int pY){
        fY = pY;
    }
    public boolean isFoodPlaced(){
        return fPlaced;
    }
    public void setFoodPlaced(boolean placeFood){
        fPlaced = placeFood;
    }
    public Food getFood(){
        return f;
    }
    public void setFood(Food food){
        f = food;
    }
}
