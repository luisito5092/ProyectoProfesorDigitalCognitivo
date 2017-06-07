package logicaDeNegocios.Controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicaDeNegocios.Estudiante;
import logicaDeNegocios.dao.DaoEstudiante;
import logicaDeNegocios.dto.DtoEstudiante;
import logicaDeNegocios.factory.FabricaEstudiante;

/**
 * Servlet implementation class ServletEstudiante2
 */
@WebServlet("/ServletEstudiante2")
public class ServletEstudiante2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEstudiante2() {
        super();
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
		// TODO Auto-generated method stub
		DtoEstudiante estudiante= new DtoEstudiante();
		estudiante.setCorreo(request.getParameter("correo"));
		estudiante.setIdEstudiante(request.getParameter("identificacion"));
		estudiante.setFechaNacimiento(request.getParameter("fecha").toString());
		estudiante.setNombre(request.getParameter("nombre"));
		estudiante.setPrimerApellido(request.getParameter("primerApellido"));
		estudiante.setSegundoApellido(request.getParameter("segundoApellido"));
		estudiante.setNumeroCarne(request.getParameter("carne"));
		estudiante.setTelefono(request.getParameter("telefono"));
		
		FabricaEstudiante fabrica= new FabricaEstudiante();
				
		DaoEstudiante almacenar=new DaoEstudiante();
		Estudiante nuevo= fabrica.fabricarEstudiante(estudiante);
		
		if(request.getParameter("registrarNuevoEstudiante")!=null){
			almacenar.crearEstudiante(nuevo);
			response.sendRedirect("registrarEstudiante.jsp");
		}
		
	}

}
