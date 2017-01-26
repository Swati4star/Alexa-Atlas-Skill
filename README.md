# Alexa Skill : Atlas

A sample skill for alexa using which users can play 'Atlas' game, a renowned game in which next user has to say a country name that starts with the letter, the previous country name ended. When user asks Alexa to start a new game, Alexa responds with a random country name. User can then say next country, and game goes on.

<p align="center"> <img src="app/image.jpg"> </p>


## Sample conversation

<b>User</b>  : start game

<b>Alexa</b> : Let us start a new game. I give you togo. You tell country with o.

<b>User</b>  : Oman

<b>Alexa</b> : Now I say namibia. You tell country with a.

<b>User</b>  : Mexico

<b>Alexa</b> : The country name is invalid.

<b>User</b>  : Armenia

<b>Alexa</b> : Now I say afghanistan. You tell country with n.

<b>User</b>  : stop

<b>Alexa</b> : Goodbye

## Code structure
The links for relevant files in the project
- [Custom Slots](app/src/main/java/atlas/speechAssets/customSlotTypes)
- [Sample utterances](app/src/main/java/atlas/speechAssets/SampleUtterances.txt)
- [Intent Schema](app/src/main/java/atlas/speechAssets/IntentSchema.json)
- [Handler](app/src/main/java/atlas/AtlasSpeechletRequestStreamHandler.java)

## Setup instructions
To run this skill you need to do two things. The first is to deploy the alphonso code in lambda, and the second is to configure the Alexa skill to use Lambda.

### AWS Lambda Setup
1. Go to the AWS Console and click on the Lambda link. Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.
2. Click on the Create a Lambda Function or Get Started Now button.
3. Skip the blueprint
4. Name the Lambda Function "Atlas-Skill" (It can be anything).
5. Select the runtime as Java 8
6. Clone the code. Go to the the root directory (Alexa-Atlas-Skill/app) containing pom.xml, and run 'mvn assembly:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "alexa-skills-kit-samples-1.0-jar-with-dependencies.jar" in the target directory.
7. Select Code entry type as "Upload a .ZIP file" and then upload the "alexa-skills-kit-samples-1.0-jar-with-dependencies.jar" file from the build directory to Lambda
8. Set the Handler as atlas.AtlasSpeechletRequestStreamHandler(this refers to the Lambda RequestStreamHandler file in the zip).
9. Create a basic execution role and click create.
10. Leave the Advanced settings as the defaults.
11. Click "Next" and review the settings then click "Create Function". This may take a number of minutes depending on your connection speed
12. Click the "Event Sources" tab and select "Add event source"
13. Set the Event Source type as Alexa Skills kit and Enable it now. Click Submit.
14. Copy the ARN from the top right to be used later in the Alexa Skill Setup.

### Alexa Skill Setup
1. Go to the [Alexa Console](https://developer.amazon.com/edw/home.html) and click Add a New Skill.
2. Set "Atlas" as the skill name and "atlas" as the invocation name, this is what is used to activate your skill. For example you would say: "Alexa, Ask atlas start game."
3. Select the Lambda ARN for the skill Endpoint and paste the ARN copied from above. Click Next.
4. Copy the custom slot types from the [customSlotTypes](app/src/main/java/atlas/speechAssets/customSlotTypes) folder. Each file in the folder represents a new custom slot type. The name of the file is the name of the custom slot type, and the values in the file are the values for the custom slot.
5. Copy the Intent Schema from the included [IntentSchema.json](app/src/main/java/atlas/speechAssets/IntentSchema.json).
6. Copy the Sample Utterances from the included [SampleUtterances.txt](app/src/main/java/atlas/speechAssets/SampleUtterances.txt). Click Next.
7. Go back to the skill Information tab and copy the appId. Paste the appId into the AtlasSpeechletRequestStreamHandler.java file for the variable supportedApplicationIds, then update the lambda source zip file with this change and upload to lambda again, this step makes sure the lambda function only serves request from authorized source.
8. Now, you can test the skill.

P.S. : DO NOT SUBMIT THIS TO AMAZON FOR CERTIFICATION AS IT WON'T PASS!
