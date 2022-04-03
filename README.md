## Objective
- Create a reusable CurrencyListFragment
- Also create a DemoActivity to showcase the CurrencyListFragment

## Requirement
- CurrencyListFragment should receive an array list of CurrencyInfo to create the ui.
- DemoActivity should provide 1 dataset, Currency List A of CurrencyInfo to
CurrencyListFragment and the dataset should be queried from local db
- DemoActivity should provide 2 buttons to do the demo.
- First button to load the data and display
- Second button for sorting currency list
- All the IO operations MUST NOT be in UI Thread.
- Please show how to handle multi threading operation, and deal with concurrency
issue when do sorting (like fast double clicking of sorting button)
- Search functionality is not required
- Unit test is welcome

## Implementation
The application follows an MVVM, clean architecture pattern and I have used Koin for dependency injection. The architecure setup for this project has
been built with the intention that the application could easily scale should additional features be included. 

The DemoActivity provides 2 buttons, both of which communicate with the CurrencyListViewmodel, utilising data binding. The "LOAD" button will insert CurrencyInfo into the observed room database, and cosequently displays an unsorted list. The "SORT" button will order the list by currency name, toggling by ascending and descending upon multiple clicks.

The CurrencyListViewmodel is shared with the CurrencyListFragment to easily handle data communication between the Activity and Fragment. A Room database has been setup for local storage of CurrencyInfo, from which the data is retrieved using Flow. Multi-threading is handled with a combination of Coroutines and Flow. 

Unit tests are provided for the GetCurrencyInfo use case as a testing example.
