#Generates an up-to-date class diagram
java -jar ../tddt/external/Heretikz.jar ../tddt/src classDiagram.tex
#Compiles the .tex files into pdf
pdflatex -jobname=class-diagram diagramSkeleton.tex
#Clean-up temporary files
find . -name "*.aux" -type f -delete
find . -name "*.log" -type f -delete
