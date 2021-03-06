#TDDT#

[![Build Status](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-xxxmathxxx.png)](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-xxxmathxxx)

A simple TDDT-application using JavaFX (Project for CS @ hhu-duesseldorf)

this project uses: java 1.8, VirtualKataLib (by Jens Bendisposto), javafx, gradle, travis ci, pdfTeX, TikZ, Heretikz

This tool can be useful for cs beginners that want to learn the technique of test-driven development. Basic JUnit and Java knowledge is recommended to profit from this tool as the exercises require you to write Java code and corresponding JUnit tests.

##How to compile from source##

This project uses gradle as a build-system.
To compile from source, first navigate to the tddt subfolder:
`cd tddt`
Then start the build-processs with the following commands:

Run `./gradlew build` on Unix based systems.
Run `gradlew build` on Windows based systems.

You can then launch the software with the following command:

Run `./gradlew run` on Unix based systems.
Run `gradlew run` on Windows based systems.

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

This program is provided under the terms of the Eclipse Public License 1.0. 
For more information regarding this, open the license.pdf, which can be found in the same folder as this file.



