import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.*;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.status;
import static play.test.Helpers.contentAsString;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.mvc.Result;
import play.test.WithApplication;


public class TaxonTest extends WithApplication 
{
	private static final String API = "/apiv1";
	
	private static final String URI_TAXON = API + "/taxon";
	
	private static final String URI_TAXON_1_GEOJSON = API + "/taxon/1/geojson";
	
	private static final String URI_TAXON_2018_GEOJSON = API + "/taxon/2018/geojson";
	
	private static final String URI_TAXON_1_INFO = API + "/taxon/1/informations";
	
	private static final String URI_TAXON_2018_INFO = API + "/taxon/2018/informations";
	
	private static final String URI_TAXON_1_PARENTS = API + "/taxon/1/parents";
	
	private static final String URI_TAXON_2018_PARENTS = API + "/taxon/2018/parents";
	
	@Test
	public void testShowGeoJson()
	{
		Result result = route(fakeRequest(GET, URI_TAXON));
		assertThat(result).isNull();
		
		result = route(fakeRequest(GET, URI_TAXON_1_GEOJSON));
		assertThat(status(result)).isEqualTo(OK);
		
		assertThat(contentType(result)).isEqualTo("application/json");
		
		result = route(fakeRequest(GET, URI_TAXON_2018_GEOJSON));
		assertThat(status(result)).isEqualTo(OK);
		
		assertThat(contentType(result)).isEqualTo("application/json");
	}
	
	@Test
	public void testShowGeoJsonContent()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_2018_GEOJSON));
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.has("type")).isTrue();
		assertThat(json.get("type").asText()).isEqualTo("FeatureCollection");
		assertThat(json.has("features")).isTrue();
	}
	
	@Test
	public void testShowInformations()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_1_INFO));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		
		result = route(fakeRequest(GET, URI_TAXON_2018_INFO));
		assertThat(status(result)).isEqualTo(OK);
		
		assertThat(contentType(result)).isEqualTo("application/json");
	}
	
	@Test
	public void testShowParents()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_1_PARENTS));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		
		result = route(fakeRequest(GET, URI_TAXON_2018_PARENTS));
		assertThat(status(result)).isEqualTo(OK);
		
		assertThat(contentType(result)).isEqualTo("application/json");
		
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.isArray()).isTrue();
		assertThat(json.get(0).has("cdnom")).isTrue();
	}
}
