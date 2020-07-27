デプロイ手順
================

## ビルド
- コマンドラインでプロジェクト直下に移動(開発PCでは/Users/imagawa/git/talking-sns-api)
- ”gradle build”を実行
    - ビルドに成功すると”build/libs/talking-sns-api.jar”が作成させる。

## AWS Elastic Beanstalk向けにjarとProcfileと拡張設定ファイル(.ebextentions)をzipで固める
- コマンドラインでElasticBeanstalkの作業フォルダに移動
    - cd /Users/imagawa/git/talking-sns-api/ElasticBeanstalk
- rm talking-sns-api.zip
- rm talking-sns-api.jar
- cp ../build/libs/talking-sns-api.jar ./talking-sns-api.jar
- zip -r talking-sns-api .ebextensions talking-sns-api.jar Procfile

    - Procfileには実行コマンドを記載している。ここに"-Dspring.profiles.active=production"と書くことで本番環境で実行することを指定している。
        -  web: java -jar -Dspring.profiles.active=production talking-sns-api.jar
        
### zipファイルをshell scriptで実行
- cd /Users/imagawa/git/talking-sns-api/docs
- sudo bash ./make_elastic_beanstalk_zip.sh

## AWS Elastic Beanstalkの管理画面からzipファイル(api.zip)をデプロイする
### 管理画面のurl
https://ap-northeast-1.console.aws.amazon.com/elasticbeanstalk/home?region=ap-northeast-1#/applications

- talking-apiを選択する。
- ダッシュボード > ”アップロードとデプロイ”ボタンを押下する。
- デプロイするtalking-sns-api.zipを設定
- バージョンラベルにデプロイしたバージョンが識別できる値を設定
