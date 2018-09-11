package WordDensity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageParser {
	
	private URL url;
    
    public PageParser(String url){
        validateURL(url);
    }
    
    public Document getDoc(){
        Document doc = null;
        try {
            doc=Jsoup.connect(url.toString()).get();
        } catch (IOException e) {
            System.out.println("Could not get document given the url: "+ url.toString());
            e.printStackTrace();
        }
        return doc;
    }
    
    private void validateURL(String url){
        try {
            this.url=new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("url provided is incorrect");
            e.printStackTrace();
        }
    }
    
    
}
