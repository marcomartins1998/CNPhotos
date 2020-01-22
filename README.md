# CNPhotos
## OBJETIVO
Realizar o desenvolvimento de um sistema de armazenamento e analítica de imagens com base na estrutura seguinte:

Figura 1 - Diagrama do Sistema de análise e classificação de imagens

O sistema foi separado em três módulos principais:
* Módulo cliente
* Módulo worker
* Módulo monitor

O módulo cliente engloba todas as componentes que têm interação direta com o utilizador, sendo estas a adição de imagens, a pesquisa de imagens por categorias e a gestão do monitor. O módulo worker é responsável por todas as componentes essenciais ao processamento de imagens, tratando-se da análise de imagens e o agente de monitorização. Por sua vez, o módulo monitor implementa a componente monitor e controlador de VM/workers garantindo o balanço entre o número de workers ativos e as métricas de utilização de cada uma.

## MÓDULOS
### __Módulo cliente:__
O módulo cliente apresenta uma interface de linha de comando onde o utilizador pode escolher entre o envio de uma imagem para análise, pesquisa de imagens existentes por categorias ou realização de pedidos ao monitor para obtenção do seu estado geral, também é possível o envio de comandos para definir o intervalo de adição/diminuição de instâncias de workers. O projeto Java referente a este módulo denomina-se CNPhotos.
 
Figura 2 - Módulo cliente

### __Módulo worker:__ 
O módulo worker encontra-se encarregue de receber notificações de novas imagens através da subscription “cn-d1-g1-images-subscription”, redirecionamento das respetivas imagens do bucket “cn-d1-g1-image-bucket” para a Vision API de forma a descobrir em que categorias se enquadram e proceder à contagem de faces, neste caso a imagem presente no bucket é atualizada com um quadrado verde à volta das caras existentes. As categorias são armazenadas num documento Firestore cujo id é o nome único da imagem. Adicionalmente, também são enviados dados do estado atual do worker para tópicos de métricas, nomeadamente quantas imagens são processadas por cada 10 segundos e a percentagem de utilização do CPU e da memória RAM. O projeto Java referente a este módulo é denominado CNPhotosWorker.
 
Figura 3 - Módulo Worker

### __Módulo monitor:__
O módulo monitor gere o número de instâncias de workers em relação às métricas que recebe, podendo também fornecer informação adicional acerca do estado global do sistema ao gestor do monitor do módulo cliente se for pedido. O projeto Java referente a este módulo é denominado por CNPhotosMonitor.
 
Figura 4 - Módulo Monitor

## COMPONENTES
### __Adição de imagens:__
A componente de adição de imagens recorre aos serviços de Storage e Pub/Sub da Google CloudPlatform.
No Bucket “cn-d1-g1-image-bucket” definido pelo grupo são armazenadas as imagens como Blobs que o utilizador pretende analisar, tendo este a possibilidade de realizar uploads consecutivos de diferentes imagens. Os nomes das imagens são publicados no tópico “cn-d1-g1-images” através do uso de um Publisher, de forma a notificar os workers que há novas imagens para serem processadas no Bucket “cn-d1-g1-image-bucket”.
Comando exemplo: /upload D:\Ano3_Semestre2\CN\Trabalho_Final_CN\Batata-frita.jpg
Um dos problemas que se detetou nesta primeira fase foi a necessidade de implementar uma forma de garantir que o nome da imagem, no projeto utilizado como o seu identificador, fosse único para que ao ser colocada como Blob dentro do Bucket esta última não se sobrepusesse a qualquer outra com o mesmo nome. A solução implementada engloba a verificação e comparação do nome da imagem com o nome de todos os outros Blobs no Bucket.

