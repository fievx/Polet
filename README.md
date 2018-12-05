# Polet
A solution to compose RecyclerView Decoration

The idea is to compose the decoration by reusing small and simple components.

Polet is itself an ItemDecoration as it extends the `RecyclerView.ItemDecoration` class. But an instance of Polet is an empty
shell that you fill by providing it informations that will compose the decoration.

Those elements, all optional, are: 
 - One `SizingDecoration` which will define the final size of the item
 - One or multiple `DrawingDecoration` which will define how you fill the space available
 - One or multiple `SelectiveDecoration` which will define which items will receive the decoration
 
In isolation, those items are simple and easy to write and understand. But combined (or composed), they can form very complex
decoration with complex selective conditions. 

### Implementation

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

The real power comes when you add the drawing composition with the selection composition. 

Going back to the previous exemple with the simple spacing decoration, I want to add an extra spacing over the very first item
The idea to do so, is to add another item decoration which will be a single space and will only apply to the first element.

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


