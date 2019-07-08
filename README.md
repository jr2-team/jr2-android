# jr2-android

## Directories structure convention
A presenter feature should be placed in own directory with `_view` and `_viewmodel` subdirectories. For an instance:
```
+-- presentation
|   +-- word
|       +-- _view
|           +-- list_adapter
|               +-- WordListAdapter.kt
|               +-- WordListViewHolder.kt
|           +-- dialogs
|               +-- NewWordDialog.kt
|           +-- WordFragment.kt
|       +-- _viewmodel
|           +-- WordViewModel.kt
|           +-- WordViewState.kt
|           +-- WordViewEvent.kt
```