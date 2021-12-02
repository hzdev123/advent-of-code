clean:
	rm -rf bin/main/*.class
	rm -rf bin/test/*.class
.PHONY: clean

compile: clean
	javac -d bin/ src/main/*.java
.PHONY: compile

run: compile
	java -cp bin main.Main
.PHONY: run

