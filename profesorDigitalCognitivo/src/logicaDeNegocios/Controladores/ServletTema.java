package logicaDeNegocios.Controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeNegocios.Bitacora;
import logicaDeNegocios.BitacoraCSV;
import logicaDeNegocios.BitacoraTXT;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.dao.DaoBitacora;
import logicaDeNegocios.dao.DaoTema;
import logicaDeNegocios.dto.DtoBitacora;
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
		DaoBitacora cambiosHechos=new DaoBitacora();
		DtoBitacora dto=new DtoBitacora();
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
				Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".csv");
				Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".xml");
				Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".txt");
				csv.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("TextOtro")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				xml.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("TextOtro")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				txt.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("TextOtro")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				datoTema.agregarTemaNuevo(request.getParameter("TextOtro"));
				datoTema.agregarTemaCurso(request.getParameter("CursoActualTema"),request.getParameter("TextOtro"));
				response.sendRedirect("Tema.jsp");
			}else{
				Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".csv");
				Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".xml");
				Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".txt");
				csv.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("comboboxTema")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				xml.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("comboboxTema")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				txt.realizarRegistro(request.getParameter("correoProfesor"), "Se ha agregado el tema: "+request.getParameter("comboboxTema")+" al curso "+request.getParameter("CursoActualTema"), request.getParameter("CursoActualTema"));
				datoTema.agregarTemaCurso(request.getParameter("CursoActualTema"),request.getParameter("comboboxTema"));
				response.sendRedirect("Tema.jsp");
			}
		}else if(request.getParameter("editarTema")!=null){
			response.sendRedirect("actualizarTema.jsp?codigoCurso="+request.getParameter("codigoCurso")+"&Descripcion="+request.getParameter("Descripcion"));
			
		}else if(request.getParameter("botonEliminarTema")!=null){
			Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".csv");
			Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".xml");
			Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".txt");
			csv.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el tema "+request.getParameter("Descripcion")+" del curso "+request.getParameter("codigoCurso"), request.getParameter("codigoCurso"));
			xml.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el tema "+request.getParameter("Descripcion")+" del curso "+request.getParameter("codigoCurso"), request.getParameter("codigoCurso"));
			txt.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el tema "+request.getParameter("Descripcion")+" del curso "+request.getParameter("codigoCurso"), request.getParameter("codigoCurso"));
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
			Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".csv");
			Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".xml");
			Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".txt");
			csv.realizarRegistro(request.getParameter("correoProfesor"), "Se ha actualizado la información del tema "+request.getParameter("tema")+" del curso "+request.getParameter("CodigoCursoActual"), request.getParameter("CodigoCursoActual"));
			xml.realizarRegistro(request.getParameter("correoProfesor"), "Se ha actualizado la información del tema "+request.getParameter("tema")+" del curso "+request.getParameter("CodigoCursoActual"), request.getParameter("CodigoCursoActual"));
			txt.realizarRegistro(request.getParameter("correoProfesor"), "Se ha actualizado la información del tema "+request.getParameter("tema")+" del curso "+request.getParameter("CodigoCursoActual"), request.getParameter("CodigoCursoActual"));
			String descripcion = request.getParameter("tema");			
			dtoTema.setDescripcion(descripcion);			
			daoTema.actualizarTema(dtoTema);
			response.sendRedirect("Tema.jsp");
		}
	}

}
		


