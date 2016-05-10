package com.homework.kubra;

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

public class SparqlKubra {
	private ResultSet results;
	
	
	private String squery = "PREFIX wikibase: <http://wikiba.se/ontology#>"
			+ "PREFIX bd: <http://www.bigdata.com/rdf#>"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
			+ "PREFIX wd: <http://www.wikidata.org/entity/>"
			+ "SELECT DISTINCT ?planetLabel ?planet ?discoverer ?discovererLabel  "
			+ "WHERE"
			+ "{"
			+ "?ppart wdt:P279* wd:Q634 ."
			+ "?planet wdt:P31 ?ppart ."
			+ "?planet wdt:P61 ?discoverer ."
			+ "SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" }"
			+"?discoverer rdfs:label ?discovererLabel ."
			+"?planet rdfs:label ?planetLabel  "
			+ "} limit 1000" 
			;  
	public SparqlKubra()
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
	public Vector<Datas> getData(){
		Document dcmnt;
		Vector<Datas> returnList = new Vector<Datas>();
		try {
			dcmnt = loadXMLFromString(ResultSetFormatter.asXMLString(results));
			dcmnt.getDocumentElement().normalize();
			
			NodeList resultList = dcmnt.getElementsByTagName("result");
			for(int i=0;i<resultList.getLength();i++)
			{
				Element result = (Element) resultList.item(i);
				NodeList current = result.getElementsByTagName("binding");
				returnList.add(new Datas(current.item(1).getTextContent().trim(),current.item(2).getTextContent().trim()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
}

