#
# Created by Szabó Gergely (Gerviba)
# https://github.com/Gerviba/webschop
#

mkfile_path := $(abspath $(lastword $(MAKEFILE_LIST)))
mkfile_dir := $(dir $(mkfile_path))

.PHONY: all
all: install

install:
	./mvnw clean install

test:
	./mvnw test
	
deploy:

package:
	./mvnw package spring-boot:repackage

docker-build: package
	cp target/webschop-*.jar docker/webschop-latest.jar
	docker build --file=docker/Dockerfile --tag=webschop:latest --rm=true docker/

docker-run:
	@echo $(mkfile_dir)
	docker run --name=webschop --publish=8080:8080 \
		--volume=$(mkfile_dir)application-docker.properties:/opt/webschop/application.properties \
		--volume=$(mkfile_dir)docker/permanent:/permanent/external/ \
		--volume=$(mkfile_dir)docker/temp/webschop/search:/tmp/webschop/search/ \
		webschop:latest

docker-remove:
	docker rm webschop

run:
	