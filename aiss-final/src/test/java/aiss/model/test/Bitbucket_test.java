package aiss.model.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import aiss.model.bitbucket.Files;
import aiss.model.bitbucket.Repos;
import aiss.model.bitbucket.ReposItem;
import aiss.model.resource.BitbucketResource;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Bitbucket_test {
	private final String access_token = "SBbs9L1PQdGC89Z5mpq9U86VfSXYmP8JqKqnledIj4b4HzasychYyv05XdS17ETl";
	BitbucketResource bbResource = new BitbucketResource(access_token);
	@Test
	public void testA() throws UnsupportedEncodingException {
		String dir = null;
		String repo = null;
		String commitId = null;
		Files res = bbResource.getDirectoryContent(dir, commitId, repo);
	
		if (res.getValues().isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Encontrado Correctamente");
		}

		assertNotNull("El directorio no puede estar vacio", res);
	}
	@Test
	public void testB() throws UnsupportedEncodingException {
		String reponame = "test1";
		ReposItem repo = bbResource.postRepos(reponame);
		Boolean res = repo.getName().isEmpty();
		if (res) {
			assertTrue("El repo no se puede crear con un nombre nulo", res);
		
		}else {
			System.out.println("Posteado Correctamente");
		}
	}
	@Test
	public void testC() throws UnsupportedEncodingException{
		String reponame = "test1";
		Boolean res = bbResource.deleteRepos(reponame);
		if(res) {
			System.out.println("Borrado Correctamente");
		}else {
			assertTrue("El repositorio no se eliminó correctamente", res);
		}
		
		
	}
}
