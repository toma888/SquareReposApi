# SquareReposApi
Android Rest Application represents Square Repositories with github api https://github.com/square

Application makes http requests for the official GitHub API and shows it in the mobile-interface.

Documentation - https://developer.github.com/v3/repos/

What downloads - list of repositories https://github.com/square

How it shows:

The first screen is a list of repositories.  
In each cell of the list - the name of the repository, number of stars and forks. 
By clicking any repository from the list the app redirects to the second screen - repository details, which contains:
full name, description, url, language, number of stars, forks, watches, issues.

Technologies:

- RxJava 2
- Retrofit 2
- Dagger 2
- OkHttp 3
- MVVM architecture
- Navigation component
- Single Activity
