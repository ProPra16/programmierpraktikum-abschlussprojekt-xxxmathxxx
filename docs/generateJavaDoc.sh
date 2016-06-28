#Check dependencies
if ! type "gradle" > /dev/null; then
	read -p "It appears that gradle is not installed on your system!" -n1 -s
	exit 0
fi
#Generates an up-to-date javadoc
cd ../tddt
gradle javadoc
#Copy to this folder
cp build/docs/javadoc ../docs/javadoc -avr
