# Architecture Component Sample
This is a sample MVVM project using the latest architecture components and the Paging Library.

## View
It's based in a [Activity - Fragment] architecture, also on a RecyclerView and Adapter view based on the data that has to be shown.
The Adapter implements PagedListAdapter to be able to handle the pagination.

## ViewModel
The ViewModel handling the Repo and the DataSource to be able to obtain the data paginated.
Favourites ViewModel handles only the repo, because the data is not being obtained in pagination, only from Persistence(Room)

## Model
The model is the one who is managing the repo and where the data is coming from. In the case of the Article search flow, the data is coming
from the Network in a pagination mode. 
The ArticleDataSource is the heart of the Pagination. The one listening for query changes and making query request to the repo api.
Also listening for the pagination when scrolling the RecyclerView to get more data and managing the NetwrokState for UI purposes (RUNNING,SUCCESS,FAILED).

## Repository
The repo has the ApiService and the Room Service. 
- We get the search data from the ApiService.
- We save data to the Room Service.
- We delete the data to the Room Service.
- We get the saved data from the Room Service.


## Main libraries used
- [Kotlin](https://kotlinlang.org/docs/reference/) :heart:
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Architecture)
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging) (Paging)
- [Room](https://developer.android.com/topic/libraries/architecture/room) (Persistence)
- [Retrofit](https://square.github.io/retrofit/) (Network)
- [Coroutines](https://developer.android.com/kotlin/coroutines)


## Instruction

Just clone the project and open in Android Studio.

