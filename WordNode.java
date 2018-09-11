package WordDensity;

import java.util.ArrayList;
import java.util.List;

public class WordNode {
	
	private String value;
	private int degree;
	private List<WordNode> children;
	
	public WordNode(String val){
		this.value = val;
		this.degree=1;
		this.children=new ArrayList<WordNode>();
	}
	
	public void setVal(String val){
		this.value=val;
	}
	
	public void setDegree(int degree){
		this.degree=degree;
	}
	
	public String getVal(){
		return value;
	}
	
	public int getDegree(){
		return degree;
	}
	
	public List<WordNode> getChildren(){
		return children;
	}
}
