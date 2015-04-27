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
	
	@Test
	public void testShowGeoJson()
	{
		System.out.print("GET "+URI_TAXON+": ");
		Result result = route(fakeRequest(GET, URI_TAXON));
		assertThat(result).isNull();
		System.out.println("N'existe pas");
		
		System.out.print("GET "+URI_TAXON_1_GEOJSON+": ");
		result = route(fakeRequest(GET, URI_TAXON_1_GEOJSON));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		System.out.println("La ressource n'existe pas");
		
		System.out.print("GET "+URI_TAXON_1_GEOJSON+": ");
		assertThat(contentType(result)).isEqualTo("application/json");
		System.out.println("C'est quand même du json");
		
		System.out.print("GET "+URI_TAXON_2018_GEOJSON+": ");
		result = route(fakeRequest(GET, URI_TAXON_2018_GEOJSON));
		assertThat(status(result)).isEqualTo(OK);
		System.out.println("La ressource existe");
		
		System.out.print("GET "+URI_TAXON_2018_GEOJSON+": ");
		assertThat(contentType(result)).isEqualTo("application/json");
		System.out.println("C'est du json");
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
		System.out.print("GET "+URI_TAXON_1_INFO+": ");
		Result result = route(fakeRequest(GET, URI_TAXON_1_INFO));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		System.out.println("La ressource n'existe pas");
		
		System.out.print("GET "+URI_TAXON_2018_INFO+": ");
		result = route(fakeRequest(GET, URI_TAXON_2018_INFO));
		assertThat(status(result)).isEqualTo(OK);
		System.out.println("La ressource existe");
		
		System.out.print("GET "+URI_TAXON_2018_INFO+": ");
		assertThat(contentType(result)).isEqualTo("application/json");
		System.out.println("C'est du json");
	}
}
