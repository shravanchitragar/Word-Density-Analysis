package WordDensity;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.jsoup.nodes.Document;

public class KeyParser {
	
	private HashSet<String> stopWords;
	private Document doc;
	private Trie Trie;
	
	/*
		pass Document to KeyWordParser
	*/
	public KeyParser(Document doc){
		this.doc=doc;
		stopWords=new HashSet<String>();
		Trie=new Trie();
	}
	
	public void startParsingKeyWords(){
		parseStopWords();
		parseKeyWords();
	}
	
	public List<String> getKeyWordsOfTopK(int k){
		return Trie.getKeyWordsOfTopK(k);
	}
	
	/*
		get all stop words from text file
	*/
	private void parseStopWords(){
		Scanner sc = new Scanner(getClass().getResourceAsStream("stopwords.txt"));
		while (sc.hasNext()) {
			stopWords.add(sc.next());
		}
		sc.close();
	}
	
	/*
		get key words tree
	*/
	private void parseKeyWords(){
		Scanner sc=new Scanner(doc.text());
		StringBuilder sb=new StringBuilder();
		while(sc.hasNext()){
			String word=sc.next();
			String temp=removePunctuation(word);
			if(isStopWord(temp)){
				if(sb.length()>0) Trie.addWord(sb.toString().split(" "));
				sb.setLength(0);
				continue;
			}
			if(isCandidateWord(temp)){
				sb.append(temp+" ");
				Trie.addWord(temp);
			}
		}
		sc.close();
	}
	
	
	/*
		remove all punctuation
	*/
	private String removePunctuation(String s){
		String string = s.trim();
		
		string =string.replace("!", "");
		string=string.replace("?", "");
		string=string.replace("\\", "");
		string=string.replace("/", "");
		string=string.replace(",", "");
		string=string.replace("(", "");
		string=string.replace(")", "");
		string=string.replace("<", "");
		string=string.replace(">", "");
		string=string.replace("#", "");	
		string=string.replace("\"", "");
		string=string.replace("+", "");
		string=string.replace(":", "");
		string=string.replace("=", "");
		string=string.replace("--", "");
		string=string.replace("*", "");
		string=string.replace("&","");
		string=string.replace("-","");
		
		if (string.startsWith("'") || string.endsWith("'")){
			string=string.replace("'", "");
		}
		
		if (string.endsWith(".")){
			string=string.replace(".", "");
		}
		
		return string;						
	}
	
	/*
		check if the word is stop word
	*/
	private boolean isStopWord(String s){
		return stopWords.contains(s.toLowerCase());
	}
	
	/*
	 * check if word is correct words(words like "   " or includes special characters are incorrect words)*/
	private boolean isCandidateWord(String s){
		
		if(s.length()==1&&s.charAt(0)-'0'==9955){ // '?'
			return false;
		}
		
		boolean isEmpty=true;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c!=' '&&c-'0'!=112) isEmpty=false;
		}
		return !isEmpty;
	}
}
