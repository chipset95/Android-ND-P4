/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.joke.teller;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.builditbigger.joker.java.JokeMaker;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v4",
        namespace = @ApiNamespace(
                ownerDomain = "teller.joke.builditbigger.gradle.udacity.com",
                ownerName = "teller.joke.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "jokeMe")
    public MyBean jokeMe() {
        MyBean response = new MyBean();
        response.setData(JokeMaker.getAJoke());
        return response;
    }

}
