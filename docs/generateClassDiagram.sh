#Check dependencies
if ! type "java" > /dev/null; then
	read -p "It appears that java is not installed on your system!" -n1 -s
	exit 0
fi
if ! type "pdflatex" > /dev/null; then
	read -p "It appears that LaTex/pdflatex is not installed on your system!" -n1 -s
	exit 0
fi
#Generates an up-to-date class diagram
java -jar ../tddt/external/Heretikz.jar ../tddt/src/main classDiagram.tex
#Compiles the .tex files into pdf
pdflatex -jobname=class-diagram diagramSkeleton.tex
#Clean-up temporary files
find . -name "*.aux" -type f -delete
find . -name "*.log" -type f -delete
