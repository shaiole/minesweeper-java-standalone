
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;



public class Minesweeper implements MouseListener{
       String mode;
       int row,col,no_bombs;
       
       int[][] revealed;
       /*
       0: not revealed
       1: revealed
       -2: flagged/marked
       */
       int[][] block;
        /*
       -1: the block has bomb
       0: clear or no bombs around it
       1-8: the number of bombs around it
       */
       
       boolean beforePlaying,play;
      
       JFrame f;
       JPanel p1, p2, p3,p4,p5;
       
       JLabel la;
       
       JMenuBar bar;
       JMenu menu;
       JMenuItem easy,normal,difficult,s1,reema, donut;
       JMenu aboutus;
       JMenuItem clarisse, nichole, francesca;
       
       Image img;
       ImageIcon ic;
       
       Image bmb;
       ImageIcon ib;
       
       Image rbmb;
       ImageIcon rib;
       
       Image hapy;
       ImageIcon ihapy;
               
      private JButton[][] tiles;
      private JButton reset;
    
       
    
    public Minesweeper(){
       mode="easy";
       row=9;
       col=9;
       no_bombs=10;
       tiles=new JButton[row][col]; 
       revealed=new int[row][col];   
       block=new int[row][col];
     
        f= new JFrame("Minesweeper by Team bear");
        la = new JLabel();
        
        
        p1= new JPanel();
        p2= new JPanel();
        p3= new JPanel();
        p4= new JPanel();
        p5= new JPanel();
        
        reset=new JButton();
        f.setSize(col*45, row*45);
        f.setMinimumSize(new Dimension(col*45, row*45));
        
        bar=new JMenuBar();
        menu = new JMenu("menu");
        easy=new JMenuItem("easy");
        normal=new JMenuItem("normal");
        difficult=new JMenuItem("difficult");
        s1=new JMenuItem("Special1");
        reema=new JMenuItem("Special2");
        s1=new JMenuItem("Smiley");
        reema=new JMenuItem("UST");
        donut=new JMenuItem("Jco");
     
        aboutus = new JMenu("about us");
        clarisse = new JMenuItem("Clarisse");
        nichole = new JMenuItem("Nichole");
        francesca = new JMenuItem("Francesca");
        
        beforePlaying=true;
        play=true;
        
        for(int i=0; i< row; i++){
                for(int j=0;j<col;j++){
                    revealed[i][j]=0;
                }
       }
 
    }
    
    
    private void launchFrame()
    {   
        tiles=new JButton[row][col]; 
        revealed=new int[row][col];   
        block=new int[row][col];
        
        f.add(p1,BorderLayout.NORTH);
        
        p1.setLayout(new GridLayout(1,3));
        p2.setLayout(new GridLayout(row,col,2,2));
      
        f.setJMenuBar(bar);
        bar.add(menu);
        menu.add(easy);
        menu.add(normal);
        menu.add(difficult);
        menu.add(s1);
	menu.add(reema);
        menu.add(donut);
       
        bar.add(aboutus);
        aboutus.add(clarisse);
        aboutus.add(nichole);
        aboutus.add(francesca);
        
        p1.add(p3);
        p1.add(p4);
        p1.add(p5);
        p4.add(reset);
        
        
        
        f.add(p2,BorderLayout.CENTER);
        reset.setBackground(Color.white);
        p2.setBackground(Color.BLACK);
        
        try{
                             
            hapy = ImageIO.read(getClass().getResource("happybear.png"));
            Image scaledImage = hapy.getScaledInstance(40,30, Image.SCALE_SMOOTH);
            ihapy =new ImageIcon(scaledImage);
            reset.setIcon(ihapy);
            f.setIconImage(hapy);
            }
                                catch(Exception e){}
        
        reset.addMouseListener(this);
        easy.addMouseListener(this);
        normal.addMouseListener(this);
        difficult.addMouseListener(this);
        s1.addMouseListener(this);
	reema.addMouseListener(this);
        donut.addMouseListener(this);
        clarisse.addMouseListener(this);
        nichole.addMouseListener(this);
        francesca.addMouseListener(this);
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
            {
            tiles[i][j]=new JButton();    
            p2.add(tiles[i][j]);
            tiles[i][j].addMouseListener(this);
            tiles[i][j].setBackground(new Color(252,213,92));
            }
        }
        
        
    
