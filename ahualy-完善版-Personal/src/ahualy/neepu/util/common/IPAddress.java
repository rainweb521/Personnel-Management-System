package ahualy.neepu.util.common;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class IPAddress {
	public String getURLInfo(String urlInfo,String charset) throws Exception {
		
		Document doc = Jsoup.connect(urlInfo).get();
      
		String address = null;
        Elements infos = doc.getElementsByClass("ul1");
        for (Element element : infos) {
        	Element links = element.getElementsByTag("li").get(1);
        	address = links.html();
		}
		return address.substring(6);
	}
}
	
	



 
