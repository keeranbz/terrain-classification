

compile: 
	javac src/*.java
	mv src/*.class bin/
	(cd docs/;javadoc ../src/*)

default: compile

clean: 
	rm bin/*
	rm docs/*

	
