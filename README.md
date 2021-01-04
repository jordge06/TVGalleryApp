# TVGalleryApp

### App Description
  - A simple Android App that let users browse and view all availble TV Shows from around the world. The app uses a free online source called TMDB.org.

## Important Notice
  - If you want to download the source code and run it to your PC. You will get an error because I removed 1 class that contains my API Key.
  - So to run the app you should make a Java Class named Constants inside the Folder called Utilities.
  - Then you need to register to https://www.themoviedb.org/ and get your own API KEY.
  - Lastly make a three constants variable inside the Constans class.
    - first: public static final String API_KEY = "paste your api key";
    - second: public static final String BASE_URL= "https://api.themoviedb.org/3/";
    - last: public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
  - Everything should work perfectly. Thanks for using the app.
  
### Library Used
  - Retrofit
  - Material Design
  - Livedata
  - ViewModel
  - Room Persistence
  - RX Java
  
### Design Architecture
  - MVVM
  - Databinding

### Screenshots

<img src="https://github.com/jordge06/TVGalleryApp/blob/master/Screenshot_2021-01-04-14-11-39-15.png" width="300"/>

<img src="https://github.com/jordge06/TVGalleryApp/blob/master/Screenshot_2021-01-04-14-11-43-43.png" width="300"/>

<img src="https://github.com/jordge06/TVGalleryApp/blob/master/Screenshot_2021-01-04-14-12-00-19.png" width="300"/>

<img src="https://github.com/jordge06/TVGalleryApp/blob/master/Screenshot_2021-01-04-14-12-15-32.png" width= "300"/>



