# api-listar-scoringcliente
## Consulta básica de variables de scoring de informes Nosis de Solnet
Permite ver las variables de score de un cliente. Además, se compone de elementos varios de resiliencia y aplica validaciones.

## Endpoint
GET http://localhost:8080/api/v1/utils/informe/nosis-en-solnet/score-nosis?{cuit}&{canal}&{modelo}&{campana}

Hace request de un body que se compone de 4 parametros; cuit, canal, modelo y campaña

## Respuestas
Ingresar a Swagger desde su navegador:
http://localhost:8080/swagger-ui/index.html

### Response 200

Retorna los campos que se utilizan para ver el score del cliente

```json
{
  "Cuota": 128601,
  "CuotaUVA": 0,
  "Limite": 500000,
  "mensaje": "",
  "ingMinimo": 633000,
  "ingMaximo": 633000,
  "CUIT": "20297187717"
}
```

### Response ERROR
```json
 {
  "codigo": "codigo",
  "descripcion": "descripcion"
}
```