        f.setLocationRelativeTo(null);
       	f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p2.revalidate();
        p2.repaint();
   
    }
    private boolean outBounds(int x,int y){
        if(mode.equals("s1")) 
            return y<0||x<0||y>col-1||x>row-1 || ((x>=2&&x<=4)&&(y>=2&&y<=4)) || ((x>=2&&x<=4)&&(y>=10&&y<=12)) || (x==10&&y==2)|| (x==10&&y==12)|| (x==11&&(y>=3&&y<=11));
        else if(mode.equals("reema"))
            return y<0||x<0||y>col-1||x>row-1|| ((y==1)&&(x>=2&&x<=10)) || ((y==2)&&(x==11)) || ((y==3)&&(x==12)) || ((y==4)&&(x==12)) || ((y==5)&&(x==11)) || ((y==6)&&(x>=2&&x<=10)) || ((y>=8&&y<=12)&&(x==12)) || ((x>=6&&x<=12)&&(y==12)) || ((y>=8&&y<=12)&&(x==6)) || ((y==8)&&(x>=2&&x<=6)) || ((y>=8&&y<=12)&&(x==2)) || ((y>=14&&y<=18)&&(x==2)) || ((y==16)&&(x>=2&&x<=12));
      
        else if(mode.equals("donut"))
            return y<0||x<0||y>col-1||x>row-1 || ((x==0)&&(y>=6&&y<=10)) || ((x==1)&&(y==5)) || ((x==1)&&(y==11)) || ((x==2)&&(y==4)) || ((x==2)&&(y==12)) || ((x>=3&&x<=4)&&(y==3)) || ((x>=3&&x<=4)&&(y==13)) || ((x>=5&&x<=9)&&(y==2)) || ((x>=5&&x<=9)&&(y==14)) || ((x>=10&&x<=11)&&(y==3)) || ((x>=10&&x<=11)&&(y==13)) || ((x>=11&&x<=12)&&(y==4)) || ((x>=11&&x<=12)&&(y==12)) || ((x==13)&&(y==5)) || ((x==13)&&(y==11)) || ((x==14)&&(y>=6&&y<=10)) || ((x==7)&&(y==6)) || ((x>=6&&x<=8)&&(y==7)) || ((x>=5&&x<=6)&&(y==8)) || ((x>=8&&x<=9)&&(y==8)) || ((x>=6&&x<=8)&&(y==9)) || ((x==7)&&(y==10)) || ((x==7)&&(y==8));
        else  
            return y<0||x<0||y>col-1||x>row-1;
    }
    
    private void easy(){
        
    
    mode="easy";    
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=9;
    col=9;
    no_bombs=10;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45, row*45);
   
            for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
            {
            tiles[i][j]=new JButton();    
            p2.add(tiles[i][j]);
            tiles[i][j].addMouseListener(this);
            tiles[i][j].setBackground(new Color(252,213,92));
           
            }
        }
    f.setLocationRelativeTo(null);
    
    }
     private void normal(){
    
    mode="normal";
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=15;
    col=15;
    no_bombs=35;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45, row*45);
   
       for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
            {
            tiles[i][j]=new JButton();    
            p2.add(tiles[i][j]);
            tiles[i][j].addMouseListener(this);
            tiles[i][j].setBackground(new Color(53, 154, 255));
            }
        }
    
    f.setLocationRelativeTo(null);
    }
    private void difficult(){
    mode="difficult";
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=15;
    col=30;
    no_bombs=99;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45,row*45);
   
       for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
            {
            tiles[i][j]=new JButton();    
            p2.add(tiles[i][j]);
            tiles[i][j].addMouseListener(this);
            tiles[i][j].setBackground(new Color(91, 183, 1));
            }
        }
    
    
    f.setLocationRelativeTo(null);
    }
    
    public void s1(){
    mode="s1";
    
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=15;
    col=15;
    no_bombs=25;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45, row*45);
    int i ,j;
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {
                tiles[i][j]=new JButton();    
                p2.add(tiles[i][j]);
                tiles[i][j].addMouseListener(this);
                tiles[i][j].setBackground(new Color(239, 228, 176));
           
            }
        }
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {   if(outBounds(i,j))
                tiles[i][j].setVisible(false);
            }
       }
    
    f.setLocationRelativeTo(null);
    }
    
     public void reema() {
        mode="reema";
    
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=15;
    col=20;
    no_bombs=28;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45, row*45);
    int i ,j;
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {
                 tiles[i][j]=new JButton();    
                p2.add(tiles[i][j]);
                tiles[i][j].addMouseListener(this);
                tiles[i][j].setBackground(new Color(240, 225, 56));
            }
        }
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {   if(outBounds(i,j))
                tiles[i][j].setVisible(false);
            }
       }
    
    f.setLocationRelativeTo(null);
	}
   public void donut() {
        mode="donut";
    
    p2.removeAll();
    p2.revalidate();
    p2.repaint();
   
    renew();
    
    row=15;
    col=17;
    no_bombs=30;
    
    tiles=new JButton[row][col]; 
    revealed=new int[row][col];   
    block=new int[row][col];
    
    p2.setLayout(new GridLayout(row,col,2,2));
    f.setMinimumSize(new Dimension(col*45, row*45));
    f.setSize(col*45, row*45);
    int i ,j;
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {
                 tiles[i][j]=new JButton();    
                p2.add(tiles[i][j]);
                tiles[i][j].addMouseListener(this);
                tiles[i][j].setBackground(new Color(250, 75, 171));
            }
        }
       for(i=0;i<row;i++){
            for(j=0;j<col;j++)
            {   if(outBounds(i,j))
                tiles[i][j].setVisible(false);
            }
       }
    
       f.setLocationRelativeTo(null);
    }
   
    public void clarisse() {
        JOptionPane.showMessageDialog(f, "Reema Clarisse Escbuil\n" + "17 yrs old\n" + "FB: reemaclarisse\n" + "Twitter: @reemaclarisse", "About Clarisse", JOptionPane.INFORMATION_MESSAGE);
    }
    public void nichole() {
        JOptionPane.showMessageDialog(f, "Shaira Nichole Marquez\n" + "16 yrs old\n" + "FB: shairanichole@yahoo.com\n", "About Nichole", JOptionPane.INFORMATION_MESSAGE);
    }
    public void francesca() {
        JOptionPane.showMessageDialog(f, "Francesca Estrada\n" + "17 yrs old\n" + "FB: chinestradaa\n" + "Twitter: @chin_estrada", "About Francesca", JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void mouseReleased(MouseEvent a) 
        {
        int i,j;
        if(SwingUtilities.isRightMouseButton(a)){
          
            
            if(play){
            for(i=0; i< row; i++){  
                 for(j=0;j<col;j++){
                     if(tiles[i][j]==a.getSource())
                         if(revealed[i][j]!=1&&revealed[i][j]!=2){
                             
                         try{
                             
                          img = ImageIO.read(getClass().getResource("paw.png"));
                          Image scaledImage = img.getScaledInstance(40,30, Image.SCALE_SMOOTH);
                          ic =new ImageIcon(scaledImage);
                          tiles[i][j].setIcon(ic);
                            }
                          catch(Exception e){}


                         revealed[i][j]=2;
                         tiles[i][j].repaint();
                         tiles[i][j].revalidate();
                         }
                         else if(revealed[i][j]==2)
                         {
                             tiles[i][j].setIcon(null);
                             tiles[i][j].setText(null);
                             revealed[i][j]=0;
                         } 
                     tiles[i][j].repaint();
                         tiles[i][j].revalidate();
                }
            }
            }
        }
        else if(SwingUtilities.isLeftMouseButton(a))
        {   
            if(a.getSource()==easy)
            {
                easy();
                
            }
            if(a.getSource()==normal)
            {
                normal();
            }
            if(a.getSource()==difficult)
            {
                difficult();
            }
             if(a.getSource()==s1)
            {
                s1();
            }
             //step 7 gawan nyo din ng event; if.... tapos ung method na ginawa nyo. dito ilagay.
             if(a.getSource()==reema)
            {
                 reema();
            }
             if(a.getSource()==donut)
             {
                 donut();
             }
            if(reset==a.getSource())
            {
                renew();
                
            }
            if(a.getSource()==clarisse)
            {
                clarisse();
            }
            if(a.getSource()==nichole)
            {
                nichole();
            }
            if(a.getSource()==francesca)
            {
                francesca();
            }
            for(i=0; i< row; i++){
                for(j=0;j<col;j++){
                    if(tiles[i][j]==a.getSource())
                    {           
                        if(play&&revealed[i][j]!=2){
                            
                           
                            if(beforePlaying){
                                putBombs(i,j);
                                beforePlaying=false;
                            }
                            if(block[i][j]!=-1){
                                putValue(i,j);
                                show(i,j);}
                            else{ 
                            try {    
                                rbmb = ImageIO.read(getClass().getResource("redcactus.png"));
                                Image scaledImage = rbmb.getScaledInstance(40,30, Image.SCALE_SMOOTH);
                                rib =new ImageIcon(scaledImage);
                                tiles[i][j].setIcon(rib);
                                tiles[i][j].revalidate();
                                tiles[i][j].repaint();
                                
                            } catch (Exception e){}
                            lose(i,j);
                            }

                        }
               
                     break;   
                    }
                } 
                
            }
           if(play){ 
           if(win())
               
                {  try{
            hapy = ImageIO.read(getClass().getResource("happybear.png"));
            Image scaledImage = hapy.getScaledInstance(100,100, Image.SCALE_SMOOTH);
            ihapy =new ImageIcon(scaledImage);
        } catch(Exception e){}
                    JOptionPane.showMessageDialog(f, "You win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE, ihapy);
                    
                    play=false;
                     for(i=0;i<row;i++){
                           for (j=0;j<col;j++){
                               tiles[i][j].setSelected(true);
                             if(block[i][j]==-1){
                              tiles[i][j].setBackground(Color.red);
                              tiles[i][j].revalidate();
                              tiles[i][j].repaint();
                              }

                           }
                    }

                }
           }
        }
    }    
    private void lose(int x,int y){
    play = false;
    
        for(int i=0;i<row;i++){
           for (int j=0;j<col;j++){
               if(block[i][j]==-1&&!win()&&!(x==i&&y==j)){// if isbomb, and did not win, and not the one u clied

                   try{   
                          bmb = ImageIO.read(getClass().getResource("cactus.png"));
                          Image scaledImage = bmb.getScaledInstance(40,30, Image.SCALE_SMOOTH);
                         
                          tiles[i][j].setIcon(new ImageIcon(scaledImage) );
                      
                        }
                          catch(Exception e){}

                   
                   tiles[i][j].setSelected(true);
                   tiles[i][j].setBackground(Color.white);
                   tiles[i][j].setForeground(Color.red);
                   
                   tiles[i][j].revalidate();
                   tiles[i][j].repaint();
                   
               }
                                   
             }

        }
         try{
            img = ImageIO.read(getClass().getResource("xbear.png"));
            Image scaledImage = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
            ic =new ImageIcon(scaledImage);
        } catch(Exception e){}
        JOptionPane.showMessageDialog(f, 
                "You lose!", 
                "Sorry", 
                JOptionPane.INFORMATION_MESSAGE, 
                ic);
    }
    
    
    private boolean win(){
        boolean win = true;
        int notBombs=0;
        int no_tiles=0;
        for(int i=0;i<row;i++){
           for (int j=0;j<col;j++){
                    if(revealed[i][j]==1)
                    notBombs++;
                    if(!outBounds(i,j)){
                        no_tiles++;
                    }
               
           }
        }
        if(notBombs!=no_tiles-no_bombs){
            win = false;
        }
   return win;    
   }
    
 
    private void putBombs(int x,int y){
       Random randomizer = new Random();
        for(int count=0;count < no_bombs;count++){
            int i,j;
            do{
            i=randomizer.nextInt(row);
            j=randomizer.nextInt(col);
            }
            while(block[i][j]==-1||(i==x&&j==y)&&!outBounds(i,j));
            
            block[i][j]=-1;
          
            
       }
       
    }
    
    
    private void putValue(int p,int q){
        int bombs;
     for(int i=0;i<row;i++){
            for(int j=0;j<col;j++)
            {   if(block[i][j]!=-1){
                bombs=0;
                for(int x= i-1 ; x<=i+1 ;x++){     //checks the surronding and counts the no of bombs around
                    for(int y= j-1 ; y<=j+1 ; y++){
                       if(!outBounds(x,y)&&block[x][y]==-1)//if it's not outofbounds AND there is a bomb besides, 
                        bombs++;
                }
                }
                block[i][j]=bombs;
                }
            }
    }
    }
    
    
   private void renew(){ 
       p2.revalidate();
        p2.repaint();
       block=new int[row][col]; //creates new arr
       play=true; //you can play again
       la.setText(null); //remove the you win sign
       beforePlaying=true;
       
       for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                tiles[i][j].setSelected(false);
                tiles[i][j].setText(null);
                if(mode.equals("easy"))
                tiles[i][j].setBackground(new Color(252,213,92));
                else if(mode.equals("normal"))
                    tiles[i][j].setBackground(new Color(53, 154, 255));
                else if(mode.equals("difficult"))
                    tiles[i][j].setBackground(new Color(91, 183,1));
                else if(mode.equals("reema"))
                     tiles[i][j].setBackground(new Color(240, 225, 56));
                else if(mode.equals("donut"))
                     tiles[i][j].setBackground(new Color(250, 75, 171));
                else if(mode.equals("s1"))
                     tiles[i][j].setBackground(new Color(239, 228, 176));

                
               tiles[i][j].setForeground(null);
               tiles[i][j].setIcon(null);
            }
       }    
       
        for(int i=0; i< row; i++){
            for(int j=0;j<col;j++){
               if(!outBounds(i,j)) 
               revealed[i][j]=0;
                }
            }
   }
   
   
   private void cascade(int x,int y){
  
      if(!outBounds(x,y)&&block[x][y]!=-1&&revealed[x][y]==0){ //if not out of bounds and is not yet open(closed)
          
                if(block[x][y]>0){
		coloredNumbers(x,y);
                tiles[x][y].setBackground(new Color(222,222,222));
                tiles[x][y].setText(""+block[x][y]); }
                else{
                tiles[x][y].setText("");
                tiles[x][y].setBackground(new Color(222,222,222));}
                
            tiles[x][y].setSelected(true);    
            
            
            revealed[x][y]=1;//it is open
            
            if(block[x][y]==0){ //if block is clear
                for(int r=-1;r<=1;r++){ //this loops checks all the surronding bombs
                    for(int c=-1;c<=1;c++){
                        
                        cascade(r + x, c + y); //cascade again
                    }
                }
            }
       }
    }
   
   
    private void show(int i, int j){
                
                if(block[i][j]==0){ 
                    cascade(i,j); 
                    
                }
                if(block[i][j]>=1)
                {   tiles[i][j].setBackground(new Color(222,222,222));
                    coloredNumbers(i,j);
                    tiles[i][j].setText(""+block[i][j]);
                    
                    tiles[i][j].setSelected(true);
                    revealed[i][j]=1;
                }
                p2.repaint();
                p2.revalidate();
                
  
    }
    
    public void coloredNumbers(int i,int j){
            switch(block[i][j]){
                case 1:tiles[i][j].setForeground(Color.BLUE);break;
                case 2:tiles[i][j].setForeground(new Color(186, 167, 31)) ;break;
                case 3:tiles[i][j].setForeground(Color.red) ;break;
                case 4:tiles[i][j].setForeground(Color.green) ;break;
                case 5:tiles[i][j].setForeground(Color.PINK) ;break;
                case 6:tiles[i][j].setForeground(Color.orange) ;break;
                case 7:tiles[i][j].setForeground(Color.black) ;break;
                case 8:tiles[i][j].setForeground(Color.MAGENTA) ;break;    
                }
      }

    public static void main(String args[])
    {
		Minesweeper ms = new Minesweeper();
		ms.launchFrame();
    }
    
    //Below are methods that are never used. Only needed for the MouseEvent Implimentation
    @Override
    public void mouseExited(MouseEvent a) 
	{ 
        }   
    @Override
    public void mouseEntered(MouseEvent a) 
	{ 
        }   
    @Override
    public void mouseClicked(MouseEvent a) 
	{ 
        }   
    @Override
    public void mousePressed(MouseEvent a) 
	{ 
        }   
}