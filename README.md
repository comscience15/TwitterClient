Twitter Client
=========

This application is simulate Twitter client. User can view and post twitters through their Twitter account

  - Layout
  - Views
  - AsyncHttpClient
  - TextView
  - JsonHttpResponseHandler
  - ImageView
  - Button
  - GridView
  - ConnectivityManager
  - MenuItem

Time spent: 20 hours

The following things are supported out of the box:

 * Authenticating with any OAuth 1.0a or OAuth 2 API
 * Sending requests for and parsing JSON API data using a defined client
 * Persisting data to a local SQLite store through an ORM layer
 * Displaying and caching remote image data into views

The following libraries are used to make this possible:

 * [scribe-java](https://github.com/fernandezpablo85/scribe-java) - Simple OAuth library for handling the authentication flow.
 * [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
 * [codepath-oauth](https://github.com/thecodepath/android-oauth-handler) - Custom-built library for managing OAuth authentication and signing of requests
 * [UniversalImageLoader](https://github.com/nostra13/Android-Universal-Image-Loader) - Used for async image loading and caching them in memory and on disk.
 * [ActiveAndroid](https://github.com/pardom/ActiveAndroid) - Simple ORM for persisting a local SQLite database on the Android device

Completed User Stories:

  - [x] Required: Includes all required user stories from Week 3 Twitter Client
  - [x] Required: User can view their home timeline tweets
  - [x] Required: User can view the recent mentions of their username
  - [x] Required: User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
  - [x] Optional: Implement tabs in a gingerbread-compatible approach
  - [x] Required: User can see picture, tagline, # of followers, # of following, and tweets on their profile
  - [x] Required: User can see picture, tagline, # of followers, # of following, and tweets of clicked user
  - [x] Required: Profile view should include that user's timeline
  - [x] Optional: User can view following / followers list through the profile
  - [ ] Advanced: Robust error handling, check if internet is available, handle error cases, network failures 
  - [ ] Advanced: When a network request is sent, user sees an indeterminate progress indicator
  - [ ] Advanced: The user that wrote the original tweet is automatically "@" replied in compose
  - [ ] Advanced: User can click on a tweet to be taken to a "detail view" of that tweet
  - [x] Advanced: Improve the user interface and theme the app to feel twitter branded
  - [ ] Advanced: User can search for tweets matching a particular query and see results
  - [ ] Bonus: User can view their direct messages (or send new ones)
 
Walkthrough of all users stories:

![Video Walkthrough](twitterFragment.gif)

GIF created with [LiceCap]

[LiceCap]:www.cockos.com/licecap/
