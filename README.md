# idme-interview

You will need to pull the repo and run the application using Android Studio Giraffe or above. The application runs using JDK 17. In case Android Studio Giraffe doesn't come bundled with JDK 17, you will need to go to the java website and install it on your machine. (Open-JDK should theoretically work as well)

## Interesting things:
* The instructions in the email told me to timebox this to 3 hours. Unfortunately, I was sent 2 different interview questions. One in a dropbox link, and one in an attached PDF. Reading the instructions in the email, I was directed down the dropbox route, which ended up taking longer than 3 hours. I got the featureset working and things are testable, but due to time constraints I wasn't able to get around to writing the tests themselves.
* This project was built mostly using modern Jetpack Compose, with Coroutines + Flow for structured concurrency.
* Modern Jetpack Compose tooling doesn't play nice with viewModel + composable previews. Most projects that are migrating to compose will have some hybridization of legacy android views + composables. I architected this project out to demonstrate that hybridization approach. Also, as a result, composable previews work for faster iterations.
* Uses dependency injection!
* Built in a way that we could easily modularize it if we chose to.
