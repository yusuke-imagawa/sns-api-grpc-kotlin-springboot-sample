#!/bin/sh

cd /Users/imagawa/git/talking-sns-api/ElasticBeanstalk
rm talking-sns-api.zip
rm talking-sns-api.jar
cp ../build/libs/talking-sns-api.jar ./talking-sns-api.jar
zip -r talking-sns-api .ebextensions talking-sns-api.jar Procfile
