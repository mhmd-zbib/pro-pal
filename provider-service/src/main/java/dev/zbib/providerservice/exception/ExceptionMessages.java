package dev.zbib.providerservice.exception;

public class ExceptionMessages {
    public static final String USER_NOT_FOUND = "There is no account associated with this id";
    public static final String PROVIDER_NOT_FOUND = "Provider not found";
    public static final String BIO_REQUIRED = "Bio is required";
    public static final String BIO_SIZE = "Bio must be between 10 and 500 characters";
    public static final String SERVICE_TYPE_REQUIRED = "Service type is required";
    public static final String MIN_HOURLY_RATE = "Hourly rate must be at least 0.0";
    public static final String SERVICE_AREA_REQUIRED = "Service area is required";
    public static final String ALREADY_EXIST = "You are already a provider";
}
