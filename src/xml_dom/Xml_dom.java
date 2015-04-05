/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_dom;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author amin
 */
public class Xml_dom {

	/**
	 * @param args the command line arguments
	 */
	private static class MyErrorHandler implements ErrorHandler{
		private PrintWriter out;
		MyErrorHandler(PrintWriter out)
		{
			this.out = out;
		}
		private String getParseExceptionInfo(SAXParseException spe)
		{
			String systemId =  spe.getSystemId();
			System.out.println(systemId);
			if(systemId == null)
			{
				systemId = "null";
			}
			String info = "URI=" + systemId + "Line=" + spe.getLineNumber() + ": " + spe.getMessage();
			return info;
		}
		public void warning(SAXParseException spe) throws SAXException
		{
			out.println("warning: " + getParseExceptionInfo((spe)));
		}
		public void error(SAXParseException spe) throws SAXException
		{
			String message = "Error: " + getParseExceptionInfo(spe);
			throw new SAXException(message);
		}
		public void fatalError(SAXParseException spe) throws SAXException 
		{
			String message = "Fatal Error: " + getParseExceptionInfo(spe);
			throw new SAXException(message);
		}
	}
	private static void usage()
	{
		
	}
	static final String outputEncoding = "UTF-8";
	static final String inputEncoding = "UTF-8";
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, InstantiationException, IllegalAccessException{
		// TODO code application logic here
		
		
		String filename = null;
		boolean dtdValidate = false;
		boolean xsdValidate = false;
		String schemaSource = null;
		for(int i=0; i < args.length; i++)
		{
			if (args[i].equals("-dtd"))  { 
				dtdValidate = true;
			} 
			else if (args[i].equals("-xsd")) {
				xsdValidate = true;
			 } 
			else if (args[i].equals("-xsdss")) {
			    if (i == args.length - 1) {
				usage();
			    }
			    xsdValidate = true;
			    schemaSource = args[++i];
			}
			else
			{
				filename = args[i];
				if(i !=args.length - 1)
				{
					usage();
				}
			}
		}
		if(filename ==null)
		{
			usage();
		}
		filename = "f:/downloads/nlp/corpus/2007/HAM2-070101.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document doc = db.parse(new File(filename));
		System.out.println(doc.getDocumentElement().getElementsByTagName("DOC").item(2).getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
		String news = doc.getDocumentElement().getElementsByTagName("DOC").item(2).getChildNodes().item(19).getTextContent();
		System.out.println(news);
//		Tokenizer1 tokens = new Tokenizer1();
//		tokens.tokenizer(news);
	}
}
