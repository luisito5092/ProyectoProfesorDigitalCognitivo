package logicaDeNegocios.Controladores;

import java.awt.Window;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicaDeNegocios.Profesor;
import logicaDeNegocios.dao.DaoProfesor;
import logicaDeNegocios.dto.DtoProfesor;
import logicaDeNegocios.factory.FabricaProfesor;

/**
 * Servlet implementation class ServletProfesorFixed
 */
@WebServlet("/ServletProfesorFixed")
public class ServletProfesorFixed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfesorFixed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoProfesor profe= new DtoProfesor();
		profe.setContrasenia(request.getParameter("contrasenia"));
		profe.setCorreoElectronico(request.getParameter("correo"));
		profe.setNombre(request.getParameter("nombre"));
		profe.setTelefono(request.getParameter("telefono"));
		
		FabricaProfesor fabrica=new FabricaProfesor();
		Profesor profesorFabricado = fabrica.fabricarProfesor(profe);
		
		profesorFabricado.almacenarProfesor(profesorFabricado);
		
		response.sendRedirect("menuProfesores.jsp");	
	}

}