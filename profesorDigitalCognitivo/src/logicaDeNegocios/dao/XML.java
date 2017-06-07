package logicaDeNegocios.dao;

import java.io.File;
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

import com.csvreader.CsvReader;

import logicaDeNegocios.dto.DtoBitacora;


public class XML {
	private Document doc;
	private String direccion;
	private File archivoXML;
	private SAXBuilder builder;
	
	public XML(String pDireccion){
		setDireccion(pDireccion);
		setArchivoXML();
		builder = new SAXBuilder();
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String pDireccion) {
		this.direccion = pDireccion;
	}

	public File getArchivoXML() {
		return archivoXML;
	}

	public void setArchivoXML() {
		this.archivoXML = new File(getDireccion());
	}
	
	public void generarRegistroXML(String email, String descripcion,String codigo){
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
		Date date = new Date();
		try {
			if(!getArchivoXML().exists()){
				Element registros = new Element("registros");
				doc = new Document();
				doc.setRootElement(registros);
			}
			else{
				doc = (Document) builder.build(archivoXML);
			}
			
			Element accion = new Element("descripcion");
			accion.addContent(new Element("fecha").setText(dateFormat.format(date).toString()));
			accion.addContent(new Element("correoProfesor").setText(email));
			accion.addContent(new Element("descripcion").setText(descripcion));
			accion.addContent(new Element("codigoCurso").setText(codigo));

			doc.getRootElement().addContent(accion);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(getDireccion()));
			} 
		catch (Exception e) {
		}
	}
	
	public ArrayList<DtoBitacora> leerRegistroXML(){
		ArrayList<DtoBitacora> listaBitacoras=new ArrayList<DtoBitacora>();
		try{
			doc = (Document) builder.build(archivoXML);
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
