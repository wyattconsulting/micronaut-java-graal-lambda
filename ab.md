sysadmin@DESKTOP-7J2H2L7:~/projects/micronaut-graal/demo$ ab -c 100 -n 100000 https://66pfk4cjvga4qklk2j5krmjvbq0gxbcn.lambda-url.us-east-2.on.aws/
This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking 66pfk4cjvga4qklk2j5krmjvbq0gxbcn.lambda-url.us-east-2.on.aws (be patient)
Completed 10000 requests
Completed 20000 requests
Completed 30000 requests
Completed 40000 requests
Completed 50000 requests
Completed 60000 requests
Completed 70000 requests
Completed 80000 requests
Completed 90000 requests
Completed 100000 requests
Finished 100000 requests


Server Software:        
Server Hostname:        66pfk4cjvga4qklk2j5krmjvbq0gxbcn.lambda-url.us-east-2.on.aws
Server Port:            443
SSL/TLS Protocol:       TLSv1.2,ECDHE-RSA-AES128-GCM-SHA256,2048,128
Server Temp Key:        ECDH P-256 256 bits
TLS Server Name:        66pfk4cjvga4qklk2j5krmjvbq0gxbcn.lambda-url.us-east-2.on.aws

Document Path:          /
Document Length:        27 bytes

Concurrency Level:      100
Time taken for tests:   332.162 seconds
Complete requests:      100000
Failed requests:        20
   (Connect: 0, Receive: 0, Length: 20, Exceptions: 0)
Non-2xx responses:      20
Total transferred:      29800060 bytes
HTML transferred:       2699880 bytes
Requests per second:    301.06 [#/sec] (mean)
Time per request:       332.162 [ms] (mean)
Time per request:       3.322 [ms] (mean, across all concurrent requests)
Transfer rate:          87.61 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:      180  236  71.3    228    3398
Processing:    62   94  36.2     87    1346
Waiting:       62   90  34.2     85    1346
Total:        255  331  81.0    319    3469

Percentage of the requests served within a certain time (ms)
  50%    319
  66%    330
  75%    339
  80%    345
  90%    367
  95%    390
  98%    426
  99%    483
 100%   3469 (longest request)