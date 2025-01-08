```toml
name = 'Login'
method = 'POST'
url = '{{url}}/login'
sortWeight = 2000000
id = '852dae09-23c9-4872-8659-c2eb3758c827'

[body]
type = 'JSON'
raw = '''
{
  "email": "test@test.com",
  "password": "test"
}'''
```
