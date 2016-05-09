package com.homework.aydin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.homework.yunus.ModelYunus;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlAydin {
private ResultSet results;
	
	private String squery = "PREFIX wd: <http://www.wikidata.org/entity/>"
			+ "PREFIX wdt: <http://www.wikidata.org/prop/direct/>"
			+ "PREFIX wikibase: <http://wikiba.se/ontology#>"
			+ "PREFIX p: <http://www.wikidata.org/prop/>"
			+ "PREFIX ps: <http://www.wikidata.org/prop/statement/>"
			+ "PREFIX pq: <http://www.wikidata.org/prop/qualifier/>"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			+ "PREFIX bd: <http://www.bigdata.com/rdf#>"
			+ "SELECT ?p ?pLabel ?w ?wLabel "
			+ "WHERE "
			+ "{"
			+ "wd:Q30 p:P6/ps:P6 ?p ."
			+ "?p wdt:P26 ?w ."
			+ "SERVICE wikibase:label {"
			+ "bd:serviceParam wikibase:language \"en\" ."
			+ "}"
			+ "}";
	
	public SparqlAydin()
	{
        Query query = QueryFactory.create(squery); 
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "https://query.wikidata.org/sparql", query );
        results = qExe.execSelect();
	}

	public Vector<ModelAydin> getData(){
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    Vector<ModelAydin> finalRes = new Vector<ModelAydin>();
		try {
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(ResultSetFormatter.asXMLString(results)));
			Document d = builder.parse(is);
			d.getDocumentElement().normalize();
			
			NodeList resList = d.getElementsByTagName("result");
			for(int i=0; i<resList.getLength(); i++){
				Element res = (Element) resList.item(i);
				NodeList temp = res.getElementsByTagName("binding");
				finalRes.add(new ModelAydin(temp.item(1).getTextContent().trim(),temp.item(3).getTextContent().trim()));
			}
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return finalRes;
	}
}
