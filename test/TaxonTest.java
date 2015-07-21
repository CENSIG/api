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

/**
 * Taxon unit test
 * @author Jean BOUDET
 *
 */

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
	
	private static final String URI_TAXON_2018_PARENTS_OR = API + "/taxon/2018/parents?limit=OR";
	
	private static final String URI_TAXON_2018_PARENTS_2 = API + "/taxon/2018/parents?limit=2";
	
	private static final String URI_TAXON_185214_CHILDS = API + "/taxon/185214/childs";
	
	private static final String URI_TAXON_185214_CHILDS_2 = API + "/taxon/185214/childs?q=2";
	
	private static final String URI_TAXON_185214_CHILDS_MACU = API + "/taxon/185214/childs?q=macu";
	
	private static final String URI_TAXON_1_BROTHERS = API + "/taxon/1/brothers";
	
	private static final String URI_TAXON_2018_BROTHERS = API + "/taxon/2018/brothers";
	
	private static final String URI_TAXON_1_FIRST_CHILD = API + "/taxon/1/first_child_obs";
	
	private static final String URI_TAXON_2018_FIRST_CHILD = API + "/taxon/2018/first_child_obs";
	
	private static final String URI_TAXON_185214_FIRST_CHILD= API + "/taxon/185214/first_child_obs";
	
	private static final String URI_TAXON_185214_PHOTOS= API + "/taxon/185214/photos";
	
	
	
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
		
		result = route(fakeRequest(GET, URI_TAXON_2018_PARENTS_2));
		assertThat(status(result)).isEqualTo(422);
		
		result = route(fakeRequest(GET, URI_TAXON_2018_PARENTS_OR));
		assertThat(status(result)).isEqualTo(OK);
		
	}
	
	@Test
	public void testShowParentsContent()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_2018_PARENTS));
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.isArray()).isTrue();
		assertThat(json.get(0).has("cdnom")).isTrue();
		
		result = route(fakeRequest(GET, URI_TAXON_2018_PARENTS_OR));
		json = Json.parse(contentAsString(result));
		assertThat(json.isArray()).isTrue();
		assertThat(json.get(0).get("rang").asText()).isEqualTo("OR");
	}
	
	@Test
	public void testShowChilds()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_185214_CHILDS));
		assertThat(status(result)).isEqualTo(422);
		
		result = route(fakeRequest(GET, URI_TAXON_185214_CHILDS_2));
		assertThat(status(result)).isEqualTo(422);
		
		result = route(fakeRequest(GET, URI_TAXON_185214_CHILDS_MACU));
		assertThat(status(result)).isEqualTo(OK);
		assertThat(contentType(result)).isEqualTo("application/json");
	}
	
	@Test
	public void testShowChildsContent()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_185214_CHILDS_MACU));
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.isArray()).isTrue();
		JsonNode first = json.get(0);
		assertThat(first.has("cdnom")).isTrue();
		assertThat(first.has("name")).isTrue();
		assertThat(first.has("isref")).isTrue();
		assertThat(first.has("observations")).isTrue();
		assertThat(first.has("troll")).isFalse();
	}
	
	@Test
	public void testShowBrothers()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_1_BROTHERS));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		
		result = route(fakeRequest(GET, URI_TAXON_2018_BROTHERS));
		assertThat(status(result)).isEqualTo(OK);
		
		assertThat(contentType(result)).isEqualTo("application/json");
	}
	
	@Test
	public void testShowBrothersContent()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_2018_BROTHERS));
		JsonNode json = Json.parse(contentAsString(result));
		assertThat(json.isArray()).isTrue();
		JsonNode first = json.get(0);
		assertThat(first.has("cdnom")).isTrue();
	}
	
	@Test 
	public void testShowFirstChildObs()
	{
		Result result = route(fakeRequest(GET, URI_TAXON_1_FIRST_CHILD));
		assertThat(status(result)).isEqualTo(NOT_FOUND);
		
	    result = route(fakeRequest(GET, URI_TAXON_2018_FIRST_CHILD));
	    assertThat(status(result)).isEqualTo(NOT_FOUND);
	    
	    result = route(fakeRequest(GET, URI_TAXON_185214_FIRST_CHILD));
	    assertThat(status(result)).isEqualTo(OK);
	    
	}
	
	@Test
	public void testShowPhoto()
	{
		 Result result = route(fakeRequest(GET, URI_TAXON_185214_PHOTOS));
		 assertThat(status(result)).isEqualTo(OK);
		 
		 assertThat(contentType(result)).isEqualTo("application/json");
	}
}
