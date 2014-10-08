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

  - [x] Required: User can sign in to Twitter using OAuth login
  - [x] Required:User should be displayed the username, name, and body for each tweet
  - [x] Required: User should be displayed the relative timestamp for each tweet "8m", "7h"
  - [x] Required: User can view more tweets as they scroll with infinite pagination
  - [x] Optional: Links in tweets are clickable and will launch the web browser
  - [x] Required: User can click a “Compose” icon in the Action Bar on the top right
  - [x] Required: User can then enter a new tweet and post this to twitter
  - [ ] Required: User is taken back to home timeline with new tweet visible in timeline
  - [x] Optional: User can see a counter with total number of characters left for tweet
  - [/] Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh) -- partially
  - [ ] Advanced: Tweets are persisted into sqlite and can be displayed from the local DB
  - [ ] Advanced: User can tap a tweet to display a "detailed" view of that tweet
  - [ ] Advanced: User can select "reply" from detail view to respond to a tweet
  - [x] Advanced: Improve the user interface and theme the app to feel "twitter branded"
  - [ ] Bonus: User can see embedded image media within the tweet detail view
  - [ ] Bonus: Compose activity is replaced with a modal overlay

 
Walkthrough of all users stories:

![Video Walkthrough](twitterFragment.gif)

GIF created with [LiceCap]

[LiceCap]:www.cockos.com/licecap/
