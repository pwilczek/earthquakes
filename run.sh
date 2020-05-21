#!/usr/bin/env bash
#
./gradlew clean build test && \
  java -jar build/libs/earthquakes-1.0-SNAPSHOT.jar