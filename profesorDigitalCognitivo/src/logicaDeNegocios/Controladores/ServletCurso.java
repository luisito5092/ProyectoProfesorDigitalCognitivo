package logicaDeNegocios.Controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeNegocios.Curso;
import logicaDeNegocios.dao.DaoCurso;
import logicaDeNegocios.dao.DaoProfesor;
import logicaDeNegocios.dto.DtoCurso;
import logicaDeNegocios.dto.DtoProfesor;
import logicaDeNegocios.factory.FabricaCurso;

/**
 * Servlet implementation class ServletCurso
 */
@WebServlet("/ServletCurso")
public class ServletCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoCurso curso= new DtoCurso();
		curso.setCodigo(request.getParameter("codigo"));
		curso.setDescripcion(request.getParameter("descripcion"));
    	
		FabricaCurso fabrica=new FabricaCurso();
		Curso cursoFabricado= fabrica.fabricarCurso(curso);
		
    	if (request.getParameter("ventanaActualizar")!=null){
    		HttpSession session = request.getSession(true);
			session.setAttribute("IdentificadorCurso",request.getParameter("CodigoCurso"));
    		response.sendRedirect("../verCurso.jsp?codigo="+request.getParameter("CodigoCurso")+"&descripcion="+
    							request.getParameter("Descripcion"));	
    		
    	}else if(request.getParameter("actualizar")!=null){
    		cursoFabricado.actualizarCurso(curso, request.getParameter("codigoOriginal"));
    		response.sendRedirect("../verCurso.jsp?codigo="+request.getParameter("codigoOriginal")+"&descripcion="+
    							request.getParameter("descripcion"));
    		
    	}else if(request.getParameter("listaDeTemas")!=null){
    		response.sendRedirect("EstudiantesCurso.jsp");
    		
    	}else if(request.getParameter("VentanaBitacoras")!=null){
    		response.sendRedirect("Bitacora.jsp");
    	}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DtoCurso curso= new DtoCurso();
		curso.setCodigo(request.getParameter("CodigoCurso"));
		curso.setDescripcion(request.getParameter("Descripcion"));
		
		FabricaCurso fabrica=new FabricaCurso();
		Curso cursoFabricado= fabrica.fabricarCurso(curso);
		
		if(request.getParameter("eliminar")!=null){
			cursoFabricado.eliminarCurso(cursoFabricado.getCodigo());
			response.sendRedirect("MisCursos.jsp");
			
		}else if(request.getParameter("asignarProfe")!=null){
			cursoFabricado.asignarProfesor(request.getParameter("SesionActual"), request.getParameter("CodigoCursoSinAsignar"));
			response.sendRedirect("CursosNoAsignados.jsp");
			
		}else if(request.getParameter("registrarCurso")!=null){
			cursoFabricado.almacenarCurso(cursoFabricado);
			response.sendRedirect("menuCurso.jsp");

		}else if(request.getParameter("consultar")!=null){
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("idCurso",request.getParameter("CodigoCurso"));
			response.sendRedirect("CursoEspecifico.jsp");
		}
	}

}