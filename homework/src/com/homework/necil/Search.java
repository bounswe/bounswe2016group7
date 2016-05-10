package com.homework.necil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;


@WebServlet("/search")
public class Search  extends HttpServlet  {
    
   /**
    * @see HttpServlet#HttpServlet()
    */
   public Search() {
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("deneme");
		try {
			List<Theater> theaterList = SearchTheaters(request,response);
			for(int i = 0; i< theaterList.size(); i++){
				System.out.print(theaterList.get(i).getName());
			}
			request.setAttribute("list", theaterList);
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request,response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher req=request.getRequestDispatcher("necil/search.jsp"); 
        req.forward(request, response);
	}
	/**
	 * @param city name of the city
	 * @return
	 */
	protected String GetCityQueryString(String city){
		city = Character.toUpperCase(city.charAt(0)) + city.substring(1);
		String query = "PREFIX wd: <http://www.wikidata.org/entity/>\n"
				+ "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
				+ "PREFIX wikibase: <http://wikiba.se/ontology#>\n"
				+ "PREFIX p: <http://www.wikidata.org/prop/>\n"
				+ "PREFIX ps: <http://www.wikidata.org/prop/statement/>\n"
				+ "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX bd: <http://www.bigdata.com/rdf#>\n"
				+ "SELECT DISTINCT ?city ?a\n"
				+ "WHERE {\n"
				+ "?city wdt:P31/wdt:P279* wd:Q515 .\n"  
				+ "?city wdt:P625 ?coordinates.\n"
				+ "?city rdfs:label \""+ city +"\" @en.\n"
				+ "?city rdfs:label ?cityLabel.\n"
				+ "SERVICE wikibase:label {\n"
				+ "bd:serviceParam wikibase:language \"en\" .\n"
				+ "}"
				+ "}"
				;  
		return query;
	}
	/**
	 * @param city sparql code for city
	 * @param radius radius that will be searched.
	 * @return query string to find theater
	 */
	protected String GetTheaterQueryString(String city, String radius){
		city = Character.toUpperCase(city.charAt(0)) + city.substring(1);
		String query = "PREFIX wd: <http://www.wikidata.org/entity/>\n"
				+ "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
				+ "PREFIX wikibase: <http://wikiba.se/ontology#>\n"
				+ "PREFIX p: <http://www.wikidata.org/prop/>\n"
				+ "PREFIX ps: <http://www.wikidata.org/prop/statement/>\n"
				+ "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX bd: <http://www.bigdata.com/rdf#>\n"
				+ "select distinct ?location ?place ?placeLabel ?a\n"
				+ "WHERE {\n"
				+ "wd:"+ city +" wdt:P625 ?center.\n"  
				+ "SERVICE wikibase:around {\n"
				+ "?place wdt:P625 ?location .\n"
				+ "bd:serviceParam wikibase:center  ?center.\n"
				+ "bd:serviceParam wikibase:radius " + radius + " . \n"
				+ " }.\n"
				+ "	?place rdfs:label ?placeLabel."
				+ " ?place wdt:P31/wdt:P279* wd:Q24354 ."
				+ "SERVICE wikibase:label {\n"
				+ "bd:serviceParam wikibase:language \"en\" .\n"
				+ "}"
				+ "}"
				;  
		return query;
	}
	/**
	 * @param request HTTP request
	 * @param response HTTP response
	 * @return List of theaters found
	 * @throws ServletException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	protected List<Theater> SearchTheaters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParserConfigurationException, SAXException {
		String keyword = request.getParameter("keyword");
		String radius = request.getParameter("radius");
		Query query = QueryFactory.create(GetCityQueryString(keyword)); 
        QueryExecution queryExec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        ResultSet city = queryExec.execSelect();
        
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource source = new InputSource(new StringReader(ResultSetFormatter.asXMLString(city)));
        Document doc = builder.parse(source);
        NodeList cityTag = doc.getElementsByTagName("binding");
        
        String cityStr = cityTag.item(0).getTextContent();
        cityStr = cityStr.substring(cityStr.lastIndexOf("/")+1);
        
        query = QueryFactory.create(GetTheaterQueryString(cityStr, radius)); 
        queryExec = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        ResultSet theaters = queryExec.execSelect();
        builder = factory.newDocumentBuilder();
        InputSource source1 = new InputSource(new StringReader(ResultSetFormatter.asXMLString(theaters)));
        Document doc1 = builder.parse(source1);
        
        List<Theater> theaterList = new ArrayList<Theater>();
        NodeList results = doc1.getElementsByTagName("result");
        for(int i = 0; i<results.getLength();i++){
        	Element currentResult = (Element) results.item(i);
        	NodeList place = currentResult.getElementsByTagName("binding");
			Theater t1 = new Theater();
			Element locationNode = (Element) place.item(0);
			String location = locationNode.getTextContent();
			location = location.substring(location.indexOf("(")+1);
			location = location.substring(0,location.indexOf(")")-1);
			
			t1.Lat = location.substring(0,location.indexOf(" ")-1);
			t1.Lng = location.substring(location.indexOf(" ")+1);
			t1.frontendNo = i;
			t1.Name = place.item(2).getTextContent().replace("^' '", "");
			while(t1.Name.charAt(0) == ' ')
				t1.Name = t1.Name.substring(1);
			
			theaterList.add(t1);
        }
		return theaterList;
	}
	
	
	

}
