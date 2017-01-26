package atlas;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class could be the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the handler field in the AWS Lambda console to
 * "atlas.AtlasSpeechletRequestStreamHandler" For this to work, you'll also need to build
 * this project using the {@code lambda-compile} Ant task and upload the resulting zip file to power
 * your function.
 */
public class AtlasSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;

    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.2041ae0d-e01e-49bb-aa6a-c3c6a872aa8e");
    }

    public AtlasSpeechletRequestStreamHandler() {
        super(new AtlasSpeechlet(), supportedApplicationIds);
    }

    public AtlasSpeechletRequestStreamHandler(Speechlet speechlet,
            Set<String> supportedApplicationIds) {
        super(speechlet, supportedApplicationIds);
    }

}
