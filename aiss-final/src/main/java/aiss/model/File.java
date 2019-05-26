package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class File {

	private String id;
	private String title;
	private String type;
	private String parent;
	private List<File> children;
	private String content;
	
	public File() {
		
	}

	public File(String title, String type) {
		this.title = title;
		this.type = type;
		if(type.equals("folder")) {
			this.children = new ArrayList<File>();
			this.content = null;
		}
		else if(type.equals("doc")) {
			this.children = null;
			this.content = "";
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public File(String title, String type, String content, String parent) {
		this.title = title;
		this.type = type;
		this.parent = parent;
		if(type.equals("folder")) {
			this.children = new ArrayList<File>();
			this.content = null;
		}
		else if(type.equals("doc")) {
			this.children = null;
			this.content = content;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public List<File> getChildren() {
		return children;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(this.type.equals("doc")) {
			this.content = content;
		}
	}
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public File getFile(String id) {
		if(this.children==null) {
			return null;
		}
		else {
			File file = null;
				for(File f: this.children)
					if (f.getId().equals(id))
					{
						file=f;
						break;
					}
			return file;
		}
	}
	
	public void addFile(File f) {
		if(this.children!=null) {
			this.children.add(f);
		}
		else {
			this.children = new ArrayList<File>();
			this.children.add(f);
		}	
	}
	
	public void removeFile(File f) {
		if(this.children!=null) {
			this.children.remove(f);
		}
	}
	
	public void removeFile(String id) {
		File f = getFile(id);
		if(f!=null) {
			this.children.remove(f);
		}
	}
}
