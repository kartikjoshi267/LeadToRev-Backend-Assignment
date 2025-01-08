```toml
name = 'Create a user'
method = 'POST'
url = '{{url}}/signup'
sortWeight = 1000000
id = '8dbf0b19-ba4d-49ad-8085-d85549a4c7c5'

[body]
type = 'JSON'
raw = '''
{
  "email": "test@test.com",
  "password": "test"
}'''
```
