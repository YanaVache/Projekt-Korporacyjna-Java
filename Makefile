# Definicja zmiennych
SRCDIR = src
BINDIR = bin
JFLAGS = -d $(BINDIR)
JC = javac
JVM = java
SOURCES = $(shell find $(SRCDIR) -name "*.java")

# Domyślny cel (kompilacja i uruchomienie)
all: compile run

# Kompilacja
compile:
	@mkdir -p $(BINDIR)
	$(JC) $(JFLAGS) $(SOURCES)

# Uruchomienie
run:
	$(JVM) -cp $(BINDIR) Main

# Czyszczenie
clean:
	rm -rf $(BINDIR)