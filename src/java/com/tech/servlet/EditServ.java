/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.servlet;

 
import com.tech.dao.AdminDAO;
import com.tech.dao.ExamineeDAO;
import com.tech.dao.StudentDAO;
import com.tech.entities.Admin;
import com.tech.entities.Examinee;
import com.tech.entities.Message;
import com.tech.entities.Student;
import com.tech.helper.ConnectionProvider;
import com.tech.helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author DELL
 */
@MultipartConfig  //for taking data like images 
public class EditServ extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Profile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet EditServ</title>");
        out.println("</head>");
        out.println("<body>");
        HttpSession session = request.getSession();
        String c=(String)session.getAttribute("user");
        if(c.equals("Examinee")){
            Examinee u=(Examinee)session.getAttribute("currentUser");
            doWork(u,request,out);
        }
        else if(c.equals("Student")){
            Student u=(Student) session.getAttribute("currentUser");
         doWork(u,request,out);
        }
        else if(c.equals("Admin")){
            Admin u=(Admin) session.getAttribute("currentUser");
         doWork(u,request,out);
        }
         

}
    
    void doWork(Examinee u,HttpServletRequest request,PrintWriter out)throws ServletException, IOException{
        u.setId(Integer.parseInt(request.getParameter("uId")));
        u.setName(request.getParameter("uUser"));
        u.setEmail(request.getParameter("uEmail"));
        u.setPassword(request.getParameter("uPass"));
         u.setMobile(request.getParameter("uMob"));
         u.setGender(request.getParameter("uGender"));
        Part part = request.getPart("image");
        String imgName = part.getSubmittedFileName();
        String old = u.getProfile();
        if (!imgName.trim().equals("")) {

            u.setProfile(imgName);

        }
        ExamineeDAO dao = new ExamineeDAO(ConnectionProvider.getConnection());
        boolean ans = dao.updateUser(u);
         String  path=request.getRealPath("/")+"\\"+"images"+"\\"+old;
           // String path = request.getRealPath("/")+"images/"+old;
        if(!old.equals("bc.jpg")){    
        Helper.imagedelete(path.trim());
        }
        if (ans) {
            out.println("Updated to DB");
            String path1 =request.getRealPath("/")+"\\"+"images"+"\\"+imgName;

            if (Helper.saveImage(part.getInputStream(), path1)) {
               
                      out.println("profile updated"+ path);
            }
            else{
                       out.println("profile not  updated");
                  }

        } else {
            out.println(" Not Updated to DB");
        }
        out.println("</body>");
        out.println("</html>");

    }
     void doWork(Student u,HttpServletRequest request,PrintWriter out)throws ServletException, IOException{
        u.setId(Integer.parseInt(request.getParameter("uId")));
        u.setName(request.getParameter("uUser"));
        u.setEmail(request.getParameter("uEmail"));
        u.setPassword(request.getParameter("uPass"));
        u.setMobile(request.getParameter("uMob"));
         u.setGender(request.getParameter("uGender"));
         
        Part part = request.getPart("image");
        String imgName = part.getSubmittedFileName();
        String old = u.getProfile();
        if (!imgName.trim().equals("")) {

            u.setProfile(imgName);

        }
        StudentDAO dao = new StudentDAO(ConnectionProvider.getConnection());
        boolean ans = dao.updateUser(u);
         String  path=request.getRealPath("/")+"\\"+"images"+"\\"+old;
           // String path = request.getRealPath("/")+"images/"+old;
            
        Helper.imagedelete(path.trim());
        if (ans) {
            out.println("Updated to DB");
            String path1 =request.getRealPath("/")+"\\"+"images"+"\\"+imgName;

            if (Helper.saveImage(part.getInputStream(), path1)) {
               
                      out.println("profile updated"+ path);
            }
            else{
                       out.println("profile not  updated");
                  }

        } else {
            out.println(" Not Updated to DB");
        }
        out.println("</body>");
        out.println("</html>");

    }
     
     void doWork(Admin u,HttpServletRequest request,PrintWriter out)throws ServletException, IOException{
        u.setId(Integer.parseInt(request.getParameter("uId")));
        u.setName(request.getParameter("uUser"));
        u.setEmail(request.getParameter("uEmail"));
        u.setPassword(request.getParameter("uPass"));
        u.setMobile(request.getParameter("uMob"));
         u.setGender(request.getParameter("uGender"));
         
        Part part = request.getPart("image");
        String imgName = part.getSubmittedFileName();
        String old = u.getProfile();
        if (!imgName.trim().equals("")) {

            u.setProfile(imgName);

        }
        
        AdminDAO dao = new AdminDAO(ConnectionProvider.getConnection());
        boolean ans = dao.updateUser(u);
         String  path=request.getRealPath("/")+"\\"+"images"+"\\"+old;
           // String path = request.getRealPath("/")+"images/"+old;
            
        Helper.imagedelete(path.trim());
        if (ans) {
            out.println("Updated to DB"+u);
            String path1 =request.getRealPath("/")+"\\"+"images"+"\\"+imgName;

            if (Helper.saveImage(part.getInputStream(), path1)) {
               
                      out.println("profile updated"+ path);
            }
            else{
                       out.println("profile not  updated");
                  }

        } else {
            out.println(" Not Updated to DB"+u);
        }
        out.println("</body>");
        out.println("</html>");

    }


}
