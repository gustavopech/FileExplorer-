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
    	Dimension min = new Dimension(400,400);
    	left = buildFileExplorer();

    	left.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				  DefaultMutableTreeNode node = (DefaultMutableTreeNode) left.getLastSelectedPathComponent();
				  String path = "";
				  Object[] temp = null;
				  if(node != null) {
					  temp = node.getPath();
					  if (temp != null) {
						  for(int i = 1;i < temp.length; i++) {
						
							  path = path+ temp[i].toString()+"\\";
						  }
					  }
					  if (node.getChildCount() == 0) {
					  	node = DFS(node,path);
					  }
				 }
			}
    		
    	});
     	desktop = new JDesktopPane();
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
    	stats = new JLabel("[MEMORY STATS LABEL]");
  
        this.add(panel);
        this.setSize(420, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		panel.add(stats,BorderLayout.SOUTH);
    }
    public DefaultMutableTreeNode DFS(DefaultMutableTreeNode temp, String path) {
    	File currentFile = new File(path);
    	File[] nextFiles = currentFile.listFiles();
    	if (nextFiles != null) {
	    	for(int i=0; i <nextFiles.length; i++) {
				DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(nextFiles[i].getName());
				temp.add(tempNode);
	    		if(nextFiles[i].isDirectory()) {
	    			if (nextFiles[i] != null) {
		    			File[] tempFiles = nextFiles[i].listFiles();
		    			if (tempFiles != null){
			    			for(int z=0; z < tempFiles.length; z++) {
				    			DefaultMutableTreeNode tempNode2 = new DefaultMutableTreeNode(tempFiles[z].getName());    	
				    			tempNode.add(tempNode2);
			    			}
		    			}
	    			}
	    		}
	    	}
    	}
	
    	return temp;
    }
    public DefaultMutableTreeNode initialDFS() {
    	
    	File[] file= File.listRoots();
    	DefaultMutableTreeNode temp = new DefaultMutableTreeNode("PC");;
    	for(int i=0; i <file.length; i++) {
			DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(file[i].toString());
			temp.add(tempNode);
    		if(file[i].isDirectory()) {
    			File[] tempFiles = file[i].listFiles();
    			tempNode = DFS(tempNode, file[i].getAbsolutePath());
//    			for(int z=0; z < tempFiles.length; z++) {
//	    			DefaultMutableTreeNode tempNode2 = new DefaultMutableTreeNode(tempFiles[z].getName());
//	    			tempNode.add(tempNode2);
//	    			
//    			}
    			
    		}
    	}
    	return temp;
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
    	DefaultMutableTreeNode rootNode = initialDFS();
    	
    	Dimension min = new Dimension(400,400);
    	JTree current = new JTree(rootNode);
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
