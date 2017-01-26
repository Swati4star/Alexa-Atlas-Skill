package atlas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.Arrays;  
import java.util.List;  
import java.util.ArrayList;  
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class AtlasSpeechlet implements Speechlet {
    
    private static final Logger log = LoggerFactory.getLogger(AtlasSpeechlet.class);

    private static List<String> VALID_COUNTRIES;

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
        throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
        throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
        throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent       = request.getIntent();
        String intentName   = intent.getName();

        populateValidCountries();

        if ("PlayGame".equals(intentName))
            return playNewGame(intent, session);
        if ("ContinueGame".equals(intentName))
            return continueGame(intent, session);
        if ("AMAZON.StartOverIntent".equals(intentName))
            return playNewGame(intent, session);
        if ("AMAZON.StopIntent".equals(intentName))
            return getByeResponse();
        if ("AMAZON.CancelIntent".equals(intentName))
            return getByeResponse();
        return getParseErrorResponse();
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
        throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    }

    /**
    * Starts new game
    */
    private SpeechletResponse playNewGame(final Intent intent, final Session session) {
        String              country       = getRandomCountry();
        String              output        = " Let us start a new game. I give you " + country;
        output                            = output + ". You tell country with " + country.substring(country.length()-1)+ ".";
        ArrayList<String>   country_done  = new ArrayList();
        country_done.add(country);
        session.setAttribute(AtlasConstants.ATT_INTERVAL, country_done);
        return showResponse(output);
    }

    /**
    * Continues current game
    */
    private SpeechletResponse continueGame(final Intent intent, final Session session) {
        Slot                slot            = intent.getSlot(AtlasConstants.SLOT_COUNTRY);
        String              input           = slot.getValue().toLowerCase();

        // If the utterance cintains null slot, or country is not in list of valid countries
        if(slot == null || input == null || !VALID_COUNTRIES.contains(input))
            return showResponse(AtlasConstants.INVALID_COUNTRY);
        
        ArrayList<String>   country_done    = (ArrayList) session.getAttribute(AtlasConstants.ATT_INTERVAL);
        String              lastCountry     = country_done.get(country_done.size()-1);

        // If the first letter of country (as said by user) is not equal to last letter of last country
        if(!lastCountry.substring(lastCountry.length()-1).equals(input.substring(0,1))) 
            return showResponse(AtlasConstants.INVALID_COUNTRY);

        // If the cpuntry name was already spoken
        if(country_done.contains(input))
            return showResponse(AtlasConstants.ALREADY_DONE_COUNTRY);

        // Add the country to list ofdone countries
        country_done.add(input);

        // Computer's turn, choose a valid country
        String  country       = getCountryWithLetter(country_done, input.substring(input.length()-1));
        String  output        = " Now I say " + country;
        output                = output + ". You tell country with " + country.substring(country.length()-1)+ ".";
        // Add the country to list ofdone countries
        country_done.add(country);
        // Update session attributes
        session.setAttribute(AtlasConstants.ATT_INTERVAL, country_done);
        return showResponse(output);
    }    

    /**
    * Generates random country from the list of valid countries
    */
    private String getRandomCountry() {
        Random rand = new Random();
        int  n = rand.nextInt(VALID_COUNTRIES.size()) + 1;
        return VALID_COUNTRIES.get(n);
    }

    /**
    * Returns a coutry which is ot there in done countries, and first letter is 'letter'
    */
    private String getCountryWithLetter(ArrayList done, String letter) {
        for(int i=0;i<VALID_COUNTRIES.size(); i++) {
            if(VALID_COUNTRIES.get(i).substring(0,1).toLowerCase().equals(letter.toLowerCase()) 
                && !done.contains(VALID_COUNTRIES.get(i)))
                return VALID_COUNTRIES.get(i);
        }
        return null;
    }

    private SpeechletResponse getByeResponse() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(AtlasConstants.GOODBYE_RESPONSE);
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getWelcomeResponse() {
        return showResponse(AtlasConstants.WELCOME_RESPONSE);
    }

  
    private SpeechletResponse getParseErrorResponse() {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(AtlasConstants.PARSE_ERROR);
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getHelpResponse() {
        return newAskResponse(AtlasConstants.HELP_RESPONSE, true,
            AtlasConstants.WELCOME_REPROMPT, false);
    }

    private SpeechletResponse showResponse(String response) {
        return newAskResponse(response, false, response, false);
    }

   
    /** HELPER FUNCTIONS **/
    private void populateValidCountries() {
        VALID_COUNTRIES = Arrays.asList(AtlasConstants.countries);
        int size = VALID_COUNTRIES.size();
        for(int i=0; i < size; i++) {
            VALID_COUNTRIES.set(i, VALID_COUNTRIES.get(i).toLowerCase());
        }
    }

   
    /**
     * Wrapper for creating the Ask response from the input strings with
     * plain text output and reprompt speeches.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is misunderstood.
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        return newAskResponse(stringOutput, false, repromptText, false);
    }

    /**
     * Wrapper for creating the Ask response from the input strings.
     *
     * @param stringOutput
     *            the output to be spoken
     * @param isOutputSsml
     *            whether the output text is of type SSML
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is misunderstood.
     * @param isRepromptSsml
     *            whether the reprompt text is of type SSML
     * @return SpeechletResponse the speechlet response
     */
    private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml,
            String repromptText, boolean isRepromptSsml) {
        OutputSpeech outputSpeech, repromptOutputSpeech;
        if (isOutputSsml) {
            outputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
        } else {
            outputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
        }

        if (isRepromptSsml) {
            repromptOutputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) repromptOutputSpeech).setSsml(stringOutput);
        } else {
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
        }
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);
        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }
}