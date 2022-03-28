FROM python:3.6-stretch

RUN apt-get update && \
    apt-get install -y gcc make apt-transport-https ca-certificates build-essential

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY requirements.txt /usr/src/app/
RUN pip3 install --no-cache-dir -r requirements.txt

COPY ./swagger_server /usr/src/app/swagger_server
COPY proto/ /usr/src/app/proto/swagger_server/proto/

RUN python3 -m grpc_tools.protoc -I proto --python_out=. \
           --grpc_python_out=. proto/swagger_server/proto/*.proto


EXPOSE 8080/tcp
EXPOSE 50051/tcp

ENTRYPOINT ["python3"]

CMD ["-m", "swagger_server"]
