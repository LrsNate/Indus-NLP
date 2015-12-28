[![Build Status](https://travis-ci.org/LrsNate/Indus-NLP.svg?branch=master)](https://travis-ci.org/LrsNate/Indus-NLP)

# Email anonymizer

This project requires Maven and JDK 1.8.

## Build

`mvn package`

## Run

The classifier to be used by the Stanford NER is set through the `-c` option.
The program can be run with either standard input or a list of file paths.

```
cd target
java -jar anonymizer.jar -c classes/english.all.3class.distsim.crf.ser.gz <files...> 
```

# Javadoc

`open javadoc/index.html`