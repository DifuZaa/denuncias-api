# Denuncias API — Primer avance

CRUD básico de denuncias escolares, a partir del caso del Colegio Max Uhle
(Resolución 2634-2025/SPC-Indecopi). Cada denuncia se guarda con fecha y hora
automáticas, y si el `tipoDenuncia` es `MALTRATO_PSICOLOGICO`,
`MALTRATO_FISICO` o `ACOSO_ESCOLAR`, el campo `protocoloActivado` queda en
`true` automáticamente.

## Cómo ejecutar

```bash
mvn spring-boot:run
```

## Endpoints

| Método | Ruta                   | Descripción            |
|--------|------------------------|-------------------------|
| POST   | `/api/denuncias`       | Crea una denuncia       |
| GET    | `/api/denuncias`       | Lista todas las denuncias |
| GET    | `/api/denuncias/{id}`  | Obtiene una denuncia     |
| PUT    | `/api/denuncias/{id}`  | Actualiza estado/descripción |
| DELETE | `/api/denuncias/{id}`  | Elimina una denuncia     |

### Ejemplo de creación

```json
POST /api/denuncias
{
  "tipoDenuncia": "MALTRATO_PSICOLOGICO",
  "descripcion": "El docente de alemán habría maltratado psicológicamente a la estudiante.",
  "nombreDenunciante": "María Pérez",
  "nombreAfectado": "Estudiante - 3ro de secundaria"
}
```

## Postman

Colección en `postman/denuncias-api.postman_collection.json`.
