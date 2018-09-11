package WordDensity;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

public class Main {
	
	private PageParser pageParser;
	private KeyParser keyWordParser;
	
	public static void main(String[] args) throws IOException {
		Main assign=new Main();
		int k = 5; 
		assign.init(args);
		System.out.println(assign.startParsingKeyWord(k));
	}
	
	private void init(String[] args) throws IOException{
		if(args.length<=0) throw new IOException("No url is found, any url needs to be provided");
		String url=args[0];
		pageParser=new PageParser(url);
	}
	
	private List<String> startParsingKeyWord(int k){
		Document doc=pageParser.getDoc();
		keyWordParser= new KeyParser(doc);
		keyWordParser.startParsingKeyWords();
		List<String> res= keyWordParser.getKeyWordsOfTopK(k);
		return res;
	}
}

