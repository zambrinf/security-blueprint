package br.com.zn43.security_blueprint.security.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String JWT_SECRET_KEY = "MYSECRET";

    public static final Integer JWT_EXPIRATION = 36000000;

    public static final String JWT_AUTHORITIES_NAME = "authorities";

    public static final String REGEX_ROLES = ",";

}
