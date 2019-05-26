package aiss.model.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.resource.GoogleDriveResource;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasGoogleDrive {
	private final String access_token = "ya29.GlsVBzMNX011_0YahDbOQMhVX6_lxfKZCPWxk3cg_zYDgTEMbmJyviIsBW0gnkCONe2sHMVBM5tmxIJg60vxXnFRiyhzd-1iIR_1H3OnwFo6ToWwViR_7My2uTtT";
	GoogleDriveResource gdrResource = new GoogleDriveResource(access_token);

	@Test
	//Coger files
	public void testA() {
		Files aux = gdrResource.getFiles();
		List<FileItem> res = aux.getItems();
		if(res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente: " + res);
		}

		assertNotNull("La lista de archivos no puede ser null", res);
	}
	@Test
	//Insertar archivo
	public void testB() {
		FileItem test = new FileItem();
		String res = gdrResource.insertarArchivo(test);
		
		if(res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Creado Correctamente");
		}

		assertNotNull("El archivo no puede ser null", res);
	}

	@Test
	public void testC()
	{
		FileItem test = new FileItem();
		String fileId = gdrResource.insertarArchivo(test);
		
		test.setMimeType("application/vnd.google-apps.spreadsheet");
		
		Boolean updateFile = gdrResource.updateFile(test);
		String res = fileId;
		
		if(!updateFile) {
			res = null;
		}
		if (res != null) {
			System.out.println("Actualizado Correctamente el archivo con id: " + res);
		}

		assertNotNull("El archivo no puede ser null", res);
	}
	

	@Test
	//Borrar Archivo
	public void testD() {
		
		FileItem test = new FileItem();
		String newFileId = gdrResource.insertarArchivo(test);
		
		String fileId = newFileId;
		
		Boolean res = gdrResource.borrarFile(fileId);
		
		if(res) {
			System.out.println("Borrado correctamente el archivo con id: " + fileId);
		}
		

		assertTrue("No se ha borrado correctamente", res);
	}
}
