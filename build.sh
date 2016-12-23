#!/bin/sh
cd $TRAVIS_BUILD_DIR/code/Your_Story/ysProject
sbt ++$TRAVIS_SCALA_VERSION package
