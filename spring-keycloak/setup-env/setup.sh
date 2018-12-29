#!/usr/bin/env bash

REALM_NAME="demo"
CLIENT_NAME="demo-client"
USER_NAME="demo-user"

echo "Setup for $REALM_NAME realm"

echo "Cleanup"
docker-compose -f keycloak-postgres.yml down

echo "Setup docker"
docker-compose -f keycloak-postgres.yml up --build --detach

echo "Wait for docker, TODO more fancy"
sleep 30

echo "Add $REALM_NAME realm"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    create realms -s realm=$REALM_NAME \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o
echo "Enable $REALM_NAME realm"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    update realms/$REALM_NAME -s \
                    enabled=true \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

echo "Create user-role"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    create roles -r $REALM_NAME -s name=user -s 'description=Regular user with limited set of permissions' \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

echo "Create admin-role"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    create roles -r $REALM_NAME -s name=admin -s 'description=Admin user with unlimited set of permissions' \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

echo "Create $CLIENT_NAME client"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    create clients -r $REALM_NAME -s clientId=$CLIENT_NAME \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

echo "Create $USER_NAME in $REALM_NAME realm"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    create users -s username=$USER_NAME -s enabled=true -r $REALM_NAME \
                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

#echo "Add admin role to demo-user"
#docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
#                    add-roles --username $USER_NAME --rolename admin -r $REALM_NAME \
#                    --server http://localhost:8080/auth --realm master --user admin --password admin -o
#
#echo "Add user role to demo-user"
#docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
#                    add-roles --username $USER_NAME --rolename user -r $REALM_NAME \
#                    --server http://localhost:8080/auth --realm master --user admin --password admin -o

echo "Update demo-user password in $REALM_NAME realm"
docker-compose -f keycloak-postgres.yml exec keycloak keycloak/bin/kcadm.sh \
                    set-password -r $REALM_NAME --username $USER_NAME --new-password NEWPASS \
                    --server http://localhost:8080/auth --realm master --user admin --password admin

echo "Check user on http://localhost:8080/auth/realms/demo/account"