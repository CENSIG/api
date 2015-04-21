import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

import org.junit.Test;

import play.mvc.Result;
import play.test.WithApplication;


/**
 * 
 * Test atlas controller action
 * @author Jean BOUDET
 *
 */
public class AtlasTest extends WithApplication 
{
	// Main uri
	private static final String API = "/apiv1";
	
	private static final String URI_PAPILLONS = API + "/papillons";
	
	private static final String URI_PAPILLON_INFO = API + "/papillon/info";
	
	private static final String URI_PAPILLONS_INF = API + "/papillons/inf";
	
	private static final String URI_PAPILLONS_INFO = API + "/papillons/info";

	private static final String URI_PAPILLON_53524 = API + "/papillon/53524";

	private static final String URI_PAPILLONS_1 = API + "/papillons/1";
			
	/**
	 * Fake URI
	 */
	@Test
    public void testInit() 
	{
		Result result = route(fakeRequest(GET, URI_PAPILLONS));
	    assertThat(result).isNull();
    }
	
	/**
	 * Test info action
	 */
	@Test
	public void testInfo()
	{
		// Not found because expected papillons not papillon
		System.out.print("GET "+URI_PAPILLON_INFO+": ");
		Result result = route(fakeRequest(GET, URI_PAPILLON_INFO));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		System.out.println("N'existe pas");
		
		// Bad request because papillons/inf match other route and inf
		// is not number
		System.out.print("GET "+URI_PAPILLONS_INF+": ");
		result = route(fakeRequest(GET, URI_PAPILLONS_INF));
		assertThat(status(result)).isEqualTo(BAD_REQUEST);
		System.out.println("Un id est attendu");
		
		// Check status code
		System.out.print("GET "+URI_PAPILLONS_INFO+": ");
		result = route(fakeRequest(GET, URI_PAPILLONS_INFO));
		assertThat(status(result)).isEqualTo(OK);
		System.out.println("Existe");
		
		// Check content-type
		System.out.print("Content-type: ");
		assertThat(contentType(result)).isEqualTo("application/json");
		System.out.println("OK");

	}
	
	/**
	 * Test show action
	 */
	@Test
	public void testShow()
	{
		// Not found because expected papillons not papillon
		System.out.print("GET "+URI_PAPILLON_53524+": ");
		Result result = route(fakeRequest(GET, URI_PAPILLON_53524));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		System.out.println("N'existe pas");
		
		// Not found because papillons with id 1 not exist
		System.out.print("GET "+URI_PAPILLONS_1+": ");
		result = route(fakeRequest(GET, URI_PAPILLONS_1));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		System.out.println("N'existe pas");
	}
}
