# WalmartCompare

#### An app to compare searched items from Walmart

#### Midori Bowen

## Description

Walmart Compare is an Android app where the user can
* search the Walmart API for a query
* view the results in an endless scrolling list
* look at the details of each item in the results list or comparison list
* add an item to a comparison list to narrow down options
* view a list with only the important attributes for easier comparison

This is a self-study personal project for learning how to use Android's Architecture Component
Libraries, such as: [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
and [Room](https://developer.android.com/topic/libraries/architecture/room). Another goal of this
 project is to practice writing more tests.


## Progress
| Task                                         | Status | Items                                                                                                      |
|----------------------------------------------|--------|------------------------------------------------------------------------------------------------------------|
| Set up Walmart API                           | Done   | [x] Retrofit Service<br>[x] Paginated DB                                                                      |
| Display search results in RecyclerView       | Done   |  [x] Activity<br>[x] ViewModel<rb>[x] Adapter<br>[x] ViewHolder<br>[x] layouts                                         |
| Show DetailView for clicked item in list     | In Progress   | [x] pass SearchItem data to DetailViewModel<br>[x] display detail view<br>[x] add to list button                    |
| Add item to comparison list        | TODO   | [] comparison list database?<br>                                                                                                          |
| Show comparison list from options menu click | TODO   | [] collapsible list of feature options<br>[] display features in table view<br>[] items clickable to see details |

## Reference materials
* [Android Jetpack Documentation](https://developer.android.com/jetpack/)
* [WalmartLabs Search API](https://developer.walmartlabs.com/docs/read/Search_API)
* [Google Samples Paging With Network Sample](https://github.com/googlesamples/android-architecture-components/tree/master/PagingWithNetworkSample)

## Issues
* [Instrumented tests for pagination in the database](https://github.com/googlesamples/android-architecture-components/issues/287)
  * This may involve adapting LiveDataTestUtil to use a PagedList vs. LiveData<> or mocking the
  pagination process through a more indirect instrumentation test (i.e. mocking an actual search)
