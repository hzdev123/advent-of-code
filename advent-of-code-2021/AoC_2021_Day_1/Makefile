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

test: compile
	rm -rf bin/test/*.class
	javac -cp .:bin:lib/* -d bin/ src/test/*.java
	java -cp .:lib/*:bin/ test.TestAoC2021D1
.PHONY: test

