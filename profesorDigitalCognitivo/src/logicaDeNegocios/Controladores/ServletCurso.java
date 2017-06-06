package logicaDeNegocios.Controladores;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

import logicaDeNegocios.Bitacora;
import logicaDeNegocios.BitacoraCSV;
import logicaDeNegocios.BitacoraTXT;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.Curso;
import logicaDeNegocios.dao.CSV;
import logicaDeNegocios.dao.DaoBitacora;
import logicaDeNegocios.dao.DaoCurso;
import logicaDeNegocios.dao.DaoProfesor;

import logicaDeNegocios.dao.TXT;
import logicaDeNegocios.dao.XML;
import logicaDeNegocios.dto.DtoBitacora;
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
    	DaoBitacora cambiosHechos=new DaoBitacora();
    	DtoBitacora dto=new DtoBitacora();
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
    		dto.setCodigoCurso(request.getParameter("codigo"));
			dto.setCorreoProfesor(request.getParameter("correoProfesor"));
			dto.setDescripcion("Se ha editado los datos del curso "+request.getParameter("codigo"));
			cambiosHechos.registrarBitacora(dto);
    		cursoFabricado.actualizarCurso(curso, request.getParameter("codigoOriginal"));
    		response.sendRedirect("../verCurso.jsp?codigo="+request.getParameter("codigoOriginal")+"&descripcion="+
    							request.getParameter("descripcion"));
    		
    	}else if(request.getParameter("listaDeTemas")!=null){
    		response.sendRedirect("EstudiantesCurso.jsp");
    		
    	}else if(request.getParameter("VentanaBitacoras")!=null){
    		response.sendRedirect("SeleccionBitacora.jsp");
    	
    		
    		//*****************************************************CSV*********************************************************/
    	}else if(request.getParameter("ConsultarBitacoras")!=null){
    		if(request.getParameter("comboboxBitacora").equals("CSV")){
    			HttpSession session = request.getSession(true);
    			session.setAttribute("Fecha11",request.getParameter("fechaInicio").toString());
    			session.setAttribute("Fecha22",request.getParameter("fechaFinal").toString());
        		response.sendRedirect("CSV.jsp");   			
    			
    		//**************************************XML************************************************/
    		}else if(request.getParameter("comboboxBitacora").equals("XML")){
    			   			
    			response.setContentType("application/xml");
    			ServletOutputStream out= response.getOutputStream();
    			DaoBitacora dao=new DaoBitacora();
    			String inicio=request.getParameter("fechaInicio").toString();
    			String finall=request.getParameter("fechaFinal").toString();
    			String correo=request.getParameter("correoProfesor");
    	
    			XML xml=new XML(System.getProperty("user.home")+"/Bitacora.txt");
    			try {

    				Element bitacora = new Element("bitacora");
    				Document doc = new Document(bitacora);
    				doc.setRootElement(bitacora);
    				
    				try {
						for(int i=0;i<dao.consultarBitacoras(inicio, finall,correo).size();i++){
							
							Element pCurso2 = new Element("curso");
							pCurso2.setAttribute(new Attribute("codigo", dao.consultarBitacoras(inicio, finall,correo).get(i).getCodigoCurso()));
							pCurso2.addContent(new Element("fecha").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getFecha()));
							pCurso2.addContent(new Element("descripción").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getDescripcion()));
							pCurso2.addContent(new Element("emailProfesor").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getCorreoProfesor()));
							doc.getRootElement().addContent(pCurso2);
							dto=dao.consultarBitacoras(inicio, finall,correo).get(i);
							xml.generarRegistroXML(dto.getCorreoProfesor(),dto.getDescripcion(), dto.getCodigoCurso());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

    				XMLOutputter xmlOutput = new XMLOutputter();

    				xmlOutput.setFormat(Format.getPrettyFormat());
    				xmlOutput.output(doc, out);
    				
    				/*
    				response.setHeader("Content-Disposition","attachment;filename=Bitacora.txt");
        			FileInputStream in = new FileInputStream(System.getProperty("user.home")+"/Bitacora.txt");
        			byte[] buffer = new byte[4096];
        			int length;
        			while ((length = in.read(buffer)) > 0){
        			    out.write(buffer, 0, length);
        			}
        			in.close();
        			out.flush();*/

    			  } finally{
    					out.close();
    			}
    			
    		//*****************************************************TXT*********************************************************/
    		}else if(request.getParameter("comboboxBitacora").equals("Posicional")){
    			HttpSession session = request.getSession(true);
    			session.setAttribute("Fecha11",request.getParameter("fechaInicio").toString());
    			session.setAttribute("Fecha22",request.getParameter("fechaFinal").toString());
        		response.sendRedirect("Posicional.jsp");
    		}}
    	else if(request.getParameter("DescargarCSV")!=null){
			response.setContentType("application/csv");
			response.setHeader("Content-Disposition","attachment;filename=Bitacora.csv");
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(System.getProperty("user.home")+"/Bitacora.csv");
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			    out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
    	}else if(request.getParameter("DescargarTXT")!=null){			
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition","attachment;filename=Bitacora.txt");
			ServletOutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(System.getProperty("user.home")+"/Bitacora.txt");
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			    out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
    		}
    		
    	}
    
    

	/**
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DtoCurso curso= new DtoCurso();
		curso.setCodigo(request.getParameter("CodigoCurso"));
		curso.setDescripcion(request.getParameter("Descripcion"));
		
		FabricaCurso fabrica=new FabricaCurso();
		Curso cursoFabricado= fabrica.fabricarCurso(curso);
		
		if(request.getParameter("eliminar")!=null){
			Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".csv");
			Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".xml");
			Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("correoProfesor")+".txt");
			csv.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el curso: "+request.getParameter("CodigoCurso"), request.getParameter("CodigoCurso"));
			xml.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el curso: "+request.getParameter("CodigoCurso"), request.getParameter("CodigoCurso"));
			txt.realizarRegistro(request.getParameter("correoProfesor"), "Se ha eliminado el curso: "+request.getParameter("CodigoCurso"), request.getParameter("CodigoCurso"));
			cursoFabricado.eliminarCurso(cursoFabricado.getCodigo());
			response.sendRedirect("MisCursos.jsp");
			
		}else if(request.getParameter("asignarProfe")!=null){
			Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+request.getParameter("SesionActual")+".csv");
			Bitacora xml=new BitacoraXML(System.getProperty("user.home")+"/Bitacora"+request.getParameter("SesionActual")+".xml");
			Bitacora txt=new BitacoraTXT(System.getProperty("user.home")+"/Bitacora"+request.getParameter("SesionActual")+".txt");
			csv.realizarRegistro(request.getParameter("SesionActual"), "Se ha asignado el curso: "+request.getParameter("CodigoCursoSinAsignar")+" al profesor "+request.getParameter("SesionActual"), request.getParameter("CodigoCursoSinAsignar"));
			xml.realizarRegistro(request.getParameter("SesionActual"), "Se ha asignado el curso: "+request.getParameter("CodigoCursoSinAsignar")+" al profesor "+request.getParameter("SesionActual"), request.getParameter("CodigoCursoSinAsignar"));
			txt.realizarRegistro(request.getParameter("SesionActual"), "Se ha asignado el curso: "+request.getParameter("CodigoCursoSinAsignar")+" al profesor "+request.getParameter("SesionActual"), request.getParameter("CodigoCursoSinAsignar"));
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