import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.Result;
import play.test.WithApplication;


public class GridTest extends WithApplication 
{
	private static final String API = "/apiv1";
	
	private static final String URI_GRID = API + "/grid";
	
	private static final String URI_GRID_11 = API + "/grid/11";
	
	private static final String URI_GRID_10 = API + "/grid/10";

	@Test
	public void testShowGeoJson()
	{
		System.out.print("GET "+URI_GRID+": ");
		Result result = route(fakeRequest(GET, URI_GRID));
		assertThat(result).isNull();
		System.out.println("N'existe pas");
		
		System.out.print("GET "+URI_GRID_11+": ");
		result = route(fakeRequest(GET, URI_GRID_11));
		assertThat(result).isNull();
		System.out.println("La ressource n'existe pas");
	}
	
	@Test
	public void testShowGeoJsonContent()
	{
		Result result = route(fakeRequest(GET, URI_GRID_10));
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.has("type")).isTrue();
		assertThat(json.get("type").asText()).isEqualTo("GeometryCollection");
		assertThat(json.has("geometries")).isTrue();
	}
}
