package kr.co.swh.lecture.springboot;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/api")
public class ForexController {

    @Autowired
    private ExchangeValueRepository repository;
    
    @Autowired
    private Environment environment;
    
//    @Autowired
    private ArrayList<ExchangeValue> Value;

    @GetMapping("/currency-exchange/")
    public List<ExchangeValue> retrieve1() {
        List<ExchangeValue> exchangeValue = repository.findAll();

        return exchangeValue;
    }
    
    @GetMapping("/currency-exchange/{id}")
    public ExchangeValue retrieve(@PathVariable String id) {

    	System.out.println(environment.getProperty("Key1"));
    	System.out.println(environment.getProperty("Key2"));
        ExchangeValue exchangeValue = repository.findById(id).get();

        return exchangeValue;
    }
    
    @GetMapping("/")
    public List<ExchangeValue> list() {
        return Value;
    }
    
    
//    @GetMapping("/hello")
//    public List<ExchangeValue> PostTest() {
//    	Document doc = Jsoup.connect("http://example.com/").get();
//    	String title = doc.title();
//    	
//    }
    
    @GetMapping("/city/{cityName}")
    public ArrayList<ArrayList<String>> PostTest(@PathVariable String cityName) {
    	ArrayList<ArrayList<String>> result = null;
    	ArrayList<String> babo = null;
    	
    	try {
    		Connection conn = Jsoup.connect("https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109");
    		Document doc = conn.get();
    		Elements eles = doc.select("location");
    		
    		for (Element ele : eles) {
    			Elements city = ele.select("city");
    			
    			if (city.text().equals(cityName)) {
    				Elements data = ele.select("data");
        			
        			for (Element dat : data) {
        				Elements tmEf = dat.select("tmEf");
        				Elements wf = dat.select("wf");
        				Elements tmn = dat.select("tmn");
        				Elements tmx = dat.select("tmx");
        				
        				babo.add(tmEf.text());
        				babo.add(wf.text());
        				babo.add(tmn.text());
        				babo.add(tmx.text());
        			}
    			}
    			
    			result.add(babo);
    			babo.clear();
    		}
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    
    
    // https://jjluveeecom.tistory.com/50
}
