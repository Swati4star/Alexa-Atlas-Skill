package atlas;

public final class AtlasConstants {

	public AtlasConstants() {}

    // Slots name
    public static final String SLOT_COUNTRY         = "country";
    
    // Sesssion attributes
    public static final String ATT_INTERVAL         = "att_interval";

    // response string for various cases
    public static final String GOODBYE_RESPONSE     = "Goodbye";
    public static final String WELCOME_RESPONSE     = 
        "Welcome to Atlas Game. I will give you a country name and you have to " +
        "name another country starting with the letter my country ends ";
    public static final String WELCOME_REPROMPT     = 
        "I will give you a country name and you have to name another country  "  +
        "starting with the letter my country ends ";
    public static final String REQUEST_REPROMPT     = 
        "Do you want to play new game? You can try help";
    public static final String PARSE_ERROR          = 
        "I am sorry, I did not understood your query. Please try help";
    public static final String INVALID_COUNTRY      =
        "The country name is invalid";    
    public static final String ALREADY_DONE_COUNTRY = 
        "The country has already been spoken.";
    public static final String HELP_RESPONSE        =  WELCOME_RESPONSE ;
    public static final String TRY_AGAIN            = 
        "It is taking a lot of time. Do you want me to try again?";

    // Sample countries used to play game
    public static final String[] countries = {"Afghanistan","Albania","Algeria","American Samoa",
    "Andorra","Angola","Anguilla","Antarctica","Antigua And Barbuda","Argentina","Armenia","Aruba",
    "Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus",
    "Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia And Herzegovina","Botswana",
    "Bouvet Island","Brazil", "British Indian Ocean Territory","Brunei Darussalam","Bulgaria",
    "Burkina Faso","Burundi", "Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands",
    "Central African Republic","Chad","Chile","China","Christmas Island","Cocos Islands","Colombia",
    "Comoros","Congo","Congo","Cook Islands","Costa Rica", "Cote D'ivoire","Croatia","Cuba","Cyprus",
    "Czech Republic","Denmark", "Djibouti","Dominica","Dominican Republic","East Timor","Ecuador",
    "Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia", "Falkland Islands",
    "Faroe Islands","Fiji","Finland","France", "French Guiana","French Polynesia",
    "French Southern Territories","Gabon","Gambia", "Georgia","Germany","Ghana","Gibraltar","Greece",
    "Greenland","Grenada", "Guadeloupe","Guam","Guatemala","Guinea","Guinea-bissau","Guyana","Haiti",
    "Heard Island And Mcdonald Islands","Holy See","Honduras","Hong Kong", "Hungary","Iceland","India",
    "Indonesia","Iran, Islamic Republic Of","Iraq", "Ireland","Israel","Italy","Jamaica","Japan","Jordan",
    "Kazakstan","Kenya", "Kiribati","Korea","Korea, Republic Of","Kosovo","Kuwait", "Kyrgyzstan",
    "Lao Peoples Democratic Republic","Latvia","Lebanon","Lesotho","Liberia", "Libyan Arab Jamahiriya",
    "Liechtenstein","Lithuania","Luxembourg","Macau", "Macedonia","Madagascar","Malawi","Malaysia",
    "Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania", "Mauritius","Mayotte",
    "Mexico","Micronesia","Moldova", "Monaco","Mongolia","Montserrat","Montenegro","Morocco","Mozambique",
    "Myanmar", "Namibia","Nauru","Nepal","Netherlands","New Caledonia", "New Zealand","Nicaragua","Niger",
    "Nigeria","Niue","Norfolk Island", "Northern Mariana Islands","Norway","Oman","Pakistan","Palau",
    "Palestinian Territory","Panama","Papua New Guinea","Paraguay","Peru", "Philippines","Pitcairn",
    "Poland","Portugal","Puerto Rico","Qatar","Reunion", "Romania","Russian Federation","Rwanda",
    "Saint Helena","Saint Kitts And Nevis", "Saint Lucia","Saint Pierre And Miquelon",
    "Saint Vincent And The Grenadines","Samoa", "San Marino","Sao Tome And Principe","Saudi Arabia",
    "Senegal","Serbia","Seychelles", "Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands",
    "Somalia", "South Africa","South Georgia And The South Sandwich Islands","Spain","Sri Lanka",
    "Sudan","Suriname","Svalbard And Jan Mayen","Swaziland","Sweden","Switzerland", "Syrian Arab Republic",
    "Taiwan","Tajikistan", "Tanzania","Thailand","Togo","Tokelau","Tonga","Trinidad And Tobago", "Tunisia",
    "Turkey","Turkmenistan","Turks And Caicos Islands","Tuvalu","Uganda", "Ukraine","United Arab Emirates",
    "United Kingdom","United States", "Uruguay","Uzbekistan","Vanuatu", "Venezuela", "Viet Nam",
    "Virgin Islands", "Wallis And Futuna","Western Sahara","Yemen","Zambia","Zimbabwe" };
}