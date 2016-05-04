package com.homework.yunus;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SparqlYunus {
	private ResultSet results;
	private String squery = "PREFIX  g:    <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
            "PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX  onto: <http://dbpedia.org/ontology/>\n" +
            "\n" +
            "SELECT  ?subject ?stadium ?lat ?long\n" +
            "WHERE\n" +
            "  { ?subject g:lat ?lat .\n" +
            "    ?subject g:long ?long .\n" +
            "    ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> onto:Stadium .\n" +
            "    ?subject rdfs:label ?stadium\n" +
            "    FILTER ( ( ( ( ( ?lat >= 52.4814 ) && ( ?lat <= 57.4814 ) ) && ( ?long >= -1.89358 ) ) && ( ?long <= 3.10642 ) ) && ( lang(?stadium) = \"en\" ) )\n" +
            "  }\n" +
            "LIMIT   5\n" +
            "";  
	
	public SparqlYunus()
	{
        Query query = QueryFactory.create(squery); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", query );
        results = qExe.execSelect();
	}

	public String getData() {
		return ResultSetFormatter.asText(results);
	}
}
