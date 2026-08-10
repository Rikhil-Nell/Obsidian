---
tags:
  - Pydantic
---
## What is Pydantic?

`pydantic` is a Python library that handles data validation and settings management using type-annotated class-fields. In this post, we will cover the basics of `pydantic`, and see how to use it to model and validate JSON data coming from an external source. We'll see how to create constrained fields, write custom field validations, and how to export models to JSON/dictionaries.

Often, when dealing with data from files, external APIs or from users, we need to validate this data and often convert data from one type to another - for example, converting a string representation of a number to an integer or a float. We may need to account for optional fields, fields with dynamic default values, and fields with very specific validation rules.

## Models

One of the primary ways of defining schema in Pydantic is via models. Models are simply classes which inherit from [`BaseModel`](https://docs.pydantic.dev/latest/api/base_model/#pydantic.BaseModel) and define fields as annotated attributes.

You can think of models as similar to structs in languages like C, or as the requirements of a single endpoint in an API.

Models share many similarities with Python's `dataclasses`, but have been designed with some subtle-yet-important differences that streamline certain workflows related to validation, serialization, and JSON schema generation. You can find more discussion of this in the `Dataclasses` section of the docs.

Untrusted data can be passed to a model and, after parsing and validation, Pydantic guarantees that the fields of the resultant model instance will conform to the field types defined on the model.

https://www.bugbytes.io/posts/introduction-to-pydantic/

https://bugbytes.io/posts/pydantic-nested-models-and-json-schemas/