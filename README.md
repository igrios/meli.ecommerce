ca va toda la descripcion del proyecto 🛒 Meli AI Ecommerce — Backend Automatizado

Sistema backend para automatizar ventas en Mercado Libre usando IA, reglas de negocio y automatización, minimizando la intervención humana al mínimo posible.

El objetivo final es que el humano solo tenga que:

Imprimir la etiqueta

Despachar el producto

Todo lo demás lo gestiona el sistema.

🎯 Objetivo del proyecto

Construir un sistema de ecommerce automatizado para Mercado Libre que:

Mantenga stock sincronizado

Responda mensajes automáticamente

Derive consultas complejas a WhatsApp

Ajuste precios según competencia (repricing)

Minimice la intervención humana

Todo corre inicialmente en local usando:

Spring Boot

MySQL

API Sandbox de Mercado Libre

🧱 Arquitectura Stack

Java 21

Spring Boot 4

Spring Data JPA

MySQL 8

Lombok

REST API

🗂️ Dominio Product

Representa un producto publicado en Mercado Libre.

Campos principales:

id

meliItemId

title

price

cost

minMargin

stock

createdAt

updatedAt

Order

Representa una orden de compra (real o sandbox).

Preparado para:

Descontar stock

Enlazar mensajes

Integrarse con ML Sandbox

Message

Tabla messages

Campos:

id

meliMessageId

buyerId

messageText

aiResponse

status

product_id

order_id

createdAt

MessageStatus RECEIVED AUTO_REPLIED DERIVED_WHATSAPP PENDING_REVIEW

📡 API de Mensajes Crear mensaje POST /api/messages

Ejemplo:

curl -X POST http://localhost:8080/api/messages
-H "Content-Type: application/json"
-d '{ "buyerId": "buyer003", "messageText": "Hola, cuanto cuesta?", "productId": 1 }'

🧠 Motor de decisión IA

Actualmente se usa una IA por reglas (luego será GPT o similar):

Auto reply si el mensaje contiene:

precio

valor

costo

stock

envio / envío

Caso contrario:

➡ Se deriva automáticamente a WhatsApp

📲 WhatsApp Fallback

Cuando la IA no puede responder:

El mensaje pasa a estado: DERIVED_WHATSAPP

Se notifica a un humano por WhatsApp (actualmente stub)

Ejemplos de mensajes derivados:

Facturación especial

Horarios

Entregas urgentes

Reclamos

🧪 Ejemplo real funcionando

Mensaje:

Hola, cuanto cuesta?

Resultado en DB:

status = AUTO_REPLIED ai_response = "Hola 👋 El precio del producto es $95000.00..."

Mensaje:

Necesito factura A y entrega mañana antes de las 9

Resultado:

status = DERIVED_WHATSAPP ai_response = NULL

🧩 Flujo completo actual

Buyer envía mensaje

Se guarda en messages

Se ejecuta MessageDecisionService

Si es simple → respuesta automática

Si es complejo → WhatsApp

Todo automático.

🔜 Próximos módulos

Órdenes reales desde Mercado Libre Sandbox

Descuento automático de stock

Repricing automático según competencia

Integración real con API ML

IA con GPT u otro modelo

🧠 Visión final

El sistema debe:

Vender

Responder

Repriciar

Gestionar stock

Escalar

El humano solo:

Imprime

Despacha

🧪 Estado del proyecto

Backend funcional Base de datos viva Mensajes automatizados IA inicial activa

🚀 Listo para escalar a Mercado Libre Sandbox y producción.
