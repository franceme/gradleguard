#region Variables
dir=./
java=${JAVA_HOME}/bin/java

ver=V00.01.01
name=gradleguard
#endregion

default:: build

#region Envrionment Handling
#Sets the current environment variable if it's not set
#Needed for gradle
setHome:
	@$(info Resetting the JAVA_HOME env variable (for Gradle).)
	@export JAVA_HOME=${java}

#Checking if the java_Home variable is set 
checkjavaHome: setHome
	@$(info Verifying the JAVA_HOME environment variable is set.)
ifeq ($(strip ${JAVA_HOME}),)
	@$(error Please set the JAVA_HOME environment variable.)
else
	@$(info JAVA_HOME is set.)
endif

#Checking if the ANDROID_HOME variable is set 
checkAndroidSDKHome:
	@$(info Verifying the ANDROID_HOME environment variable is set.)
ifeq ($(strip ${ANDROID_HOME}),)
	@$(info Please set the ANDROID_SDK environment variable.)
else
	@$(info ANDROID_SDK is set.)
endif

#A method to check if all of the required environment variables are set
checkEnv: checkjavaHome checkAndroidSDKHome
	@$(info Envrionment Variables are set.)
#endregion

build: checkEnv
	@$(info Running a gradle build)
	@./gradlew build

clean:
	@$(info Running a gradle clean)
	@./gradlew clean
