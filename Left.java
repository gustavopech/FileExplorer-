package fileexplorer;

import java.awt.Dimension;
import java.io.File;


import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
public class Left  extends JPanel{
	JTree tree;
	FileFrame ff; 
	/**
	 * 
	 */
	private static final long serialVersionUID = -1815121610264820107L;
	
	public Left(FileFrame f) {        

		this.ff = f;
		this.setVisible(true);
		tree = buildFileExplorer();
		tree.addTreeSelectionListener(new DFSTreeSelectionListener());
		this.add(tree);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
		renderer.setLeafIcon(renderer.getClosedIcon());
	}
    private JTree  buildFileExplorer() {
    	DefaultMutableTreeNode rootNode = initialDFS();
    	
    	Dimension min = new Dimension(400,400);
    	JTree current = new JTree(rootNode);
    	current.setMinimumSize(min);
    	return current;
    }
    public DefaultMutableTreeNode DFS(DefaultMutableTreeNode temp, String path) {
    	File currentFile = new File(path);
    	File[] nextFiles = currentFile.listFiles();
    	if (nextFiles != null) {
    		System.out.println(path);
	    	for(int i=0; i <nextFiles.length; i++) {

	    		if(nextFiles[i].isDirectory()) {
					DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new FileNode(nextFiles[i]));
					temp.add(tempNode);
	    			if (nextFiles[i] != null) {
		    			File[] tempFiles = nextFiles[i].listFiles();
		    			if (tempFiles != null){
			    			for(int z=0; z < tempFiles.length; z++) {
			    				if (tempFiles[z] != null) {
					    			DefaultMutableTreeNode tempNode2 = new DefaultMutableTreeNode(new FileNode(tempFiles[z]));    	
					    			tempNode.add(tempNode2);
			    				}
			    			}
		    			}
	    			}
	    		}
	    	}
    	}
	
    	return temp;
    }
    public DefaultMutableTreeNode initialDFS() {
    	
//    	File[] file= File.listRoots();
    	DefaultMutableTreeNode temp = new DefaultMutableTreeNode(ff.getFileNode());
    	File[] file = ff.getFileNode().getFile().listFiles();
    	for(int i=0; i <file.length; i++) {

    		if(file[i].isDirectory()) {
    			DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new FileNode(file[i]));
    			temp.add(tempNode);
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
    class DFSTreeSelectionListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			ff.getRight().updateTree(node);
			String path = "";
			//Object[] temp = null;
			if(node != null) {
				Object[] temp = node.getPath();
				if (temp != null) {
					
					for(int i = 0;i < temp.length; i++) {
						//System.out.println(temp[i]);
						path = path+ temp[i].toString()+"\\";
					  }
				  }
			
		
				
				if (node.getChildCount() == 0) {
					//System.out.println("holiday");
				  	node = DFS(node,path);
				  }
			 }
			
		}
    	
    }
}
