#!/usr/bin/env python3
from http.server import BaseHTTPRequestHandler, HTTPServer
from urllib.parse import urlparse, parse_qs

class VulnHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        query = parse_qs(urlparse(self.path).query)
        # Vulnerability: Reads ANY file requested by the user
        file_to_read = query.get('file', ['/etc/hostname'])[0]
        
        self.send_response(200)
        self.send_header("Content-type", "text/plain")
        self.end_headers()
        
        try:
            with open(file_to_read, 'r') as f:
                self.wfile.write(f.read().encode())
        except Exception as e:
            self.wfile.write(str(e).encode())

if __name__ == "__main__":
    server = HTTPServer(('0.0.0.0', 8080), VulnHandler)
    server.serve_forever()
