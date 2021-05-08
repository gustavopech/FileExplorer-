package fileexplorer;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import java.lang.Runtime;


class App extends JFrame {
    // Ignore the next line, VSCODE throws a fit without it
    private static final long serialVersionUID = 3725860681747915637L;
    JPanel panel;
    JPanel toolbar;
    JDesktopPane desktop;
    JMenuBar menubar;
    JTree left;
    JTree right;
    JLabel stats;

    public App() {
    	panel = new JPanel();
    	this.setTitle("CECS 277 File Manager"); 
    	panel.setLayout(new BorderLayout());
    	////MENU ///
    	menubar = new JMenuBar();
    	buildMenu();
    	////////////
    	Dimension min = new Dimension(600,600);

    	
     	desktop = new JDesktopPane();
     	FileFrame f = new FileFrame(this);
     	desktop.add(f);

      
    	JPanel topPanel = buildTopWidgets();
    	topPanel.add(menubar,BorderLayout.NORTH);
        panel.add(topPanel,BorderLayout.NORTH);
        panel.add(desktop,BorderLayout.CENTER);
    }
    public JTree updateRightJTree(DefaultMutableTreeNode newTree, String path) {
    	if (newTree != null) {
  
    	}
    	return null;
    }

    public void go() {
    	Runtime rgr = Runtime.getRuntime();
	long usedMB = ((rgr.totalMemory() - rgr.freeMemory())/1000);
	String umb = usedMB + "";
	JLabel stats = new JLabel("Total: " + ((rgr.totalMemory())/1000) + "GB" + "      Used Memory: " + umb + " GB" );
  
        this.add(panel);
        this.setSize(600, 600);
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
