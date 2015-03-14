import java.awt.Rectangle;

public class GameEngine implements Runnable{
        private boolean started = false;
	private boolean finishedBlink1 = false;
	private boolean finishedBlink2 = false;
	private boolean finished = false;														
	private int score = 0;
	private int maxScoreLeft = 0;
	private int seconds = 0;
	private int minutes = 0;
	private boolean pause = false;
	private boolean restart = false;
	private boolean startMenu = true;
	private int difficultyLevel = 0;	//0=easy, 1=normal, 2=hard
        private int LevelNo = 0;                //0=1, 1=2, 2=3
        private int wrap = 0;
	private  int difficultyLevel_thread;
	private String menuSelection = "Start game";
        private Snake s;
	private GameEngine sGame;
	private Food food;
        private BadFood bfood;
        
        public void startGame(GameEngine sGame){										
            started = true;
            Thread t = new Thread(sGame);      
	    menuSelection = "Restart game";

		switch(sGame.getDifficultyLevel()){  
		    case 0: sGame.setDifficultyLevel_thread(100);
			    break;
                    case 1: sGame.setDifficultyLevel_thread(75);
			    break;
                    case 2: sGame.setDifficultyLevel_thread(50);
			    break;		
		}
	    t.start();
	}
	
	public void restartGame(){
		s.setSPX(100);
		s.setSPY(100);
		s.setSL(false);
		s.setSU(false);
		s.setSD(false);
		s.setSR(true);
		sGame.setFinished(false);
		sGame.setScore(0);
		sGame.setSeconds(0);
		sGame.setMinutes(0);
		sGame.setMaxScoreLeft(100);
		sGame.setPause(false);
		s.getList().clear();
		s.getList().add(new Rectangle(s.getSPX(), s.getSPY(), 10, 10));
	}

        public void finishGame(){															
		started = false;
                
		for(int i=0; i<4; i++){			
			finishedBlink1 = true;
			GUI.Gframe.repaint();
			try{
			    Thread.sleep(500);
			} 
                        catch (InterruptedException e){
			}
			finishedBlink1 = false;
			finishedBlink2 = true;
			GUI.Gframe.repaint();
			
			try{
			    Thread.sleep(500);
			} 
                        catch (InterruptedException e){
                            
			}
			finishedBlink2 = false;
		}
		finishedBlink1 = false;
		finishedBlink2 = false;
		finished = true;
		menuSelection = "Restart game";											
		GUI.Gframe.repaint();
	}
	