### __Análise de imagens:__
Esta componente recorre aos serviços de Storage, Pub/Sub e CloudVision da Google Cloud Platform.
Cada worker encontra-se subscrito ao tópico “cn-d1-g1-images” através da pull subscription “cn-d1-g1-images-subscription” onde são colocadas as informações referentes à publicação de Imagens num determinado Bucket.
Enquanto existirem imagens por analisar, os workers continuarão a consumir as mensagens existentes na queue gerada pela subscription que contêm o nome da imagem adicionada. Desta forma o worker acede à imagem armazenada no Bucket sobre a forma de um Blob e procede ao seu envio para a CloudVision API de forma a ser analisada, por fim as categorias detetadas a que a imagem se enquadra bem como o número de caras presentes na imagem são adicionadas a um documento Firestore com o nome da imagem como id. Será enviado um Acknowledge para a subscription com o intuito de esta retirar da queue a necessidade de processamento da imagem em causa. No caso em que a imagem tenha faces há um paço extra, este sendo a atualização da imagem no bucket com as caras detetadas tendo agora um quadrado verde à volta.
Caso o Acknowledge não seja recebido pela subscription em 10 segundos (Tempo configurável no tópico “cn-d1-g1-images”), a mensagem será reposta na queue. Desta forma serão evitadas perdas de mensagens, sendo esta uma maneira de recuperar de eventuais falhas de algum dos workers antes de estes terminarem o processamento da Imagem.

### __Pesquisa de imagens por categorias:__
A componente de pesquisa de imagens por categorias recorre ao serviço Firestore da Google CloudPlatform.
Para esta pesquisa é possível concatenar características separadas por “:” tornando a pesquisa mais especifica dentro do leque de imagens que se encontram no Bucket.
As coleções do modelo de dados Firestore permitem o armazenamento de documentos, podendo estes ter vários campos de tipos variados. A coleção “cn-d1-g1-images” armazena documentos, cujo ID é o nome da imagem que representa, com um campo “labels” que é um array com todas as diferentes etiquetas da imagem, permitindo desta forma uma pesquisa eficiente por images que se enquadram em certas categorias.
Comando exemplo: /search Vertebrate:Mammal:Newfoundland

### __Agente de monitorização:__
A componente de agente de monitorização recorre ao serviço Pub/Sub da Google CloudPlatform.
Por cada processamento de imagemconcluído com sucesso, e no fim do envio de Acknowledgepor parte do worker à suscription o número de imagens processadas é incrementado. Este dado será relevante para o cálculo da métrica: Imagens processadas a cada 10 segundos, sendo esta a informação publicada no tópico “cn-d1-g1-appmetric” através da subscription “cn-d1-g1-appmetric-subscription”.
Para a publicação contínua desta métrica é utilizado o método de instância scheduleAtFixedRate da classe ScheduledExecutorService, que tal como o nome indica executa um pequeno código que realiza o seu cálculo e publicação periodicamente.
Esta é a descrição do que ocorre em cada worker dentro do campo de monitorização, ou seja, estas informações são todas publicadas num tópico específico no qual o Monitor estará subscrito para obter todos estes dados e tomar as decisões corretas quanto ao aumento ou diminuição de instâncias de workers.

### __Monitor e controlador de VM/workers:__ 
Esta componente de recorre ao serviço Pub/Sub da Google CloudPlatform e ao middleware gRPC.
O monitor encontra-se subscrito aos tópicos “cn-d1-g1-appmetric” e “cn-d1-g1-cpumem” através das respetivas subscriptions “cn-d1-g1-appmetric-subscription” e “cn-d1-g1-cpumem-subscription”, desta forma podendo aceder às 3 métricas selecionadas para a correta monitorização dos workers. 
Escolheu-se a métrica de APP (Imagens por 10 segundos) para a decisão de instanciação e paragem de workers realizando este a média de utilização a partir das diferentes mensagens recebidas da subscription “cn-d1-g1-appmetric-subscription”.
O Monitor pode também disponibilizar informações do estado global através do middleware gRPC. O contrato de serviço, definido utilizando a linguagem protocol buffers, é implementado nesta componente, disponibilizando a possibilidade de modificar o intervalo para aumentar/diminuir instâncias de workers bem como aceder a informações extra como imagens processadas por cada 10 segundos e a percentagem de utilização global de CPU e RAM.

### __Gestão do Monitor__:  
Esta componente disponibiliza formas do utilizador, através de comandos de input, interagir com o estado global do sistema através do middleware gRPC.
