#!/bin/bash

function readJson {
  UNAMESTR=`uname`
  if [[ "$UNAMESTR" == 'Linux' ]]; then
    SED_EXTENDED='-r'
  elif [[ "$UNAMESTR" == 'Darwin' ]]; then
    SED_EXTENDED='-E'
  fi;

  VALUE=`grep -m 1 "${2}" ${1} | sed ${SED_EXTENDED} 's/^ *//;s/.*= *//;s/,?//'`

  if [ ! "$VALUE" ]; then
    echo "Error: Cannot find \"${2}\" in ${1}" >&2;
    exit 1;
  else
    echo $VALUE ;
  fi;
}

VERSION=`readJson app.properties version` || exit 1;
key=`readJson app.properties sonar-key` || exit 1;

branch=`git branch | grep \* | cut -d ' ' -f2`
#sonar-scanner -X -Dsonar.projectVersion=$VERSION

./gradlew sonarqube \
	-Dsonar.projectKey=Blue-Bash_UrlShortener \
	-Dsonar.organization=blue-bash \
	-Dsonar.host.url=https://sonarcloud.io \
	-Dsonar.login=$key \
	-Dsonar.branch.name=$branch \
	-Dsonar.projectVersion=$VERSION
