package fileexplorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
    	panel = new JPanel();
    	this.setTitle("CECS 277 File Manager"); 
    	panel.setLayout(new BorderLayout());
    	
    	////MENU ///
    	menubar = new JMenuBar();
    	buildMenu();
    	
    	//////
    	Dimension min = new Dimension(400,400);
    	left = buildFileExplorer();
    	right = buildFileExplorer();
    	
    	
    	JLabel test = new JLabel();
    	JLabel tests = new JLabel();
    	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,right);
    	splitPane.setOneTouchExpandable(true);
    	splitPane.setDividerLocation(150);
        panel.add(splitPane,BorderLayout.CENTER);
        

    }

    public void go() {
    	JLabel stats = new JLabel("[MEMORY STATS LABEL]");
        this.add(panel);
        this.setSize(420, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		panel.add(stats,BorderLayout.SOUTH);
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
		panel.add(menubar,BorderLayout.NORTH);

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