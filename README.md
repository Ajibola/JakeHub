# JakeHub

A sample app listing repos by Jake Wharton from the GitHub API.

The app loads 15 items per request and fetches more items when there are 3 or fewer visible items on screen.

The app can also work offline, if results have previously been cached.

## Libraries
 * Retrofit - for Networking
 * ButterKnife - for android view injection
 * AutoValue - for model creation
 * Gson - for parsing the JSON responses
 * Dagger2 - for dependency injection
 * RxJava/RxAndroid - for reactive programming
 * JUnit - for testing
 * Mockito - for mocking & stubbing objects
 * Realm for data persistence
 * Checkstyle - for static code analysis
 * Google AppCompat Support Libraries
 * Google Material Design Support Libraries
 
 ## Building and running the project
 * Android Studio 2.3+
 * Android Gradle Plugin `2.3.3`
 * Minimum SDK Version 16
 * Build Tools Version 25.0.2
  
 ## Architecture
 
 The main modules of the app are presentation, domain, data, created in order to ensure a separation of concerns for the layers of the app.
 
 The presentation layer handles all views and interations with views and invokes the interactor that fetches the data.
 
 The domain layer is independent of any other layer. It defines ways of communicating with outside layers by dependency inversion.
 
 The data layer implements interfaces from the domain layer and also uses the repository to pick the right implementation to serve the presentation layer.
 
 Each layer has its own data objects that are mapped to each other with data mappers.
 
 ### Model Layer
 
 
 ## View Layer
 
 
 ## Presenter Layer
 
   
 ## Offline Functionality
 The API results are cached using Realm as the data store.

 
 ## Testing
 This projects has local unit tests powered by JUnit and mockito for mocking objects.
 
 ## Code Quality
 This project uses Checkstyle for static code analysis - it uses the Google Java Style guideline rules
 to ensure that the code always follows the guide.
 
 ## Possible Improvements
 * Continuous Integration - to continuously and automatically run the tests and other tasks as we introduce new code to the repository
 * More and more tests (especially UI tests)
 
 ## Additional Notes
 * 