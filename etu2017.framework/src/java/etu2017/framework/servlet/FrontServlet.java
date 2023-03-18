/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu2017.framework.servlet;

import annotation.Url;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author IirnaKaren
 */
public class FrontServlet extends HttpServlet {
   HashMap<String,Mapping> MappingURLS;

    //getters
      public HashMap<String, Mapping> getMappingURLS() {
          return MappingURLS;
      }

    //setters
    public void setMappingURLS(HashMap<String, Mapping> MappingURLS) {
        this.MappingURLS = MappingURLS;
    }
    
   @Override
   public void init() throws ServletException{
       try{
        MappingURLS = new HashMap<>();        
        String path = FrontServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = java.net.URLDecoder.decode(path, "UTF-8");
        String currentDirectory = new java.io.File(decodedPath).getParent()+"\\classes\\etu2017\\framework\\servlet";

        File dir = new File(currentDirectory);
        File[] files = dir.listFiles();        
                
            for (File file : files) {
            String[] filename = file.getName().split("\\.");
            Class className = Class.forName("etu2017.framework.servlet."+filename[0]);
            if(className!=null){
                 Method[] methods = className.getDeclaredMethods();
                for (Method met : methods) {               
                    if (met.isAnnotationPresent(Url.class)) {
                    Url annotation = met.getAnnotation(Url.class);
                    String urlValue = annotation.value();
                        MappingURLS.put(urlValue, new Mapping(className.getSimpleName(), met.getName()));                             
                    }
                }
            }            
        }
       }
       catch(UnsupportedEncodingException | ClassNotFoundException | SecurityException ex){
          
        }
    }
       
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            /*String url = request.getRequestURI();
            response.setContentType("text/plain");
            response.getWriter().write(url);
            PrintWriter out = response.getWriter();*/
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Liste des mappings :</h1>");
            for (Map.Entry<String, Mapping> entry : MappingURLS.entrySet()) {
               out.println("<p>URL: " + entry.getKey() + "</p>");
               out.println("<p>Class: " + entry.getValue().getClassName() + "</p>");
               out.println("<p>Method: " + entry.getValue().getMethod() + "</p><br>");
            }
            out.println("</body></html>");
    }
}
