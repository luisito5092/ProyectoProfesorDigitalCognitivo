package logicaDeNegocios.Controladores;



import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

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
    			DaoBitacora dao=new DaoBitacora();
    			String inicio=request.getParameter("fechaInicio");
    			String finall=request.getParameter("fechaFinal");
    			String correo=request.getParameter("correoProfesor");
    			DtoBitacora bitacorActual=new DtoBitacora(); 
    			
    			try {

    				Element bitacora = new Element("bitacora");
    				Document doc = new Document(bitacora);
    				doc.setRootElement(bitacora);
    				
    				try {
    					Element pCurso = new Element("pCurso");
    					pCurso.setAttribute(new Attribute("id", "1"));
    					pCurso.addContent(new Element("firstname").setText("yong"));
    					pCurso.addContent(new Element("lastname").setText("mook kim"));
    					pCurso.addContent(new Element("nickname").setText("mkyong"));
    					pCurso.addContent(new Element("salary").setText("199999"));

    					doc.getRootElement().addContent(pCurso);
    					
						for(int i=0;i<dao.consultarBitacoras(inicio, finall,correo).size();i++){
							
							Element pCurso2 = new Element("curso");
							pCurso2.setAttribute(new Attribute("codigo", dao.consultarBitacoras(inicio, finall,correo).get(i).getCodigoCurso()));
							pCurso2.addContent(new Element("fecha").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getFecha()));
							pCurso2.addContent(new Element("descripción").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getDescripcion()));
							pCurso2.addContent(new Element("emailProfesor").setText(dao.consultarBitacoras(inicio, finall,correo).get(i).getCorreoProfesor()));
							doc.getRootElement().addContent(pCurso2);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

    				XMLOutputter xmlOutput = new XMLOutputter();

    				xmlOutput.setFormat(Format.getPrettyFormat());
    				xmlOutput.output(doc, out);

    			  } finally{
    					out.close();
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
		TXT cambiosHechos=new TXT("");
		DtoBitacora dto=new DtoBitacora();
		DtoCurso curso= new DtoCurso();
		curso.setCodigo(request.getParameter("CodigoCurso"));
		curso.setDescripcion(request.getParameter("Descripcion"));
		
		FabricaCurso fabrica=new FabricaCurso();
		Curso cursoFabricado= fabrica.fabricarCurso(curso);
		
		if(request.getParameter("eliminar")!=null){
			//String ruta = System.getProperty("user.home")+ File.separator + "Desktop" + File.separator + "Bitacora.txt";
			//File home = FileSystemView.getFileSystemView().getHomeDirectory();	
			 //PrintWriter out = response.getWriter();
			    //out.println(home.getAbsolutePath());
			    //out.close();
			dto.setCodigoCurso(cursoFabricado.getCodigo());
			dto.setCorreoProfesor(request.getParameter("correoProfesor"));
			dto.setDescripcion("Se ha eliminado el curso "+cursoFabricado.getCodigo());
			TXT txt=new TXT("C:\\Users\\luisj_000\\Desktop\\Bitacora.txt");
			XML xml =new XML("C:\\Users\\luisj_000\\Desktop\\Bitacora.xml");
			CSV csv=new CSV("C:\\Users\\luisj_000\\Desktop\\Bitacora.csv");
			xml.generarRegistroXML(dto.getCorreoProfesor(),dto.getDescripcion(), dto.getCodigoCurso());
			txt.registrarBitacoraTXT(dto.getCorreoProfesor(),dto.getDescripcion(), dto.getCodigoCurso());
			csv.realizarRegistroCSV(dto.getCorreoProfesor(),dto.getDescripcion(), dto.getCodigoCurso());
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