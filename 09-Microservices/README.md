# Refer
**Microservices(*New)** <br>
https://www.interviewbit.com/microservices-interview-questions/

**12 factors of microservices:**<br>
https://medium.com/techmonks/12-factor-app-principles-and-cloud-native-microservices-a383f6abc97f

**Docker**<br>
https://www.interviewbit.com/docker-interview-questions/

**Kafka**<br>

**AWS(*New)**<br>
https://www.whizlabs.com/blog/aws-developer-interview-questions/

**Kubernetes**<br>
https://www.interviewbit.com/kubernetes-interview-questions/

https://www.simplilearn.com/tutorials/kubernetes-tutorial/kubernetes-interview-questions

# Docker
Docker:
- The main aim of docker containers is to get rid of the infrastructure dependency while deploying and running applications. This means that any containerized application can run on any platform irrespective of the infrastructure being used beneath
- share the kernel and system resources with other containers and run as isolated systems in the host operating system

docker images
- executable packages: application code & dependencies, software packages...
- for the propose of creating containers
- Docker images can be deployed to any docker environment and the containers can be spun up there to run the application

DockerFile
- a text file that has all commands which need to be run for building a given image.
- DockerFile -> docker image -> docker container
![DockerFile](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/099/original/Docker_File.png?1615190478)


functionality of a hypervisor
- A hypervisor is a software that makes **virtualization** happen because of which is sometimes **referred to as the Virtual Machine Monitor**

Docker Compose
- YAML file: with various services, networks, and volumes that are needed for setting up the Docker-based application
- docker-compose is used for creating multiple containers, host them and establish communication between them
- to communicate amongst the containers, ports are exposed by each and every container

docker namespace
- a Linux feature that ensures OS resources partition in a mutually exclusive manner.
- forms the core concept behind containerization
- introduce a layer of isolation amongst the containers
- the containers are portable and they don't affect the underlying host
- Docker – PID, Mount, User, Network, IPC

status of all docker containers:
`docker ps -a`

docker image registry
- is an area where the docker images are stored (Docker hub)

Docker components:
- Docker Client: performs “build” and “run” operations for the purpose of opening communication with the docker host
- Docker Host: This component has the main docker daemon and hosts containers and their associated images. The daemon establishes a connection with the docker registry.
- Docker Registry: This component stores the docker images. There can be a public registry or a private one. exp.: Docker Hub, Docker Cloud
![](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/101/original/docker_components.png?1615192180)

Docker Hub
- It is a public cloud-based registry provided by Docker for storing public images of the containers along with the provision of finding and sharing them.

export a docker image as an archive
`docker save -o <exported_name>.tar <container-name>`

import a pre-exported Docker image into another Docker host?
`docker load -i <export_image_name>.tar`

Can a paused container be removed from Docker?
**No, it is not possible!** A container **MUST be in the stopped state before** we can remove it.

check for the version of docker client and server
`docker version`


virtualization V.s containerization
- Run
  - Virtualization: to run **multiple OS instances** on a **single server**
  - Containerization: runs a **single OS instance**, with **multiple user** spaces to isolate processes from one another
  
- Isolation
  - Virtualization: a **fully isolated** OS and VM instance
  - Containerization: isolates **the host OS machine** and **containers** from one another

- OS
  - Virtualization: >1 OS
  - Containerization: reliant on the host OS (Linux containers cannot be run on Windows)

- Virtual Load Balancing
  - Virtualization: failover clusters are used to run VMs with load balancing support
  - Containerization: uses orchestration via Docker or Kubernetes to start and stop containers. it maximizes resource utilization


https://www.trianz.com/insights/containerization-vs-virtualization#:~:text=Virtualization%20aims%20to%20run%20multiple,to%20run%20multiple%20processes%20simultaneously.

Differentiate between COPY and ADD commands in DockerFile
- COPY: copies files from a local source location to a destination in the Docker container
- ADD: ADD command is used to copy files/directories into a Docker image. It can also copy files from a URL

Can a container restart by itself?
- Yes. Docker provides restart policies to control containers start automatically after exit, or after docker restarts
```
docker run -dit — restart [restart-policy-value] [container_name]

docker run -d --restart unless-stopped redis
```

differences between a docker Image and Layer
- images: Images are built as a series of layers. Docker image is like a Java class. An image corresponds to the docker container. 
  - Docker image will have an Operating System layer, a JVM, and our Hello World application
