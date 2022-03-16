package by.schepov.bsu.diploma.censorgram.main.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Response object for authentication process
 * Created by dkadirbekov on 23.08.2018.
 */
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

    private String redirectPath;
    private String message;

}
