package fr.casir.primefaces.service.parser.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import fr.casir.primefaces.model.Student;

public class DomParser {

    private Document m_doc;
    private String xmlfile;
    DocumentBuilder builder;
    DocumentBuilderFactory factory;
    
    public DomParser(String xmlfile) throws Exception {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        this.xmlfile = xmlfile;
        System.out.println(this.xmlfile);
        m_doc = builder.parse(new FileInputStream(new File(this.xmlfile)));
    }

    public int getChildCount(String parentTag, int parentIndex, String childTag) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        return childList.getLength();
    }

    public String getChildValue(String parentTag, int parentIndex, String childTag, int childIndex) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        Element element = (Element) childList.item(childIndex);
        Node child = element.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    public String getChildAttribute(String parentTag, int parentIndex, String childTag, int childIndex,
                                    String attributeTag) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        NodeList childList = parent.getElementsByTagName(childTag);
        Element element = (Element) childList.item(childIndex);
        return element.getAttribute(attributeTag);
    }

    public void addAllChildsAttributes(List<Student> students)
    {
    	try {
    	    Document document= builder.newDocument();
    	    
    	    final Element racine = document.createElement("students");
    	    document.appendChild(racine);
    	    
    	    for (Student std : students) {
    	    	
        	    final Element personne = document.createElement("student");
        	    personne.setAttribute("id",String.valueOf(std.getId()));
        	    racine.appendChild(personne);
        	    
        	    final Element firstName = document.createElement("firstName");
        	    firstName.appendChild(document.createTextNode(std.getFirstName()));
        			
        	    final Element lastName = document.createElement("lastName");
        	    lastName.appendChild(document.createTextNode(std.getLastName()));
        	    
        	    final Element mail = document.createElement("mail");
        	    mail.appendChild(document.createTextNode(std.getMail()));
        			
        	    personne.appendChild(firstName);
        	    personne.appendChild(lastName);	
        	    personne.appendChild(mail);	
    	    }
	
    	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	    Transformer transformer = transformerFactory.newTransformer();
    	    DOMSource source = new DOMSource(document);
    	    StreamResult sortie = new StreamResult(new File(this.xmlfile));

    	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
    	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			

    	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    			
    	    transformer.transform(source, sortie);	
    	}
    	catch (Exception e) {
    	    e.printStackTrace();
    	}	
    }


}