        public void run() {
	    s = new Snake();
            food = new Food();
	    food.setFood(food);
            bfood = new BadFood();
            bfood.setBFood(bfood);
            Main.gui.setBFood(bfood);
	    Main.gui.setFood(food);							
	    Main.gui.setSnake(s);
		
		s.getList().add(new Rectangle(s.getSPX(), s.getSPY(), 10, 10));
                long startTime = System.currentTimeMillis();
		long currentTime = 0;

		while(sGame.started == true){
		    if(sGame.isPause() == false){
                        collisionObs();
			collisionWall();
			placeFood();
			    if(sGame.getMaxScoreLeft()>0){
				sGame.setMaxScoreLeft(sGame.getMaxScoreLeft()-1);
			    }
                            collisionFood();
                            collisionSnake();
			
		        s.getList().add(new Rectangle(s.getSPX(), s.getSPY(), 10, 10));
			s.getList().remove(0);
			currentTime = System.currentTimeMillis();
			    if(currentTime - startTime >= 901){						
				if(sGame.getSeconds()<59){
					sGame.setSeconds(sGame.getSeconds()+1);
				}
                                else if(sGame.getSeconds()>=59){
				    sGame.setSeconds(0);
	            		    sGame.setMinutes(sGame.getMinutes()+1);
				}	
			        startTime += currentTime - startTime + 100;
                            }			
                    }
		    GUI.Gframe.repaint();
			try{
			    Thread.sleep(sGame.getDifficultyLevel_thread());
			} 
                        catch (InterruptedException e){

                        }
                        
		}
	}
	//COLLISION LOGIC
        public void collisionObs(){
            if(sGame.getLevelNo()==0){
                if(s.isSR()==true){
                    if((s.getSPX()>309)&&(s.getSPX()<320)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()>65)&&(s.getSPX()<75)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
                else if((s.isSL()==true)){
                    if((s.getSPX()>320)&&(s.getSPX()<340)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()<100)&&(s.getSPX()>80)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
            }
            else if(sGame.getLevelNo()==1){
                if(s.isSR()==true){
                    if((s.getSPX()>309)&&(s.getSPX()<320)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()>65)&&(s.getSPX()<75)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
                else if((s.isSL()==true)){
                    if((s.getSPX()>320)&&(s.getSPX()<340)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()<100)&&(s.getSPX()>80)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
                else if((s.isSU()==true)){
                    if((s.getSPY()<70)&&(s.getSPY()>50)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                   }
                }
                else if((s.isSD()==true)){
                    if((s.getSPY()<55)&&(s.getSPY()>39)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                    }
                }
            }
            else if(sGame.getLevelNo()==2){
                if(s.isSR()==true){
                    if((s.getSPX()>309)&&(s.getSPX()<320)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()>65)&&(s.getSPX()<75)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
                else if((s.isSL()==true)){
                    if((s.getSPX()>320)&&(s.getSPX()<340)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                    else if((s.getSPX()<100)&&(s.getSPX()>80)&&(s.getSPY()>75)&&(s.getSPY()<275)){
                        finishGame();
                    }
                }
                else if((s.isSU()==true)){
                    if((s.getSPY()<70)&&(s.getSPY()>50)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                   }
                    else if((s.getSPY()>300)&&(s.getSPY()<320)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                    }
                }
                else if((s.isSD()==true)){
                    if((s.getSPY()<55)&&(s.getSPY()>39)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                    }
                    else if((s.getSPY()>290)&&(s.getSPY()<310)&&(s.getSPX()>125)&&(s.getSPX()<275)){
                        finishGame();
                    }
                }
            }
        }
	
        public void collisionWall(){
            if(sGame.getWrapNo()==0){
                if(s.isSU()==true){
                    if(s.getSPY()<20){
                     finishGame();
                    }
	            else{
                         s.setSPY(s.getSPY() - 15);
                    }
                }
                else if(s.isSD()==true){
			if(s.getSPY()>335){
			   finishGame();
			}
                        else{
			     s.setSPY(s.getSPY() + 15);
			}
                }
                else if(s.isSL()==true){
			if(s.getSPX()<20){
			   finishGame();
			}
                        else{
			     s.setSPX(s.getSPX() - 15);
			}
	        }
                else if(s.isSR()==true){
			if(s.getSPX()>370){
			   finishGame();
			}
                        else{
			     s.setSPX(s.getSPX() + 15);
			}
	        }
            }
            else if(sGame.getWrapNo()==1){
                    if(s.isSU()==true) {
                        if(s.getSPY()<20) {
                            s.setSPY(385);
                        }
	                else{
                             s.setSPY(s.getSPY() - 15);
                        }
                    }
                    else if(s.isSD()==true){
			    if(s.getSPY()>335){
				s.setSPY(20);
			    }
                            else{
				 s.setSPY(s.getSPY() + 15);
			    }
		    }
                    else if(s.isSL()==true){
			    if(s.getSPX()<20){
				s.setSPX(395);
			    }
                            else{
				 s.setSPX(s.getSPX() - 15);
			    } 
		    }
                    else if(s.isSR()==true){
			    if(s.getSPX()>380){
				s.setSPX(10);
			    }
                            else{
				 s.setSPX(s.getSPX() + 15);
			    }
		    }
            }
        }
	
        public void collisionSnake(){
		for(int i=1; i<s.getList().size()-1; i++) {
		    if(i+1<s.getList().size()) {
			if(s.getList().get(0).intersects(s.getList().get(i+1))) {
                            finishGame();
                        } 
                    }
		}
	}
	
	public void collisionFood(){
		if(Math.abs(food.getFoodX()-s.getSPX())<=10  &&  Math.abs(food.getFoodY()-s.getSPY())<=10){
		    food.setFoodPlaced(false);
                    s.getList().add(new Rectangle(s.getSPX(), s.getSPY(), 10, 10));
                    sGame.score += sGame.getMaxScoreLeft();
		}
                else if((Math.abs(bfood.getBFoodX()-s.getSPX())<=7) &&  (Math.abs(bfood.getBFoodY()-s.getSPY())<=7)){
                        finishGame();
	        }
        }
	
	public void placeFood(){
	        if(food.isFoodPlaced() == false){
		    food.setFoodX((int) (35+Math.random()*320));
	       	    food.setFoodY((int) (35+Math.random()*305));
	            food.setFoodPlaced(true);
		    sGame.maxScoreLeft = 100;
                    bfood.setBFoodX((int) (35+Math.random()*320));
	            bfood.setBFoodY((int) (35+Math.random()*305));
                    bfood.setBFoodPlaced(true);
		}
	}
	
        public Snake getSnake(){
	    return s;
	}

        public void setSnake(Snake s){
	    this.s = s;
	}

	public boolean isStarted(){
	    return started;
	}

	public void setStarted(boolean started){
	    this.started = started;
	}

	public boolean isFinished(){
	    return finished;
	}

	public void setFinished(boolean finished){
	    this.finished = finished;
	}

	public int getScore(){
	    return score;
	}

	public void setScore(int score){
		this.score = score;
	}

	public GameEngine getSnakeGame(){
		return sGame;
	}

        public void setGameEngine(GameEngine sGame){
	    this.sGame = sGame;
	}

	public boolean isFinishedBlink1(){
	    return finishedBlink1;
	}

        public void setFinishedBlink1(boolean finishedBlink1){
	    this.finishedBlink1 = finishedBlink1;
	}

        public boolean isFinishedBlink2(){
	    return finishedBlink2;
	}

        public void setFinishedBlink2(boolean finishedBlink2){
	    this.finishedBlink2 = finishedBlink2;
	}

        public boolean isPause(){
	    return pause;
	}

        public void setPause(boolean pause){
	    this.pause = pause;
	}

        public boolean isRestart(){
	    return restart;
	}

        public void setRestart(boolean restart){
	    this.restart = restart;
	}

	public String getMenuSelection(){
            return menuSelection;
	}

	public void setMenuSelection(String menuSelection){
            this.menuSelection = menuSelection;
	}

        public int getMaxScoreLeft(){
	    return maxScoreLeft;
	}

	public void setMaxScoreLeft(int maxScoreLeft){
            this.maxScoreLeft = maxScoreLeft;
	}

	public int getSeconds(){
            return seconds;
	}

	public void setSeconds(int seconds){
            this.seconds = seconds;
	}

	public int getMinutes(){
            return minutes;
	}

	public void setMinutes(int minutes){
            this.minutes = minutes;
	}

        public boolean isStartmenu(){
            return startMenu;
	}

	public void setStartmenu(boolean startmenu){
            this.startMenu = startmenu;
	}

	public int getDifficultyLevel(){
            return difficultyLevel;
	}
        public int getLevelNo(){
            return LevelNo;
        }

	public void setDifficultyLevel(int difficultyLevel){
            this.difficultyLevel = difficultyLevel;
	}
        public void setLevelNo(int LevelNo){
            this.LevelNo = LevelNo;
        }

	public int getDifficultyLevel_thread(){
            return difficultyLevel_thread;
	}

	public void setDifficultyLevel_thread(int difficultyLevel_thread){
            this.difficultyLevel_thread = difficultyLevel_thread;
	}
        
        public int getWrapNo(){
            return wrap;
        }
        
        public void setWrapNo(int wrap){
            this.wrap = wrap;
        }
}
