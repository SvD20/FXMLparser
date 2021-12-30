package edu.bsuir.webxml.servlets;

import edu.bsuir.webxml.entities.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ParserServlet extends HttpServlet {

    private static ArrayList<Employee> employees = new ArrayList<>();
    private String resultstring = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        XMLHandler handler = new XMLHandler();
        try {
            parser.parse(new File("forparsing"), handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        int count_of_juniors = 0;
        int count_of_middles = 0;
        int count_of_seniors = 0;

        for (Employee employee : employees){
            if (employee.getJob().equals("Junior Software Developer")){
                count_of_juniors++;
            }
            if (employee.getJob().equals("Middle Software Developer")){
                count_of_middles++;
            }
            if (employee.getJob().equals("Senior Software Developer")){
                count_of_seniors++;
            }
        }

        resultstring = "В компании " + count_of_juniors + " младших разработчиков" + "В компании " + count_of_middles + " опытных разработчиков"
                + "В компании " + count_of_seniors + " старших разработчиков";

        req.setAttribute("Result", resultstring);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/infofromxml.jsp");
        requestDispatcher.forward(req, resp);
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("employee")) {
                String name = attributes.getValue("name");
                String job = attributes.getValue("job");
                employees.add(new Employee(name, job));
            }
        }
    }



}
