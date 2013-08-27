 import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    
//Max Sorto
//900691011
//CSCI 1302B
//Nov. 26, 2012

public class SimplePaint {
	//Variables
	private JFrame frame;
	private JPanel btnpanel;
	private JButton redbtn;
	private JButton greenbtn;
	private JButton bluebtn;
	private JButton erasebtn;
	private JButton clearbtn;
	private PaintPanel canvas;
	
	 //Constructor
    public SimplePaint(){

        frame = new JFrame("Paint Program");
        frame.setSize(500,500);
        frame.setLocation(500,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redbtn = new JButton("RED");
        greenbtn = new JButton("GREEN");
        bluebtn = new JButton("BLUE");
        clearbtn = new JButton("CLEAR");
        erasebtn = new JButton("ERASE");

        btnpanel = new JPanel();
        canvas = new PaintPanel();

        btnpanel.setLayout(new GridLayout(5,1));

        btnpanel.add(redbtn);
        btnpanel.add(greenbtn);
        btnpanel.add(bluebtn);
        btnpanel.add(erasebtn);
        btnpanel.add(clearbtn);
        frame.add(BorderLayout.CENTER, canvas);
        frame.add(BorderLayout.SOUTH, btnpanel);

        final PaintPanel paint = new PaintPanel();

        redbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                paint.red();
            }   
            });
        bluebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                paint.blue();
            }   
            });
        greenbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                paint.green();
            }   
            });
        clearbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                paint.clear1();
            }   
            });
        erasebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                paint.green();
            }   
            });
    }
    //Makes the frame visible and working
    public void launch(){
        frame.setVisible(true);
    }

    //Main Method
    public static void main(String[] args){
        SimplePaint GUI = new SimplePaint();
        GUI.launch();
    }
    
	
	//class to draw the canvas to paint in
	public class PaintPanel extends JComponent{
		//Variables
		private Graphics2D g;
		private Cursor paintcursor;
		private Cursor selectcursor;
		private Image drawing;
		private int x1;
		private int y1;
		private int x2;
		private int y2;
		
		//Constructor + Mouse Listener
		public PaintPanel(){
			
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					x1 = e.getX();
					y1 = e.getY();
				}
			});
			addMouseMotionListener(new MouseMotionAdapter(){
				public void MouseDragged(MouseEvent e){
					x2 = e.getX();
					y2 = e.getY();
					
					if(g != null)
						g.drawLine(x1, y1, x2, y2);
					repaint();
					x1 = x2;
					y1 = y2;
					
				}
			});
		}
		
		//PaintPanel method that is responsible for the cursor and doing the drawing
		public void paintComponent(Graphics gr){
			super.paintComponent(gr);
			gr.setColor(Color.WHITE);
			gr.fillRect(0,0, this.getWidth(), this.getHeight());
			paintcursor= new Cursor(Cursor.CROSSHAIR_CURSOR);
			selectcursor= new Cursor(Cursor.HAND_CURSOR);
			canvas.setCursor(paintcursor);
			btnpanel.setCursor(selectcursor);
			
			if(drawing == null){
				drawing = createImage(this.getWidth(), this.getHeight());
				g = (Graphics2D)drawing.getGraphics();
				clear1();
			}
			gr.drawImage(drawing, 0, 0, null);
		}
			public void clear1(){
				g.setPaint(Color.WHITE);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				g.setPaint(Color.blue);
				repaint();
			}
			public void red(){ 
                g.drawLine(x1, y1, x2, y2);
                g.setPaint(Color.red);

                x1 = x2;
                y1 = y2;
                repaint();
            }
            public void blue(){    
                g.drawLine(x1, y1, x2, y2);
                g.setPaint(Color.blue);

                x1 = x2;
                y1 = y2;
                repaint();
            }
            public void green(){   
               g.drawLine(x1, y1, x2, y2);
               g.setPaint(Color.green);

                x1 = x2;
                y1 = y2;
                repaint();
            }
           }
}