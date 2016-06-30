@echo off
rem Check dependencies
rem TODO
rem Generates an up-to-date class diagram
echo "Running Heretikz"
call java -jar ../tddt/external/Heretikz.jar ../tddt/src/main classDiagram.tex
rem Compiles the .tex files into pdf
echo "Running LaTex / PdfTex"
call pdflatex -jobname=class-diagram diagramSkeleton.tex
rem Clean-up temporary files
echo "Cleaning up ..."
call del /S *.aux
call del /S *.log
pause