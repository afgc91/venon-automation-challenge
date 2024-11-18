package utils;

public class Constants {
    //API methods
    public static final String POST = "POST";
    public static final String PATCH = "PATCH";
    public static final String OPTIONS = "OPTIONS";

    //API response codes
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    public static final int METHOD_NOT_ALLOWED = 405;

    //Negative test cases data
    public static final int NON_EXISTENT_PET_ID = 0;
    public static final String INVALID_PET_ID = "abc";

    //API parameters
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHOTO_URLS = "photoUrls";

    //API plain text responses
    public static final String PET_DELETED = "Pet deleted";

    //Negative assert messages
    public static final String GENERAL_NEGATIVE_ASSERT_MESSAGE = "Wrong response message";
    public static final String NEGATIVE_METHOD_NOT_ALLOWED_MESSAGE = "405 response code was expected for method ";
}
