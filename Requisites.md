# Mercado de apuntes - Requisitos

### Módulos

* Identificación
* Publicación preguntas
* Responder a preguntas
* Votación respuestas
* Exploración
* Requisitos dudosos

---

### Identificación

* Como usuario puedo visualizar preguntas, esté o no identificado.
* Como usuario no identificado no puedo visualizar el número de respuestas a una pregunta.
* Como usuario no identificado puedo crear una cuenta en la plataforma.
* Como usuario no identificado puedo identificarme si he creado una cuenta anteriormente.
* Como usuario no identificado puedo identificarme con mi Facebook.
* Como usuario no identificado puedo identificarme con mi Twitter.

---

### Publicación de preguntas

* Como usuario identificado puedo publicar preguntas si dispongo de puntos suficientes.
* Como usuario identificado puedo marcar preguntas como repetidas proporcionando la pregunta original.
* Como usuario preguntando debo escribir un título a mi pregunta.
* Como usuario preguntando debo redactar un cuerpo a mi pregunta.
* Como usuario preguntando puedo añadir imágenes a mi pregunta.
* Como usuario preguntando puedo añadir links a mi pregunta.
* Como usuario preguntando puedo redactar el cuerpo a la pregunta en formato Markdown.
* Como usuario preguntando puedo añadir fórmulas en formato Mathjax.
* Como usuario preguntando puedo añadir trozos de código con resaltado de color.
* Como usuario preguntando puedo establecer temas para que mi pregunta sea mas fácil de encontrar.
* Como usuario preguntando puedo visualizar cualquier respuesta a mi pregunta.
* Como usuario preguntando puedo solicitar que se me notifique cuando alguien conteste a mi pregunta.

---

### Responder a preguntas

* Como usuario identificado puedo responder a preguntas (una vez por pregunta).
* Como usuario respondiendo debo redactar un cuerpo a la respuesta.
* Como usuario respondiendo puedo añadir links a mi respuesta.
* Como usuario respondiendo puedo redactar el cuerpo a la respuesta en formato Markdown.
* Como usuario respondiendo puedo añadir fórmulas en formato Mathjax.
* Como usuario respondiendo puedo añadir trozos de código con resaltado de color.
* Como usuario visualizando una pregunta puedo pagar puntos para visualizar una respuesta.

---

### Votación respuestas

* Como usuario identificado puedo votar positivamente respuestas que haya desbloqueado anteriormente.
* Como usuario identificado puedo votar negativamente respuestas que haya desbloqueado anteriormente.
* Como usuario preguntando puedo votar positivamente cualquier respuesta a mi pregunta.
* Como usuario preguntando puedo votar negativamente cualquier respuesta a mi pregunta.
* Como usuario respondiendo no puedo votar mi respuesta.
* Como usuario respondiendo se me notifica cuando alguien vota mi respuesta a una pregunta.

---

### Exploración

* Como usuario puedo ver las últimas preguntas realizadas ordenadas de mas reciente a más antigua.
* Como usuario puedo ver los temas más activos ordenados de más activo a menos.
* Como usuario puedo seleccionar un tema para ver las últimas preguntas realizadas de ese tema ordenadas por antigüedad.
* Como usuario puedo buscar un tema por texto y recibir una lista de temas que incluyan ese texto.
* Como usuario puedo buscar una pregunta por su título y recibir una lista de preguntas que incluyan ese título.
* Como usuario identificado puedo consultar mi actividad en la plataforma recibiendo una lista de eventos (respuestas a mis preguntas y votos a mi respuestas)

### Requisitos dudosos

**Este apartado no forma parte de la especificación. Únicamente es para anotar requisitos futuros o que puedan crear conflicto**

* Edición de preguntas
  - Un usuario podria editar su pregunta despues de que haya sido respondida para que nadie mas se pueda beneficiar.
* Edición de respuestas
  - Razones similares al punto anterior