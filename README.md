pom.xml not working as far as I tested

Run "gradle archive" to generate JAR w/ dependencies

Then deploy to maven with: "mvn deploy:deploy-file -DpomFile=pom.xml -Dfile=build/libs/notacariocafacil-1.2.jar -DrepositoryId=fisgomaven -Durl=s3://fisgomaven/release" changing to your repository configs