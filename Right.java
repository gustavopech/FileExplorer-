package fileexplorer;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


public class Right extends JPanel{
	JTree j;
	FileNode[] jlist;
	JList<FileNode> list;
	JList<String> detailDate;
	JList<String> detailSize;
	DefaultMutableTreeNode temp;
	DefaultListModel listModel= new DefaultListModel();
	DefaultListModel<String> listDetailsDate = new DefaultListModel<String>();
	DefaultListModel<String> listDetailsSize = new DefaultListModel<String>();
	//DefaultListModel listModel q= new DefaultListModel();

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234143991718072810L;

	public Right(FileFrame f) {
		this.setDropTarget(new MyDropTarget());
//		this.setSize(400,400);
		this.setLayout(new BorderLayout());
		j = new JTree(temp);
		 DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) j.getCellRenderer();
	        renderer.setLeafIcon(renderer.getClosedIcon());
		this.setVisible(true);
	}

	public void updateTree(DefaultMutableTreeNode tree) {
		this.removeAll();
		this.listModel.clear();
		this.listDetailsDate.clear();
		this.listDetailsSize.clear();


		jlist = null;
		temp = new DefaultMutableTreeNode(tree.getUserObject());
		//DefaultMutableTreeNode root = new DefaultMutableTreeNode(temp.getParent().toString());
		Object[] files = tree.getPath();
		String path ="";		
		
		for(int i = 0;i < files.length; i++) {
			path = path+ files[i].toString()+"\\";
		}
		if (files != null) {
			jlist = new FileNode[files.length];
	    	File currentFile = new File(path);
	    	if (currentFile.isDirectory()) {
	    		File[] nextFiles = currentFile.listFiles();
		    	if (nextFiles != null) {
					jlist = new FileNode[nextFiles.length];
			    	for(int i=0; i <nextFiles.length; i++) {
			    		nextFiles[i].lastModified();
			    		listDetailsDate.addElement(
			    				new SimpleDateFormat("MM-dd-yyyy").format(
			    						new Date(nextFiles[i].lastModified()) 
			    							));
			    		listModel.addElement(new FileNode(nextFiles[i]));
			    		listDetailsSize.addElement((nextFiles[i].length()/1024+"MB"));
			    		//listDetails.addElement(nextFiles[i].get);
						DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(new FileNode(nextFiles[i]));
						temp.add(tempNode);
			    	}
		    	}
	    	}
		}
		if(listModel.isEmpty()) {
			File single = new File(path);
			listModel.addElement(new FileNode(single));
		}
		
		
		j = new JTree(temp);
		j.addTreeSelectionListener(new OpenTreeSelectionListener());
		list = new JList<FileNode>(jlist);
		list.addListSelectionListener(new OpenFileSelectionListener());
		list.addMouseListener((new PopClickListener()));
		list.setModel(this.listModel);
		detailDate = new JList<String>();
		detailSize = new JList<String>();
		this.add(list,BorderLayout.WEST);
		if (listDetailsSize != null) {
			detailSize.setModel(listDetailsSize);
			this.add(detailSize,BorderLayout.EAST);
		}
		if (listDetailsDate != null) {
			detailDate.setModel(listDetailsDate);
			this.add(detailDate,BorderLayout.CENTER);
		}
		this.revalidate();
		this.repaint();
	}
	class OpenTreeSelectionListener implements TreeSelectionListener{

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) j.getLastSelectedPathComponent();
			FileNode f = (FileNode) node.getUserObject();
			Desktop d = Desktop.getDesktop();
			try {
				d.open(f.getFile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//  d.open();
			
		}
		
	}
	class MyDropTarget extends DropTarget{
		public void drop(DropTargetDropEvent evt) {
			try {
				evt.acceptDrop(DnDConstants.ACTION_COPY);
				List result = new ArrayList();
				result = (List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				for (Object o:result) {
					listModel.addElement(new FileNode((File)o));
				}
			}
			catch(Exception ex){
				
			}
		}


	}
	class OpenFileSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			FileNode node =(FileNode)  list.getSelectedValue();
			Desktop d = Desktop.getDesktop();
			try {
				d.open(node.getFile());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public void simple() {
		detailDate.setVisible(false);
		detailSize.setVisible(false);
		// TODO Auto-generated method stub
		
	}
	public void detail() {
		detailDate.setVisible(true);
		detailSize.setVisible(true);
		// TODO Auto-generated method stub
		
	}
}
