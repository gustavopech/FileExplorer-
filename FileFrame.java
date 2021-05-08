package fileexplorer;

import java.awt.Container;

import javax.swing.JComboBox;
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
	FileNode currentDrive;
	/**
	 * 
	 */
	private static final long serialVersionUID = 784942332454346209L;
	public FileFrame(App A, FileNode n) {
		this.main = A;
		currentDrive = n;
		 JComboBox cb = A.getComboBox();
		 FileNode f = (FileNode) cb.getSelectedItem();
		this.setTitle(n.getFile().toString());
		this.setSize(700,700);
		this.setVisible(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
     	desktop = new JDesktopPane();
     	desktop.setSize(400,400);
     	left = new Left(this);
     	right = new Right(this);
    	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(left),new JScrollPane(right));
    	splitPane.setOneTouchExpandable(true);
    	splitPane.setDividerLocation(90);
    	desktop.add(splitPane);
        this.setBounds(20, 20, 500, 500);  
        Container c1 = this.getContentPane( ) ;  
        c1.add(splitPane);  
        desktop.add( this );  
        this.setVisible(true);  
		
	}
	public FileNode getFileNode() {
		return this.currentDrive;
	}
	public App getApp(){
		return this.main;
	}
	public Right getRight() {
		return this.right;
	}
	public void simple() {
		this.right.simple();
		// TODO Auto-generated method stub
		
	}
	public void detail() {
		this.right.detail();
		// TODO Auto-generated method stub
		
	}
	
}
