package fileexplorer;

import java.io.File;

public class FileNode {
	File file;
	public FileNode(File F) {
		this.file = F;
	}
	public File getFile() {
		return this.file;
	}
	public String toString() { 
		if (this.file.toPath().getNameCount() == 0) {
			return this.file.toString();
		}
	    return (this.file.getName());
	} 
}
