package logicaDeNegocios.Controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeNegocios.dao.DaoTema;
import logicaDeNegocios.dto.DtoTema;

/**
 * Servlet implementation class Tema
 */
@WebServlet("/ServletTema")
public class ServletTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTema() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoTema tema=new DtoTema();
		DaoTema datoTema=new DaoTema();
		
		if(request.getParameter("listaDeTemas")!=null){
			response.sendRedirect("../Tema.jsp?codigo="+request.getParameter("codigoCurso"));
			
		}else if(request.getParameter("agregarSubtema")!=null){
			HttpSession session = request.getSession(true);
			session.setAttribute("DescripcionTema",request.getParameter("Descripcion"));
			response.sendRedirect("Subtema2.jsp");
			
		}else if(request.getParameter("AgregarTema")!=null){
			response.sendRedirect("registrarTema.jsp");
			
		}else if(request.getParameterValues("registrarTemaCurso")!=null){
			if(request.getParameter("comboboxTema").equals("Otro")){
				datoTema.agregarTemaNuevo(request.getParameter("TextOtro"));
				datoTema.agregarTemaCurso(request.getParameter("CursoActualTema"),request.getParameter("TextOtro"));
				response.sendRedirect("Tema.jsp");
			}else{
				datoTema.agregarTemaCurso(request.getParameter("CursoActualTema"),request.getParameter("comboboxTema"));
				response.sendRedirect("Tema.jsp");
			}
		}else if(request.getParameter("editarTema")!=null){
			response.sendRedirect("actualizarTema.jsp?codigoCurso="+request.getParameter("codigoCurso")+"&Descripcion="+request.getParameter("Descripcion"));
			
		}else if(request.getParameter("botonEliminarTema")!=null){
			datoTema.eliminarTema(request.getParameter("Descripcion"), request.getParameter("codigoCurso"));
			response.sendRedirect("Tema.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoTema dtoTema = new DtoTema();
		DaoTema daoTema = new DaoTema();
		
		if(request.getParameter("agregarSubtema")!=null){
			HttpSession session = request.getSession(true);
			session.setAttribute("DescripcionTema",request.getParameter("Descripcion"));
			response.sendRedirect("Subtema2.jsp");
			
		}else if(request.getParameter("realizarActualizacion")!=null){
			String descripcion = request.getParameter("tema");			
			dtoTema.setDescripcion(descripcion);			
			daoTema.actualizarTema(dtoTema);
			response.sendRedirect("Tema.jsp");
		}
	}

}
		


