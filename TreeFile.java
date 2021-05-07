package fileexplorer;

import java.io.File;

public class TreeFile extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TreeFile(File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}
    @Override
    public String toString() {
        return String.format(this.getName());
    }
}
