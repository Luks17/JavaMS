
dev-up:
	docker-compose up -d

dev-down:
	docker-compose down

.PHONY: dev-up dev-down
