import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GUI{
    
    static JFrame Gframe;
    static JPanel Gpanel;
    static JLabel gifLabel;
    private Snake s;
    private GameEngine sGame;
    private Food food;
    private BadFood bfood;
    private Font MenuFont;
    private Font HeaderFont;
    private Font SideFont;
    
    public GUI(){
        sGame = new GameEngine();
        sGame.setGameEngine(sGame);
    }
    
    public void createGameWindow(){
        Gframe = new JFrame();
        Gframe.setSize(675,360);
        Gframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Gframe.setUndecorated(true);
        Gframe.setResizable(false);
        Gframe.setAlwaysOnTop(true);
        Gframe.setLocationRelativeTo(null);
        ImageIcon gif = new ImageIcon("GIF/snake.gif");
        Image anim = gif.getImage().getScaledInstance(200, 200, 0);
        gif = new ImageIcon(anim);
        gifLabel = new JLabel(gif);
        gifLabel.setSize(200, 200);
        gifLabel.setLocation(430, 150);
        gifLabel.setVisible(true);
        Gframe.add(gifLabel);
        Gpanel = new Draw();
        Gpanel.setLayout(null);
        Gframe.add(Gpanel);
        
        MenuFont = new Font(Font.SERIF,Font.PLAIN,14);
        HeaderFont = new Font(Font.SANS_SERIF,Font.BOLD,16);
        SideFont = new Font(Font.MONOSPACED,Font.BOLD,15);
        
        Gframe.setVisible(true);
        Gframe.addKeyListener(new KeyListener(){
            
            public void keyTyped(KeyEvent e){
                
            }
            public void keyPressed(KeyEvent e){
                if(sGame.isStarted() == true){
                    if(sGame.isPause() == true){
                        checkPauseMenuInputs(e);
                    }
                    else if(sGame.isPause() == false){
                        checkCurrentGameInputs(e);
                    }
                }
                else if(sGame.isFinished() == true){
                    checkGameOverInputs(e);
                }
                else if(sGame.isStartmenu()==true) {
                     checkStartMenuInputs(e);
                }
            }
          
            public void keyReleased(KeyEvent e){
                
            }
        });
    }
    
    public class Draw extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            
            if(sGame.isStarted() == true){
                if(sGame.isPause() == true){
                    drawPauseMenu(g2D);
                }
                else if(sGame.isPause() == false){
                    drawGame(g2D);
                }
            }
            else if(sGame.isFinishedBlink1() == true){
                drawBlink1(g2D);
            }
            else if(sGame.isFinishedBlink2() == true){
                drawBlink2(g2D);
            }
            else if(sGame.isFinished() == true){
                drawGameOver(g2D);
            }
            else if(sGame.isStartmenu() == true){
                drawStartMenu(g2D);
            }
        }
    }
    public void checkPauseMenuInputs(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            sGame.setPause(false);
        }
        else if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)){
            if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Back to main menu");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Back to main menu")){
                sGame.setMenuSelection("Restart game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Restart game")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)){
            if(sGame.getMenuSelection().equals("Restart game")){
                sGame.setMenuSelection("Back to main menu");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Back to main menu")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Restart game");
                GUI.Gframe.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            switch(sGame.getMenuSelection()){
                case "Restart game": sGame.restartGame();
                                     break;
                case "Exit game": System.exit(0);
                                  break;
                case "Back to main menu": sGame.setPause(false);
                                          sGame.setStarted(false);
                                          sGame.restartGame();
                                          sGame.setStartmenu(true);
                                          sGame.setMenuSelection("Start game");
                                          GUI.Gframe.repaint();
                                          break;
            }                     
            
        }
    }
    public void checkCurrentGameInputs(KeyEvent e){
        if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)){
            if(s.isSD() == false){
                s.setSD(false);
                s.setSL(false);
                s.setSR(false);
                s.setSU(true);
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)){
            if(s.isSU() == false){
                s.setSU(false);
                s.setSR(false);
                s.setSL(false);
                s.setSD(true);
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)){
            if(s.isSR() == false){
                s.setSU(false);
                s.setSD(false);
                s.setSR(false);
                s.setSL(true);
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)){
            if(s.isSL() == false){
                s.setSU(false);
                s.setSD(false);
                s.setSL(false);
                s.setSR(true);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            sGame.setPause(true);
        }
    }
    
    public void checkGameOverInputs(KeyEvent e){
        if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)){
            if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Back to start menu");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Back to start menu")){
                sGame.setMenuSelection("Restart game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Restart game")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)){
            if(sGame.getMenuSelection().equals("Restart game")){
                sGame.setMenuSelection("Back to start menu");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Back to start menu")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Restart game");
                GUI.Gframe.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            switch(sGame.getMenuSelection()){
                case "Restart game": sGame.restartGame();
                                     sGame.startGame(sGame);
                                     break;
                case "Back to start menu": sGame.setPause(false);
				           sGame.setStarted(false);
                                           sGame.restartGame();
                                           sGame.setStartmenu(true);
                                           sGame.setMenuSelection("Start game");
                                           GUI.Gframe.repaint();
                                           break;
                case "Exit game": System.exit(0);
                                  break;
            }
        }
    }
    public void checkStartMenuInputs(KeyEvent e){
        if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)){
            if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Wrap");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Wrap")){
                sGame.setMenuSelection("Level");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Level")){
                sGame.setMenuSelection("Difficulty");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Difficulty")){
                sGame.setMenuSelection("Start game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Start game")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)){
            if(sGame.getMenuSelection().equals("Start game")){
                sGame.setMenuSelection("Difficulty");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Difficulty")){
                sGame.setMenuSelection("Level");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Level")){
                sGame.setMenuSelection("Wrap");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Wrap")){
                sGame.setMenuSelection("Exit game");
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Exit game")){
                sGame.setMenuSelection("Start game");
                GUI.Gframe.repaint();
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)){
            if(sGame.getMenuSelection().equals("Difficulty") && sGame.getDifficultyLevel()!=2){
                sGame.setDifficultyLevel(sGame.getDifficultyLevel()+1);
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Level") && sGame.getLevelNo()!=2){
                     sGame.setLevelNo(sGame.getLevelNo()+1);
                     GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Wrap") && sGame.getWrapNo()!=1){
                     sGame.setWrapNo(1);
                     GUI.Gframe.repaint();
            }
        }
        else if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)){
            if(sGame.getMenuSelection().equals("Difficulty") && sGame.getDifficultyLevel()!=0){
                sGame.setDifficultyLevel(sGame.getDifficultyLevel()-1);
                GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Level") && sGame.getLevelNo()!=0){
                     sGame.setLevelNo(sGame.getLevelNo()-1);
                     GUI.Gframe.repaint();
            }
            else if(sGame.getMenuSelection().equals("Wrap") && sGame.getWrapNo()!=0){
                     sGame.setWrapNo(0);
                     GUI.Gframe.repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            switch(sGame.getMenuSelection()){
                case "Start game": sGame.startGame(sGame);
                                   break;
                case "Exit game": System.exit(0);
                                  break;
            }
        }
    }
    public void drawPauseMenu(Graphics2D g2D){
        gifLabel.setVisible(true);
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, 400, 400);
        g2D.setColor(Color.BLACK);
        g2D.setFont(SideFont);
        g2D.drawString("Press ESC to return to game", 410, 75);
        g2D.setColor(Color.WHITE);
        g2D.setFont(HeaderFont);
        g2D.drawString("Pause Menu", 100, 100);
        g2D.setColor(Color.WHITE);
        g2D.setFont(MenuFont);
        g2D.drawString("Restart game", 100, 200);
        g2D.drawString("Back to main menu", 100, 250);
        g2D.drawString("Exit game", 100, 300);
        
        switch (sGame.getMenuSelection()){
            case "Restart game": g2D.fillRect(70, 190, 10, 10);
                                 break;
            case "Back to main menu": g2D.fillRect(70, 240, 10, 10);
                                      break;
            case "Exit game": g2D.fillRect(70, 290, 10, 10);
                              break;
        }
        
    }
    public void drawGame(Graphics2D g2D){
        gifLabel.setVisible(false);
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, 400, 400);
        g2D.setColor(Color.BLACK);
        g2D.fillRect(395, 0, 10, 400);
        g2D.fillRect(0, 0, 10, 400);
        g2D.fillRect(0, 0, 400, 10);
        g2D.fillRect(0, 350, 400, 10);
        if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                    
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                    
                }
                else{
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                    
                }
        
        for(int i=1; i<=s.getList().size(); i++){
            g2D.fillRect(s.getList().get(i-1).x,s.getList().get(i-1).y, 10, 10);
        }
        
        if(food.isFoodPlaced() == true){
            g2D.setColor(Color.BLUE);
            g2D.fillRect(food.getFoodX(), food.getFoodY(), 10, 10);
        }
        if(bfood.isBFoodPlaced() == true){
                    g2D.setColor(Color.RED);
                    g2D.fillRect(bfood.getBFoodX(), bfood.getBFoodY(), 10, 10);
                    g2D.drawRect(bfood.getBFoodX()-6, bfood.getBFoodY()-6, 21, 21);
                }
        
        g2D.setColor(Color.BLACK);
        g2D.setFont(SideFont);
        if(sGame.getMinutes()<10 && sGame.getSeconds()<10){
            g2D.drawString("0"+sGame.getMinutes()+":0"+sGame.getSeconds(), 420, 50);
        }
        else if(sGame.getMinutes()<10 && sGame.getSeconds()>9){
            g2D.drawString("0"+sGame.getMinutes()+":"+sGame.getSeconds(), 420, 50);
        }
        else if(sGame.getMinutes()>9 && sGame.getSeconds()>9){
            g2D.drawString(sGame.getMinutes()+":"+sGame.getSeconds(), 420, 50);
        }
        else if(sGame.getMinutes()>9 && sGame.getSeconds()<10){
            g2D.drawString(sGame.getMinutes()+":0"+sGame.getSeconds(), 420, 50);
        }
            if(sGame.getWrapNo()==1){
                    g2D.setFont(HeaderFont);
                    g2D.setColor(Color.DARK_GRAY);
                    g2D.drawString("WRAP IS ON", 500, 50);
            }
            else if(sGame.getWrapNo()==0){
                    g2D.setFont(HeaderFont);
                    g2D.setColor(Color.DARK_GRAY);
                    g2D.drawString("WRAP IS OFF", 500, 50);
            }
        g2D.drawString("SCORE: "+sGame.getScore(), 420, 100);
        g2D.drawString("MAX SCORE LEFT: "+sGame.getMaxScoreLeft(), 420, 150);
        g2D.setColor(Color.GREEN);
        g2D.drawString("Use arrow keys to move the", 420 , 200);
        g2D.drawString("snake", 420, 225);
        g2D.setColor(Color.ORANGE);
        g2D.drawString("Press ESC to pause", 420, 260);
        g2D.setColor(Color.RED);
        g2D.drawString("Stay away from red !", 420, 300);
    }
    public void drawBlink1(Graphics2D g2D){
        gifLabel.setVisible(false);
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, 400, 400);
        if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==2){
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
        g2D.setColor(Color.BLACK);
        if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==2){
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
        for(int i=1; i<=s.getList().size(); i++){
            g2D.fillRect(s.getList().get(i-1).x, s.getList().get(i-1).y, 10, 10);
        }
        if(food.isFoodPlaced() == true){
            g2D.setColor(Color.BLUE);
            g2D.fillRect(food.getFoodX(), food.getFoodY(), 10, 10);
        }
        if(bfood.isBFoodPlaced() == true){
                    g2D.setColor(Color.RED);
                    g2D.fillRect(bfood.getBFoodX(), bfood.getBFoodY(), 10, 10);
                }
    }
    public void drawBlink2(Graphics2D g2D){
        gifLabel.setVisible(false);
        g2D.setColor(Color.BLACK);
        g2D.fillRect(395, 0, 10, 400);
        g2D.fillRect(0, 0, 10, 400);
        g2D.fillRect(0, 0, 400, 10);
        g2D.fillRect(0, 350, 400, 10);
        if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==2){
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, 400, 400);
            if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==2){
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
        g2D.setColor(Color.BLACK);
        g2D.fillRect(395, 0, 10, 400);
        g2D.fillRect(0, 0, 10, 400);
        g2D.fillRect(0, 0, 400, 10);
        g2D.fillRect(0, 350, 400, 10);
                 if(sGame.getLevelNo()==0){
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==1){
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
                else if(sGame.getLevelNo()==2){
                    g2D.fillRect(125, 305, 150, 6);
                    g2D.fillRect(125, 50, 150, 6);
                    g2D.fillRect(320, 75, 5, 200);
                    g2D.fillRect(79, 75, 5, 200);
                }
        
    }
    
    public void drawGameOver(Graphics2D g2D){
        gifLabel.setVisible(true);
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(0, 0, 400, 400);
        g2D.setFont(SideFont);
        g2D.drawString("Thank you for playing !!", 420, 75);
        g2D.setColor(Color.WHITE);
        g2D.setFont(HeaderFont);
        g2D.drawString("GAME OVER !", 100, 100);
        g2D.drawString("Your Score: "+sGame.getScore(), 100, 130);
        g2D.setFont(MenuFont);
        g2D.drawString("Restart game", 100, 200);
        g2D.drawString("Back to start menu", 100, 250);
        g2D.drawString("Exit game", 100, 300);
        
        switch(sGame.getMenuSelection()){
            case "Restart game": g2D.fillRect(70,190, 10, 10);
                                 break;
            case "Back to start menu": g2D.fillRect(70, 240, 10, 10);
                                       break;
            case "Exit game": g2D.fillRect(70, 290, 10, 10);
                              break;
            
        }
    }
    public void drawStartMenu(Graphics2D g2D) {
                gifLabel.setVisible(true);
		g2D.setColor(Color.DARK_GRAY);
		g2D.fillRect(0, 0, 400, 400);
                g2D.setFont(SideFont);
                g2D.drawString("Welcome to the Snake Game", 415, 60);
                g2D.drawString("Use arrow keys to navigate", 415, 88);
                g2D.drawString("Press ENTER to choose", 415, 115);
		g2D.setColor(Color.WHITE);
		g2D.setFont(HeaderFont);
		g2D.drawString("SNAKE GAME", 100, 60);
		g2D.setFont(MenuFont);
		g2D.drawString("Start game", 100, 110);
		g2D.drawString("Difficulty:", 100, 160);
                g2D.drawString("Level", 100, 210);
                g2D.drawString("Wrap", 100, 260);
		g2D.drawString("Exit game", 100, 310);
		g2D.drawString("EASY", 205, 160);
		g2D.drawString("NORMAL", 255, 160);
		g2D.drawString("HARD", 330, 160);
                g2D.drawString("1", 205, 210);
                g2D.drawString("2", 267, 210);
                g2D.drawString("3", 330, 210);
                g2D.drawString("OFF", 205, 260);
                g2D.drawString("ON", 255, 260);

         	switch (sGame.getMenuSelection()) {
                    case "Start game": g2D.fillRect(70, 100, 10, 10);
			               break; 
		    case "Difficulty": g2D.fillRect(70, 150, 10, 10);
                                       break; 
                    case "Level": g2D.fillRect(70, 200, 10, 10);
                                  break;
                    case "Wrap": g2D.fillRect(70, 250, 10, 10);
                                 break;
		    case "Exit game": g2D.fillRect(70, 300, 10, 10);
                                      break; 
		}	
		
		switch(sGame.getDifficultyLevel()) {
		    case 0: g2D.setColor(Color.CYAN);
                            g2D.drawString("EASY", 205, 180);
			    break;
		    case 1: g2D.setColor(Color.CYAN);
                            g2D.drawString("NORMAL", 255, 180);
			    break;
		    case 2: g2D.setColor(Color.CYAN);
                            g2D.drawString("HARD", 330, 180);
			    break;
                }
                switch(sGame.getLevelNo()){
                    case 0:
                           g2D.setColor(Color.MAGENTA);
                           g2D.drawString("1", 205, 230);
                           break;
                    case 1:
                           g2D.setColor(Color.MAGENTA);
                           g2D.drawString("2", 267, 230);
                           break;
                    case 2:
                           g2D.setColor(Color.MAGENTA);
                           g2D.drawString("3", 330, 230);
                           break;
                }
                switch(sGame.getWrapNo()){
                    case 0: g2D.setColor(Color.YELLOW);
                            g2D.drawString("OFF", 205, 280);
                            break;
                    case 1: g2D.setColor(Color.YELLOW);
                            g2D.drawString("ON", 255, 280);
                            break;
                }
    }
    public GameEngine getSnakeGame() {
	return sGame;
    }
    public void setGameEngine(GameEngine sGame) {
	this.sGame = sGame;
    }
    public Food getFood() {
	return food;
    }
    public BadFood getBFood(){
        return bfood;
    }
    public void setFood(Food food) {
	this.food = food;
    }
    public void setBFood(BadFood bfood){
        this.bfood = bfood;
    }
    public Snake getSnake() {
	return s;
    }
    public void setSnake(Snake snake) {
	this.s = snake;
    }
}
