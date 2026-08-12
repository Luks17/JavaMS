compile: ./user/src/main/java/com/msjava/user/UserApplication.java ./email/src/main/java/com/msjava/email/EmailApplication.java
	cd user && mvn -B -DskipTests compile
	cd email && mvn -B -DskipTests compile

dev-up:
	docker-compose up -d

dev-down:
	docker-compose down

.PHONY: dev-up dev-down
