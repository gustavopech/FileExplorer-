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
    JLabel stats = new JLabel("");
    String currentDrive;
    String freeSpace;
    String usedSpace;
    String totalSpace;
    JComboBox<FileNode> driveLocation;
    JButton simple;
    JButton details;

    public App() {
    	
    	panel = new JPanel();
    	this.setTitle("CECS 277 File Manager"); 
    	panel.setLayout(new BorderLayout());
    	////MENU ///
    	menubar = new JMenuBar();
    	buildMenu();
    	////////////
    	Dimension min = new Dimension(900,900);

    	
     	desktop = new JDesktopPane();


      
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
	public JComboBox<FileNode> getComboBox(){
		return this.driveLocation;
	}
    public void go() {
    	for (int  k = 0;k < this.driveLocation.getItemCount(); k++) {
     		FileFrame f = new FileFrame(this, this.driveLocation.getItemAt(k));
     		desktop.add(f);
    	}
     	
    	FileNode drive= (FileNode) this.driveLocation.getSelectedItem();
    	update(drive);
   
  
        this.add(panel);
        this.setSize(700, 700);
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
    	FileNode[] exampleList2 = new FileNode[exampleList.length];
    	for (int z = 0; z < exampleList.length; z++){
    		File temp = new File(exampleList[z]);
    		exampleList2[z] = new FileNode(temp);
    	}
    	driveLocation = new JComboBox<FileNode>(exampleList2);
    	driveLocation.addActionListener(new DriveListener());
    	details = new JButton("Details");
    	simple = new JButton("Simple");
    	simple.addActionListener(new simpleAction());
    	details.addActionListener(new detailAction());
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
    public class DriveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
	        @SuppressWarnings("unchecked")
			JComboBox<FileNode> cb = (JComboBox<FileNode>)e.getSource();
	        FileNode drive= (FileNode) cb.getSelectedItem();
	        update(drive);
			
		}
    	
    }
    public class detailAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileFrame f= (FileFrame) desktop.getSelectedFrame();
			f.detail();
		}
    	
    } 
    public class simpleAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileFrame f= (FileFrame) desktop.getSelectedFrame();
			f.simple();
		}
    	
    } 
    private class AboutActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AboutDig dig = new AboutDig();
			dig.setVisible(true);
			
		}
    	
    }
    


	public void update(FileNode drive) {
		this.freeSpace = " " +drive.getFile().getFreeSpace()/1000000000+"GB ";
		this.totalSpace = " " +drive.getFile().getTotalSpace()/1000000000+"GB ";
		this.usedSpace = " " + (drive.getFile().getTotalSpace()-drive.getFile().getUsableSpace())/1000000000+"GB ";
		this.currentDrive = drive.getFile().toString();
		String result = "Current Drive"+this.currentDrive+
		" Free Space"+this.freeSpace+"Used Space"+this.usedSpace+ "Total Space"+this.totalSpace;
		this.stats.setText(result);
		this.stats.updateUI();
	}
	
    }
