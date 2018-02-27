BIN=main.native

all: $(BIN)

$(BIN):
	ocamlbuild -I src -use-ocamlfind $(BIN)

clean:
	rm $(BIN)

.PHONY: clean
