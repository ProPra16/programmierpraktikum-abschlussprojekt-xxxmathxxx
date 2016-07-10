#TDDT#

[![Build Status](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-xxxmathxxx.png)](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-xxxmathxxx)

A simple TDDT-application using JavaFX (Project for CS @ hhu-duesseldorf)

this project uses: java 1.8, VirtualKataLib (by Jens Bendisposto), javafx, gradle, travis ci, pdfTeX, TikZ, Heretikz

##How to compile from source##

This project uses gradle as a build-system.
To compile from source, first navigate to the tddt subfolder:
`cd tddt`
Then start the build-processs with the following command:
`gradle build`
The compiled .jar will be found in tddt/build/libs.

##How to generate documentation##

We do not provide a static documentation here, instead you can create your own up-to-date documentation from our source with simple tools we provided.

###How to generate javadoc###

Navigate to the docs folder with `cd docs`.
Then execute the following command: `sh generateJavaDoc.sh`
On Windows you can use the corresponding batch-file.

###How to generate a class-diagram as .pdf###

Navigate to the docs folder with `cd docs`.
Then execute the following command: `sh generateClassDiagram.sh`
On Windows you can use the corresponding batch-file.
Note that in order for this to work you need a working LaTex system with pdftex and the following packages:
tikz, tikz-qtree, tikz-qtree-compat

##How to use our software##

To get started, refer to our manual, located in the manual folder.

##How to find our logfiles##

Our logfiles, documenting our weekly meeting, can be found in the logs folder.

##License and Warranty##

//TODO//




