Assignment 1
============

Implemented Features
---------------------

* Drawings:
   - The outlines of a square based pyramid (with a red color)
   - A filled in blue square
   - A simple green colored 4 pointed star
* The figures are implemented with function calls that takes coordinates as parameters. This makes it easy to create additional figures if needed.
* All of the functions described above preserves the OpenGL state by using glPushMatrix()/glPopMatrix() for preserving the current matrix and glPushAttrib()/glPopAttrib() for preserving the state variables.

References
----------

* The coordinates for the triangle is taken from this [site](http://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html).
