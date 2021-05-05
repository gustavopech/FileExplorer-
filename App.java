package fileexplorer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.Runtime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

class App extends JFrame {
    // Ignore the next line, VSCODE throws a fit without it
    private static final long serialVersionUID = 3725860681747915637L;
    //
    JPanel panel;
    JMenuBar menubar;
    JTree left;
    JTree right;
  

    public App() {
	Runtime rgr = Runtime.getRuntime();
        long usedMB = (rgr.totalMemory() - rgr.freeMemory());
        String umb = usedMB + "";
        JLabel stats = new JLabel("Used Memory: " + umb + " MB" );
    	this.setTitle("CECS 277 File Manager"); 
    	panel.setLayout(new BorderLayout());
    	
    	////MENU ///
    	String[] exampleList = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };
    	menubar = new JMenuBar();
    	buildMenu();
    	
    	//////
    	Dimension min = new Dimension(400,400);
    	left = buildFileExplorer();
    	right = buildFileExplorer();

     	JDesktopPane desktop = new JDesktopPane();
     	JInternalFrame jframe = new JInternalFrame("Internal Frame ",  true, true, true, true);  
     	  
  
    	
    	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(left),new JScrollPane(right));
    	splitPane.setOneTouchExpandable(true);
    	splitPane.setDividerLocation(150);
    	desktop.add(splitPane);
        jframe.setBounds(20, 20, 250, 185);  
        Container c1 = jframe.getContentPane( ) ;  
        c1.add(splitPane);  
        desktop.add( jframe );  
        jframe.setVisible(true);       
    	JPanel topPanel = buildTopWidgets();
    	topPanel.add(menubar,BorderLayout.NORTH);
        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(desktop,BorderLayout.CENTER);
    }

    public void go() {
    	JLabel stats = new JLabel("[MEMORY STATS LABEL]");
  
        this.add(panel);
        this.setSize(420, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		panel.add(stats,BorderLayout.SOUTH);
    }
    private String[] getPaths() {
    	File[] temp = File.listRoots();
    	String listOfPaths[] = new String[temp.length];
    	for (int z = 0; z < temp.length; z++){
    		listOfPaths[z] = temp[z].toString();
    	}
    	return listOfPaths;
    }
    private JPanel buildTopWidgets() {
    	String[] exampleList = getPaths();
    	JComboBox driveLocation = new JComboBox(exampleList);

    	JButton details = new JButton("Details");
    	JButton simple = new JButton("Simple");
    	JLabel test = new JLabel("WELP");
    	JLabel tests = new JLabel("bILLY");
    	JPanel top = new JPanel();
    	top.setLayout(new BorderLayout());
    	top.add(driveLocation,BorderLayout.WEST);
    	top.add(details,BorderLayout.CENTER);
    	top.add(simple,BorderLayout.EAST);
    	return top;
    }

    private void buildMenu() {
		JMenu file, tree, window, help;
		file = new JMenu("File");
		tree = new JMenu("Tree");
		window = new JMenu("Window");
		help = new JMenu("Help");

		JMenuItem about = new JMenuItem("about");
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		help.add(about);
		exit.addActionListener(new ExitActionListener());
		about.addActionListener(new AboutActionListener());
		menubar.add(file);
		menubar.add(tree);
		menubar.add(help);
		menubar.add(window);
		

	}
//    private JTree buildFileTree() {
//
//    	return fileList;
//    }
    private JTree  buildFileExplorer() {
    	Dimension min = new Dimension(400,400);
    	JTree current = new JTree();
    	current.setMinimumSize(min);
    	return current;
    }
    private class ExitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
    	
    }
    private class AboutActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AboutDig dig = new AboutDig();
			dig.setVisible(true);
			
		}
    	
    }
	private class okActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Okay")) {
                System.out.println("You pressed Okay!");
            } else {
                System.out.println("You pressed Cancel!");
            }
        }
    }
}
