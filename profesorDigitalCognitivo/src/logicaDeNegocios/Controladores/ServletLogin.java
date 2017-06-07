package logicaDeNegocios.Controladores;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexionMySql.ConectaDb;
import conexionMySql.ConexionSingleton;
import logicaDeNegocios.verificacionSMS;
import logicaDeNegocios.dao.DaoProfesor;
import logicaDeNegocios.dto.DtoProfesor;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String user_name;
	Statement state;
	

    /**
     * Default constructor. 
     */
    public ServletLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				process(request,response);
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
					 
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession sesion = request.getSession(true);
		
		if(request.getParameter("enviarSMS")!=null){
			
			DtoProfesor profe=new DtoProfesor();
			profe.setCorreoElectronico(request.getParameter("usuario"));
			profe.setContrasenia(request.getParameter("contrasenia"));
			DaoProfesor consulta=new DaoProfesor();
			if(consulta.validarUsuario(profe)){   
				 sesion.setAttribute("logueado",profe.getCorreoElectronico());
				 sesion.setAttribute("contrasenia",profe.getContrasenia());
				 
				 verificacionSMS sms = new verificacionSMS();
				 String codigo = sms.generarPIN();
				 String telefono = consulta.getTelefono(request.getParameter("usuario").toString());
				 
				 //**********************sms.enviarSms(telefono, codigo);**************************
				 
				 sesion.setAttribute("telefono",telefono);
				 sesion.setAttribute("codigoVerificacion",codigo); 
				 sesion.setAttribute("contador","2"); 
				 response.sendRedirect("VerificacionTelefonica.jsp");				 
			 }else{    
				request.setAttribute("errorMessage","Usuario o contrasena invalida");
				 //request.getRequestDispatcher("LogIn.jsp").forward(request, response);
			 	response.sendRedirect("LogIn.jsp");
			 }	
		}else if(request.getParameter("verificacionSMS")!=null){
			
			String codigoVerificacion = sesion.getAttribute("codigoVerificacion").toString();
			int contador =Integer.parseInt(sesion.getAttribute("contador").toString()); 
			String codigoIngreso = request.getParameter("codigoVerificacion");			
			if(codigoIngreso.equalsIgnoreCase(codigoVerificacion)){
					response.sendRedirect("MenuPrincipal.jsp");
			}
			contador --;
			sesion.setAttribute("contador", contador);			
			if(contador>0)
				response.sendRedirect("VerificacionTelefonica.jsp");
			}else{
				sesion.setAttribute("logueado","incorrecto");
				sesion.setAttribute("contrasenia","incorrecto");
				response.sendRedirect("index.jsp");
			}						
				
		}	

	}

