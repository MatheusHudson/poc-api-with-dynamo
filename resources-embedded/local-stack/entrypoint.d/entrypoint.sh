#!/bin/bash

echo -e "******************************************"
echo -e "* Iniciando criacao de recursos  *"
echo -e "******************************************"

BASE_PATH=/docker-entrypoint-initaws.d

if [ -e "$BASE_PATH/dynamodb" ]; then
   bash $BASE_PATH/dynamodb/dynamo.sh
else
  echo -e ""
  echo -e "$BASE_PATH/dynamodb/dynamo.sh"
  echo -e "Script de criacao do dynamo não encontrado!"
fi


