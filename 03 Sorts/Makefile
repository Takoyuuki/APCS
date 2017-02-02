.DEFAULT_GOAL := default
JFLAGS = -g
JC = javac
RUN = java

%:
	$(JC) $(JFLAGS) $@.java
	$(RUN) $@ $(JAVAARGS)

clean:
	$(RM) *.class

default:
	@echo "Please enter a filename or 'clean'"
