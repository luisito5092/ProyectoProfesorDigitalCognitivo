package logicaDeNegocios.Controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logicaDeNegocios.dao.DaoPregunta;
import logicaDeNegocios.dto.DtoPregunta;

/**
 * Servlet implementation class ServletPregunta
 */
@WebServlet("/ServletPregunta")
public class ServletPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<String> listaRespuestasIncorrectas=new ArrayList<String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPregunta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoPregunta pasarPregunta=new DtoPregunta();
		DaoPregunta pregunta=new DaoPregunta();
		
		if(request.getParameter("guardarPregunta")!=null){
			pasarPregunta.setPregunta(request.getParameter("pregunta"));
			pasarPregunta.setDescripcionAyuda(request.getParameter("ayuda"));
			pasarPregunta.setDescripcionPregunta(request.getParameter("radios"));
			pasarPregunta.setRespuestaCorrecta(request.getParameter("respuestaCorrecta"));
			
			if(request.getParameter("radios").equals("Seleccion Unica")){
				pregunta.crearPregunta(pasarPregunta.getPregunta(),pasarPregunta.getDescripcionPregunta(),
						pasarPregunta.getDescripcionAyuda(), pasarPregunta.getRespuestaCorrecta(),
						request.getParameter("DescripcionSubtema"), request.getParameter("DescripcionTema"));
				HttpSession session = request.getSession(true);
				session.setAttribute("Pregunta",request.getParameter("pregunta"));
				response.sendRedirect("RespuestasIncorrectas.jsp");

			}else{
				pregunta.crearPregunta(pasarPregunta.getPregunta(),pasarPregunta.getDescripcionPregunta(),
						pasarPregunta.getDescripcionAyuda(), pasarPregunta.getRespuestaCorrecta(),
						request.getParameter("DescripcionSubtema"), request.getParameter("DescripcionTema"));
				response.sendRedirect("Preguntas3.jsp");
			}
		}
		else if(request.getParameter("agregarRIncorrecta")!=null){
			listaRespuestasIncorrectas.add(request.getParameter("respuestaIncorrecta"));
			response.sendRedirect("RespuestasIncorrectas.jsp");
		}
		else if(request.getParameter("finalizarRIncorrectas")!=null){
			pregunta.AgregarRespuestasIncorrectas(listaRespuestasIncorrectas, request.getParameter("Pregunta"),
					request.getParameter("DescripcionSubtema"),request.getParameter("DescripcionTema"));
			listaRespuestasIncorrectas.clear();
			response.sendRedirect("Preguntas3.jsp");
		}
		else if(request.getParameter("eliminarPregunta")!=null){
			DtoPregunta dtoPregunta = new DtoPregunta();
			dtoPregunta.setTema(request.getParameter("tema"));
			dtoPregunta.setSubtema(request.getParameter("subtema"));
				dtoPregunta.setDescripcionPregunta(request.getParameter("descripcion"));
				dtoPregunta.setPregunta(request.getParameter("pregunta"));
				
				pregunta.eliminarPregunta(dtoPregunta);
				response.sendRedirect("Preguntas3.jsp");
			
		}else if(request.getParameter("modificarPregunta")!=null){
			pasarPregunta.setDescripcionPregunta(request.getParameter("descripcion"));
			pasarPregunta.setPregunta(request.getParameter("pregunta"));
			pasarPregunta.setTema(request.getParameter("tema"));
			pasarPregunta.setSubtema(request.getParameter("subtema"));
			DtoPregunta datos = pregunta.getDatosPregunta(pasarPregunta);
			
			if (request.getParameter("decripcion")=="Selección Única"){
				response.sendRedirect("../ActualizarPreguntaSU.jsp?pregunta="+request.getParameter("pregunta")+"&descripcion="+
						request.getParameter("descripcion")+"&ayuda="+datos.getDescripcionAyuda()+
						"&respuesta="+datos.getRespuestaCorrecta());
			}else{
				response.sendRedirect("../ActualizarPreguntas.jsp?pregunta="+request.getParameter("pregunta")+"&descripcion="+
						request.getParameter("descripcion")+"&ayuda="+datos.getDescripcionAyuda()+
						"&respuesta="+datos.getRespuestaCorrecta());				
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoPregunta pregunta=new DaoPregunta();
		
		if(request.getParameter("agregarPregunta")!=null) {			
			String tema = request.getParameter("tema");
			String subtema = request.getParameter("subtema");
			String descripcion = request.getParameter("descripcion");
			String pPregunta = request.getParameter("pregunta");
			String evaluacion = request.getParameter("nombreEvaluacion");
			String codigo = request.getParameter("codigoCurso");
			pregunta.agregarPreguntaEvaluacion(tema, subtema, descripcion, pPregunta, evaluacion, codigo);
		
		}else if(request.getParameter("agregarPreguntaEvaluacion")!=null){
			String curso = request.getParameter("codigoCurso"); 
			String evaluacion = request.getParameter("nombreEvaluacion"); 
			String tema = request.getParameter("tema"); 
			String subtema = request.getParameter("subtema"); 
			String descripcion = request.getParameter("descripcion"); 
			String pPregunta = request.getParameter("pregunta"); 
			pregunta.agregarPreguntaEvaluacion(tema, subtema, descripcion, pPregunta, evaluacion, curso);
			
		}else if(request.getParameter("parar")!=null){
			response.sendRedirect("MenuPrincipal.jsp");
		}
		else if(request.getParameter("actualizacionPregunta")!=null){
			DtoPregunta dtoPregunta = new DtoPregunta();
			
			dtoPregunta.setTema(request.getParameter("tema"));
			dtoPregunta.setSubtema(request.getParameter("subtema"));
			dtoPregunta.setDescripcionPregunta(request.getParameter("descripcion"));
			dtoPregunta.setPregunta(request.getParameter("pregunta"));
			dtoPregunta.setDescripcionAyuda(request.getParameter("ayuda"));
			dtoPregunta.setRespuestaCorrecta(request.getParameter("respuesta"));
			
			pregunta.actualizarPregunta(dtoPregunta,request.getParameter("preguntaOriginal"));
			response.sendRedirect("Preguntas3.jsp");
		}
	}
}