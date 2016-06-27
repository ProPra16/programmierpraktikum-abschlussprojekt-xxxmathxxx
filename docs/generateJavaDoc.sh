#Generates an up-to-date javadoc
cd ../tddt
gradle javadoc
#Copy to this folder
cp build/docs/javadoc ../docs/javadoc -avr
