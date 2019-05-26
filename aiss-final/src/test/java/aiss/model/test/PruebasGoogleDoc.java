package aiss.model.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import aiss.model.google.doc.Document;
import aiss.model.google.drive.FileItem;
import aiss.model.resource.GoogleDocResource;
import aiss.model.resource.GoogleDriveResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasGoogleDoc {
	private final String access_tokenGD = "ya29.GlsVB4ElOL1VModKGSCC8kBE_BWy0F3kdgcJ-H-Q4AMoKz-zf-EIzH8XDzS5Tb96_t3tfj0AboMxlVVGkVbQl9zh1cJuYjZ5RmcycqstUpDXQ_WrPD_Dz0EST8Wm";
	private final String access_tokenGDr = "ya29.GlsVBzMNX011_0YahDbOQMhVX6_lxfKZCPWxk3cg_zYDgTEMbmJyviIsBW0gnkCONe2sHMVBM5tmxIJg60vxXnFRiyhzd-1iIR_1H3OnwFo6ToWwViR_7My2uTtT";
	GoogleDocResource gdcResource = new GoogleDocResource(access_tokenGD);
	GoogleDriveResource gdrResource = new GoogleDriveResource(access_tokenGDr);
	@Test
	//Open doc
	public void testA() {
		FileItem file = new FileItem();
		file.setMimeType("application/vnd.google-apps.document");
		String fileId = gdrResource.insertarArchivo(file);
		
		String id = fileId;
		Document aux = gdcResource.getDocs(id);
		
		String  res = aux.getDocumentId();
		if(res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado correctamente el documento con id: " + id);
		}

		assertNotNull("El id del documento no puede ser null", res);
	}
	
}

