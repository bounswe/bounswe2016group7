package com.homework.yunus;

import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlYunus {
	private ResultSet results;
	
	private String squery = "PREFIX wd: <http://www.wikidata.org/entity/>"
			+ "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
			+ "PREFIX wikibase: <http://wikiba.se/ontology#>"
			+ "PREFIX p: <http://www.wikidata.org/prop/>"
			+ "PREFIX ps: <http://www.wikidata.org/prop/statement/>"
			+ "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX bd: <http://www.bigdata.com/rdf#>"
			+ "SELECT DISTINCT ?country ?countryLabel ?capitalLabel ?capital "
			+ "WHERE"
			+ "{"
			+ "?country wdt:P31 wd:Q3624078 ."
			+ "OPTIONAL { ?country wdt:P36 ?capital } ."
			+ "SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" }"
			+ "}"
			+ "ORDER BY ?countryLabel" 
			;  
	
	public SparqlYunus()
	{
        Query query = QueryFactory.create(squery); 
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        results = qExe.execSelect();
	}
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	public Vector<ModelYunus> getData(){
		Document dcmnt;
		Vector<ModelYunus> returnList = new Vector<ModelYunus>();
		try {
			dcmnt = loadXMLFromString(ResultSetFormatter.asXMLString(results));
			dcmnt.getDocumentElement().normalize();
			
			NodeList resultList = dcmnt.getElementsByTagName("result");
			for(int i=0;i<resultList.getLength();i++)
			{
				Element result = (Element) resultList.item(i);
				NodeList current = result.getElementsByTagName("binding");
				if(current.getLength() != 4) continue;
				if(current.item(1).getTextContent().trim().equals("Chad")) continue;
				if(current.item(1).getTextContent().trim().equals("Côte d'Ivoire")) continue;
				if(current.item(1).getTextContent().trim().equals("People's Republic of China")) continue;
				if(current.item(1).getTextContent().trim().equals("Tonga")) continue;
				if(current.item(1).getTextContent().trim().equals("Mongolian People's Republic")) continue;
				returnList.add(new ModelYunus(current.item(1).getTextContent().trim(),current.item(2).getTextContent().trim()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnList;
	}
}
