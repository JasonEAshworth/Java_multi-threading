#!/usr/bin/env python3

import sys
import os,stat 

v=sys.hexversion >> 24
if v == 2:
    import BaseHTTPServer
    HTTPServer = BaseHTTPServer.HTTPServer
    import SimpleHTTPServer
    Handler = SimpleHTTPServer.SimpleHTTPRequestHandler
else:
    import http.server 
    HTTPServer = http.server.HTTPServer 
    Handler = http.server.SimpleHTTPRequestHandler
    
class R(Handler):
    def guess_type(self,p):
        if p.endswith(".html"):
            return "text/html; charset=UTF-8"
        else:
            return Handler.guess_type(self,p)
            
    def do_GET(self):
        Handler.do_GET(self)
            
            
port=8081
if len(sys.argv) > 1:
    port=int(sys.argv[1])
server = HTTPServer( ('127.0.0.1',port), R)
print ("Listening on port *** "+str(port)+" ***")

while 1:
    server.handle_request()



