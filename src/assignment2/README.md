Assignment 2
============

[Github page](https://github.com/AnotherDay/M7002E-Assignments/tree/master/src/assignment2)

Implemented features
---------------------

* Creation:
    - Objects, are created by first picking one in the drop down menu to the far right and then filling out all the boxes below for coordinates, ambient, specular, diffuse and so on.
    - Light sources can be found in the drop down menu on the far right and are created in a similar way as objects.

* Manipulation:
    - Moving a object, is done by first picking `Manipulate -> Move` in the menu bar. After that click which object you want to move and then click again on the position you want the object to be placed. 
    - Resizing a object, is done by first picking `Manipulate -> Resize` in the menu bar. Then pick which object you want to resize, you will then get a pop up window asking for the scaling factor. This number will then be multiplied with the numbers that help describe the object (like the height, width or radius for example).
    - Deleting an object is done by selecting `Manipulate -> Delete` in the menu bar and then click on which object should be deleted.

* Other features:
    - Clear all objects from the canvas, can be found under the Canvas option in the menu bar.
    - Clear all light sources, can also be found under the Canvas option in the menu bar.
    - Print information about all shapes, prints out the information about all the objects and the light sources in the console. This feature can be found under the Canvas option in the menu bar.
 
 Known bugs
 ----------

 * There is a bug which causes the drop down menu in the left tool bar to close before the user has time to select an object. The workaround for this is to double click on the drop down menu before going down with the mouse to select an item. 