FROM openjdk
COPY ./target/ProductsReview-0.0.1-SNAPSHOT.jar ProductsReview-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "ProductsReview-0.0.1-SNAPSHOT.jar" ]

