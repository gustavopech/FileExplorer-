package fileexplorer;

import java.awt.Container;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

public class FileFrame extends JInternalFrame{
	Left left;
	Right right;
	JDesktopPane desktop;
	App main;
	/**
	 * 
	 */
	private static final long serialVersionUID = 784942332454346209L;
	public FileFrame(App A) {
		this.main = A;
		this.setTitle("C;");
		this.setSize(300,300);
		this.setVisible(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
     	desktop = new JDesktopPane();
     	left = new Left(this);
     	right = new Right(this);
    	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(left),new JScrollPane(right));
    	splitPane.setOneTouchExpandable(true);
    	splitPane.setDividerLocation(150);
    	desktop.add(splitPane);
        this.setBounds(20, 20, 250, 185);  
        Container c1 = this.getContentPane( ) ;  
        c1.add(splitPane);  
        desktop.add( this );  
        this.setVisible(true);  
		
	}
	public App getApp(){
		return this.main;
	}
	public Right getRight() {
		return this.right;
	}
	
}
