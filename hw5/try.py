email_address = "lihzhao@seas.upenn.edu"

if "@" in email_address:
    username, domain = email_address.split("@")

print(type(username))
