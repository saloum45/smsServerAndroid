# Transformer un téléphone android en server d'envoie SMS
## NB : utilisez AndroidStudioCode pour le builder sur le téléphone 
## NB : le téléphone doit avoir une puce et du forfait sms
### Une fois installer sur le téléphone il faut envoyer une reqêtte post avec comme params number (qui est le numéro du téléphone destanitaire de sms) et message (qui est le message à envoyer), vers l'ip de votre téléphone sur le réseau local avec le port 8080 et il faut que l'émétteur de la requête soit sur le même réseau que le téléphone.

### exemple : mon ordinateur est connécté au même wifi que le téléphone server (qui a l'adresse 192.168.1.13) : 
## pour envoyer un sms je vais prendre l'ordinateur et me rendre sur postman(ou n'importe quel aussi de test api) et lancer une requête du genre : http://192.168.1.13:8080/?number=776548967&message=test

#### http://192.168.1.13 : représente le téléphone sur le réseau et 8080 représente le port qui exécute la logique de l'envoie de sms

#### Vous l'aurez compris number contient le numéro destinataire, et message le contenu de sms à envoyer

## Tout ceci est en local c'est à dire les appareils doivent être sur le même réseau

## Pour rendre le téléphone server public et disponible via un url vous pouvez Ngrok ou tout autre générateur de tunnel.

