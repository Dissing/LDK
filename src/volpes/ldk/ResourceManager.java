package volpes.ldk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class ResourceManager {

    private Map<String,ResourceLoader> loaders = new HashMap<String, ResourceLoader>();


    /**
     * Initializes the resource manager and loads ALL resources into memory
     * @param filename The filename of the resource file
     */
    protected void initialize(String filename) {
        InputStream is = null;
        try {
            is = new FileInputStream(new File(filename));
        }  catch (FileNotFoundException e) {
            System.err.println("Unable to locate the resource file \""+filename+"\" at the games root directory");
            System.exit(1);
        }
        loadResources(parseXML(is));
    }

    /**
     * Utility functions that wraps much of the xml boilerplate code.
     * @param is The inpustream of the XML file
     * @return The {@link Document} of the XML file
     * @throws LDKException
     */
    public static Document parseXML(InputStream is) throws LDKException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }   catch (ParserConfigurationException e) {
            throw new LDKException("Unable to load resources",e);
        }
        Document doc;
        try {
            doc = documentBuilder.parse(is);
        }   catch (SAXException e) {
            throw new LDKException("Unable to load resources",e);
        }   catch (IOException e) {
            throw new LDKException("Unable to load resources",e);
        }

        doc.getDocumentElement().normalize();

        return doc;
    }

    private void loadResources(Document doc) {
        NodeList resourceNodes = doc.getElementsByTagName("resource");
        int totalResources = resourceNodes.getLength();

        for (int i = 0; i < totalResources; i++) {
            Node resourceNode = resourceNodes.item(i);

            if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element resourceElement = (Element)resourceNode;

                String loaderID = resourceElement.getAttribute("type");

                if (loaders.containsKey(loaderID)) {
                    loaders.get(loaderID).load(resourceElement);
                } else {
                    System.err.println("No loader was registered for ID: " + loaderID + " ignoring file");
                }
            }
        }
    }

    /**
     * Attaches a new loader to the manager.
     * All resources with the type corresponding to the loaderID will be loaded using this loader
     * @param loader The loader
     * @param loaderID The type this loader must respond to
     */
    public void attachLoader(ResourceLoader loader, String loaderID) {
        loaders.put(loaderID,loader);
        loader.initialize();
    }

    /**
     * Returns a resource with of the specified type with the id
     * @param type The type of the resource
     * @param id The id of the resource
     * @return The resource
     */
    public Object get(String type, String id) {
        return loaders.get(type).get(id);
    }


}
