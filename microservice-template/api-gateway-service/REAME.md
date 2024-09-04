# API GATEWAY

## Getting started
Global filter is implemented in api gateway so that each call are 
filtered and send to a stand-alone SECURITY-SERVER application to verify token

### FeignClient
FeignClient is implemented to call the stand-alone SECURITY-SERVER application 

### Routes
Eureka Client/Server relationship is established in this microservice to route 
to other services from api-gateway