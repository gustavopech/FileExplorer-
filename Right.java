package fileexplorer;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JScrollPane;

public class Right extends JPanel{
	JTree j;
	DefaultMutableTreeNode temp;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2234143991718072810L;

	public Right(FileFrame f) {
		
		this.setLayout(new BorderLayout());
		j = new JTree(temp);
		 DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) j.getCellRenderer();
	        renderer.setLeafIcon(renderer.getClosedIcon());
		this.setVisible(true);
	}

	public void updateTree(DefaultMutableTreeNode tree) {
		this.removeAll();
		temp = new DefaultMutableTreeNode(tree.toString());
		//DefaultMutableTreeNode root = new DefaultMutableTreeNode(temp.getParent().toString());
		Object[] files = tree.getPath();
		String path ="";
		for(int i = 1;i < files.length; i++) {
			System.out.println(files[i]);
			path = path+ files[i].toString()+"\\";
	}
		if (files != null) {
		
	    	File currentFile = new File(path);
	    	if (currentFile.isDirectory()) {
	    		File[] nextFiles = currentFile.listFiles();
		    	if (nextFiles != null) {
			    	for(int i=0; i <nextFiles.length; i++) {
						DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(nextFiles[i].getName());
						temp.add(tempNode);
			    	}
		    	}
	    	}
		}
		j = new JTree(temp);
		j.addTreeSelectionListener(new OpenTreeSelectionListener());

		this.add(j,BorderLayout.WEST);
		this.revalidate();
		this.repaint();
	}
	class OpenTreeSelectionListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			
			
		}
		
	}
}
