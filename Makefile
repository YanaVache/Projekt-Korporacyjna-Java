# Definicja zmiennych
SRCDIR = src
BINDIR = bin
LIBDIR = lib
JFLAGS = -d $(BINDIR) -cp .:$(LIBDIR)/gson-2.10.1.jar
JC = javac
JVM = java
SOURCES = $(shell find $(SRCDIR) -name "*.java")
RESOURCES = $(shell find $(SRCDIR) -name "*.properties")

# Domyślny cel (kompilacja i uruchomienie)
all: compile run

# Kompilacja
compile:
	@mkdir -p $(BINDIR)
	$(JC) $(JFLAGS) $(SOURCES)
	cp $(RESOURCES) $(BINDIR)/

# Uruchomienie
run:
	$(JVM) -cp .:$(BINDIR):$(LIBDIR)/gson-2.10.1.jar Main

# Czyszczenie
clean:
	rm -rf $(BINDIR)
