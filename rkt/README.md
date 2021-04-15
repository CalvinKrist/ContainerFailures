## Initial Steps

From a macos or windows host machine:
* Follow https://rocket.readthedocs.io/en/latest/Documentation/trying-out-rkt/
  * git clone https://github.com/rkt/rkt
  * cd rkt
  * change scripts/install-rkt.sh by removing the gpg key download and gpg check after the rkt wget's, the site no longer hosts the key
  * vagrant up --provision
  * vagrant ssh (this will put you inside the machine)
  * rkt --insecure-options=image run docker://jboss/wildfly
  

