# aurora_limiter
small application to wrap calling the aurora cli limiting the rate based on the number of threads available to the application (set with -x)

# build
You can create a fat jar by running
```
sbt assembly
```
# run
The fat jar takes a file name which contains line separated  aurora job command DC/role/env/job filename.aurora and controls the number of jobs created using the java thread pool size parameter

```
scala -Dscala.concurrent.context.numThreads=8 -Dscala.concurrent.context.maxThreads=8  -cp target\scala-2.11\aurora_limiter-assembly-0.1.jar aurora_limiter.cli <file.txt>
