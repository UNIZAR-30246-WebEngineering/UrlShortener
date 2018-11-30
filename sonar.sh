#!/bin/bash

function readJson {
  UNAMESTR=`uname`
  if [[ "$UNAMESTR" == 'Linux' ]]; then
    SED_EXTENDED='-r'
  elif [[ "$UNAMESTR" == 'Darwin' ]]; then
    SED_EXTENDED='-E'
  fi;

  VALUE=`grep -m 1 "${2}" ${1} | sed ${SED_EXTENDED} 's/^ *//;s/.*= *//;s/,?//'`

  if [[ ! "$VALUE" ]]; then
    echo "Error: Cannot find \"${2}\" in ${1}" >&2;
    exit 1;
  else
    echo ${VALUE} ;
  fi;
}

function getEnvOrDefault {
    if [[ -z "$1" ]]; then
        echo $1
    else
        echo $2
    fi
}

VERSION=`readJson app.properties version` || exit 1;
key=`readJson app.properties sonar-key` || exit 1;

branch=`git branch | grep \* | cut -d ' ' -f2`
pullRequest="${TRAVIS_PULL_REQUEST:-false}"

echo $pullRequest

if [[ ! pullRequest || pullRequest="false"  ]]; then
    echo "Perform branch analysis: $branch"
    ./gradlew sonarqube \
        -Dsonar.projectKey=Blue-Bash_UrlShortener \
        -Dsonar.organization=blue-bash \
        -Dsonar.host.url=https://sonarcloud.io \
        -Dsonar.login=${key} \
        -Dsonar.branch.name=${branch} \
        -Dsonar.projectVersion=${VERSION}
else
    echo "Perform #$pullRequest PR analysis from ${TRAVIS_BRANCH} to ${TRAVIS_PULL_REQUEST_BRANCH}"
    ./gradlew sonarqube \
        -Dsonar.projectKey=Blue-Bash_UrlShortener \
        -Dsonar.organization=blue-bash \
        -Dsonar.host.url=https://sonarcloud.io \
        -Dsonar.login=${key} \
        -Dsonar.pullrequest.branch=${TRAVIS_PULL_REQUEST_BRANCH} \
        -Dsonar.pullrequest.key=${pullRequest} \
        -Dsonar.pullrequest.base=${TRAVIS_BRANCH}
fi