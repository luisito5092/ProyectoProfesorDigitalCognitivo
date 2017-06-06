package logicaDeNegocios;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import logicaDeNegocios.dto.DtoBitacora;


public class BitacoraXML extends Bitacora {
	private Document doc;
	private SAXBuilder builder;
	
	//************************************** CONSTRUCTOR *****************************************
	public BitacoraXML(String pRuta){
		super(pRuta);
		builder = new SAXBuilder();
		
	}

	
	public void realizarRegistro(String email, String descripcion,String codigo){
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
		Date date = new Date();
		try {
			if(!getArchivo().exists()){
				Element registros = new Element("registros");
				doc = new Document();
				doc.setRootElement(registros);
			}
			else{
				doc = (Document) builder.build(archivo);
			}
			
			Element accion = new Element("descripcion");
			accion.addContent(new Element("fecha").setText(dateFormat.format(date).toString()));
			accion.addContent(new Element("correoProfesor").setText(email));
			accion.addContent(new Element("descripcion").setText(descripcion));
			accion.addContent(new Element("codigoCurso").setText(codigo));

			doc.getRootElement().addContent(accion);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(getRuta()));
			} 
		catch (Exception e) {
		}
	}
	
	public ArrayList<DtoBitacora> leerRegistro(){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		try{
			doc = (Document) builder.build(archivo);
			Element raiz = doc.getRootElement();
			List<Element> descripcionesEncontradas = raiz.getChildren("accion");
			for(int descripcion = 0; descripcion < descripcionesEncontradas.size(); descripcion++){
				DtoBitacora dto=new DtoBitacora();	
				Element descrip = (Element) descripcionesEncontradas.get(descripcion);
				dto.setFecha(descrip.getChildText("fecha"));
				dto.setCorreoProfesor(descrip.getChildText("correoProfesor"));
				dto.setDescripcion(descrip.getChildText("descripcion"));
				dto.setDescripcion(descrip.getChildText("codigoCurso"));
				listaBitacoras.add(dto);
			}
		}
		catch(Exception e){

		}
		return listaBitacoras;
	}
		
}
