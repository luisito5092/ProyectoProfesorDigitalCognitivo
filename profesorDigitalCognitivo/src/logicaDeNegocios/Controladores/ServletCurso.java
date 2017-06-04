package logicaDeNegocios.Controladores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
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
    		response.sendRedirect("SeleccionBitacora.jsp");
    		
    	}else if(request.getParameter("ConsultarBitacoras")!=null){
    		if(request.getParameter("comboboxBitacora").equals("CSV")){
    			response.sendRedirect("CSV.jsp");
    			
    		}else if(request.getParameter("comboboxBitacora").equals("XML")){
    			
    			response.setContentType("application/xml");
    			ServletOutputStream out= response.getOutputStream();
    			
    			try {

    				Element bitacora = new Element("bitacora");
    				Document doc = new Document(bitacora);
    				doc.setRootElement(bitacora);

    				Element staff = new Element("staff");
    				staff.setAttribute(new Attribute("id", "1"));
    				staff.addContent(new Element("firstname").setText("yong"));
    				staff.addContent(new Element("lastname").setText("mook kim"));
    				staff.addContent(new Element("nickname").setText("mkyong"));
    				staff.addContent(new Element("salary").setText("199999"));

    				doc.getRootElement().addContent(staff);

    				Element staff2 = new Element("staff");
    				staff2.setAttribute(new Attribute("id", "2"));
    				staff2.addContent(new Element("firstname").setText("low"));
    				staff2.addContent(new Element("lastname").setText("yin fong"));
    				staff2.addContent(new Element("nickname").setText("fong fong"));
    				staff2.addContent(new Element("salary").setText("188888"));

    				doc.getRootElement().addContent(staff2);

    				XMLOutputter xmlOutput = new XMLOutputter();

    				// display nice nice
    				xmlOutput.setFormat(Format.getPrettyFormat());
    				xmlOutput.output(doc, out);

    			  } catch (IOException io) {
    				
    			  }
    		}else if(request.getParameter("comboboxBitacora").equals("Posicional")){
    			response.sendRedirect("");
    		}else{
    			
    		}
    		
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