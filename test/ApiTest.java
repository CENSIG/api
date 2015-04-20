
import org.junit.*;

import play.mvc.Result;
import play.test.WithApplication;
import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;


/**
*
* Simple (JUnit) tests
* @author Jean BOUDET
*
*/
public class ApiTest extends WithApplication {
	
	/**
	 * Fake URI
	 */
	@Test
    public void testInit() 
	{
		Result result = route(fakeRequest(GET, "/xx/Kiki"));
	    assertThat(result).isNull();
    }
	
	/**
	 * Fake URI for api
	 */
	@Test
    public void testUri() 
	{
		Result result = route(fakeRequest(POST, "/apiv1/"));
	    assertThat(result).isNull();
    }
}
