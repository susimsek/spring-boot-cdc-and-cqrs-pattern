cd denormalizer
./mvnw compile jib:dockerBuild
cd ..

sleep 1

cd reactive-cqrs-api
./mvnw compile jib:dockerBuild
cd ..