- layer: Basically, a layer, or image layer is a change on an image, or an intermediate image. 
  - Every command you specify (FROM, RUN, COPY, etc.) in your Dockerfile causes the previous image to change, thus creating a new layer.
  - Each layer corresponds to an instruction of the image’s Dockerfile [refer](https://stackoverflow.com/a/33836848)
- container: A container is an instance of an image

https://www.baeldung.com/ops/docker-images-vs-containers#:~:text=An%20image%20is%20a%20file,on%20top%20of%20one%20another.

What is the purpose of the volume parameter in a docker run command?
- Using the parameter -v allows you to bind a local directory
- for syncing a directory of a container with any of the host directories. `docker run -v /data/app:usr/src/app myapp` 
  - We can sync the container with the data files from the host without having the need to restart
- ensures data security in cases of container deletion


Where are docker volumes stored in docker?
- `/var/lib/docker/volumes/`

`docker info [OPTIONS]`
- displays system wide information regarding the Docker installation

`docker-compose start`
- Starts existing containers for a service.

`docker-compose up`
- Builds, (re)creates, starts, and attaches to containers for a service.

`docker run [OPTIONS] IMAGE [COMMAND] [ARG...]`
-Create and run a new container from an image

basic requirements for the docker to run on any system
- Docker can run on both Windows and Linux platforms
- Windows: Windows 10 64bit with 2GB RAM
- Linux: Ubuntu >=12.04, Fedora >=19, RHEL >=6.5, CentOS >=6 etc

DockerFile sample
- FROM: to set the base image for subsequent instructions. In every valid Dockerfile, FROM is the first instruction.
- LABEL: to organize images as per project, module, licensing etc. We can also use LABEL to help in automation.
  - In LABEL we specify a **key value pair** that can be later used **for programmatically handling the** Dockerfile.
- RUN: **RUN command to execute** any instructions in **a new layer on top of the current image**. With each RUN command we add something on top of the image and use it in subsequent steps in Dockerfile.
- CMD: to provide **default values of an executing container**. In a Dockerfile, if we include multiple CMD commands, then **only the last instruction is used**.
```
# syntax=docker/dockerfile:1
   
FROM node:18-alpine
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/index.js"]
EXPOSE 3000
```

differentiate between Daemon Logging and Container Logging?
- Daemon Level: Debug, Info, Error, and Fatal
- Container Level: 
  - `sudo docker run –it <container_name> /bin/bash`
  - `sudo docker logs <container_id>`

establish communication between docker host and Linux host?
- using networking by identifying the “ipconfig” on the docker host

best way of deleting a container?
- `docker stop <container_id>`
- `docker rm <container_id>`

difference between CMD and ENTRYPOINT
- **CMD** command provides **executable defaults** for an executing container
- **ENTRYPOINT** specifies that the instruction within it will always be run when the container starts
  - `/bin/sh`
  -` /bin/bash`

Can we use JSON instead of YAML while developing docker-compose file in Docker?
- Yes. using `docker-compose -f docker-compose.json up` to run docker-compose with JSON

How many containers you can run in docker and what are the factors influencing this limit?

Describe the lifecycle of Docker Container?<br> 
![lifecycle of Docker Container](https://k21academy.com/wp-content/uploads/2020/10/Capture-5.png)
https://k21academy.com/docker-kubernetes/docker-container-lifecycle-management/

use docker for multiple application environments
- using `docker-compose up`:
  - In the docker-compose file, we can define multiple services, networks, and containers along with the volume mapping in a clean manner, and then we can just call the command “docker-compose up”.
- define the server-specific dependencies and processes for running the application
  - creating environment-specific docker-compose files
  - witht the name: `docker-compose.{environment}.yml` file and run the application


How will you ensure that a container 1 runs before container 2 while using docker compose?
Docker-compose does not wait for any container to be “ready” before going ahead with the next containers
to achieve the order of execution, we can use `depends_on`
```
version: "2.4"
services:
 backend:
   build: .
   depends_on:
     - db           // the `db` container is started before the backend
 db:
   image: postgres
```
- running `docker-compose up backend` creates and starts DB (dependency of backend)
- `docker-compose stop` the backend service is stopped before the DB service.

# 12-factor principles for enterprise applications
- Codebase 
  - Microservices: In Microservices, every service should have its own codebase
- Dependencies 
  - Microservices: All the application packages will be managed through package managers like sbt, maven.
- Config 
  - Database connections and credentials, system integration endpoints
  - Credentials to external services such as Amazon S3 or Twitter or any other external apps
  - Application-specific information like IP Addresses, ports, and hostnames, etc.
- Backing Services 
  - Database, Message Brokers, any other external systems that the app communicates is treated as Backing service.
- Build, release, and Run
  - Microservices:
    - use CI/CD tools to automate the builds and deployment process
    - Docker images make it easy to separate the build, release, and run stages
- Processes 
- Port Binding
- Concurrency : scaling the application
  - consider running your application as multiple processes/instances instead of running in one large system
  - vertical scaling- Add additional hardware to the system
  - Horizontal scaling — Add additional instances of the application
  - Microservices: By adopting the containerization, applications can be scaled horizontally as per the demands.
- Disposability: maximize the robustness with fast startup and graceful shutdown
   - By adopting the containerization into the deployment process of microservices. Docker containers can be started or stopped instantly.
- Dev/prod parity
- Logs (Treat logs as event streams) 
- Admin processes


