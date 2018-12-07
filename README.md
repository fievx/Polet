# Polet
A solution to compose RecyclerView Decoration
[![](https://jitpack.io/v/fievx/Polet.svg)](https://jitpack.io/#fievx/Polet)

The idea is to compose the decoration by reusing small and simple components.

Polet is itself an ItemDecoration as it extends the `RecyclerView.ItemDecoration` class. But an instance of Polet is an empty
shell that you fill by providing it informations that will compose the decoration.

Those elements, all optional, are: 
 - One `SizingDecoration` which will define the final size of the item
 - One or multiple `DrawingDecoration` which will define how you fill the space available
 - One or multiple `DecorationSelector` which will define which items will receive the decoration
 
In isolation, those items are simple and easy to write and understand. But combined (or composed), they can form very complex
decoration with complex selective conditions. 

## Implementation

A very basic decoration which adds space around every item: 

```kotlin
val spacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
addItemDecoration(Polet().apply {
    sizingDecoration = SimpleSpacingDecoration(spacing)
})
```

![image](https://image.noelshack.com/fichiers/2018/49/3/1544024014-simple-spacing.png)

A more complex decoration with a background, a line under each element and another line one the side.

```kotlin
addItemDecoration(Polet().apply {
    sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
    drawingDecorations.add(PaintingDecoration(primaryColor))
    drawingDecorations.add(
        LineDrawingDecoration(accentColor, LineDrawingDecoration.Position.right,defaultSpacing)
    )
    drawingDecorations.add(
        LineDrawingDecoration(black,LineDrawingDecoration.Position.bottom, lineHeight)
    )
})
```
![image](https://image.noelshack.com/fichiers/2018/49/3/1544024782-complex-decoration.png)

### Composing with Decoration Selector
The real power comes when you combine the drawing composition with the selection composition. 

Going back to the previous exemple with the simple spacing decoration, imagine that you want to add an extra spacing over the very first item.
The idea to do so, is to add another item decoration which will be a single space and will only apply to the first element.

For that, we can use the `PositionningSelector` and add it to the `selectiveDecoration` list. The `PositionningSelector` lets you include or exclude certain positions in your RecyclerView. Here we only include the First position.

```kotlin
val spacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
addItemDecoration(Polet().apply {
    sizingDecoration = SimpleSpacingDecoration(spacing)
})

addItemDecoration(Polet().apply {
    sizingDecoration = SimpleSpacingDecoration(t = spacing * 3)
    selectiveDecoration.add(PositionningSelector().apply {
        excludeAll()
        includeFirst = true
    })
})
```
![image](https://image.noelshack.com/fichiers/2018/49/3/1544026251-simple-spacing-with-etra-on-top.png)

Another example could be to have a certain decoration if the data matches a certain condition and another decoration if the condition is not met. 
For that, we can use the `DataConditionSelector` which takes a function as constructor parameter. This function recieves the adapter and the current position and must return a boolean indicating if the condition is matched. 

Here the RecyclerView displays a list of random Ints between 0 and 9 and we need to apply a color if the number is between 0 and 5.

```kotlin
   addItemDecoration(Polet().apply {
       sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
       drawingDecorations.add(PaintingDecoration(accentColor))
       selectiveDecoration.add(DataConditionSelector { adapter, position ->
           return@DataConditionSelector (adapter as RandomNumberAdapter?)?.list?.get(position) ?: 0 > 5
       })
   })
   addItemDecoration(Polet().apply {
       sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
       drawingDecorations.add(PaintingDecoration(primaryColor))
       selectiveDecoration.add(DataConditionSelector { adapter, position ->
           return@DataConditionSelector (adapter as RandomNumberAdapter?)?.list?.get(position) ?: 0 <= 5
       })
   })
 ```
![image](https://image.noelshack.com/fichiers/2018/49/4/1544091878-conditional-decoration.png)

## Content

### SizingDecoration
 - SimpleSpacingDecoration -> Adds space around the item. Dimention needs to be provided in pixels, not resource id. 
 
### DrawingDecoration
 - PaintingDecoration -> Fills alls the space available with the provided color. Color needs to be provided as a resolved RGB color, not resource id.
 - LineDrawingDecoration -> Draws a line of the specified width and color at the specified location (top, bottom, left or right)
 
### DecorationSelector
 - ViewTypeSelector -> Applies the decoration to the selected ViewTypes
 - SkippingSelector -> Applies decoration, then skips the required number of views and repeat
 - PositionningSelector -> Applies decoration to the selected positions: first, last, inner, firstInSameViewTypeGroup, lastInSameViewTypeGroup, innerInSameViewTypeGroup
 - DataConditionSelector -> Applies decoration if the data at the current position matches the provided condition. 
 
This content is volontarily reduced to keep the library small and still cover many use cases. If you browse throught the code, you will realize that the drawing and selection classes are very simple. So if anything is missing, it should be quite easy to create your own `DrawingDecoration` or `DecorationSelector`

## Install
The library is available using Jitpack



Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.fievx:Polet:<version>'
	}

### License
```
Copyright (C) 2018 Jeremy Denais

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

### Note
This library is named after a very good friend who had a brief career as an interior designer/decorator. Love++
