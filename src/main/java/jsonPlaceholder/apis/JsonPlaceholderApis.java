package jsonPlaceholder.apis;

public class JsonPlaceholderApis {
//    private ApiActions apiObject;
    public static final String BASE_URL = System.getProperty("baseUrl");

    // Expected status codes
    protected enum Status {
	SUCCESS(200), SUCCESS_CREATE(201);

	private int code;

	Status(int code) {
	    this.code = code;
	}

	protected int getCode() {
	    return code;
	}
    }

    // Services Names

    // Constructor
//    public JsonPlaceholderApis(ApiActions apiObject) {
//	this.apiObject = apiObject;
//    }
    
    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    // Generals APIs here that are applicable with all test cases like Login/Logout/etc...
}
