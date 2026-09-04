import json
import os
import urllib.request
import urllib.error

payload = {
    "username": "Amethyst's Stupid Stuff",
    "embeds": [
        {
            "title": os.environ["RELEASE_NAME"],
            "url": os.environ["RELEASE_URL"],
            "description": os.environ["RELEASE_BODY"],
            "color": 0x9B59B6,
            "footer": {
                "text": f"Amethyst's Stupid Stuff • {os.environ['RELEASE_TAG']}"
            }
        }
    ]
}

req = urllib.request.Request(
    os.environ["WEBHOOK"],
    data=json.dumps(payload).encode(),
    headers={
        "Content-Type": "application/json",
        "User-Agent": "Amethysts-Stupid-Stuff-Discord-Webhook/1.0"
    },
    method="POST"
)

try:
    response = urllib.request.urlopen(req)
    print(response.status)
    print(response.read().decode())
except urllib.error.HTTPError as e:
    print("Discord returned:", e.code)
    print(e.read().decode())
    raise
