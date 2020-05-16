# Aplicação CIF - Evaluation API

Microserviço que contempla o gerenciamento dos dados de avaliação.

# Veja Também
Os links abaixo levam as outras API's usadas no projeto. 
 * [Question API](https://github.com/MathLanca/QuestionAPI/blob/master/README.md)
 * [Information Sorce API](https://github.com/MathLanca/informationSourceAPI/blob/master/README.md)
 * [Person API](https://github.com/MathLanca/personAPI/blob/master/README.md)


# Endpoints e Responses
Base Url: https://java-cif-evaluation-api.herokuapp.com

:point_right: [Download postman collection](https://drive.google.com/file/d/1n8u304yYKJAL5DkNAohzgSLJq7qMr8zi/view?usp=sharing)  :point_left:

New Evaluation
-
* Salva uma avaliação na base
```http
 POST /v1/evaluation/new
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `body` | `Evaluation object` | `object` | Evaluation Object  |  **TRUE**    |

Evaluation Object

|   Type   | Name |  Required    |
| :--- | :--- | :--- |
| `LocalDateTime` | `date` | **true** |
| `String` | `location` | **true** |
| `String` | `therapistId` | **true** |
| `String` | `patientId` | **true** |
| `List<Answer>` | `answers` | **true** |

Answer Object

|   Type   | Name |  Required    |
| :--- | :--- | :--- |
| `String` | `questionId` | **true** |
| `String` | `infoSource` | **true** |
| `String` | `problemDescription` | **true** |
| `Integer` | `generalGrade` | **false** |
| `Integer` | `cGrade` | **false** |
| `Integer` | `pGrade` | **false** |
| `Integer` | `locationGrade` | **false** |
| `Integer` | `extensionGrade` | **false** |
| `Integer` | `natureGrade` | **false** |

Retrieve evaluations from therapist
-
* Retorna uma lista de avaliações feitas pelo terapeuta
```http
 GET /v1/evaluation/therapistevaluations/{cpf}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `cpf` | `string` | Therapist CPF  |  **TRUE**    |
